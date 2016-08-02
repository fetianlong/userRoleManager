package com.yuedi.web.orderResource;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
import com.yuedi.entity.OrderContent;
import com.yuedi.entity.OrderResource;
import com.yuedi.entity.OrderType;
import com.yuedi.entity.Seller;
import com.yuedi.service.OrderResourceService;
import com.yuedi.util.Constants;
import com.yuedi.util.ResourceType;
import com.yuedi.web.common.SupperController;
@Controller
@RequestMapping(value="/orderResource")
public class OrderResourceController extends SupperController {
	@Autowired
	private OrderResourceService orderResourceService;

	private static final Logger logger = Logger
			.getLogger(OrderResourceController.class);

	// 根据测评内容ID查找关联的资源
	@RequestMapping(value = "/listSelectOrderResource/{parentId}", method = RequestMethod.GET)
	public String listSelectOrderResource(
			@PathVariable("parentId") Long parentId,Model model,HttpServletRequest request,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber) {
		String orderContentId = request.getParameter("orderContentId");
		try{
		model.addAttribute("parentId", parentId);
		model.addAttribute("date", this.getCurrentDate());
		model.addAttribute("orderContentId",orderContentId);
		// 获取当前用户所拥有的菜单
		/*List<Menu> menuList = this.getFirstMenuByUserId();
		model.addAttribute("menuList", menuList);*/
		// 获取二级菜单
		List<Menu> towMenuList = this.getTwoMenuByParentId(parentId);
		model.addAttribute("towMenuList", towMenuList);
		MyPage<OrderResource> page = new MyPage<OrderResource>();
		page.setNumber(pageNumber);
		page.getParams().put("orderContentId", orderContentId);
		model.addAttribute("searchParams","orderContentId="+orderContentId);
		List<OrderResource> orderResourceSelectList = orderResourceService
				.selectResourcebyOrderId(page);
		model.addAttribute("orderResourceSelectList", orderResourceSelectList);
		model.addAttribute("pageData", page);
	} catch (Exception e) {
		logger.error("orderContentController err list", e);
		e.printStackTrace();
	}

	return "orderResource/orderResourceSelectList";
	}
	/*
	 * 前往新增功能，初始化数据
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model, HttpServletRequest request) {

		// 获取当前用户所拥有的菜单
		/*List<Menu> menuList = this.getFirstMenuByUserId();
		model.addAttribute("menuList", menuList);*/
		String parentId = request.getParameter("parentId");
		String orderContentId=request.getParameter("orderContentId");
		model.addAttribute("parentId", parentId);
		model.addAttribute("orderContentId",orderContentId);
		return "orderResource/orderResourceNoSelectList";
	}

	//新增： 查找与该测评内容未被选择关联的资源
	@ResponseBody
	@RequestMapping(value = "listNoSelectOrderResource", method = RequestMethod.GET,produces="application/json;charset=UTF-8")
	public List<OrderResource> listNoSelectOrderResource(Model model,
			OrderResource or,HttpServletResponse response,HttpServletRequest request,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber) {
			String parentId = request.getParameter("parentId");
			model.addAttribute("parentId", parentId);
			MyPage<OrderResource> page = new MyPage<OrderResource>();
//			List<OrderResource> selectList=orderResourceService.allSelectResource(or);
//			page.setNumber(pageNumber);
//			page.getParams().put("seriesId", or.getSeriesId());
//			page.getParams().put("resourceType", or.getResourceType());
//			page.getParams().put("brandId",or.getBrandId());
//			page.getParams().put("selectList",selectList);
			//该测评内容未被选择关联的资源
			List<OrderResource> orderResourcelist = orderResourceService
					.selectNoResourcebyOrderId(or);
			//model.addAttribute("pageData", page);
			//model.addAttribute("searchParams","orderContentId="+or.getOrderId());
			//model.addAttribute("orderContentId", or.getOrderId());
			return orderResourcelist;
		
	}

	// 选择
	@ResponseBody
	@RequestMapping(value="/select", method = RequestMethod.GET,produces="application/json;charset=UTF-8")
	public String select(HttpServletResponse response,OrderResource or) throws IOException {
		System.out.println(or.getResourceId());
		orderResourceService.insertOrderResource(or);
		return "{\"success\":true,\"message\":\"成功\"}";
	}

	// 删除
	@RequestMapping(value = "delete/{orderResourceId}", method = RequestMethod.GET)
	public String  delete(@PathVariable("orderResourceId") Integer orderResourceId, Model model,
			OrderResource or,HttpServletRequest request,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber) {
		String parentId = request.getParameter("parentId");
		String orderContentId=request.getParameter("orderContentId");
		model.addAttribute("parentId",parentId);
		model.addAttribute("orderContentId", orderContentId);
		orderResourceService.deleteOrderResourceByPrimaryKey(orderResourceId);
		return "redirect:/orderResource/listSelectOrderResource/" + orderContentId;
	}
	//加载系列资源
	@ResponseBody
	@RequestMapping(value="selectSeriesByBrandResourceType", method = RequestMethod.GET,produces="application/json;charset=UTF-8")
	public List<OrderResource>  selectSeriesByBrandResourceType( Model model,
			OrderResource or,HttpServletRequest request,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber) {
		String brandId=request.getParameter("brandId");
		Integer resourceType=Integer.parseInt(request.getParameter("resourceType"));
		Integer category = -1;
		if (resourceType == ResourceType.MUSIC.getValue()) {
			category = 3;
		} else if (resourceType == ResourceType.PICKTURE.getValue()) {
			category = 4;
		} else if (resourceType == ResourceType.DOC.getValue()) {
			category = 5;
		} else if (resourceType == ResourceType.VIDEO.getValue()) {
			category = 6;
		}
		or.setBrandId(Long.parseLong(brandId));
		or.setResourceType(category);
		List<OrderResource> seriesList=orderResourceService.selectSeriesByBrandCategory(or);
		return  seriesList;
	}
}
