package com.yuedi.web.system;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

import com.google.common.collect.Maps;
import com.yuedi.entity.MyPage;
import com.yuedi.entity.Seller;
import com.yuedi.entity.UserInfo;
import com.yuedi.service.SellerService;
import com.yuedi.service.UserInfoService;
import com.yuedi.web.common.SupperController;

/**
 * Task管理的Controller, 使用Restful风格的Urls:
 * 
 * List page : GET /task/
 * Create page : GET /task/create
 * Create action : POST /task/create
 * Update page : GET /task/update/{id}
 * Update action : POST /task/update
 * Delete action : GET /task/delete/{id}
 * 
 * @author calvin
 */
@Controller
@RequestMapping(value = "/userInfo")
public class UserInfoController extends SupperController{

	private static final String PAGE_SIZE = "10";

	private static Map<String, String> sortTypes = Maps.newLinkedHashMap();
	static {
		sortTypes.put("auto", "自动");
		sortTypes.put("title", "标题");
	}
	private static final Logger logger = Logger.getLogger(RoleController.class);

	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private SellerService sellerService;
	
   /*
	@RequestMapping(method = RequestMethod.GET)
	public String list(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize,
			@RequestParam(value = "sortType", defaultValue = "auto") String sortType, Model model,
			ServletRequest request) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		Long userId = getCurrentUserId();

		List<UserInfo> userList =userInfoService.findAllUserInfo(new UserInfo());

		model.addAttribute("userList", userList);
		model.addAttribute("sortType", sortType);
		model.addAttribute("sortTypes", sortTypes);
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));

		return "task/taskList";
	}
*/
	
	@RequestMapping(value = "/list/{parentId}", method = RequestMethod.GET)
	public String list(@PathVariable("parentId") Long parentId,Model model, 
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			HttpServletRequest request){
	 try{
		model.addAttribute("parentId", parentId);
//		model.addAttribute("date", this.getCurrentDate());
		//获取当前用户所拥有的菜单
		/*List<Menu> menuList = this.getFirstMenuByUserId();
		model.addAttribute("menuList", menuList);*/
		//获取二级菜单
//		List<Menu> towMenuList = this.getTwoMenuByParentId(parentId);
//		model.addAttribute("towMenuList", towMenuList);
		
		MyPage<UserInfo> page = new MyPage<UserInfo>();
		page.setNumber(pageNumber);
		
		String userName = request.getParameter("userName");
		model.addAttribute("userName", userName);
		if(userName != null && !"".equals(userName)) {
			page.getParams().put("userName", userName+ "%");
		}
		String searchParams = "parentId="+parentId;
		String franchiseesId = request.getParameter("franchiseesId");
		if(!StringUtils.isEmpty(franchiseesId)){
			model.addAttribute("franchiseesId", franchiseesId);
			searchParams += "&franchiseesId="+franchiseesId;
			page.getParams().put("franchiseesId", franchiseesId);
		}else{
			if(this.getCurrentUserFranchiseesIdId()!=3){	//当前不是总部的，就需要加机构ID进行查询
				page.getParams().put("franchiseesId", this.getCurrentUserFranchiseesIdId());
			}
		}
		model.addAttribute("searchParams", searchParams);
		
		List<UserInfo> userList = userInfoService.selectUserInfoLimit(page);
		
		model.addAttribute("userList", userList);
		model.addAttribute("pageData", page);
		
		List<Seller> sellerList = sellerService.findAllSellerFranchisees();
		model.addAttribute("sellerList", sellerList);
		
		String titleName = "用户管理";
		if(!StringUtils.isEmpty(request.getParameter("titleName"))){
			titleName = request.getParameter("titleName");
		}
		model.addAttribute("titleName", titleName);
	}catch(Exception e) {
		logger.error("UserInfoController err listMenuByParentId", e);
	}
	return "user/userList";
	}
	
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(Model model,HttpServletRequest request) {
		//获取当前用户所拥有的菜单
		/*List<Menu> menuList = this.getFirstMenuByUserId();
		model.addAttribute("menuList", menuList);*/
		String parentId = request.getParameter("parentId");
		model.addAttribute("parentId", parentId);
		List<Seller> sellerList = sellerService.findAllSellerFranchisees();
		model.addAttribute("sellerList", sellerList);
		return "user/adduser";
	}
	
	@RequestMapping(value = "addUserInfo", method = RequestMethod.POST)
	public String addUserInfo(Model model, @ModelAttribute("form") UserInfo userInfo) {
		try{
			if(userInfo.getId() == null) {
				if(!StringUtils.isEmpty(userInfo.getUserName())) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					userInfo.setCreaterId(this.getCurrentUserId());
					userInfo.setCreateDateTime(sdf.format(new Date()));
					userInfo.setCreaterName(this.getCurrentUserName());
					userInfo.setIsDeleteFlag(false);
					userInfoService.saveUserInfo(userInfo);
				}
			}else {
				if(!StringUtils.isEmpty(userInfo.getUserName())) {
//					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//					userInfo.setCreaterId(this.getCurrentUserId());
//					userInfo.setCreateDateTime(sdf.format(new Date()));
//					userInfo.setCreaterName(this.getCurrentUserName());
//					userInfo.setIsDeleteFlag(false);
					userInfoService.updateUserInfo(userInfo);
				}
			}
		}catch(Exception e) {
			logger.error("UserInfoController err addRole", e);
		}
		
		return "redirect:/userInfo/list/" + new Long(userInfo.getParentId());
	}
	
	@RequestMapping(value = "close/{parentId}", method = RequestMethod.GET)
	public String close(@PathVariable("parentId") Long parentId) {
		return "redirect:/userInfo/list/" + parentId;
	}
	
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model,HttpServletRequest request) {
		String parentId = request.getParameter("parentId");
		model.addAttribute("parentId", parentId);
		//获取当前用户所拥有的菜单
		/*List<Menu> menuList = this.getFirstMenuByUserId();
		model.addAttribute("menuList", menuList);*/
		UserInfo user = userInfoService.getUserInfoById(id);
		model.addAttribute("user", user);
		List<Seller> sellerList = sellerService.findAllSellerFranchisees();
		model.addAttribute("sellerList", sellerList);
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
		/*List<Menu> menuList = this.getFirstMenuByUserId();
		model.addAttribute("menuList", menuList);*/
		userInfoService.recover(id);
		return "redirect:/userInfo/list/" + parentId;
	}
	
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
	/*
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("user") UserInfo user, RedirectAttributes redirectAttributes) {
		if(userInfoService.updateUserInfo(user)>0){
			redirectAttributes.addFlashAttribute("message", "更新任务成功");
		}
		return "redirect:/userInfo/";
	}

	@RequestMapping(value = "delete/{id}")
	public String delete(@RequestParam(value = "page") int pageNumber,
			@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		userInfoService.deleteUserInfo(id);
		redirectAttributes.addFlashAttribute("message", "删除任务成功");
		return "redirect:/userInfo/?page=" + pageNumber;
	}
	*/
	
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
	
	/*
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("task", new UserInfo());
		model.addAttribute("action", "create");
		return "task/taskForm";
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(@Valid UserInfo newUserInfo, RedirectAttributes redirectAttributes) {
		User user = new User(getCurrentUserId());
		newUserInfo.setUser(user);

		taskService.saveTask(newTask);
		redirectAttributes.addFlashAttribute("message", "创建任务成功");
		return "redirect:/task/";
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("task", taskService.getTask(id));
		model.addAttribute("action", "update");
		return "task/taskForm";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("task") Task task, RedirectAttributes redirectAttributes) {
		taskService.saveTask(task);
		redirectAttributes.addFlashAttribute("message", "更新任务成功");
		return "redirect:/task/";
	}

	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		taskService.deleteTask(id);
		redirectAttributes.addFlashAttribute("message", "删除任务成功");
		return "redirect:/task/";
	}
*/
	/**
	 * 所有RequestMapping方法调用前的Model准备方法, 实现Struts2 Preparable二次部分绑定的效果,先根据form的id从数据库查出Task对象,再把Form提交的内容绑定到该对象上。
	 * 因为仅update()方法的form中有id属性，因此仅在update时实际执行.
	 */
	/*@ModelAttribute
	public void getTask(@RequestParam(value = "id", defaultValue = "-1") Long id, Model model) {
		if (id != -1) {
			model.addAttribute("task", taskService.getTask(id));
		}
	}
*/
}
