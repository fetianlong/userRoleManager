package com.yuedi.web.product;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.yuedi.entity.Menu;
import com.yuedi.entity.MyPage;
import com.yuedi.entity.Product;
import com.yuedi.service.AreasService;
import com.yuedi.service.ProductService;
import com.yuedi.web.activityLine.ActivityLineController;
import com.yuedi.web.common.SupperController;

@Controller
@RequestMapping(value = "/product")
public class ProductController extends SupperController{
	@Autowired
	private ProductService productService;
	@Autowired
	private AreasService areasService;
	
	private static String relativePathSeller = "D:/yuedi-resource/resources/picture/product";
	
	private static final Logger logger = Logger.getLogger(ActivityLineController.class);
	
	@RequestMapping(value="list",method = RequestMethod.GET)
	public String list(Model model, 
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			HttpServletRequest request){
		try {
			Long parentId = Long.parseLong(request.getParameter("parentId"));
			model.addAttribute("parentId", parentId);
			model.addAttribute("date", this.getCurrentDate());
			//获取当前用户所拥有的菜单
			/*List<Menu> menuList = this.getFirstMenuByUserId();
			model.addAttribute("menuList", menuList);*/
			//获取二级菜单
			List<Menu> towMenuList = this.getTwoMenuByParentId(parentId);
			model.addAttribute("towMenuList", towMenuList);
			
			MyPage<Product> page = new MyPage<Product>();
			page.setNumber(pageNumber);
			
			String sellername = request.getParameter("sellername");
			if(sellername != null && !"".equals(sellername)) {
				page.getParams().put("actionTitle", sellername+ "%");
				model.addAttribute("sellername", sellername);
			}
			String customize = request.getParameter("customize");
			if(null != customize){
				if(customize.equals("1")){	//如果是定制商品，就根据加盟商ID获取商品信息
					page.getParams().put("sellersign", this.getCurrentUserFranchiseesIdId());
					model.addAttribute("sellersign", this.getCurrentUserFranchiseesIdId());
				}else{	//如果是标准商品，就获取总部商品信息
					page.getParams().put("sellersign", 3);
					model.addAttribute("sellersign", 3);
				}
			}
			
			List<Product> productlist = productService.selectProductLimit(page);
			model.addAttribute("productlist", productlist);
			model.addAttribute("pageData", page);
		} catch (Exception e) {
			logger.error("ProductController err list", e);
		}
		return "product/productList";
	}
	
	@RequestMapping(value="add",method = RequestMethod.GET)
	public String add(Model model,HttpServletRequest request){
		//获取当前用户所拥有的菜单
		/*List<Menu> menuList = this.getFirstMenuByUserId();
		model.addAttribute("menuList", menuList);*/
		String parentId = request.getParameter("parentId");
		model.addAttribute("parentId", parentId);
		
		return "product/productForm";
	}
	
	@RequestMapping(value="addProduct",method=RequestMethod.POST)
	public String addProduct(@RequestParam(value = "file", required = false) MultipartFile file,Model model, 
			@ModelAttribute("form") Product product) {
		try{
			if(product.getProductId() == null) {
				if(!StringUtils.isEmpty(product.getProductName())) {
					product.setSellerId(this.getCurrentUserFranchiseesIdId());
					product.setSellersign(1);
					product.setCreateTime(new Date());
					product.setCreateUser(this.getCurrentUserName());
					product.setIsdeleteFlag(2);
					
					/*
					 * 添加上传图片的功能
					 */
					/*String path = request.getSession().getServletContext().getRealPath(relativePathSeller).split(":")[0];
					*/
					
			        String fileName = file.getOriginalFilename();
			        String realName = String.format("%1$s%2$s", UUID.randomUUID(), fileName.substring(fileName.lastIndexOf(".")));
			        File targetFile = new File(relativePathSeller, realName);  
			        if(!targetFile.exists()){  
			            targetFile.mkdirs();  
			        }  
			  
			        file.transferTo(targetFile);  
			        product.setImage("product/"+realName);
			        
			        productService.insertProduct(product);
				}
			}else {
				if(!StringUtils.isEmpty(product.getProductName())) {
					product.setSellerId(this.getCurrentUserFranchiseesIdId());
					product.setSellersign(1);
					product.setUpdateTime(new Date());
					product.setUpdateUser(this.getCurrentUserName());
					
					//上传了新的图片
					if (file != null && file.getSize() > 0) {
				        String fileName = file.getOriginalFilename();
				        String realName = String.format("%1$s%2$s", UUID.randomUUID(), fileName.substring(fileName.lastIndexOf(".")));
				        product.setImage("product/"+realName);

				        int result = productService.updateProductbyId(product);
						//编辑成功，删除本地目录下之前上传的图片。
						if(result>0){
							//String path = String.format("%1$s/%2$s", request.getSession().getServletContext().getInitParameter(RESOURCE_ROOT_DIR), relativePathSeller);
							 String oldImgPath=relativePathSeller+product.getImage();
							 
							 File oldImgFile=new File(oldImgPath);
							 if(oldImgFile.exists()){
								 oldImgFile.delete();
							 }
						}
					}else{
						product.setImage(product.getImage());//未上传新的图片
						productService.updateProductbyId(product);
					}
				}
			}
		}catch(Exception e) {
			logger.error("ProductController err addProduct", e);
		}
		
		return "redirect:/product/list?customize=1&parentId=" + product.getParentId();
	}
	
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model,HttpServletRequest request) {
		try{
			String parentId = request.getParameter("parentId");
			model.addAttribute("parentId", parentId);
			//获取当前用户所拥有的菜单
			/*List<Menu> menuList = this.getFirstMenuByUserId();
			model.addAttribute("menuList", menuList);*/
			
			Product product = productService.selectProductById(id);
			model.addAttribute("product", product);
		}catch(Exception e) {
			logger.error("ProductController err updateForm", e);
		}
		
		return "product/productForm";
	}
	
	// 商品下线
	@RequestMapping(value = "removeProduct/{id}", method = RequestMethod.GET)
	public String removeProduct(@PathVariable("id") Long id, Model model,HttpServletRequest request) {
		String parentId = request.getParameter("parentId");
		try{
			//获取当前用户所拥有的菜单
			/*List<Menu> menuList = this.getFirstMenuByUserId();
			model.addAttribute("menuList", menuList);*/
			
			productService.deleteProductbyId(id);
		}catch(Exception e) {
			logger.error("ProductController err removeProduct", e);
		}
		
		return "redirect:/product/list?customize=1&parentId=" + parentId;
	}
	
	/**
	 * 商品上线
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author 兼画  
	 * @createtime 2015年4月30日 下午2:14:36
	 */
	@RequestMapping(value = "updateIsDeleteFlag/{id}", method = RequestMethod.GET)
	public String updateIsDeleteFlag(@PathVariable("id") Long id, Model model,HttpServletRequest request) {
		String parentId = request.getParameter("parentId");
		try{
			//获取当前用户所拥有的菜单
			/*List<Menu> menuList = this.getFirstMenuByUserId();
			model.addAttribute("menuList", menuList);*/
			
			productService.updateIsDeleteFlag(id);
		}catch(Exception e) {
			logger.error("ProductController err updateIsDeleteFlag", e);
		}
		
		return "redirect:/product/list?customize=1&parentId=" + parentId;
	}
	
	@RequestMapping(value = "close/{parentId}", method = RequestMethod.GET)
	public String close(@PathVariable("parentId") Long parentId) {
		return "redirect:/product/list?customize=1&parentId=" + parentId;
	}
}
