package com.yuedi.web.system;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import org.springside.modules.web.MediaTypes;

import com.yuedi.entity.MyPage;
import com.yuedi.entity.UserInfo;
import com.yuedi.log.SystemControllerLog;
import com.yuedi.service.UserInfoService;
import com.yuedi.web.common.SupperController;

/**
 * 用户管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "/userInfo")
public class UserInfoController extends SupperController{

	private static final Logger logger = Logger.getLogger(RoleController.class);

	@Autowired
	private UserInfoService userInfoService;
//	@Autowired
//	private RoleService roleService;

//	@SystemControllerLog(description = "查看用户列表")
	@RequestMapping(value = "/list/{parentId}", method = RequestMethod.GET)
	public String list(@PathVariable("parentId") Long parentId,Model model, 
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			HttpServletRequest request){
		try{
			model.addAttribute("parentId", parentId);
			
			MyPage<UserInfo> page = new MyPage<UserInfo>();
			page.setNumber(pageNumber);
			
			String userName = request.getParameter("userName");
			model.addAttribute("userName", userName);
			if(userName != null && !"".equals(userName)) {
				page.getParams().put("userName", userName+ "%");
			}
			String searchParams = "parentId="+parentId;
			model.addAttribute("searchParams", searchParams);
			
			List<UserInfo> userList = userInfoService.selectUserInfoLimit(page);
			
			model.addAttribute("userList", userList);
			model.addAttribute("pageData", page);
			
	//		String titleName = "用户管理";
	//		if(!StringUtils.isEmpty(request.getParameter("titleName"))){
	//			titleName = request.getParameter("titleName");
	//		}
	//		model.addAttribute("titleName", titleName);
		}catch(Exception e) {
			logger.error("UserInfoController err listMenuByParentId", e);
		}
		return "user/userList";
	}
	
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(Model model,HttpServletRequest request) {
		String parentId = request.getParameter("parentId");
		model.addAttribute("parentId", parentId);
		model.addAttribute("action", "addUserInfo");
//		List<Role> roleList = roleService.findAllRole();
//		model.addAttribute("roleList", roleList);
		return "user/adduser";
	}
	
	@SystemControllerLog(description = "新增用户")
	@RequestMapping(value = "addUserInfo", method = RequestMethod.POST)
	public String addUserInfo(Model model, @ModelAttribute("form") UserInfo userInfo,
			@RequestParam(value = "sellerId", defaultValue = "1") Long sellerId) {
//		try{
			if(userInfo.getId() == null) {
				if(!StringUtils.isEmpty(userInfo.getUserName())) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					userInfo.setCreaterId(this.getCurrentUserId());
					userInfo.setCreateDateTime(sdf.format(new Date()));
					userInfo.setCreaterName(this.getCurrentUserName());
					userInfo.setIsDeleteFlag(false);
					userInfo.setFranchiseesId(sellerId);
					userInfoService.saveUserInfo(userInfo);
				}
			}
//		}catch(Exception e) {
//			logger.error("UserInfoController err addRole", e);
//		}
		
		return "redirect:/userInfo/list/" + new Long(userInfo.getParentId());
	}
	
	@SystemControllerLog(description = "修改用户信息")
	@RequestMapping(value = "updateUserInfo", method = RequestMethod.POST)
	public String updateUserInfo(Model model, @ModelAttribute("form") UserInfo userInfo,
			@RequestParam(value = "sellerId", defaultValue = "1") Long sellerId) {
		if(!StringUtils.isEmpty(userInfo.getUserName())) {
			userInfoService.updateUserInfo(userInfo);
		}
		return "redirect:/userInfo/list/" + new Long(userInfo.getParentId());
	}
	
	@RequestMapping(value = "close/{parentId}", method = RequestMethod.GET)
	public String close(@PathVariable("parentId") Long parentId) {
		return "redirect:/userInfo/list/" + parentId;
	}
	
//	@SystemControllerLog(description = "更新用户信息")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model,HttpServletRequest request) {
		String parentId = request.getParameter("parentId");
		model.addAttribute("parentId", parentId);
		UserInfo user = userInfoService.getUserInfoById(id);
		model.addAttribute("user", user);
		model.addAttribute("action", "updateUserInfo");
		return "user/adduser";
	}
	
	@RequestMapping(value = "freeze/{id}", method = RequestMethod.GET)
	public String freeze(@PathVariable("id") Long id, Model model,HttpServletRequest request) {
		String parentId = request.getParameter("parentId");
		model.addAttribute("parentId", parentId);
		//获取当前用户所拥有的菜单
		/*List<Menu> menuList = this.getFirstMenuByUserId();
		model.addAttribute("menuList", menuList);*/
		userInfoService.freezeUser(id);
		return "redirect:/userInfo/list/" + parentId;
	}
	
	@RequestMapping(value = "recover/{id}", method = RequestMethod.GET)
	public String recover(@PathVariable("id") Long id, Model model,HttpServletRequest request) {
		String parentId = request.getParameter("parentId");
		model.addAttribute("parentId", parentId);
		//获取当前用户所拥有的菜单
//		List<Menu> menuList = this.getFirstMenuByUserId();
//		model.addAttribute("menuList", menuList);
		userInfoService.recover(id);
		return "redirect:/userInfo/list/" + parentId;
	}
	
	@SystemControllerLog(description = "重置密码")
	@RequestMapping(value = "resetPwd/{id}", method = RequestMethod.GET)
	public String resetPwd(@PathVariable("id") Long id, Model model,HttpServletRequest request) {
		UserInfo user = new UserInfo();
		UserInfo userInfo = userInfoService.getUserInfoById(id);
		String parentId = request.getParameter("parentId");
		model.addAttribute("parentId", parentId);
		//获取当前用户所拥有的菜单
		/*List<Menu> menuList = this.getFirstMenuByUserId();
		model.addAttribute("menuList", menuList);*/
		user.setId(id);
		user.setResetPwdFlag("1");
		user.setPlainPassword("123456");
		user.setSalt(userInfo.getSalt());
		userInfoService.resetPwdById(user); 
		return "redirect:/userInfo/list/" + parentId;
	}
	
	/**
	 * 删除单个用户
	 * @param id
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年11月12日 下午5:57:42
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteUser/{id}", method=RequestMethod.DELETE)
	public Boolean delete(@PathVariable("id") Long id) {
		Boolean flag = true;
		try {
			userInfoService.deleteUserInfo(id);
		} catch (Exception e) {
			flag = false;
			logger.warn("删除用户失败");
		}
		return flag;
	}
	
	/**
	 * 判断当前用户名是否存在
	 * @param loginName
	 * @param request
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年11月12日 下午5:13:23
	 */
	@ResponseBody
	@RequestMapping(value="checkExistByUserName",method = RequestMethod.GET,produces=MediaTypes.JSON_UTF_8)
	public Boolean checkExistByUserName(@RequestParam("loginName") String loginName,HttpServletRequest request) {
		Boolean flag = true;
		try{
			if(loginName != null && !"".equals(loginName)) {
				if(userInfoService.checkExistByUserName(loginName)){
					flag = false;
				}
				return flag;
			}else {
				return false;
			}
		}catch(Exception e) {
			logger.error("SellerController err checkExistByUserName", e);
			return false;
		}
	}

	/**
	 * 所有RequestMapping方法调用前的Model准备方法, 实现Struts2 Preparable二次部分绑定的效果,先根据form的id从数据库查出Task对象,再把Form提交的内容绑定到该对象上。
	 * 因为仅update()方法的form中有id属性，因此仅在update时实际执行.
	 */
	@ModelAttribute
	public void getUserInfo(@RequestParam(value = "id", defaultValue = "-1") Long id, Model model) {
		if (id != -1) {
			model.addAttribute("userInfo", userInfoService.getUserInfoById(id));
		}
	}

}
