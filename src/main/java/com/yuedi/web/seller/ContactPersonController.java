package com.yuedi.web.seller;

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

import com.yuedi.entity.ContactPerson;
import com.yuedi.entity.Menu;
import com.yuedi.entity.MyPage;
import com.yuedi.entity.Role;
import com.yuedi.entity.Seller;
import com.yuedi.service.ContactPersonService;
import com.yuedi.service.RoleService;
import com.yuedi.service.SellerService;
import com.yuedi.web.common.SupperController;
import com.yuedi.web.system.DictionaryController;

@Controller
@RequestMapping(value = "/contactPerson")
public class ContactPersonController extends SupperController{
	@Autowired
	private ContactPersonService contactPersonService;
	@Autowired
	private SellerService sellerService;
	@Autowired
	private RoleService roleService;
	
	private static final Logger logger = Logger.getLogger(DictionaryController.class);
	
	/**
	 * 分页查询所有联系人
	 * @param parentId
	 * @param model
	 * @param pageNumber
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/list/{parentId}",method = RequestMethod.GET)
	public String list(@PathVariable("parentId") Long parentId,Model model,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,HttpServletRequest request){
		try{
			model.addAttribute("parentId", parentId);
			model.addAttribute("date", this.getCurrentDate());
			//获取当前用户所拥有的菜单
			/*List<Menu> menuList = this.getFirstMenuByUserId();
			model.addAttribute("menuList", menuList);*/
			//获取二级菜单
			List<Menu> towMenuList = this.getTwoMenuByParentId(parentId);
			model.addAttribute("towMenuList", towMenuList);
			
			MyPage<ContactPerson> page = new MyPage<ContactPerson>();
			page.setNumber(pageNumber);
			//分页查询设定分页查询参数
			model.addAttribute("searchParams","parentId="+parentId);
			//判断业务员
			List<Role> roles = roleService.selectRoleByUserId(this.getCurrentUserId());
			if(roles != null) {
				for(int i=0; i<roles.size(); i++) {
					if(roles.get(i).getId() == 41 || roles.get(i).getId() == 37) {
						page.getParams().put("userInfoId", this.getCurrentUserId());
					}
				}
			}
			String fullName = request.getParameter("fullName");
			model.addAttribute("fullName", fullName);
			if(fullName != null && !"".equals(fullName)) {
				page.getParams().put("fullName", fullName +"%");
			}
			String position = request.getParameter("position");
			model.addAttribute("position", position);
			if(position != null && !"".equals(position)) {
				page.getParams().put("position", position +"%");
			}
			String telephone = request.getParameter("telephone");
			model.addAttribute("telephone", telephone);
			if(telephone != null && !"".equals(telephone)) {
				page.getParams().put("telephone", telephone +"%");
			}
			page.getParams().put("sellerId", this.getCurrentUserFranchiseesIdId());
			
			List<ContactPerson> contactPersonList = contactPersonService.findListContactPersonByLimit(page);
			model.addAttribute("contactPersonList", contactPersonList);
			model.addAttribute("pageData", page);
		}catch(Exception e) {
			logger.error("ContactPersonController err list", e);
		}
		
		return "contactPerson/contactPersonList";
	}
	
	/**
	 * 跳到增加页面
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="add",method = RequestMethod.GET)
	public String add(Model model,HttpServletRequest request){
		//获取当前用户所拥有的菜单
		/*List<Menu> menuList = this.getFirstMenuByUserId();
		model.addAttribute("menuList", menuList);*/
		String parentId = request.getParameter("parentId");
		model.addAttribute("parentId", parentId);
		
		//查询所有加盟商
		Seller seller = new Seller();
		seller.setSelledcnt(0);
		seller.setSellersign(0);
		List<Seller> sellerList = sellerService.selectAllSeller(seller);
		model.addAttribute("sellerList", sellerList);
		
		return "contactPerson/contactPersonForm";
	}
	/**
	 * 增加和修改功能
	 * @param model
	 * @param contactPerson
	 * @return
	 */
	@RequestMapping(value="addContactPerson",method=RequestMethod.POST)
	public String addContactPerson(Model model, @ModelAttribute("form") ContactPerson contactPerson) {
		try{
			//增加功能
			if(contactPerson.getId() == null) {
				if(!StringUtils.isEmpty(contactPerson.getFullName())) {
					contactPerson.setCreateTime(new Date());
					contactPerson.setCreateUser(this.getCurrentUserId());
					
					List<Role> roles = roleService.selectRoleByUserId(this.getCurrentUserId());
					if(roles != null) {
						for(int i=0; i<roles.size(); i++) {
							if(roles.get(i).getId() == 41 || roles.get(i).getId() == 37) {
								contactPerson.setUserInfoId(this.getCurrentUserId());
							}
						}
					}
					
					contactPersonService.insertContactPerson(contactPerson);
				}
			//修改功能
			}else {
				if(!StringUtils.isEmpty(contactPerson.getFullName())) {
					contactPerson.setUpdateTime(new Date());
					contactPerson.setCreateUser(this.getCurrentUserId());
					
					contactPersonService.updateContactPerson(contactPerson);
				}
			}
		}catch(Exception e) {
			logger.error("ContactPersonController err addContactPerson", e);
		}
		
		return "redirect:/contactPerson/list/" + contactPerson.getParentId();
	}
	/**
	 * 关闭页面功能
	 * @param parentId
	 * @return
	 */
	@RequestMapping(value = "close/{parentId}", method = RequestMethod.GET)
	public String close(@PathVariable("parentId") Long parentId) {
		return "redirect:/contactPerson/list/" + parentId;
	}
	/**
	 * 跳到修改页面
	 * @param id
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model,HttpServletRequest request) {
		try{
			String parentId = request.getParameter("parentId");
			model.addAttribute("parentId", parentId);
			//获取当前用户所拥有的菜单
			/*List<Menu> menuList = this.getFirstMenuByUserId();
			model.addAttribute("menuList", menuList);*/
			
			ContactPerson contactPerson = contactPersonService.findContactPersonById(id);
			model.addAttribute("contactPerson", contactPerson);
			
			Seller seller = new Seller();
			seller.setSelledcnt(0);
			seller.setSellersign(0);
			List<Seller> sellerList = sellerService.selectAllSeller(seller);
			model.addAttribute("sellerList", sellerList);
		}catch(Exception e) {
			logger.error("ContactPersonController err updateForm", e);
		}
		
		return "contactPerson/contactPersonForm";
	}
	/**
	 * 删除功能
	 * @param id
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Long id,Model model,HttpServletRequest request) {
		String parentId = request.getParameter("parentId");
		try{
			//获取当前用户所拥有的菜单
			/*List<Menu> menuList = this.getFirstMenuByUserId();
			model.addAttribute("menuList", menuList);*/
			contactPersonService.deleteContactPersonById(id);
		}catch(Exception e) {
			logger.error("ContactPersonController err delete", e);
		}
		
		return "redirect:/contactPerson/list/" + parentId;
	}
}
