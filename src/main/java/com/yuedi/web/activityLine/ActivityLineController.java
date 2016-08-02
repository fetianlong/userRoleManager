package com.yuedi.web.activityLine;

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

import com.yuedi.entity.ActivityLine;
import com.yuedi.entity.Menu;
import com.yuedi.entity.MyPage;
import com.yuedi.entity.Role;
import com.yuedi.entity.UserInfo;
import com.yuedi.service.ActivityLineService;
import com.yuedi.service.RoleService;
import com.yuedi.service.UserInfoService;
import com.yuedi.web.common.SupperController;

@Controller
@RequestMapping(value = "/activityLine")
public class ActivityLineController extends SupperController{
	@Autowired
	private ActivityLineService activityLineService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserInfoService userInfoService;
	
	private static final Logger logger = Logger.getLogger(ActivityLineController.class);
	
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
			
			MyPage<ActivityLine> page = new MyPage<ActivityLine>();
			page.setNumber(pageNumber);
			String activityName = request.getParameter("activityName");
			model.addAttribute("activityName", activityName);
			if(activityName != null && !"".equals(activityName)) {
				page.getParams().put("activityName", activityName+ "%");
			}
			String excutedId = request.getParameter("excutedId");
			model.addAttribute("excutedId", excutedId);
			if(excutedId != null && !"".equals(excutedId)) {
				page.getParams().put("excutedId", excutedId);
			}
			String beginDateTime = request.getParameter("beginDateTime");
			String endDateTime = request.getParameter("endDateTime");
			model.addAttribute("beginDateTime", beginDateTime);
			model.addAttribute("endDateTime", endDateTime);
			if((beginDateTime != null && !"".equals(beginDateTime)) && (endDateTime != null && !"".equals(endDateTime))) {
				page.getParams().put("beginDateTime", beginDateTime);
				page.getParams().put("endDateTime", endDateTime);
			}
			
			if(this.getCurrentUserFranchiseesIdId() != 3){
				page.getParams().put("sellerID", this.getCurrentUserFranchiseesIdId());
			}
			
			//某个业务员看到自己客户数据
			List<Role> roles = roleService.selectRoleByUserId(this.getCurrentUserId());
			if(roles != null) {
				for(int i=0; i<roles.size(); i++) {
					if(roles.get(i).getId() == 41 || roles.get(i).getId() == 37) {
						page.getParams().put("userInfoId", this.getCurrentUserId());
					}
				}
			}
			
			List<ActivityLine> activityLineList = activityLineService.findListActivityLineByLimit(page);
			model.addAttribute("activityLineList", activityLineList);
			model.addAttribute("pageData", page);
			
			List<UserInfo> userInfoList = userInfoService.queryUserBySellerId(this.getCurrentUserFranchiseesIdId());
			model.addAttribute("userInfoList", userInfoList);
		}catch(Exception e) {
			logger.error("ActivityLineController err list", e);
		}
		
		return "activityLine/activityLineList";
	}
	
	@RequestMapping(value="add",method = RequestMethod.GET)
	public String add(Model model,HttpServletRequest request){
		model.addAttribute("date", this.getCurrentDate());
		//获取当前用户所拥有的菜单
		/*List<Menu> menuList = this.getFirstMenuByUserId();
		model.addAttribute("menuList", menuList);*/
		String parentId = request.getParameter("parentId");
		model.addAttribute("parentId", parentId);
		return "activityLine/activityLineForm";
	}
	
	@RequestMapping(value="addActivityLine",method=RequestMethod.POST)
	public String addActivityLine(Model model, @ModelAttribute("form") ActivityLine activityLine) {
		try{
			if(activityLine.getId() == null) {
				if(!StringUtils.isEmpty(activityLine.getActivityName())) {
					activityLine.setSellerId(this.getCurrentUserFranchiseesIdId());
					activityLine.setExcutedId(this.getCurrentUserId());
					activityLine.setExcutedName(this.getCurrentUserName());
					activityLine.setCreateDateTime(new Date());
					activityLine.setCreateName(this.getCurrentUserName());
					
					//某个业务员看到自己客户数据
					List<Role> roles = roleService.selectRoleByUserId(this.getCurrentUserId());
					if(roles != null) {
						for(int i=0; i<roles.size(); i++) {
							if(roles.get(i).getId() == 41 || roles.get(i).getId() == 37) {
								activityLine.setUserInfoId(this.getCurrentUserId());
							}
						}
					}
					activityLineService.insertActivityLine(activityLine);
				}
			}else {
				if(!StringUtils.isEmpty(activityLine.getActivityName())) {
					activityLine.setSellerId(this.getCurrentUserFranchiseesIdId());
					activityLine.setExcutedId(this.getCurrentUserId());
					activityLine.setExcutedName(this.getCurrentUserName());
					activityLine.setCreateDateTime(new Date());
					activityLine.setCreateName(this.getCurrentUserName());
					
					List<Role> roles = roleService.selectRoleByUserId(this.getCurrentUserId());
					if(roles != null) {
						for(int i=0; i<roles.size(); i++) {
							if(roles.get(i).getId() == 41 || roles.get(i).getId() == 37) {
								activityLine.setUserInfoId(this.getCurrentUserId());
							}
						}
					}
					activityLineService.updateActivityLine(activityLine);
				}
			}
		}catch(Exception e) {
			logger.error("ActivityLineController err addActivityLine", e);
		}
		
		return "redirect:/activityLine/list/" + activityLine.getParentId();
	}
	
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model,HttpServletRequest request) {
		try{
			String parentId = request.getParameter("parentId");
			model.addAttribute("parentId", parentId);
			//获取当前用户所拥有的菜单
			/*List<Menu> menuList = this.getFirstMenuByUserId();
			model.addAttribute("menuList", menuList);*/
			
			ActivityLine activityLine = activityLineService.findActivityLineById(id);
			model.addAttribute("activityLine", activityLine);
		}catch(Exception e) {
			logger.error("ActivityLineController err updateForm", e);
		}
		
		return "activityLine/activityLineForm";
	}
	
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Long id,Model model,HttpServletRequest request) {
		String parentId = request.getParameter("parentId");
		try{
			//获取当前用户所拥有的菜单
			/*List<Menu> menuList = this.getFirstMenuByUserId();
			model.addAttribute("menuList", menuList);*/
			
			activityLineService.deleteActivityLineById(id);
		}catch(Exception e) {
			logger.error("ActivityLineController err delete", e);
		}
		
		return "redirect:/activityLine/list/" + parentId;
	}
	
	@RequestMapping(value = "close/{parentId}", method = RequestMethod.GET)
	public String close(@PathVariable("parentId") Long parentId) {
		return "redirect:/activityLine/list/" + parentId;
	}
}
