package com.yuedi.web.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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

import com.yuedi.entity.Menu;
import com.yuedi.entity.MyPage;
import com.yuedi.service.MenuService;
import com.yuedi.web.common.SupperController;

@Controller
@RequestMapping(value = "/menu")
public class MenuController extends SupperController{
	@Autowired
	private MenuService menuService;
	
	private static final Logger logger = Logger.getLogger(RoleController.class);
	
/*	@RequestMapping(value="/list/{parentId}",method = RequestMethod.GET)
	public String list(@PathVariable("parentId") Long parentId,Model model,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			HttpServletRequest request){
		try{
			model.addAttribute("parentId", parentId);
			model.addAttribute("date", this.getCurrentDate());
			//获取当前用户所拥有的菜单
			List<Menu> menuList = this.getFirstMenuByUserId();
			model.addAttribute("menuList", menuList);
			//获取二级菜单
			List<Menu> towMenuList = this.getTwoMenuByParentId(parentId);
			model.addAttribute("towMenuList", towMenuList);
			
			MyPage<Menu> page = new MyPage<Menu>();
			page.setNumber(pageNumber);
			model.addAttribute("searchParams","parentId="+parentId);
			
			String parentMenuId = request.getParameter("parentMenuId");
			if(parentMenuId != null && !"".equals(parentMenuId)) {
				page.getParams().put("parentId",parentMenuId);
				model.addAttribute("parentMenuId",parentMenuId);
			}
			String name = request.getParameter("name");
			if(name != null && !"".equals(name)) {
				page.getParams().put("name",name +"%");
			}
			List<Menu> menusList = menuService.selectMenuLimit(page);
			model.addAttribute("menusList", menusList);
			List<Menu> parentMenuList = menuService.findParentMenu();
			model.addAttribute("parentMenuList", parentMenuList);
			model.addAttribute("pageData", page);
		}catch(Exception e) {
			logger.error("MenuController err list", e);
		}
		
		return "menu/menuList";
	}*/
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/list/{parentId}",method = RequestMethod.GET)
	public String list(@PathVariable("parentId") Long parentId,Model model,
			HttpServletRequest request){
		try{
			List<Menu> menusList = new ArrayList<Menu>();
			String parentMenuId = request.getParameter("parentMenuId");

			if(!StringUtils.isEmpty(parentMenuId)){
				List<Menu> menus = menuService.getMyMenuByParentId(new Long(parentMenuId));
				List<Menu> list = new ArrayList<Menu>();
				for (int i = 0; i < menus.size(); i++) {
					Menu menu = menus.get(i);
					if (menu.getParentId() == null) {
						menus.get(i).setName("<font style='font-weight:bold;font-style:normal;'>"+menu.getName()+"</font>");
						list.add(menu);
						for (int si = 0; si < menus.size(); si++) {
							Menu submenu = menus.get(si);
							if (menu.getId().equals(submenu.getParentId())) {
								menus.get(si).setName("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+ submenu.getName());
								list.add(submenu);
							}
						}
					}
				}
				menusList = list;
			}else{
				menusList = menuService.selectAllMenu();
			}
			model.addAttribute("parentMenuId", parentMenuId);
			model.addAttribute("parentId", parentId);
			model.addAttribute("menusList", menusList);
			
			List<Menu> parentMenuList = new ArrayList<Menu>();
			/*for (int i = 0; i < menusList.size(); i++) {
				Menu menu = menusList.get(i);
				if (null == menu.getParentId()) {
					parentMenuList.add(menu);
				}
			}*/
			parentMenuList = menuService.findParentMenu();
			model.addAttribute("parentMenuList", parentMenuList);
			
			String titleName = "菜单管理";
			if(!StringUtils.isEmpty(request.getParameter("titleName"))){
				titleName = request.getParameter("titleName");
			}
			model.addAttribute("titleName", titleName);
		}catch(Exception e) {
			logger.error("MenuController err list", e);
		}
		
		return "menu/menuList";
	}
	
	@RequestMapping(value="add",method = RequestMethod.GET)
	public String add(Model model,HttpServletRequest request){
		//获取当前用户所拥有的菜单
		/*List<Menu> menuList = this.getFirstMenuByUserId();
		model.addAttribute("menuList", menuList);*/
		String parentId = request.getParameter("parentId");
		model.addAttribute("parentId", parentId);
		
		List<Menu> parentMenuList = menuService.findParentMenu();
		model.addAttribute("parentMenuList", parentMenuList);
		return "menu/menuForm";
	}
	
	@RequestMapping(value="addMenu",method=RequestMethod.POST)
	public String addMenu(Model model, @ModelAttribute("form") Menu menu) {
		try{
			if(menu.getId() == null) {
				if(!StringUtils.isEmpty(menu.getName())) {
					menu.setCreateDateTime(new Date());
					menu.setCreaterId(this.getCurrentUserId());
					menu.setCreaterName(this.getCurrentUserName());
					menu.setNetWork(2L);
					
					menuService.addMenu(menu);
				}
			}else {
				if(!StringUtils.isEmpty(menu.getName())) {
					menu.setCreateDateTime(new Date());
					menu.setCreaterId(this.getCurrentUserId());
					menu.setCreaterName(this.getCurrentUserName());
					
					menuService.updateMenu(menu);
				}
			}
		}catch(Exception e) {
			logger.error("MenuController err addMenu", e);
		}
		
		return "redirect:/menu/list/" + menu.getParentParamId();
	}
	
	@RequestMapping(value = "close/{parentId}", method = RequestMethod.GET)
	public String close(@PathVariable("parentId") Long parentId) {
		return "redirect:/menu/list/" + parentId;
	}
	
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model,HttpServletRequest request) {
		try{
			String parentId = request.getParameter("parentId");
			model.addAttribute("parentId", parentId);
			//获取当前用户所拥有的菜单
			/*List<Menu> menuList = this.getFirstMenuByUserId();
			model.addAttribute("menuList", menuList);*/
			
			Menu menu = menuService.getMenuById(id);
			model.addAttribute("menu", menu);
			
			List<Menu> parentMenuList = menuService.findParentMenu();
//			List<Menu> parentMenuList = menuService.selectAllMenuIsDeleteFlag();
			model.addAttribute("parentMenuList", parentMenuList);
		}catch(Exception e) {
			logger.error("MenuController err updateForm", e);
		}
		
		return "menu/menuForm";
	}
	
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Long id,Model model,HttpServletRequest request) {
		String parentId = request.getParameter("parentId");
		try{
			//获取当前用户所拥有的菜单
			/*List<Menu> menuList = this.getFirstMenuByUserId();
			model.addAttribute("menuList", menuList);*/
			menuService.deleteMenuById(id);
		}catch(Exception e) {
			logger.error("MenuController err delete", e);
		}
		
		return "redirect:/menu/list/" + parentId;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ResponseBody
	@RequestMapping(value = "queryMenuAll/{roleId}",method = RequestMethod.GET,produces="application/json;charset=UTF-8")
	public List<Object> queryMenuAll(@PathVariable("roleId") Long roleId,Model model) {
		List<Object> menuList = new ArrayList<Object>();
		try {
			List<Menu> menus = menuService.findAllMenuIsDeleteFlag();
			List<Menu> myMenus = menuService.findAllMenuByRole(roleId);	//当前角色所拥有的菜单资源
			
			if (menus != null && menus.size() > 0) {
				for (int i = 0; i < menus.size(); i++) {
					Map menuMap = new HashMap();
					Menu menu = menus.get(i);
					
					menuMap.put("id", menu.getId());
					if (menu.getParentId() == null) {
						menuMap.put("pId", 0);
						menuMap.put("open", true);
					}else{
						menuMap.put("pId", menu.getParentId());
					}
					
					menuMap.put("name", menu.getName());
					
					for (int j = 0; j < myMenus.size(); j++) {
						Menu mymenu = myMenus.get(j);
						if(menu.getId().equals(mymenu.getId())){
							menuMap.put("checked", true);
						}
					}
					menuList.add(menuMap);
				}
			}
		} catch (Exception e) {
			logger.error("MenuController err queryMenuAll", e);
		}
		return menuList;
	}
}
