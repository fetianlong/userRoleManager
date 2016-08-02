package com.yuedi.web.fm;

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
import org.springside.modules.web.MediaTypes;

import com.yuedi.entity.Menu;
import com.yuedi.entity.MyPage;
import com.yuedi.entity.OrderContent;
import com.yuedi.entity.OrderType;
import com.yuedi.entity.Seller;
import com.yuedi.entity.fm.DeliveryResource;
import com.yuedi.service.fm.DeliveryResourceService;
import com.yuedi.util.Constants;
import com.yuedi.util.ResourceType;
import com.yuedi.web.common.SupperController;
@Controller
@RequestMapping(value="/deliveryResource")
public class DeliveryResourceController extends SupperController {
	@Autowired
	private DeliveryResourceService deliveryResourceService;

	private static final Logger logger = Logger
			.getLogger(DeliveryResourceController.class);

	// 根据测评内容ID查找关联的资源
	@RequestMapping(value = "/listSelectDeliveryResource/{parentId}", method = RequestMethod.GET)
	public String listSelectdeliveryResource(
			@PathVariable("parentId") Long parentId,Model model,HttpServletRequest request,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber) {
		String deliveryId = request.getParameter("deliveryId");
		try{
			MyPage<DeliveryResource> page = new MyPage<DeliveryResource>();
			page.setNumber(pageNumber);
			page.getParams().put("deliveryId", deliveryId);
			List<DeliveryResource> deliveryResourceSelectList = deliveryResourceService.selectResourcebyOrderId(page);
			
			String connenctDeliveryName = request.getParameter("connenctDeliveryName");
			String cmName = request.getParameter("cmName");
			model.addAttribute("connenctDeliveryName", connenctDeliveryName);
			model.addAttribute("cmName", cmName);
			
			model.addAttribute("parentId", parentId);
			model.addAttribute("deliveryId", deliveryId);
			model.addAttribute("date", this.getCurrentDate());
			model.addAttribute("deliveryResourceSelectList", deliveryResourceSelectList);
			model.addAttribute("pageData", page);
		} catch (Exception e) {
			logger.error("orderContentController err list", e);
			e.printStackTrace();
		}
	
		return "deliveryResource/deliveryResourceSelectList";
	}
	/*
	 * 前往新增功能，初始化数据
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model, HttpServletRequest request) {

		String parentId = request.getParameter("parentId");
		String deliveryId=request.getParameter("deliveryId");
		String connenctDeliveryName = request.getParameter("connenctDeliveryName");	//第几产程
		String cmName = request.getParameter("cmName");	//公分
		
		model.addAttribute("parentId", parentId);
		model.addAttribute("deliveryId",deliveryId);
		model.addAttribute("connenctDeliveryName",connenctDeliveryName);
		model.addAttribute("cmName",cmName);
		return "deliveryResource/deliveryResourceNoSelectList";
	}

	//新增： 查找与该测评内容未被选择关联的资源
	@ResponseBody
	@RequestMapping(value = "listNoSelectDeliveryResource", method = RequestMethod.GET,produces="application/json;charset=UTF-8")
	public List<DeliveryResource> listNoSelectDeliveryResource(Model model,
		DeliveryResource or,HttpServletResponse response,HttpServletRequest request,
		@RequestParam(value = "page", defaultValue = "1") int pageNumber) {
		String parentId = request.getParameter("parentId");
		String connenctDeliveryName = request.getParameter("connenctDeliveryName");
		String cmName = request.getParameter("cmName");
		
		model.addAttribute("parentId", parentId);
		model.addAttribute("connenctDeliveryName", connenctDeliveryName);
		model.addAttribute("cmName", cmName);
		
		//该测评内容未被选择关联的资源
		List<DeliveryResource> deliveryResourcelist = deliveryResourceService.selectNoResourcebyOrderId(or);
		return deliveryResourcelist;
		
	}
	
	/**
	 * 加载APP资源信息
	 * @param model
	 * @param deliveryResource
	 * @param response
	 * @param request
	 * @param pageNumber
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年12月31日 下午7:49:14
	 */
	@ResponseBody
	@RequestMapping(value = "selectAppResourceInfo", method = RequestMethod.GET,produces="application/json;charset=UTF-8")
	public List<DeliveryResource> selectAppResourceInfo(Model model,
			DeliveryResource deliveryResource,HttpServletResponse response,HttpServletRequest request,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber) {
		String parentId = request.getParameter("parentId");
		String connenctDeliveryName = request.getParameter("connenctDeliveryName");
		String cmName = request.getParameter("cmName");
		
		model.addAttribute("parentId", parentId);
		model.addAttribute("connenctDeliveryName", connenctDeliveryName);
		model.addAttribute("cmName", cmName);
		
		//该测评内容未被选择关联的资源
		List<DeliveryResource> deliveryResourcelist = deliveryResourceService.selectAppResourceInfo(deliveryResource);
		return deliveryResourcelist;
		
	}

	// 选择
	@ResponseBody
	@RequestMapping(value="/select", method = RequestMethod.GET,produces=MediaTypes.JSON_UTF_8)
	public String select(HttpServletResponse response,DeliveryResource or) throws IOException {
		System.out.println(or.getResourceId());
		System.out.println(or.getDeliveryId());
		deliveryResourceService.insertDeliveryResource(or);
		return "{\"success\":true,\"message\":\"成功\"}";
	}

	// 删除
	@RequestMapping(value = "delete/{deliveryResourceId}", method = RequestMethod.GET)
	public String  delete(@PathVariable("deliveryResourceId") Integer deliveryResourceId, Model model,
			DeliveryResource or,HttpServletRequest request,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber) {
		String parentId = request.getParameter("parentId");
		String deliveryId=request.getParameter("deliveryId");
		String connenctDeliveryName = request.getParameter("connenctDeliveryName");
		String cmName = request.getParameter("cmName");
		
		model.addAttribute("connenctDeliveryName", connenctDeliveryName);
		model.addAttribute("cmName", cmName);
		model.addAttribute("parentId",parentId);
		model.addAttribute("deliveryId", deliveryId);
		deliveryResourceService.deleteDeliveryResourceByPrimaryKey(deliveryResourceId);
		return "redirect:/deliveryResource/listSelectDeliveryResource/" + deliveryId;
	}
	//加载系列资源
	@ResponseBody
	@RequestMapping(value="selectSeriesByBrandResourceType", method = RequestMethod.GET,produces=MediaTypes.JSON_UTF_8)
	public List<DeliveryResource>  selectSeriesByBrandResourceType( Model model,
			DeliveryResource or,HttpServletRequest request,
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
		List<DeliveryResource> seriesList = deliveryResourceService.selectSeriesByBrandCategory(or);
		return  seriesList;
	}
}
