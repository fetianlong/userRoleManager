package com.yuedi.web.orderType;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.yuedi.entity.OrderType;
import com.yuedi.service.OrderTypeService;
import com.yuedi.web.common.SupperController;

@Controller
@RequestMapping(value = "/orderType")
public class OrderTypeController extends SupperController {

	@Autowired
	private OrderTypeService orderTypeService;

	private static final Logger logger = Logger
			.getLogger(OrderTypeController.class);

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

			MyPage<OrderType> page = new MyPage<OrderType>();
			String name = request.getParameter("name");
			model.addAttribute("name", name);
			if(name != null && !"".equals(name)) {
				page.getParams().put("name", name+ "%");
			}
			
			String type = request.getParameter("type");
			model.addAttribute("type", type);
			if(type != null && !"".equals(type)) {
				page.getParams().put("type", type);
			}
			page.setNumber(pageNumber);
			List<OrderType> orderTypeList = orderTypeService
					.selectOrderTypeLimit(page);
			model.addAttribute("orderTypeList", orderTypeList);
			model.addAttribute("pageData", page);
		} catch (Exception e) {
			logger.error("OrderTypeController err list", e);
			e.printStackTrace();
		}

		return "orderType/orderTypeList";
	}

	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(Model model, HttpServletRequest request) {

		// 获取当前用户所拥有的菜单
		/*List<Menu> menuList = this.getFirstMenuByUserId();
		model.addAttribute("menuList", menuList);*/
		String parentId = request.getParameter("parentId");
		model.addAttribute("parentId", parentId);

		return "orderType/orderTypeForm";
	}

	@RequestMapping(value = "/addOrderType", method = RequestMethod.POST)
	public String addAnnouncement(Model model,@ModelAttribute("form") OrderType orderType,HttpServletRequest request) {
		String parentId = request.getParameter("parentId");
		try {
			
			if(orderType.getId()==null){
				orderTypeService.insertOrderType(orderType);
			}else{
				orderTypeService.updateOrderTypeByPrimaryKey(orderType);
			}
			
		} catch (Exception e) {
			logger.error("MenuController err addMenu", e);
			e.printStackTrace();
		}

		return "redirect:/orderType/list/" + parentId;
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Integer id, Model model,
			HttpServletRequest request) {

		try {
			String parentId = request.getParameter("parentId");
			model.addAttribute("parentId", parentId);
			// 获取当前用户所拥有的菜单
			/*List<Menu> menuList = this.getFirstMenuByUserId();
			model.addAttribute("menuList", menuList);*/
			OrderType orderType = orderTypeService
					.selectOrderTypeByPrimaryKey(id);
			model.addAttribute("orderType", orderType);
		} catch (Exception e) {
			logger.error("OrderTypeController err updateForm", e);
		}

		return "orderType/orderTypeForm";
	}

	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Integer id, Model model,
			HttpServletRequest request) {
		String parentId = request.getParameter("parentId");
		try {
			// 获取当前用户所拥有的菜单
			/*List<Menu> menuList = this.getFirstMenuByUserId();
			model.addAttribute("menuList", menuList);*/
			orderTypeService.deleteOrderTypeByPrimaryKey(id);
		} catch (Exception e) {
			logger.error("AnnouncementController err delete", e);
		}

		return "redirect:/orderType/list/" + parentId;
	}
}
