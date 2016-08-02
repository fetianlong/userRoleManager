package com.yuedi.web.system;

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

import com.yuedi.entity.Menu;
import com.yuedi.entity.MyPage;
import com.yuedi.entity.Permission;
import com.yuedi.service.PermissionService;
import com.yuedi.web.common.SupperController;

@Controller
@RequestMapping(value = "/permission")
public class PermissionController extends SupperController{
	@Autowired
	private PermissionService permissionService;
	
	private static final Logger logger = Logger.getLogger(RoleController.class);
	
	@RequestMapping(value="/list/{parentId}",method = RequestMethod.GET)
	public String list(@PathVariable("parentId") Long parentId,Model model,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber){
		try{
			model.addAttribute("parentId", parentId);
			model.addAttribute("date", this.getCurrentDate());
			//获取当前用户所拥有的菜单
			/*List<Menu> menuList = this.getFirstMenuByUserId();
			model.addAttribute("menuList", menuList);*/
			//获取二级菜单
			List<Menu> towMenuList = this.getTwoMenuByParentId(parentId);
			model.addAttribute("towMenuList", towMenuList);
			
			MyPage<Permission> page = new MyPage<Permission>();
			page.setNumber(pageNumber);
			model.addAttribute("searchParams","parentId="+parentId);
			List<Permission> permissionList = permissionService.selectPermissionLimit(page);
			model.addAttribute("permissionList", permissionList);
			model.addAttribute("pageData", page);
		}catch(Exception e) {
			logger.error("PermissionController err list", e);
		}
		
		return "permission/permissionList";
	}
	
	@RequestMapping(value="add",method = RequestMethod.GET)
	public String add(Model model,HttpServletRequest request){
		//获取当前用户所拥有的菜单
		/*List<Menu> menuList = this.getFirstMenuByUserId();
		model.addAttribute("menuList", menuList);*/
		String parentId = request.getParameter("parentId");
		model.addAttribute("parentId", parentId);
		
		return "permission/permissionForm";
	}
	
	@RequestMapping(value="addPermission",method=RequestMethod.POST)
	public String addPermission(Model model, @ModelAttribute("form") Permission permission) {
		try{
			if(permission.getId() == null) {
				if(!StringUtils.isEmpty(permission.getName())) {
					permissionService.addPermission(permission);
				}
			}else {
				if(!StringUtils.isEmpty(permission.getName())) {
					permissionService.updateByPrimaryKey(permission);
				}
			}
		}catch(Exception e) {
			logger.error("PermissionController err addPermission", e);
		}
		
		return "redirect:/permission/list/" + permission.getParentId();
	}
	
	@RequestMapping(value = "close/{parentId}", method = RequestMethod.GET)
	public String close(@PathVariable("parentId") Long parentId) {
		return "redirect:/permission/list/" + parentId;
	}
	
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model,HttpServletRequest request) {
		try{
			String parentId = request.getParameter("parentId");
			model.addAttribute("parentId", parentId);
			//获取当前用户所拥有的菜单
			/*List<Menu> menuList = this.getFirstMenuByUserId();
			model.addAttribute("menuList", menuList);*/
			
			Permission permission = permissionService.getPermissionById(id);
			model.addAttribute("permission", permission);
		}catch(Exception e) {
			logger.error("PermissionController err updateForm", e);
		}
		
		return "permission/permissionForm";
	}
	
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Long id,Model model,HttpServletRequest request) {
		String parentId = request.getParameter("parentId");
		try{
			//获取当前用户所拥有的菜单
			/*List<Menu> menuList = this.getFirstMenuByUserId();
			model.addAttribute("menuList", menuList);*/
			permissionService.deletePermissionById(id);
		}catch(Exception e) {
			logger.error("PermissionController err delete", e);
		}
		
		return "redirect:/permission/list/" + parentId;
	}
}
