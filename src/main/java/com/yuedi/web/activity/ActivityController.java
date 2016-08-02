package com.yuedi.web.activity;

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

import com.yuedi.entity.Activity;
import com.yuedi.entity.Areas;
import com.yuedi.entity.Menu;
import com.yuedi.entity.MyPage;
import com.yuedi.entity.Seller;
import com.yuedi.service.ActivityService;
import com.yuedi.service.AreasService;
import com.yuedi.service.SellerService;
import com.yuedi.util.HxUtil;
import com.yuedi.web.activityLine.ActivityLineController;
import com.yuedi.web.common.SupperController;

@Controller
@RequestMapping(value = "/activity")
public class ActivityController extends SupperController{
	@Autowired
	private ActivityService activityService;
	@Autowired
	private AreasService areasService;
	@Autowired
	private SellerService sellerService;
	
	private static String relativePathSeller = "D:/yuedi-resource/resources/picture/activity";
	
	private static final Logger logger = Logger.getLogger(ActivityLineController.class);
	
	@RequestMapping(value="list/{parentId}",method = RequestMethod.GET)
	public String list(@PathVariable("parentId") Long parentId,Model model, 
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			HttpServletRequest request){
		try{
			model.addAttribute("parentId", parentId);
			model.addAttribute("date", this.getCurrentDate());
			//获取当前用户所拥有的菜单
			/*List<Menu> menuList = this.getFirstMenuByUserId();
			model.addAttribute("menuList", menuList);*/
			//获取二级菜单
			List<Menu> towMenuList = this.getTwoMenuByParentId(parentId);
			model.addAttribute("towMenuList", towMenuList);
			
			MyPage<Activity> page = new MyPage<Activity>();
			page.setNumber(pageNumber);
			
			List<Activity> activityList = activityService.findListActivityByLimit(page);
			model.addAttribute("activityList", activityList);
			model.addAttribute("pageData", page);
		}catch(Exception e) {
			logger.error("ActivityController err list", e);
		}
		
		return "activity/activityList";
	}
	
	@RequestMapping(value="add",method = RequestMethod.GET)
	public String add(Model model,HttpServletRequest request){
		//获取当前用户所拥有的菜单
		/*List<Menu> menuList = this.getFirstMenuByUserId();
		model.addAttribute("menuList", menuList);*/
		String parentId = request.getParameter("parentId");
		model.addAttribute("parentId", parentId);
		
		List<Areas> areasList = areasService.findProvince();
		model.addAttribute("areasList", areasList);
		
		return "activity/activityForm";
	}
	
	@RequestMapping(value="addActivity",method=RequestMethod.POST)
	public String addActivityLine(@RequestParam(value = "file", required = false) MultipartFile file,Model model, 
			@ModelAttribute("form") Activity activity) {
		try{
			if(activity.getId() == null) {
				if(!StringUtils.isEmpty(activity.getActivityName())) {
					activity.setCreateDateTime(new Date());// 创建时间
					activity.setCreaterName(this.getCurrentUserName());// 创建人
					activity.setReviewStatus(0);// 默认未审核
					activity.setSellerId(this.getCurrentUserFranchiseesIdId());
					
					Seller seller = sellerService.selectSellerById(this.getCurrentUserFranchiseesIdId());
					if(seller != null) {
						activity.setSellerName(seller.getName());
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
			        activity.setImg("activity/"+realName);
			        
			        String token=HxUtil.HttpPostHxtoken();
					if(null != token){
						String groupId=HxUtil.HttpPostDataHxGroup(token, new String(activity.getActivityName()), "LamTracy",new String(activity.getDetails()));
						if(groupId!=null){
							activity.setGroupid(groupId);
							activityService.add(activity);
						}else{
							logger.debug("群组groupId为空");
						}
					}else{
						logger.debug("获取token为空");
					}
				}
			}else {
				if(!StringUtils.isEmpty(activity.getActivityName())) {
					activity.setCreateDateTime(new Date());// 创建时间
					activity.setCreaterName(this.getCurrentUserName());// 创建人
					activity.setReviewStatus(0);// 默认未审核
					activity.setSellerId(this.getCurrentUserFranchiseesIdId());
					
					//上传了新的图片
					if (file != null && file.getSize() > 0) {
				        String fileName = file.getOriginalFilename();
				        String realName = String.format("%1$s%2$s", UUID.randomUUID(), fileName.substring(fileName.lastIndexOf(".")));
				        activity.setImg("activity/"+realName);   

				        int result = activityService.update(activity);
						//编辑成功，删除本地目录下之前上传的图片。
						if(result>0){
							//String path = String.format("%1$s/%2$s", request.getSession().getServletContext().getInitParameter(RESOURCE_ROOT_DIR), relativePathSeller);
							 String oldImgPath=relativePathSeller+activity.getImg();
							 
							 File oldImgFile=new File(oldImgPath);
							 if(oldImgFile.exists()){
								 oldImgFile.delete();
							 }
						}
					}else{
						activity.setImg(activity.getImg());//未上传新的图片
						activityService.update(activity);
					}
				}
			}
		}catch(Exception e) {
			logger.error("ActivityController err addActivityLine", e);
		}
		
		return "redirect:/activity/list/" + activity.getParentId();
	}
	
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Integer id, Model model,HttpServletRequest request) {
		try{
			String parentId = request.getParameter("parentId");
			model.addAttribute("parentId", parentId);
			//获取当前用户所拥有的菜单
			/*List<Menu> menuList = this.getFirstMenuByUserId();
			model.addAttribute("menuList", menuList);*/
			
			Activity activity = activityService.findActivityById(id);
			model.addAttribute("activity", activity);
		}catch(Exception e) {
			logger.error("ActivityController err updateForm", e);
		}
		
		return "activity/activityForm";
	}
	
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Integer id,Model model,HttpServletRequest request) {
		String parentId = request.getParameter("parentId");
		try{
			//获取当前用户所拥有的菜单
			/*List<Menu> menuList = this.getFirstMenuByUserId();
			model.addAttribute("menuList", menuList);*/
			
			activityService.deleteById(id);
		}catch(Exception e) {
			logger.error("ActivityController err delete", e);
		}
		
		return "redirect:/activity/list/" + parentId;
	}
	
	@RequestMapping(value = "reviewSuccess/{id}")
	public String reviewSuccess(@PathVariable("id") Integer id,Model model,HttpServletRequest request) {
		String parentId = request.getParameter("parentId");
		try{
			//获取当前用户所拥有的菜单
			/*List<Menu> menuList = this.getFirstMenuByUserId();
			model.addAttribute("menuList", menuList);*/
			
			Activity ac = new Activity();
			
			ac.setId(id);
			ac.setReviewStatus(1);
			activityService.updateReviewStatus(ac);
		}catch(Exception e) {
			logger.error("ActivityController err reviewSuccess", e);
		}
		
		return "redirect:/activity/list/" + parentId;
	}
	
	@RequestMapping(value = "reviewFail/{id}")
	public String reviewFail(@PathVariable("id") Integer id,Model model,HttpServletRequest request) {
		String parentId = request.getParameter("parentId");
		try{
			//获取当前用户所拥有的菜单
			/*List<Menu> menuList = this.getFirstMenuByUserId();
			model.addAttribute("menuList", menuList);*/
			
			Activity ac = new Activity();
			
			ac.setId(id);
			ac.setReviewStatus(2);
			activityService.updateReviewStatus(ac);
		}catch(Exception e) {
			logger.error("ActivityController err reviewFail", e);
		}
		
		return "redirect:/activity/list/" + parentId;
	}
	
	@RequestMapping(value = "close/{parentId}", method = RequestMethod.GET)
	public String close(@PathVariable("parentId") Long parentId) {
		return "redirect:/activityLine/list/" + parentId;
	}
}
