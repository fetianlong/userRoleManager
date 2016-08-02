package com.yuedi.web.feedBack;

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
import com.yuedi.entity.MyPage;
import com.yuedi.service.InvitationService;
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
//			model.addAttribute("parentId", parentId);
			
			request.getSession().setAttribute("parentId", parentId);
			
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
		model.addAttribute("task", new Invitation());
		model.addAttribute("action", "add");
		return "invitation/invitationForm";
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
	 * 删除单个用户
	 * @param id
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年11月12日 下午5:57:42
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteUser/{id}", method=RequestMethod.DELETE)
	public Boolean delete(@PathVariable("id") Integer id) {
		Boolean flag = true;
		try {
			invitationService.deleteByPrimaryKey(id);
		} catch (Exception e) {
			flag = false;
			logger.warn("删除失败");
		}
		return flag;
	}
	
	
	/*
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("task", new UserInfo());
		model.addAttribute("action", "create");
		return "task/taskForm";
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(@Valid UserInfo newUserInfo, RedirectAttributes redirectAttributes) {
		User user = new User(getCurrentUserId());
		newUserInfo.setUser(user);

		taskService.saveTask(newTask);
		redirectAttributes.addFlashAttribute("message", "创建任务成功");
		return "redirect:/task/";
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("task", taskService.getTask(id));
		model.addAttribute("action", "update");
		return "task/taskForm";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("task") Task task, RedirectAttributes redirectAttributes) {
		taskService.saveTask(task);
		redirectAttributes.addFlashAttribute("message", "更新任务成功");
		return "redirect:/task/";
	}

	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		taskService.deleteTask(id);
		redirectAttributes.addFlashAttribute("message", "删除任务成功");
		return "redirect:/task/";
	}
*/
	/**
	 * 所有RequestMapping方法调用前的Model准备方法, 实现Struts2 Preparable二次部分绑定的效果,先根据form的id从数据库查出Task对象,再把Form提交的内容绑定到该对象上。
	 * 因为仅update()方法的form中有id属性，因此仅在update时实际执行.
	 */
	/*@ModelAttribute
	public void getTask(@RequestParam(value = "id", defaultValue = "-1") Long id, Model model) {
		if (id != -1) {
			model.addAttribute("task", taskService.getTask(id));
		}
	}
*/
}
