package com.yuedi.web.orderContent;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yuedi.entity.Announcement;
import com.yuedi.entity.Menu;
import com.yuedi.entity.MyPage;
import com.yuedi.entity.OrderContent;
import com.yuedi.entity.OrderType;
import com.yuedi.entity.Role;
import com.yuedi.service.OrderContentService;
import com.yuedi.service.OrderTypeService;
import com.yuedi.web.common.SupperController;

@Controller
@RequestMapping(value = "/orderContent")
public class OrderContentController extends SupperController {

	@Autowired
	private OrderContentService orderContentService;
	@Autowired
	private OrderTypeService orderTypeService;

	private static final Logger logger = Logger
			.getLogger(OrderContentController.class);

	/*
	 * 获取订制内容列表
	 */
	@RequestMapping(value = "list/{parentId}", method = RequestMethod.GET)
	public String list(@PathVariable("parentId") Long parentId, HttpServletRequest request,Model model,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber) {

		try {
			model.addAttribute("parentId", parentId);
			model.addAttribute("date", this.getCurrentDate());
			// 获取当前用户所拥有的菜单
			/*List<Menu> menuList = this.getFirstMenuByUserId();
			model.addAttribute("menuList", menuList);*/
			// 获取二级菜单
			List<Menu> towMenuList = this.getTwoMenuByParentId(parentId);
			model.addAttribute("towMenuList", towMenuList);

			MyPage<OrderContent> page = new MyPage<OrderContent>();
			String illustrate = request.getParameter("illustrate");
			model.addAttribute("illustrate", illustrate);
			if(illustrate != null && !"".equals(illustrate)) {
				page.getParams().put("illustrate", illustrate+ "%");
			}
			String orderTypeId = request.getParameter("orderTypeId");
			model.addAttribute("orderTypeId", orderTypeId);
			if(orderTypeId != null && !"".equals(orderTypeId)) {
				page.getParams().put("orderTypeId", orderTypeId);
			}
			page.setNumber(pageNumber);
			List<OrderContent> orderContentList = orderContentService
					.selectOrderContentLimit(page);
			model.addAttribute("orderContentList", orderContentList);
			model.addAttribute("pageData", page);
			List<OrderType> orderTypeList=orderTypeService.selectAllOrderType();
			model.addAttribute("orderTypeList", orderTypeList);
		} catch (Exception e) {
			logger.error("orderContentController err list", e);
			e.printStackTrace();
		}

		return "orderContent/orderContentList";
	}
	/*
	 * 前往新增功能，初始化数据
	 */
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(Model model, HttpServletRequest request) {

		// 获取当前用户所拥有的菜单
		/*List<Menu> menuList = this.getFirstMenuByUserId();
		model.addAttribute("menuList", menuList);*/
		String parentId = request.getParameter("parentId");
		model.addAttribute("parentId", parentId);
		List<OrderType> orderTypeList=orderTypeService.selectAllOrderType();
		model.addAttribute("orderTypeList", orderTypeList);
		return "orderContent/orderContentForm";
	}
	/*
	 * 新增订制内容
	 */
	@RequestMapping(value = "/addOrderContent", method = RequestMethod.POST)
	public String addAnnouncement(Model model,@ModelAttribute("form") OrderContent orderContent,HttpServletRequest request) {
		String parentId = request.getParameter("parentId");
		orderContent.setIsDel(false);
		orderContent.setCtime(new Date());
		try {
			
			if(orderContent.getId()==null){
				orderContentService.insertOrderContent(orderContent);
			}else{
				orderContentService.updateOrderContentByPrimaryKey(orderContent);
			}
			
		} catch (Exception e) {
			logger.error("MenuController err addMenu", e);
			e.printStackTrace();
		}

		return "redirect:/orderContent/list/" + parentId;
	}
	/*
	 * 前往编辑功能，初始化数据
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Integer id, Model model,
			HttpServletRequest request) {

		try {
			String parentId = request.getParameter("parentId");
			model.addAttribute("parentId", parentId);
			// 获取当前用户所拥有的菜单
			/*List<Menu> menuList = this.getFirstMenuByUserId();
			model.addAttribute("menuList", menuList);*/
			OrderContent orderContent = orderContentService
					.selectOrderContentByPrimaryKey(id);
			model.addAttribute("orderContent", orderContent);
			List<OrderType> orderTypeList=orderTypeService.selectAllOrderType();
			model.addAttribute("orderTypeList", orderTypeList);
		} catch (Exception e) {
			logger.error("orderContentController err updateForm", e);
			e.printStackTrace();
		}

		return "orderContent/orderContentForm";
	}
	/*
	 * 删除订制内容
	 */
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Integer id, Model model,
			HttpServletRequest request) {
		String parentId = request.getParameter("parentId");
		try {
			// 获取当前用户所拥有的菜单
			/*List<Menu> menuList = this.getFirstMenuByUserId();
			model.addAttribute("menuList", menuList);*/
			orderContentService.deleteOrderContentByPrimaryKey(id);
		} catch (Exception e) {
			logger.error("OrderContentController err delete", e);
		}

		return "redirect:/orderContent/list/" + parentId;
	}
}
