package com.yuedi.web.saler;

import java.util.Date;
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
import com.yuedi.entity.Saler;
import com.yuedi.entity.SalerUserinfo;
import com.yuedi.entity.UserInfo;
import com.yuedi.service.RoleService;
import com.yuedi.service.SalerDeclareService;
import com.yuedi.service.SalerService;
import com.yuedi.service.UserInfoService;
import com.yuedi.web.common.SupperController;

@Controller
@RequestMapping(value = "/saler")
public class SalerController extends SupperController{
	@Autowired
	private SalerService salerService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private SalerDeclareService salerDeclareService;
	@Autowired
	private UserInfoService userInfoService;
	
	private static final Logger logger = Logger.getLogger(SalerController.class);
	
	@RequestMapping(value="mySaler/{parentId}",method = RequestMethod.GET)
	public String listSeller(@PathVariable("parentId") Long parentId,Model model, 
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
			
			Saler saler = salerService.selectSalerByUserId(this.getCurrentUserId());
			model.addAttribute("saler", saler);
		}catch(Exception e) {
			logger.error("SalerController err listSeller", e);
		}
		return "saler/mySaler";
	}
	
	@RequestMapping(value="salerList/{parentId}",method = RequestMethod.GET)
	public String listSaler(@PathVariable("parentId") Long parentId,Model model, 
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
			
			MyPage<Saler> page = new MyPage<Saler>();
			page.setNumber(pageNumber);
			String name = request.getParameter("name");
			model.addAttribute("name", name);
			if(name != null && !"".equals(name)) {
				page.getParams().put("name", name+ "%");
			}
			String sjname = request.getParameter("sellerName");
			model.addAttribute("sjname", sjname+ "%");
			if(sjname != null && !"".equals(sjname)) {
				page.getParams().put("sellerName", sjname);
			}
			String state = request.getParameter("state");
			model.addAttribute("state", state);
			if(state != null && !"".equals(state)) {
				page.getParams().put("state", state);
			}
			if(this.getCurrentUserFranchiseesIdId() != 3){
				page.getParams().put("sellerId", this.getCurrentUserFranchiseesIdId());
			}
			
			List<Saler> salerList = salerService.selectSalerLimit(page);
			model.addAttribute("salerList", salerList);
			model.addAttribute("pageData", page);
		}catch(Exception e) {
			logger.error("SalerController err listSaler", e);
		}
		return "saler/salerList";
	}

	
	/**
	 * 二维码预览
	 * 
	 * @return
	 */
	@RequestMapping(value="preview/{salerId}",method = RequestMethod.GET)
	public String preview(@PathVariable("salerId") Long salerId,Model model,HttpServletRequest request) {
		String parentId = request.getParameter("parentId");
		model.addAttribute("parentId", parentId);
		//获取当前用户所拥有的菜单
		/*List<Menu> menuList = this.getFirstMenuByUserId();
		model.addAttribute("menuList", menuList);*/
		//获取二级菜单
		List<Menu> towMenuList = this.getTwoMenuByParentId(Long.parseLong(parentId));
		model.addAttribute("towMenuList", towMenuList);
		if (salerId != null) {
			Saler saler = salerService.findSalerbyId(salerId);
			model.addAttribute("saler", saler);
		}
		return "saler/previewSaler";
	}
	
	/**
	 * 分配二维码
	 * 
	 * @return
	 */
	@RequestMapping(value="dispatchSaler/{salerId}",method = RequestMethod.GET)
	public String dispatchSaler(@PathVariable("salerId") Long salerId,Model model,HttpServletRequest request) {
		String parentId = request.getParameter("parentId");
		model.addAttribute("parentId", parentId);
		//获取当前用户所拥有的菜单
		/*List<Menu> menuList = this.getFirstMenuByUserId();
		model.addAttribute("menuList", menuList);*/
		//获取二级菜单
		List<Menu> towMenuList = this.getTwoMenuByParentId(Long.parseLong(parentId));
		model.addAttribute("towMenuList", towMenuList);
		
		List<UserInfo> userInfoList = userInfoService.queryUserSaler(this.getCurrentUserFranchiseesIdId());
		model.addAttribute("userInfoList", userInfoList);
		
		Saler saler = salerService.findSalerbyId(salerId);
		model.addAttribute("saler", saler);
		
		return "saler/dispatchSalerForm";
	}
	
	@RequestMapping(value="editSaler",method=RequestMethod.POST)
	public String editSaler(Model model, @ModelAttribute("form") SalerUserinfo salerUserinfo) {

		UserInfo userInfo = userInfoService.getUserInfoById(salerUserinfo.getUserId());
	
		salerUserinfo.setCreateDateTime(new Date());
		salerUserinfo.setCreaterName(userInfo.getRealname());
		salerUserinfo.setIsUsed("1");
		
		Saler saler = new Saler();
		saler.setId(salerUserinfo.getSallerId());
		saler.setSex(userInfo.getSex());
		saler.setEmail(userInfo.getEmail());
		saler.setContactsPhone(userInfo.getTelephone());
		saler.setSpec(salerUserinfo.getSpec());
	
		salerService.updateSalerbyId(saler,salerUserinfo);
		
		return "redirect:/saler/salerList/" + salerUserinfo.getParentId();
	}
	
	@RequestMapping(value = "close/{parentId}", method = RequestMethod.GET)
	public String close(@PathVariable("parentId") Long parentId) {
		return "redirect:/saler/salerList/" + parentId;
	}
}
