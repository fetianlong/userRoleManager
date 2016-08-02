package com.yuedi.web.system;

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

import com.yuedi.entity.Menu;
import com.yuedi.entity.MyPage;
import com.yuedi.entity.Permission;
import com.yuedi.entity.Role;
import com.yuedi.entity.RolePermission;
import com.yuedi.entity.RoleUserInfo;
import com.yuedi.entity.UserInfo;
import com.yuedi.service.MenuService;
import com.yuedi.service.PermissionService;
import com.yuedi.service.RoleService;
import com.yuedi.service.UserInfoService;
import com.yuedi.web.common.SupperController;

@Controller
@RequestMapping(value = "/role")
public class RoleController extends SupperController{
	
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private MenuService menuService;
	
	private static final Logger logger = Logger.getLogger(RoleController.class);
	
	@RequestMapping(value="/list/{parentId}",method = RequestMethod.GET)
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
			
			MyPage<Role> page = new MyPage<Role>();
			page.setNumber(pageNumber);
			
			String name = request.getParameter("name");
			model.addAttribute("name", name);
			if(name != null && !"".equals(name)) {
				page.getParams().put("name", name+ "%");
			}
			
			String state = request.getParameter("state");
			model.addAttribute("state", state);
			if(state != null && !"".equals(state)) {
				page.getParams().put("state", state);
			}
			List<Role> roleList = roleService.selectRoleLimit(page);
			
			String titleName = "角色管理";
			if(!StringUtils.isEmpty(request.getParameter("titleName"))){
				titleName = request.getParameter("titleName");
			}
			model.addAttribute("titleName", titleName);
			model.addAttribute("searchParams","parentId="+parentId + "&state=" + state);
			model.addAttribute("roleList", roleList);
			model.addAttribute("pageData", page);
		}catch(Exception e) {
			logger.error("RoleController err list", e);
		}
		
		return "role/roleList";
	}
	
	@RequestMapping(value="add",method = RequestMethod.GET)
	public String add(Model model,HttpServletRequest request){
		//获取当前用户所拥有的菜单
		/*List<Menu> menuList = this.getFirstMenuByUserId();
		model.addAttribute("menuList", menuList);*/
		String parentId = request.getParameter("parentId");
		model.addAttribute("parentId", parentId);
		return "role/roleForm";
	}
	
	@RequestMapping(value="addRole",method=RequestMethod.POST)
	public String addRole(Model model, @ModelAttribute("form") Role role) {
		try{
			if(role.getId() == null) {
				if(!StringUtils.isEmpty(role.getName())) {
					role.setCreaterId(this.getCurrentUserId());
					role.setCreateDateTime(new Date());
					String userName = this.getCurrentUserName();
					role.setCreaterName(userName);
					role.setIsDeleteFlag(false);
					roleService.addRole(role);
				}
			}else {
				if(!StringUtils.isEmpty(role.getName())) {
					role.setCreaterId(this.getCurrentUserId());
					role.setCreateDateTime(new Date());
					role.setCreaterName(this.getCurrentUserName());
					role.setIsDeleteFlag(false);
					roleService.updateRole(role);
				}
			}
		}catch(Exception e) {
			logger.error("UserInfoController err addRole", e);
		}
		
		return "redirect:/role/list/" + role.getParentId();
	}
	
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model,HttpServletRequest request) {
		try{
			String parentId = request.getParameter("parentId");
			model.addAttribute("parentId", parentId);
			//获取当前用户所拥有的菜单
			/*List<Menu> menuList = this.getFirstMenuByUserId();
			model.addAttribute("menuList", menuList);*/
			Role role = roleService.getRoleById(id);
			model.addAttribute("role", role);
		}catch(Exception e) {
			logger.error("RoleController err updateForm", e);
		}
		
		return "role/roleForm";
	}
	
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Long id,Model model,HttpServletRequest request) {
		String parentId = request.getParameter("parentId");
		try{
			//获取当前用户所拥有的菜单
			/*List<Menu> menuList = this.getFirstMenuByUserId();
			model.addAttribute("menuList", menuList);*/
			roleService.deleteRoleById(id);
		}catch(Exception e) {
			logger.error("RoleController err delete", e);
		}
		
		return "redirect:/role/list/" + parentId;
	}
	
	@RequestMapping(value = "close/{parentId}", method = RequestMethod.GET)
	public String close(@PathVariable("parentId") Long parentId) {
		return "redirect:/role/list/" + parentId;
	}
	
	@RequestMapping(value="assignUserInfo/{id}",method=RequestMethod.GET)
	public String assignUserInfo(@PathVariable("id") Long id,Model model,HttpServletRequest request) {
		String parentId = request.getParameter("parentId");
		model.addAttribute("parentId", parentId);
		model.addAttribute("roleId", id);
		//获取当前用户所拥有的菜单
		/*List<Menu> menuList = this.getFirstMenuByUserId();
		model.addAttribute("menuList", menuList);*/
		List<UserInfo> unassignUserList = userInfoService.selectUnassignUserByRoleId(id);
		model.addAttribute("unassignUserList", unassignUserList);
		List<UserInfo> assignUserList = userInfoService.selectAssignUserByRoleId(id);
		model.addAttribute("assignUserList", assignUserList);
		return "role/assignUserInfo";
	}
	
	@RequestMapping(value="assignButton/{id}",method=RequestMethod.GET)
	public String assignButton(@PathVariable("id") Long id,Model model,HttpServletRequest request) {
		String parentId = request.getParameter("parentId");
		model.addAttribute("parentId", parentId);
		model.addAttribute("roleId", id);
		//获取当前用户所拥有的菜单
		/*List<Menu> menuList = this.getFirstMenuByUserId();
		model.addAttribute("menuList", menuList);*/
		
		List<Permission> unassignPermissionList = permissionService.selectUnassignButtonByRoleId(id);
		model.addAttribute("unassignPermissionList", unassignPermissionList);
		List<Permission> assignPermissionList = permissionService.selectAssignButtonByRoleId(id);
		model.addAttribute("assignPermissionList", assignPermissionList);
		return "role/assignButton";
	}
	
	@RequestMapping(value="assignUserToRole/{roleId}",method=RequestMethod.GET)
	public String assignUserToRole(@PathVariable("roleId") Long roleId,Model model,HttpServletRequest request) {
		try{
			String userInfoId = request.getParameter("userInfoId");
			String parentId = request.getParameter("parentId");
			model.addAttribute("parentId", parentId);
			//获取当前用户所拥有的菜单
			/*List<Menu> menuList = this.getFirstMenuByUserId();
			model.addAttribute("menuList", menuList);*/
			if (!StringUtils.isEmpty(roleId.toString()) 
					&& !StringUtils.isEmpty(userInfoId)){
				RoleUserInfo roleUserInfo = new RoleUserInfo();
				roleUserInfo.setRoleId(roleId);
				roleUserInfo.setUserInfoId(Long.parseLong(userInfoId));
				roleService.assignUserToRole(roleUserInfo);
				
				List<UserInfo> unassignUserList = userInfoService.selectUnassignUserByRoleId(roleId);
				model.addAttribute("unassignUserList", unassignUserList);
				List<UserInfo> assignUserList = userInfoService.selectAssignUserByRoleId(roleId);
				model.addAttribute("assignUserList", assignUserList);
			}
		}catch(Exception e){
			logger.error("RoleController err assignUserToRole", e);
		}
		
		return "role/assignUserInfo";
	}
	
	@RequestMapping(value="assignPermissionToRole/{roleId}",method=RequestMethod.GET)
	public String assignPermissionToRole(@PathVariable("roleId") Long roleId,Model model,HttpServletRequest request) {
		try{
			String permissionId = request.getParameter("permissionId");
			String parentId = request.getParameter("parentId");
			model.addAttribute("parentId", parentId);
			//获取当前用户所拥有的菜单
			/*List<Menu> menuList = this.getFirstMenuByUserId();
			model.addAttribute("menuList", menuList);*/
			if (!StringUtils.isEmpty(roleId.toString()) 
					&& !StringUtils.isEmpty(permissionId)){
				RolePermission rolePermission = new RolePermission();
				rolePermission.setRoleId(roleId);
				rolePermission.setPermissionId(Long.parseLong(permissionId));
				permissionService.assignPermissionToRole(rolePermission);
				
				List<Permission> unassignPermissionList = permissionService.selectUnassignButtonByRoleId(roleId);
				model.addAttribute("unassignPermissionList", unassignPermissionList);
				List<Permission> assignPermissionList = permissionService.selectAssignButtonByRoleId(roleId);
				model.addAttribute("assignPermissionList", assignPermissionList);
			}
		}catch(Exception e){
			logger.error("RoleController err assignUserToRole", e);
		}
		
		return "role/assignButton";
	}
	
	@RequestMapping(value = "removeUserFromRole/{roleUserInfoId}")
	public String removeUserFromRole(@PathVariable("roleUserInfoId") Long roleUserInfoId,Model model,HttpServletRequest request) {
		String roleId = request.getParameter("roleId");
		model.addAttribute("roleId", roleId);
		String parentId = request.getParameter("parentId");
		model.addAttribute("parentId", parentId);
		//获取当前用户所拥有的菜单
		/*List<Menu> menuList = this.getFirstMenuByUserId();
		model.addAttribute("menuList", menuList);*/
		try{
			if (!StringUtils.isEmpty(roleId.toString())){
				roleService.removeUserFromRole(roleUserInfoId);
				
				List<UserInfo> unassignUserList = userInfoService.selectUnassignUserByRoleId(Long.parseLong(roleId));
				model.addAttribute("unassignUserList", unassignUserList);
				List<UserInfo> assignUserList = userInfoService.selectAssignUserByRoleId(Long.parseLong(roleId));
				model.addAttribute("assignUserList", assignUserList);
			}
		}catch(Exception e) {
			logger.error("RoleController err removeUserFromRole", e);
		}
		
		return "role/assignUserInfo";
	}
	
	@RequestMapping(value = "removePermissionFromRole/{rolePermissionId}")
	public String removePermissionFromRole(@PathVariable("rolePermissionId") Long rolePermissionId,Model model,HttpServletRequest request) {
		String roleId = request.getParameter("roleId");
		model.addAttribute("roleId", roleId);
		String parentId = request.getParameter("parentId");
		model.addAttribute("parentId", parentId);
		//获取当前用户所拥有的菜单
		/*List<Menu> menuList = this.getFirstMenuByUserId();
		model.addAttribute("menuList", menuList);*/
		try{
			if (!StringUtils.isEmpty(roleId.toString())){
				permissionService.removePermissionFromRole(rolePermissionId);
				
				List<Permission> unassignPermissionList = permissionService.selectUnassignButtonByRoleId(Long.parseLong(roleId));
				model.addAttribute("unassignPermissionList", unassignPermissionList);
				List<Permission> assignPermissionList = permissionService.selectAssignButtonByRoleId(Long.parseLong(roleId));
				model.addAttribute("assignPermissionList", assignPermissionList);
			}
		}catch(Exception e) {
			logger.error("RoleController err removeUserFromRole", e);
		}
		
		return "role/assignButton";
	}
	
	@RequestMapping(value="assignMenu/{id}",method=RequestMethod.GET)
	public String assignMenu(@PathVariable("id") Long id,Model model,HttpServletRequest request) {
		String parentId = request.getParameter("parentId");
		model.addAttribute("parentId", parentId);
		model.addAttribute("roleId", id);
		//获取当前用户所拥有的菜单
		/*List<Menu> menuList = this.getFirstMenuByUserId();
		model.addAttribute("menuList", menuList);*/
		
		return "role/assignMenu";
	}
	
	@ResponseBody
	@RequestMapping(value="assignRoleMenu",method=RequestMethod.POST)
	public String assignRoleMenu(Model model, HttpServletRequest request){
		String idList = request.getParameter("idList");	//菜单ID
		String roleId = request.getParameter("roleId");
		try {
			roleService.addRoleMenu(idList,roleId.toString());
			return "{\"success\":true,\"message\":\"成功\"}";
		} catch (Exception e) {
			logger.error("RoleController err assignRoleMenu", e);
			return "{\"success\":true,\"message\":\"失败\"}";
		}
	}
}
