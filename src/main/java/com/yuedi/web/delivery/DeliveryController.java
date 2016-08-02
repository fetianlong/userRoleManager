package com.yuedi.web.delivery;

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

import com.yuedi.entity.Delivery;
import com.yuedi.entity.Menu;
import com.yuedi.entity.MyPage;
import com.yuedi.entity.OrderType;
import com.yuedi.service.DeliveryService;
import com.yuedi.service.OrderTypeService;
import com.yuedi.web.common.SupperController;

@Controller
@RequestMapping(value ="/delivery")
public class DeliveryController extends SupperController {

	@Autowired
	private DeliveryService deliveryService;
	@Autowired
	private OrderTypeService orderTypeService;

	private static final Logger logger = Logger
			.getLogger(DeliveryController.class);

	/**
	 * 分娩导乐列表
	 * @param parentId
	 * @param request
	 * @param model
	 * @param pageNumber
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年11月30日 下午3:48:51
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

			MyPage<Delivery> page = new MyPage<Delivery>();
			page.setNumber(pageNumber);
			List<Delivery> deliveryList = deliveryService.selectDeliveryLimit(page);
			model.addAttribute("deliveryList", deliveryList);
			
			String titleName = "分娩导乐";
			if(!StringUtils.isEmpty(request.getParameter("titleName"))){
				titleName = request.getParameter("titleName");
			}
			model.addAttribute("titleName", titleName);
			
			model.addAttribute("pageData", page);
		} catch (Exception e) {
			logger.error("deliveryController err list", e);
			e.printStackTrace();
		}

		return "delivery/deliveryList";
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
		return "delivery/deliveryForm";
	}
	
	/*
	 * 新增订制内容
	 */
	@RequestMapping(value = "/addDelivery", method = RequestMethod.POST)
	public String addAnnouncement(Model model,@ModelAttribute("form") Delivery delivery,HttpServletRequest request) {
		String parentId = request.getParameter("parentId");
		try {
			if(delivery.getId()==null){
				delivery.setCtime(new Date());
				delivery.setCreateUserId(getCurrentUserId());
				deliveryService.insertDelivery(delivery);
			}else{
				deliveryService.updateByPrimaryKeySelective(delivery);
			}
			
		} catch (Exception e) {
			logger.error("MenuController err addMenu", e);
			e.printStackTrace();
		}

		return "redirect:/delivery/list/" + parentId;
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
			Delivery delivery = deliveryService
					.selectDeliveryByPrimaryKey(id);
			model.addAttribute("delivery", delivery);
			List<OrderType> orderTypeList=orderTypeService.selectAllOrderType();
			model.addAttribute("orderTypeList", orderTypeList);
		} catch (Exception e) {
			logger.error("deliveryController err updateForm", e);
			e.printStackTrace();
		}

		return "delivery/deliveryForm";
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
			deliveryService.deleteDeliveryByPrimaryKey(id);
		} catch (Exception e) {
			logger.error("deliveryController err delete", e);
		}

		return "redirect:/delivery/list/" + parentId;
	}
}
