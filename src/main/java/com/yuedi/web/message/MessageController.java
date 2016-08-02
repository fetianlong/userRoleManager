package com.yuedi.web.message;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuedi.entity.Areas;
import com.yuedi.entity.Menu;
import com.yuedi.entity.Message;
import com.yuedi.entity.MessageUserinfo2c;
import com.yuedi.entity.MyPage;
import com.yuedi.entity.Seller;
import com.yuedi.service.AreasService;
import com.yuedi.service.MenuService;
import com.yuedi.service.MessageService;
import com.yuedi.service.MessageUserinfo2cService;
import com.yuedi.service.SellerService;
import com.yuedi.service.UserInfoService;
import com.yuedi.web.common.SupperController;
import com.yuedi.web.login.LoginController;

@Controller
@RequestMapping(value = "/message")
public class MessageController extends SupperController{
	private static final Logger logger = Logger.getLogger(LoginController.class);
	@Autowired
	private MessageService messageService;
	@Autowired
	private SellerService sellerService;
	@Autowired
	private MenuService menuService;
	@Autowired
	private UserInfoService userInfoservice;
	@Autowired
	private AreasService areasService;
	@Autowired
	private MessageUserinfo2cService messageUserinfo2cService;
	
	/**
	 * 获得短信列表
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
			//List<Menu> menuList = this.getFirstMenuByUserId();
			/*Subject currentUser = SecurityUtils.getSubject();  
			Session session = currentUser.getSession();
			List<Menu> menuList = (List<Menu>)session.getAttribute("menuList");
			model.addAttribute("menuList", menuList);*/
			//获取二级菜单
			List<Menu> towMenuList = this.getTwoMenuByParentId(parentId);
			model.addAttribute("towMenuList", towMenuList);
			
			MyPage<Message> page = new MyPage<Message>();
			page.setNumber(pageNumber);
			
			String state = request.getParameter("state");
			if(state != null && !"".equals(state)) {
				model.addAttribute("state", state);
				page.getParams().put("state", state);
			}
			//非北京总部
			if(this.getCurrentUserFranchiseesIdId() != 3){
				model.addAttribute("sellerId", this.getCurrentUserFranchiseesIdId());
				page.getParams().put("sellerId", this.getCurrentUserFranchiseesIdId());
				List<Message> messageList = messageService.findMessageByLimit(page);
				model.addAttribute("messageList", messageList);
			//北京总部
			}else{
				String sellerId = request.getParameter("sellerId");
				if(sellerId != null && !"".equals(sellerId)) {
					page.getParams().put("sellerId",sellerId);
				}
				//查询条件机构ID
				model.addAttribute("sellerSelectId", sellerId);
				model.addAttribute("sellerId", this.getCurrentUserFranchiseesIdId());
				List<Message> messageList = messageService.findMessageByCenterLimit(page);
				model.addAttribute("messageList", messageList);
				//加盟商
				Seller seller = new Seller();
				seller.setSelledcnt(1);
				seller.setSellersign(1);
				List<Seller> sellerList = sellerService.selectAllSeller(seller);
				model.addAttribute("sellerList", sellerList);
			}
			
			model.addAttribute("pageData", page);
			
			List<Areas> provinceList = areasService.findProvince();
			model.addAttribute("provinceList", provinceList);
		}catch(Exception e) {
			logger.error("MessageController err list", e);
		}
		
		return "message/messageList";
	}
	/**
	 * 跳转到短信增加页面
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="add",method = RequestMethod.GET)
	public String add(Model model,HttpServletRequest request){
		model.addAttribute("date", this.getCurrentDate());
		//获取当前用户所拥有的菜单
		/*List<Menu> menuList = this.getFirstMenuByUserId();
		model.addAttribute("menuList", menuList);*/
		String parentId = request.getParameter("parentId");
		model.addAttribute("parentId", parentId);
		return "message/messageForm";
	}
	/**
	 * 新增和修改短信
	 * @param model
	 * @param message
	 * @param request
	 * @return
	 */
	@RequestMapping(value="addMessage",method=RequestMethod.POST)
	public String addMessage(Model model, @ModelAttribute("form") Message message,HttpServletRequest request) {
		String parentId = request.getParameter("parentId");
		try{
			//新增短信
			if(message.getId() == null) {
				if(!StringUtils.isEmpty(message.getMessageContext())) {
					message.setSellerId(this.getCurrentUserFranchiseesIdId());
					message.setCreateDateTime(new Date());
					message.setCreaterName(this.getCurrentUserName());
					message.setState("1");
					
					Seller seller = sellerService.selectSellerById(this.getCurrentUserFranchiseesIdId());
					message.setProvince(Long.parseLong(seller.getProvince()));
					message.setCity(Long.parseLong(seller.getCity()));
					message.setArea(Long.parseLong(seller.getArea()));
					
					messageService.insertMessage(message);
				}
			//修改短信
			}else {
				if(!StringUtils.isEmpty(message.getMessageContext())) {
					message.setSellerId(this.getCurrentUserFranchiseesIdId());
					
					messageService.updateMessage(message);
				}
			}
		}catch(Exception e) {
			logger.error("MessageController err addMessage", e);
		}
		
		return "redirect:/message/list/" + parentId;
	}
	/**
	 * 跳转到短信修改页面
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
			
			Message message = messageService.findMessageById(id);
			model.addAttribute("message", message);
		}catch(Exception e) {
			logger.error("MessageController err updateForm", e);
		}
		
		return "message/messageForm";
	}
	/**
	 * 分配人员
	 * @param id
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "dispatchMessage/{id}", method = RequestMethod.GET)
	public String dispatchMessage(@PathVariable("id") Long id, Model model,HttpServletRequest request) {
		try{
			String parentId = request.getParameter("parentId");
			model.addAttribute("parentId", parentId);
			model.addAttribute("messageId", id);
			//获取当前用户所拥有的菜单
			/*List<Menu> menuList = this.getFirstMenuByUserId();
			model.addAttribute("menuList", menuList);*/
			MessageUserinfo2c messageUserinfo2c = new MessageUserinfo2c();
			messageUserinfo2c.setSellerId(this.getCurrentUserFranchiseesIdId());
			messageUserinfo2c.setMessageDateTime(new Date());
			messageUserinfo2c.setMessageId(id);
			//未分配人员
			List<MessageUserinfo2c> undistributedUserinfo2cList = messageUserinfo2cService.findUndistributedUserinfo2c(messageUserinfo2c);
			//已分配人员
			List<MessageUserinfo2c> distributedUserinfo2cList = messageUserinfo2cService.findDistributedUserinfo2c(messageUserinfo2c);
			model.addAttribute("undistributedUserinfo2cList", undistributedUserinfo2cList);
			model.addAttribute("distributedUserinfo2cList", distributedUserinfo2cList);
		}catch(Exception e) {
			logger.error("MessageController err updateForm", e);
		}
		
		return "message/dispatchMessage";
	}
	
	/**
	 * 添加客户
	 * @param model
	 * @param messageUserinfo2c
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "distributedUserinfo2cForm", method = RequestMethod.POST)
	public String distributedUserinfo2cForm(Model model,@ModelAttribute("form")MessageUserinfo2c messageUserinfo2c,
			HttpServletRequest request) {
		messageUserinfo2c.setSellerId(this.getCurrentUserFranchiseesIdId());
		messageUserinfo2c.setMessageDateTime(new Date());
		//操作短信客户关系表
		messageUserinfo2cService.distributedUserinfo2cForm(messageUserinfo2c);
		
		Long messageId = messageUserinfo2c.getMessageId();
		Long parentId = messageUserinfo2c.getParentId();
		
		return "redirect:/message/dispatchMessage/"+messageId+"?parentId=" + parentId;
	}
	
	/**
	 * 移除客户
	 * @param model
	 * @param messageUserinfo2c
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "removeUserinfo2c", method = RequestMethod.POST)
	public String removeUserinfo2c(Model model,@ModelAttribute("form")MessageUserinfo2c messageUserinfo2c,
			HttpServletRequest request) {
		//移除客户
		messageUserinfo2cService.removeUserinfo2c(messageUserinfo2c);
		Long messageId = messageUserinfo2c.getMessageId();
		Long parentId = messageUserinfo2c.getParentId();
		
		return "redirect:/message/dispatchMessage/"+messageId+"?parentId=" + parentId;
	}
	
	/**
	 * 申请短信
	 * @param id
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "applyMessage/{id}", method = RequestMethod.GET)
	public String applyMessage(@PathVariable("id") Long id, Model model,HttpServletRequest request) {
		String parentId = request.getParameter("parentId");
		try{
			model.addAttribute("parentId", parentId);
			model.addAttribute("messageId", id);
			
			MessageUserinfo2c messageUserinfo2c = new MessageUserinfo2c();
			messageUserinfo2c.setSellerId(this.getCurrentUserFranchiseesIdId());
			messageUserinfo2c.setMessageDateTime(new Date());
			messageUserinfo2c.setMessageId(id);
			//更新短信状态
			Message message = new Message();
			message.setId(id);
			message.setState("2");
			message.setApplyDateTime(new Date());
			
			messageService.updateMessageStateAndAllpyDateTime(message);
		}catch(Exception e) {
			logger.error("MessageController err applyMessage", e);
		}
		
		return "redirect:/message/list/" + parentId;
	}
	
	/**
	 * 取消短信
	 * @param id
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "cancelMessage/{id}", method = RequestMethod.GET)
	public String cancelMessage(@PathVariable("id") Long id, Model model,HttpServletRequest request) {
		String parentId = request.getParameter("parentId");
		try{
			model.addAttribute("parentId", parentId);
			model.addAttribute("messageId", id);
			Message message = new Message();
			message.setId(id);
			message.setState("3");
			
			messageService.updateMessageState(message);
		}catch(Exception e) {
			logger.error("MessageController err cancelMessage", e);
		}
		
		return "redirect:/message/list/" + parentId;
	}
	
	/**
	 * 审核通过
	 * @param id
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "adoptMessage/{id}", method = RequestMethod.GET)
	public String adoptMessage(@PathVariable("id") Long id, Model model,HttpServletRequest request) {
		String parentId = request.getParameter("parentId");
		try{
			model.addAttribute("parentId", parentId);
			model.addAttribute("messageId", id);
			
			MessageUserinfo2c messageUserinfo2c = new MessageUserinfo2c();
			messageUserinfo2c.setSellerId(this.getCurrentUserFranchiseesIdId());
			messageUserinfo2c.setMessageId(id);
			
			messageUserinfo2cService.adoptMessage(messageUserinfo2c);
		}catch(Exception e) {
			logger.error("MessageController err adoptMessage", e);
		}
		
		return "redirect:/message/list/" + parentId;
	}
	
	/**
	 * 跳转到申请不通过原因页面
	 * @param id
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "notgoReason/{id}", method = RequestMethod.GET)
	public String notgoReason(@PathVariable("id") Long id, Model model,HttpServletRequest request) {
		String parentId = request.getParameter("parentId");
		try{
			model.addAttribute("parentId", parentId);
			model.addAttribute("messageId", id);
			
			//获取当前用户所拥有的菜单
			/*List<Menu> menuList = this.getFirstMenuByUserId();
			model.addAttribute("menuList", menuList);*/
		}catch(Exception e) {
			logger.error("MessageController err notgoReason", e);
		}
		
		return "message/notgoReasonForm";
	}
	
	/**
	 * 保存申请不通过原因
	 * @param id
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "notgoMessage", method = RequestMethod.POST)
	public String notgoMessage(Model model, @ModelAttribute("form") Message message,HttpServletRequest request) {
		String parentId = request.getParameter("parentId");
		try{
			model.addAttribute("parentId", parentId);
			message.setState("5");
			
			messageService.updateMessageState(message);
		}catch(Exception e) {
			logger.error("MessageController err notgoMessage", e);
		}
		
		return "redirect:/message/list/" + parentId;
	}
	/**
	 * 关闭页面
	 * @param parentId
	 * @return
	 */
	@RequestMapping(value = "close/{parentId}", method = RequestMethod.GET)
	public String close(@PathVariable("parentId") Long parentId) {
		return "redirect:/message/list/" + parentId;
	}
	
	/**
	 * 通过异步请求获取短信信息
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="getMessageByAjax",method = RequestMethod.GET,produces="application/json;charset=UTF-8")
	public Message getMessageByAjax(HttpServletRequest request) {
		String messageParamId = request.getParameter("messageId");
		if(messageParamId != null && !"".equals(messageParamId)) {
			Long messageId = Long.parseLong(messageParamId);
			Message message = messageService.findMessageById(messageId);
			return message;
		}else {
			return null;
		}
	}
	
	/**
	 * 获取已分配人员
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="getDistributedUserinfo2cListByAjax",method = RequestMethod.GET,produces="application/json;charset=UTF-8")
	public List<MessageUserinfo2c> getDistributedUserinfo2cListByAjax(HttpServletRequest request) {
		String messageParamId = request.getParameter("messageId");
		if(messageParamId != null && !"".equals(messageParamId)) {
			Long messageId = Long.parseLong(messageParamId);
			MessageUserinfo2c messageUserinfo2c = new MessageUserinfo2c();
			messageUserinfo2c.setSellerId(this.getCurrentUserFranchiseesIdId());
			messageUserinfo2c.setMessageDateTime(new Date());
			messageUserinfo2c.setMessageId(messageId);
			
			List<MessageUserinfo2c> distributedUserinfo2cList = messageUserinfo2cService.findDistributedUserinfo2c(messageUserinfo2c);
			return distributedUserinfo2cList;
		}else {
			return null;
		}
	}
}
