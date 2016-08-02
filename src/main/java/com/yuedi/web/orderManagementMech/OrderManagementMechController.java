package com.yuedi.web.orderManagementMech;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yuedi.entity.MyPage;
import com.yuedi.entity.OrderManagementMech;
import com.yuedi.entity.Seller;
import com.yuedi.service.OrderManagementMechService;
import com.yuedi.service.SellerService;
import com.yuedi.web.common.SupperController;

@Controller
@RequestMapping(value="/orderManagementMech")
public class OrderManagementMechController extends SupperController {
	
	@Autowired
	OrderManagementMechService orderManagementMechService;
	@Autowired
	private SellerService sellerService;
	
	@RequestMapping(value = "list/{parentId}", method = RequestMethod.GET)
	public String list(@PathVariable("parentId") Long parentId, HttpServletRequest request,Model model,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber) {
		MyPage<OrderManagementMech> page = new MyPage<OrderManagementMech>();
//		String sellerId = request.getParameter("franchiseesId");
		String orderId = request.getParameter("orderId");
		String pid = request.getParameter("pid");
		String orderType = request.getParameter("orderType");
		String orderState = request.getParameter("orderState");
		
		if(!StringUtils.isEmpty(pid)){
			model.addAttribute("pid", pid);
			page.getParams().put("sellerId", pid);
			if(request.getParameter("mypid").equals("3")){
				model.addAttribute("mypid", 3);
			}
		}else{
			Long fid = this.getCurrentUserFranchiseesIdId();
			if(fid != 3){	//普通加盟商查看
				page.getParams().put("sellerId", fid);
				model.addAttribute("pid", fid);
			}else{	//总部查看
	//			String sellerId = request.getParameter("sellerId");
	//			page.getParams().put("pid", sellerId);
				model.addAttribute("mypid", fid);
			}
		}
//		if(!StringUtils.isEmpty(sellerId)){
//			page.getParams().put("sellerId",sellerId);
//		}else{
//			page.getParams().put("sellerId", super.getCurrentUserFranchiseesIdId());
//		}
		if(!StringUtils.isEmpty(orderId)){
			page.getParams().put("orderId", orderId);
			model.addAttribute("orderId", orderId);
		}
		if(!StringUtils.isEmpty(orderType)){
			page.getParams().put("orderType", orderType);
			model.addAttribute("orderType", orderType);
		}
		if(!StringUtils.isEmpty(orderState)){
			page.getParams().put("orderState", orderState);
			model.addAttribute("orderState", orderState);
		}
		
		List<OrderManagementMech> list = orderManagementMechService.selectOrderManagementMechLimit(page);
		
		
		model.addAttribute("orderManagementMechList", list);
		model.addAttribute("pageData", page);
		
		List<Seller> sellerList = sellerService.findAllSellerFranchisees();
		model.addAttribute("sellerList", sellerList);
		
		String titleName = "交易记录";
		if(!StringUtils.isEmpty(request.getParameter("titleName"))){
			titleName = request.getParameter("titleName");
		}
		model.addAttribute("titleName", titleName);
		return "orderManagementMech/orderManagementMechList";
	}
}
