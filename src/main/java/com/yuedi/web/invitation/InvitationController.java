package com.yuedi.web.invitation;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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

import com.yuedi.entity.Invitation;
import com.yuedi.entity.InvitationCount;
import com.yuedi.entity.MyPage;
import com.yuedi.entity.Seller;
import com.yuedi.service.InvitationService;
import com.yuedi.service.SellerService;
import com.yuedi.web.common.SupperController;

/**
 * 邀请码管理
 * @description   
 * @version currentVersion(1.0)  
 * @author pujh  
 * @createtime 2015年11月30日 下午8:01:13
 */
@Controller
@RequestMapping(value = "/invitation")
public class InvitationController extends SupperController{

	private static final Logger logger = Logger.getLogger(InvitationController.class);

	@Autowired
	private InvitationService invitationService;
	@Autowired
	private SellerService sellerService;
	
	@RequestMapping(value = "/list/{parentId}", method = RequestMethod.GET)
	public String list(@PathVariable("parentId") Long parentId, Model model, @RequestParam(value = "page", defaultValue = "1") int pageNumber,
			HttpServletRequest request){
		try{
		
			MyPage<Invitation> page = new MyPage<Invitation>();
			page.setNumber(pageNumber);
			
			Long fid = this.getCurrentUserFranchiseesIdId();
			if(fid != 3){
				page.getParams().put("pid", fid);
			}
			String invitationCode = request.getParameter("invitationCode");
			page.getParams().put("code", invitationCode);
			List<Invitation> invitationList = invitationService.selectInvitationLimit(page);
			
			model.addAttribute("invitationList", invitationList);
			model.addAttribute("pageData", page);
			model.addAttribute("parentId", parentId);
			
//			request.getSession().setAttribute("parentId", parentId);
			
			String titleName = "邀请码管理";
			if(!StringUtils.isEmpty(request.getParameter("titleName"))){
				titleName = request.getParameter("titleName");
			}
			model.addAttribute("titleName", titleName);
			model.addAttribute("invitationCode", invitationCode);
		}catch(Exception e) {
			logger.error("/invitation/list", e);
		}
		return "invitation/invitationList";
	}
	
	/**
	 * 添加页面跳转
	 * @param model
	 * @param request
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年12月1日 下午2:36:30
	 */
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(Model model,HttpServletRequest request) {
		List<Seller> sellerList = sellerService.findAllSellerFranchisees();
		model.addAttribute("sellerList", sellerList);
		model.addAttribute("parentId", request.getParameter("parentId"));
		model.addAttribute("invitationCount", new InvitationCount());
		model.addAttribute("action", "addInvitationBySeller");
		return "invitation/invitationForm";
	}
	
	/**
	 * 给加盟商添加邀请码数量限制
	 * @param invitationCount
	 * @param request
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年12月17日 下午4:24:36
	 */
	@RequestMapping(value = "addInvitationBySeller", method = RequestMethod.POST)
	public String addInvitationBySeller(@Valid InvitationCount invitationCount,HttpServletRequest request){
		String parentId = request.getParameter("parentId");
		invitationCount.setCreateTime(new Date());
		invitationCount.setCreateUser(getCurrentUserId());
		invitationService.insertInvitationCount(invitationCount);
		return "redirect:/invitation/list/" + parentId;
	}
	
	/**
	 * 执行添加
	 * @param model
	 * @param invitation
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年12月1日 下午3:11:09
	 */
	@ResponseBody
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String add(Model model, @ModelAttribute("invitation") Invitation invitation, HttpServletRequest request) {
		String flag = "1";
		try{
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			String code = getRandomInvitationcode(new Integer(5));
			
			invitation.setInvitationcode(code);
			invitation.setCtime(new Date());
			invitation.setProposerid(getCurrentUserId());
			invitation.setInstitutionid(getCurrentUserFranchiseesIdId());
			invitation.setIseffe(1);
			
			invitationService.insert(invitation);
		}catch(Exception e) {
			flag = "0";
			logger.error("/invitation/add", e);
		}
		
		return flag;
//		return "redirect:/invitation/list/" + request.getParameter("parentId");
	}
	
	/**
	 * 随机生成5位字符
	 * @param integer
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年12月2日 下午12:06:29
	 */
	private String getRandomInvitationcode(int length) {
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
	    Random random = new Random();     
	    StringBuffer sb = new StringBuffer();     
	    for (int i = 0; i < length; i++) {     
	        int number = random.nextInt(base.length());     
	        sb.append(base.charAt(number));     
	    }
	    String code = sb.toString();
	    MyPage<Invitation> page = new MyPage<Invitation>();
		page.getParams().put("code", code);
		List<Invitation> invitationList = invitationService.selectInvitationLimit(page);
		if(invitationList.size()>0){
			code = getRandomInvitationcode(5);
		}
		return code;
	}

	/**
	 * 更新页面跳转
	 * @param id
	 * @param model
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年12月1日 下午3:16:54
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("invitation", invitationService.selectByPrimaryKey(id));
		model.addAttribute("action", "update");
		return "invitation/invitationForm";
	}
	
	/**
	 * 执行更新
	 * @param invitation
	 * @param redirectAttributes
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年12月1日 下午3:19:18
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("invitation") Invitation invitation, HttpServletRequest request) {
		invitationService.updateByPrimaryKeySelective(invitation);
		return "redirect:/invitation/list/" + request.getParameter("parentId");
	}
	
	/**
	 * 二维码统计
	 * @param parentId
	 * @param model
	 * @param pageNumber
	 * @param request
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年12月16日 下午4:32:47
	 */
	@RequestMapping(value = "/listCountInvitation/{parentId}", method = RequestMethod.GET)
	public String listCountInvitation(@PathVariable("parentId") Long parentId, Model model, @RequestParam(value = "page", defaultValue = "1") int pageNumber,
	HttpServletRequest request){
		MyPage<Invitation> page = new MyPage<Invitation>();
		page.setNumber(pageNumber);
		
		Long fid = this.getCurrentUserFranchiseesIdId();
		if(fid != 3){	//普通加盟商查看
			page.getParams().put("pid", fid);
		}else{	//总部查看
			String sellerId = request.getParameter("sellerId");
			page.getParams().put("pid", sellerId);
		}
		
		String orderByW = request.getParameter("orderByW");
		if(!StringUtils.isEmpty(orderByW)){
			page.getParams().put("groupByW", orderByW);
		}else{
			page.getParams().put("groupByW", "i.InstitutionId");
		}
		
		String starTime = request.getParameter("beginDateTime");
		page.getParams().put("fmSTime", starTime);
		
		String endTime = request.getParameter("endDateTime");
		page.getParams().put("fmETime", endTime);
		
		List<Invitation> list = invitationService.selectInvitationCountLimit(page);
		model.addAttribute("listCount", list);
		model.addAttribute("pageData", page);
		model.addAttribute("parentId", parentId);
		
//		request.getSession().setAttribute("parentId", parentId);
		
		String titleName = "邀请码管理";
		if(!StringUtils.isEmpty(request.getParameter("titleName"))){
			titleName = request.getParameter("titleName");
		}
		model.addAttribute("titleName", titleName);
		
		return "invitation/invitationCount";
	}
}
