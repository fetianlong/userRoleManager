package com.yuedi.web.messageMoney;

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

import com.yuedi.entity.Menu;
import com.yuedi.entity.MessageMoney;
import com.yuedi.entity.MyPage;
import com.yuedi.entity.Seller;
import com.yuedi.service.MessageMoneyService;
import com.yuedi.service.SellerService;
import com.yuedi.web.common.SupperController;
import com.yuedi.web.login.LoginController;

@Controller
@RequestMapping(value = "/messageMoney")
public class MessageMoneyController extends SupperController {
	private static final Logger logger = Logger.getLogger(LoginController.class);
	@Autowired
	private MessageMoneyService messageMoneyService;
	@Autowired
	private SellerService sellerService;
	/**
	 * 短信充值列表
	 * @param parentId
	 * @param model
	 * @param pageNumber
	 * @param request
	 * @return
	 */
	@RequestMapping(value="list/{parentId}",method = RequestMethod.GET)
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
			
			MyPage<MessageMoney> page = new MyPage<MessageMoney>();
			page.setNumber(pageNumber);
			//非北京总部
			if(this.getCurrentUserFranchiseesIdId() != 3){
				model.addAttribute("sellerId", this.getCurrentUserFranchiseesIdId());
				page.getParams().put("sellerId", this.getCurrentUserFranchiseesIdId());
			//北京总部
			}else{
				String sellerId = request.getParameter("sellerId");
				if(sellerId != null && !"".equals(sellerId)) {
					page.getParams().put("sellerId",sellerId);
					//查询条件机构ID
					model.addAttribute("sellerSelectId", sellerId);
				}
				
				model.addAttribute("sellerId", this.getCurrentUserFranchiseesIdId());
				//加盟商
				Seller seller = new Seller();
				seller.setSelledcnt(1);
				seller.setSellersign(1);
				List<Seller> sellerList = sellerService.selectAllSeller(seller);
				model.addAttribute("sellerList", sellerList);
			}
			
			List<MessageMoney> messageMoneyList = messageMoneyService.findMessageMoneyByLimit(page);
			model.addAttribute("messageMoneyList", messageMoneyList);
			model.addAttribute("pageData", page);
			
			Seller seller = new Seller();
			seller.setSelledcnt(1);
			seller.setSellersign(1);
			List<Seller> sellerList = sellerService.selectAllSeller(seller);
			model.addAttribute("sellerList", sellerList);
		}catch(Exception e) {
			logger.error("MessageMoneyController err list", e);
		}
		
		return "messageMoney/messageMoneyList";
	}
	//跳转到增加页面
	@RequestMapping(value="add",method = RequestMethod.GET)
	public String add(Model model,HttpServletRequest request){
		model.addAttribute("date", this.getCurrentDate());
		//获取当前用户所拥有的菜单
		/*List<Menu> menuList = this.getFirstMenuByUserId();
		model.addAttribute("menuList", menuList);*/
		String parentId = request.getParameter("parentId");
		model.addAttribute("parentId", parentId);
		//加盟商
		Seller seller = new Seller();
		seller.setSelledcnt(1);
		seller.setSellersign(1);
		List<Seller> sellerList = sellerService.selectAllSeller(seller);
		model.addAttribute("sellerList", sellerList);
		return "messageMoney/messageMoneyForm";
	}
	/**
	 * 增加和修改短信充值
	 * @param model
	 * @param messageMoney
	 * @param request
	 * @return
	 */
	@RequestMapping(value="addMessageMoney",method=RequestMethod.POST)
	public String addMessageMoney(Model model, @ModelAttribute("form") MessageMoney messageMoney,HttpServletRequest request) {
		String parentId = request.getParameter("parentId");
		try{
			if(messageMoney.getId() == null) {
				if(!StringUtils.isEmpty(messageMoney.getMessageCount().toString())) {
					messageMoney.setCreateDateTime(new Date());
					messageMoney.setCreateName(this.getCurrentUserName());
					
					messageMoneyService.insertMessageMoney(messageMoney);
				}
			}else {
				if(!StringUtils.isEmpty(messageMoney.getMessageCount().toString())) {
					messageMoney.setUpdateDateTime(new Date());
					messageMoney.setUpdateName(this.getCurrentUserName());
					
					messageMoneyService.updateMessageMoney(messageMoney);
				}
			}
		}catch(Exception e) {
			logger.error("MessageMoneyController err addMessageMoney", e);
		}
		
		return "redirect:/messageMoney/list/" + parentId;
	}
	/**
	 * 跳转到修改页面
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
			
			MessageMoney messageMoney = messageMoneyService.findMessageMoneyById(id);
			model.addAttribute("messageMoney", messageMoney);
		}catch(Exception e) {
			logger.error("MessageController err updateForm", e);
		}
		
		return "messageMoney/messageMoneyForm";
	}
	/**
	 * 关闭页面
	 * @param parentId
	 * @return
	 */
	@RequestMapping(value = "close/{parentId}", method = RequestMethod.GET)
	public String close(@PathVariable("parentId") Long parentId) {
		return "redirect:/messageMoney/list/" + parentId;
	}
}
