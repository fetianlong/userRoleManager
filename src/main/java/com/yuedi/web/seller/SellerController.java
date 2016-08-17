package com.yuedi.web.seller;

import java.io.File;
import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yuedi.entity.Areas;
import com.yuedi.entity.MyPage;
import com.yuedi.entity.Role;
import com.yuedi.entity.RoleUserInfo;
import com.yuedi.entity.Seller;
import com.yuedi.entity.UserInfo;
import com.yuedi.service.AreasService;
import com.yuedi.service.MenuService;
import com.yuedi.service.RoleService;
import com.yuedi.service.SellerService;
import com.yuedi.service.UserInfoService;
import com.yuedi.util.BaiduAPI;
import com.yuedi.web.common.SupperController;
import com.yuedi.web.system.RoleController;

@Controller
@RequestMapping(value = "/seller")
public class SellerController extends SupperController{
	
	private static String relativePathSeller = "D:/yuedi-resource/resources/picture/shangjia";
	
	@Autowired
	private SellerService sellerService;
	@Autowired
	private MenuService menuService;
	@Autowired
	private AreasService areasService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserInfoService userInfoService;
	
	private static final Logger logger = Logger.getLogger(RoleController.class);
	
	@RequestMapping(value="listSeller",method = RequestMethod.GET)
	public String listSeller(Model model, @RequestParam(value = "page", defaultValue = "1") int pageNumber,
			HttpServletRequest request){
		try{
			model.addAttribute("date", this.getCurrentDate());
			//获取当前用户所拥有的菜单
			/*List<Menu> menuList = this.getFirstMenuByUserId();
			model.addAttribute("menuList", menuList);*/
			//获取二级菜单
			Long parentId = Long.parseLong(request.getParameter("parentId"));
//			model.addAttribute("parentId", parentId);
//			List<Menu> towMenuList = this.getTwoMenuByParentId(parentId);
//			model.addAttribute("towMenuList", towMenuList);
			
			MyPage<Seller> page = new MyPage<Seller>();
			page.setNumber(pageNumber);
			
			String selledcnt = request.getParameter("sellencnt");
			if(StringUtils.isEmpty(selledcnt)){
				selledcnt = "0";	//潜在商家
			}
			model.addAttribute("sellencnt", selledcnt);
			page.getParams().put("sellencnt", selledcnt);
			
			String sellersign = request.getParameter("sellersign");
			model.addAttribute("sellersign", sellersign);
			page.getParams().put("sellersign", sellersign);
			
			model.addAttribute("searchParams","sellencnt=" +selledcnt+"&sellersign="+sellersign+"&parentId="+parentId);
			String name = request.getParameter("name");
			model.addAttribute("name", name);
			if(name != null && !"".equals(name)) {
				page.getParams().put("name", name+ "%");
			}
			String province = request.getParameter("province");
			model.addAttribute("province", province);
			if(province != null && !"".equals(province)) {
				page.getParams().put("Sheng", province);
			}
			String city = request.getParameter("city");
			model.addAttribute("city", city);
			if(city != null && !"".equals(city)) {
				page.getParams().put("Shi",city);
			}
			String category = request.getParameter("category");
			if(category != null && !"".equals(category)) {
				page.getParams().put("category", category);
			}
			if(this.getCurrentUserId() != 3){
				page.getParams().put("userInfoId", this.getCurrentUserId());
			}
			
			List<Seller> sellerList = sellerService.selectSellerLimit(page);
			model.addAttribute("sellerList", sellerList);
			model.addAttribute("pageData", page);
			
			//title
//			String titleName = null;
//			if(!StringUtils.isEmpty(request.getParameter("titleName"))){
//				titleName = (String)request.getParameter("titleName");
//			}else{
//				titleName = towMenuList.get(0).getName();
//			}
//			model.addAttribute("titleName", titleName);
			List<Areas> areasList = areasService.findProvince();
			model.addAttribute("areasList", areasList);
		}catch(Exception e) {
			logger.error("SellerController err listMenuByParentId", e);
		}
		return "seller/sellerList";
	}
	
	@RequestMapping(value="add",method = RequestMethod.GET)
	public String add(Model model,HttpServletRequest request){
		//获取当前用户所拥有的菜单
		/*List<Menu> menuList = this.getFirstMenuByUserId();
		model.addAttribute("menuList", menuList);*/
		String parentId = request.getParameter("parentId");
		model.addAttribute("parentId", parentId);
		
		String selledcnt = request.getParameter("selledcnt");
		model.addAttribute("selledcnt", selledcnt);
		String sellersign = request.getParameter("sellersign");
		model.addAttribute("sellersign", sellersign);
		String titleName = request.getParameter("titleName");
		model.addAttribute("titleName", titleName);
		List<Areas> areasList = areasService.findProvince();
		model.addAttribute("areasList", areasList);
		
		return "seller/sellerForm";
	}
	
	@RequestMapping(value="addSeller",method=RequestMethod.POST)
	public String addSeller(@RequestParam(value = "file", required = false) MultipartFile file,Model model, 
			@ModelAttribute("form") Seller seller,HttpServletRequest request) {
		try{
			if(seller.getId() == null) {
				if (!StringUtils.isEmpty(seller.getName()) && !StringUtils.isEmpty(seller.getProvince())
						&& !StringUtils.isEmpty(seller.getCity())
						&& !StringUtils.isEmpty(seller.getArea())) {
					List<Areas> listsheng = areasService.findAreas(Integer
							.parseInt(seller.getProvince()));
					String areassheng = listsheng.get(0).getName();
					List<Areas> listshi = areasService.findAreas(Integer.parseInt(seller.getCity()));
					String areasshi = listshi.get(0).getName();
					List<Areas> listqu = areasService.findAreas(Integer.parseInt(seller.getArea()));
					String areasqu = listqu.get(0).getName();
					String areas = areassheng + areasshi + areasqu;
					BaiduAPI getLatAndLngByBaidu = new BaiduAPI();
					
					Object[] o = getLatAndLngByBaidu.getCoordinate(areas + seller.getOfficeAddr());
					
					seller.setParentId(this.getCurrentUserFranchiseesIdId());
					seller.setLongitude(new Double(o[0].toString()));// 添加经度
					seller.setLatitude(new Double(o[1].toString()));// 添加纬度
					seller.setSellersign(seller.getSellersign());
					seller.setObligationOrg("责任机构");// 责任机构
					seller.setBusinessScope("经营品类");
					seller.setIsDeleteFlag(false);
					seller.setCreateDateTime(new Date());
					seller.setCreaterId(this.getCurrentUserId());
					seller.setCreaterName(this.getCurrentUserName());
					seller.setCharterEffectiveDate(new Date());// 营业执照有效日
					seller.setCharterExpiredDate(new Date());// 营业执照失效日
					
					List<Role> roles = roleService.selectRoleByUserId(this.getCurrentUserId());
					if(roles != null) {
						for(int i=0; i<roles.size(); i++) {
							if(roles.get(i).getId() == 41 || roles.get(i).getId() == 37) {
								seller.setUserInfoId(this.getCurrentUserId());
							}
						}
					}
		
					if("0".equals(seller.getSelledcnt()) && "0".equals(seller.getSellersign())){	//潜在商家
						seller.setAuthentication(1);// 商家认证状态 1 。未认证 2 。已认证
					}else{
						seller.setAuthentication(2);
					}
					
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
			        seller.setImg("shangjia/"+realName);
			        
					if (sellerService.insertSeller(seller) > 0) {
						if(null != seller.getLoginName() && seller.getLoginName() != ""){
							boolean flag = addUser(seller);	//添加用户角色
							if(flag){
								//给加盟商或商家3个二维码
//								Saler saler = new Saler();
//								saler.setSellerId(this.getCurrentUserFranchiseesIdId());
//								saler.setOrdIndex("1");
//								saler.setIsDeleteFlag(false);
//								saler.setCreateDateTime(new Date());
//								saler.setCreaterId(this.getCurrentUserId());
//								saler.setCreaterName(this.getCurrentUserName());
//								
//								salerService.saveSaler(saler, 3, null);
							}
						}
					}
				}
			}else {
				if (!StringUtils.isEmpty(seller.getId().toString()) 
						&& !StringUtils.isEmpty(seller.getName()) 
						&& !StringUtils.isEmpty(seller.getProvince())
						&& !StringUtils.isEmpty(seller.getCity())
						&& !StringUtils.isEmpty(seller.getArea())) {
					List<Areas> listsheng = areasService.findAreas(Integer
							.parseInt(seller.getProvince()));
					String areassheng = listsheng.get(0).getName();
					List<Areas> listshi = areasService.findAreas(Integer.parseInt(seller.getCity()));
					String areasshi = listshi.get(0).getName();
					List<Areas> listqu = areasService.findAreas(Integer.parseInt(seller.getArea()));
					String areasqu = listqu.get(0).getName();
					String areas = areassheng + areasshi + areasqu;
					BaiduAPI getLatAndLngByBaidu = new BaiduAPI();
					if (seller.getOfficeAddr() != null) {
						Object[] o = getLatAndLngByBaidu.getCoordinate(areas
								+ seller.getOfficeAddr());
						seller.setLongitude(new Double(o[0].toString()));// 添加经度
						seller.setLatitude(new Double(o[1].toString()));// 添加纬度
					}
					seller.setObligationOrg("责任机构");// 责任机构
					seller.setBusinessScope("经营品类");
					seller.setIsDeleteFlag(false);
					seller.setCharterEffectiveDate(new Date());// 营业执照有效日
					seller.setCharterExpiredDate(new Date());// 营业执照失效日
					
					//上传了新的图片
					if (file != null && file.getSize() > 0) {
				        String fileName = file.getOriginalFilename();
				        String realName = String.format("%1$s%2$s", UUID.randomUUID(), fileName.substring(fileName.lastIndexOf(".")));
				        seller.setImg("shangjia/"+realName);   

						int result=sellerService.updateSellerbyId(seller);
						//编辑成功，删除本地目录下之前上传的图片。
						if(result>0){
							//String path = String.format("%1$s/%2$s", request.getSession().getServletContext().getInitParameter(RESOURCE_ROOT_DIR), relativePathSeller);
							 String oldImgPath=relativePathSeller+seller.getImg();
							 
							 File oldImgFile=new File(oldImgPath);
							 if(oldImgFile.exists()){
								 oldImgFile.delete();
							 }
						}
					}else{
						seller.setImg(seller.getImg());//未上传新的图片
						sellerService.updateSellerbyId(seller);
					}
				}
			}
		}catch(Exception e) {
			logger.error("sellerController err addSeller", e);
		}
		
		return "redirect:/seller/listSeller?sellersign="+seller.getSellersign()+"&selledcnt="+seller.getSelledcnt()+"&parentId=" + seller.getParentId();
	}
	
	/**
	 * 增加加盟商或正式商家时，新建用户角色
	 * @param seller
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年6月24日 下午2:35:44
	 */
	private boolean addUser(Seller seller) {
		boolean flag = true;
		try {
			/**/
			// 创建加盟商成功后，给加盟商自动建个管理员用户,建个管理员角色，把该用户放入角色中
			UserInfo userInfo = new UserInfo();
			SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
			
			userInfo.setFranchiseesId(seller.getId());
	//		userInfo.setFranchiseesId(getUserFranchiseesId());
			userInfo.setUserName(seller.getLoginName());
			userInfo.setPlainPassword(seller.getTel());
			userInfo.setState(1);
			userInfo.setIsDeleteFlag(false);
			userInfo.setCreateDateTime(sdf.format(new Date()));
			userInfo.setCreaterId(seller.getId());
			userInfo.setCreaterName(seller.getName());
			userInfo.setRoleCategory("ROLE_ADMIN");
			
			userInfo.setRemark("创建加盟商或商家时自动生成账号");
			
			if (userInfoService.saveUserInfo(userInfo) > 0) {
				String sellerType = "商家";
				if (null != seller.getSellersign()) {
					if (seller.getSellersign() == 1) {
						sellerType = "加盟商";
					}
				}
	//			String s = name + sellerType + "管理员";
				
				// 插入角色信息
				Long roleId = null;
				if(seller.getSellersign() == 0 && !seller.getSelledcnt().equals("0")){	//当前是商家
					roleId = new Long(39);
				}else if(seller.getSellersign()==1){	//加盟商
					roleId = new Long(32);
				}
				
	//			role.setName(s);
	//			role.setIsDeleteFlag(false);
	//			role.setRoleType(getUserFranchiseesId().toString());
	//			role.setRemark(s);
	//			role.setCreateDateTime(new Date());
	//			role.setCreaterId(seller.getId());
	//			role.setCreaterName(seller.getName());
	
				if (null != roleId && roleId > 0) {
					// 插入用户角色关联关系
					RoleUserInfo roleUserInfo = new RoleUserInfo();
					roleUserInfo.setRoleId(roleId);
					roleUserInfo.setUserInfoId(userInfo.getId());
	
					roleService.assignUserToRole(roleUserInfo);
	
					// 插入角色菜单资源关联关系
				}
				
			}
		} catch (Exception e) {
			flag = false;
			logger.error(e);
		}
		return flag;
	}
	
	@RequestMapping(value = "close/{parentId}", method = RequestMethod.GET)
	public String close(@PathVariable("parentId") Long parentId,HttpServletRequest request) {
		String sellersign = request.getParameter("sellersign");
		String selledcnt = request.getParameter("selledcnt");
		return "redirect:/seller/listSeller?sellersign="+sellersign+"&selledcnt="+selledcnt+"&parentId=" + parentId;
	}
	
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model,HttpServletRequest request) {
		try{
			String parentId = request.getParameter("parentId");
			model.addAttribute("parentId", parentId);
			//获取当前用户所拥有的菜单
			/*List<Menu> menuList = this.getFirstMenuByUserId();
			model.addAttribute("menuList", menuList);*/
			
			Seller seller = sellerService.selectSellerById(id);
			model.addAttribute("seller", seller);
			
			//获取项目ip和port
			String ip = request.getRemoteAddr(); 
			int port = request.getRemotePort();
			model.addAttribute("ip",ip);
			model.addAttribute("port",port);
			
			List<Areas> areasList = areasService.findProvince();
			model.addAttribute("areasList", areasList);
			
			String selledcnt = request.getParameter("selledcnt");
			model.addAttribute("selledcnt", selledcnt);
			String sellersign = request.getParameter("sellersign");
			model.addAttribute("sellersign", sellersign);
			
		}catch(Exception e) {
			logger.error("SellerController err updateForm", e);
		}
		
		return "seller/sellerForm";
	}
	
	@RequestMapping(value = "convertFormatalSeller/{id}", method = RequestMethod.GET)
	public String convertFormatalSeller(@PathVariable("id") Long id, Model model,HttpServletRequest request) {
		try{
			String parentId = request.getParameter("parentId");
			model.addAttribute("parentId", parentId);
			//获取当前用户所拥有的菜单
			/*List<Menu> menuList = this.getFirstMenuByUserId();
			model.addAttribute("menuList", menuList);*/
			
			Seller seller = sellerService.selectSellerById(id);
			model.addAttribute("seller", seller);
			
			String selledcnt = request.getParameter("selledcnt");
			model.addAttribute("selledcnt", selledcnt);
			String sellersign = request.getParameter("sellersign");
			model.addAttribute("sellersign", sellersign);
			
		}catch(Exception e) {
			logger.error("SellerController err updateForm", e);
		}
		
		return "seller/formalSellerForm";
	}
	
	@RequestMapping(value = "editSellerUserName", method = RequestMethod.POST)
	public String editSellerUserName(@ModelAttribute("form") Seller seller, Model model,HttpServletRequest request) {
		try{
			String parentId = request.getParameter("parentId");
			model.addAttribute("parentId", parentId);
			//获取当前用户所拥有的菜单
			/*List<Menu> menuList = this.getFirstMenuByUserId();
			model.addAttribute("menuList", menuList);*/
			
			String selledcnt = request.getParameter("selledcnt");
			model.addAttribute("selledcnt", selledcnt);
			String sellersign = request.getParameter("sellersign");
			model.addAttribute("sellersign", sellersign);
			
			if(sellerService.updateSellerbyId(seller)>0){
				boolean flag = addUser(seller);
			}
		}catch(Exception e) {
			logger.error("SellerController err updateForm", e);
		}
		
		return "redirect:/seller/listSeller?sellersign="+seller.getSellersign()+"&selledcnt="+seller.getSelledcnt()+"&parentId=" + seller.getParentId();
	}
	
	@RequestMapping(value = "examineMessage")
	public String examineMessage(@PathVariable("id") Long id,Model model,HttpServletRequest request) {
		try{
			//获取当前用户所拥有的菜单
			/*List<Menu> menuList = this.getFirstMenuByUserId();
			model.addAttribute("menuList", menuList);*/
			String parentId = request.getParameter("parentId");
			model.addAttribute("parentId", parentId);
			
			String selledcnt = request.getParameter("selledcnt");
			model.addAttribute("selledcnt", selledcnt);
			String sellersign = request.getParameter("sellersign");
			model.addAttribute("sellersign", sellersign);
		}catch(Exception e) {
			logger.error("SellerController err delete", e);
		}
		
		return "seller/examineMessageForm";
	}
	
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Long id,Model model,HttpServletRequest request) {
		String parentId = request.getParameter("parentId");
		try{
			//获取当前用户所拥有的菜单
			/*List<Menu> menuList = this.getFirstMenuByUserId();
			model.addAttribute("menuList", menuList);*/
			sellerService.deleteSellerById(id);
		}catch(Exception e) {
			logger.error("SellerController err delete", e);
		}
		
		return "redirect:/seller/list/" + parentId;
	}
	
	/**
	 * 获取市信息
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="listCityByParentId",method = RequestMethod.GET,produces="application/json;charset=UTF-8")
	public List<Areas> listCityByParentId(HttpServletRequest request) {
		String provinceParamsId = request.getParameter("provinceId");
		if(!StringUtils.isEmpty(provinceParamsId)) {
//			if(provinceParamsId != null && !"".equals(provinceParamsId)) {
			Integer provinceId = Integer.parseInt(provinceParamsId);
			List<Areas> areas = areasService.findCityByProvince(provinceId);
			return areas;
		}else {
			return null;
		}
	}
	
	/**
	 * 获取市信息
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="listAreaByParentId",method = RequestMethod.GET,produces="application/json;charset=UTF-8")
	public List<Areas> listAreaByParentId(HttpServletRequest request) {
		String cityParamsId = request.getParameter("cityId");
		if(cityParamsId != null && !"".equals(cityParamsId)) {
			Integer cityId = Integer.parseInt(cityParamsId);
			List<Areas> areas = areasService.findCityByProvince(cityId);
			return areas;
		}else {
			return null;
		}
	}
	
	/**
	 * 校验用户名是否重复
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="checkExistByUserName",method = RequestMethod.GET,produces="application/json;charset=UTF-8")
	public Boolean checkExistByUserName(@RequestParam("loginName") String loginName,HttpServletRequest request) {
//		String loginName = request.getParameter("loginName");
		try{
			if(loginName != null && !"".equals(loginName)) {
				Boolean userInfoFlag = userInfoService.checkExistByUserName(loginName);
				System.out.println("用户名：" + loginName + "------" + userInfoFlag);
				return userInfoFlag;
//				return true;
			}else {
				return false;
			}
		}catch(Exception e) {
			logger.error("SellerController err checkExistByUserName", e);
			return false;
		}
	}

}
