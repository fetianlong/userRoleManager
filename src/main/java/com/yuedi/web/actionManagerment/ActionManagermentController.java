package com.yuedi.web.actionManagerment;

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

import com.yuedi.entity.ActionManagerment;
import com.yuedi.entity.Menu;
import com.yuedi.entity.MyPage;
import com.yuedi.entity.Role;
import com.yuedi.entity.UserInfo;
import com.yuedi.service.ActionManagermentService;
import com.yuedi.service.RoleService;
import com.yuedi.service.UserInfoService;
import com.yuedi.web.common.SupperController;

@Controller
@RequestMapping(value = "/actionManagerment")
public class ActionManagermentController extends SupperController{
	@Autowired
	private ActionManagermentService actionManagermentService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserInfoService userInfoService;
	
	private static final Logger logger = Logger.getLogger(ActionManagermentController.class);
	
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
			
			MyPage<ActionManagerment> page = new MyPage<ActionManagerment>();
			page.setNumber(pageNumber);
			String actionTitle = request.getParameter("actionTitle");
			model.addAttribute("actionTitle", actionTitle);
			if(actionTitle != null && !"".equals(actionTitle)) {
				page.getParams().put("actionTitle", actionTitle+ "%");
			}
			String executorId = request.getParameter("executorId");
			model.addAttribute("executorId", executorId);
			if(executorId != null && !"".equals(executorId)) {
				page.getParams().put("executorId", executorId);
			}
			String state = request.getParameter("state");
			model.addAttribute("state", state);
			if(state != null && !"".equals(state)) {
				page.getParams().put("state", state);
			}
			List<ActionManagerment> actionManagermentList = actionManagermentService.selectActionManagermentLimit(page);
			model.addAttribute("actionManagermentList", actionManagermentList);
			model.addAttribute("pageData", page);
			
			List<UserInfo> userInfoList = userInfoService.queryUserBySellerId(this.getCurrentUserFranchiseesIdId());
			model.addAttribute("userInfoList", userInfoList);
		}catch(Exception e) {
			logger.error("ActionManagermentController err list", e);
		}
		
		return "actionManagerment/actionManagermentList";
	}
	
	@RequestMapping(value="add",method = RequestMethod.GET)
	public String add(Model model,HttpServletRequest request){
		//获取当前用户所拥有的菜单
		/*List<Menu> menuList = this.getFirstMenuByUserId();
		model.addAttribute("menuList", menuList);*/
		String parentId = request.getParameter("parentId");
		model.addAttribute("parentId", parentId);
		
		return "actionManagerment/actionManagermentForm";
	}
	
	
	@RequestMapping(value="addActionManagerment",method=RequestMethod.POST)
	public String addActionManagerment(Model model, @ModelAttribute("form") ActionManagerment actionManagerment) {
		try{
			if(actionManagerment.getId() == null) {
				if(!StringUtils.isEmpty(actionManagerment.getActionTitle())) {
					actionManagerment.setSellerId(this.getCurrentUserFranchiseesIdId());
					actionManagerment.setExecutorId(this.getCurrentUserId());
					actionManagerment.setExecutorName(this.getCurrentUserName());
					List<Role> roles = roleService.selectRoleByUserId(this.getCurrentUserId());
					if(roles != null) {
						for(int i=0; i<roles.size(); i++) {
							if(roles.get(i).getId() == 41 || roles.get(i).getId() == 37) {
								actionManagerment.setUserInfoId(this.getCurrentUserId());
							}
						}
					}
					actionManagermentService.insertActionManagerment(actionManagerment);
				}
			}else {
				if(!StringUtils.isEmpty(actionManagerment.getActionTitle())) {
					actionManagerment.setSellerId(this.getCurrentUserFranchiseesIdId());
					actionManagerment.setExecutorId(this.getCurrentUserId());
					actionManagerment.setExecutorName(this.getCurrentUserName());
					List<Role> roles = roleService.selectRoleByUserId(this.getCurrentUserId());
					if(roles != null) {
						for(int i=0; i<roles.size(); i++) {
							if(roles.get(i).getId() == 41 || roles.get(i).getId() == 37) {
								actionManagerment.setUserInfoId(this.getCurrentUserId());
							}
						}
					}
					actionManagermentService.updateActionManagermenById(actionManagerment);
				}
			}
		}catch(Exception e) {
			logger.error("MenuController err addMenu", e);
		}
		
		return "redirect:/actionManagerment/list/" + actionManagerment.getParentId();
	}
	
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model,HttpServletRequest request) {
		try{
			String parentId = request.getParameter("parentId");
			model.addAttribute("parentId", parentId);
			//获取当前用户所拥有的菜单
			/*List<Menu> menuList = this.getFirstMenuByUserId();
			model.addAttribute("menuList", menuList);*/
			
			ActionManagerment actionManagerment = actionManagermentService.selectActionManagermentById(id);
			model.addAttribute("actionManagerment", actionManagerment);
		}catch(Exception e) {
			logger.error("ActionManagermentController err updateForm", e);
		}
		
		return "actionManagerment/actionManagermentForm";
	}
	
	
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Long id,Model model,HttpServletRequest request) {
		String parentId = request.getParameter("parentId");
		try{
			//获取当前用户所拥有的菜单
			/*List<Menu> menuList = this.getFirstMenuByUserId();
			model.addAttribute("menuList", menuList);*/
			
			actionManagermentService.deleteActionManagermentbyId(id);
		}catch(Exception e) {
			logger.error("ActionManagermentController err delete", e);
		}
		
		return "redirect:/actionManagerment/list/" + parentId;
	}
	
	@RequestMapping(value = "close/{parentId}", method = RequestMethod.GET)
	public String close(@PathVariable("parentId") Long parentId) {
		return "redirect:/actionManagerment/list/" + parentId;
	}
}
