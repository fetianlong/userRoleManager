package com.yuedi.web.saler;

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
import com.yuedi.entity.MyPage;
import com.yuedi.entity.Saler;
import com.yuedi.entity.SalerDeclare;
import com.yuedi.service.RoleService;
import com.yuedi.service.SalerDeclareService;
import com.yuedi.service.SalerService;
import com.yuedi.web.common.SupperController;
import com.yuedi.web.system.RoleController;

@Controller
@RequestMapping(value = "/salerDeclare")
public class SalerDeclareController extends SupperController{
	@Autowired
	private SalerService salerService;
	@Autowired
	private SalerDeclareService salerDeclareService;
	@Autowired
	private RoleService roleService;
	
	private static final Logger logger = Logger.getLogger(RoleController.class);
	
	@RequestMapping(value="/salerDeclareList/{parentId}",method = RequestMethod.GET)
	public String salerDeclareList(@PathVariable("parentId") Long parentId,Model model, 
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
			
			MyPage<SalerDeclare> page = new MyPage<SalerDeclare>();
			page.setNumber(pageNumber);
			String name = request.getParameter("name");
			model.addAttribute("name", name);
			if(name != null && !"".equals(name)) {
				page.getParams().put("name", name+ "%");
			}
			String sjname = request.getParameter("sellerName");
			model.addAttribute("sellerName", sjname);
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
			
			List<SalerDeclare> salerDeclareList = salerDeclareService.selectSalerDeclareLimit(page);
			model.addAttribute("salerDeclareList", salerDeclareList);
			model.addAttribute("pageData", page);
			
		}catch(Exception e) {
			logger.error("SalerDeclareController err salerDeclareList", e);
		}
		return "saler/salerDeclareList";
	}
	
	@RequestMapping(value="applicationSaler",method = RequestMethod.GET)
	public String applicationSaler(Model model,HttpServletRequest request){
		//获取当前用户所拥有的菜单
		/*List<Menu> menuList = this.getFirstMenuByUserId();
		model.addAttribute("menuList", menuList);*/
		String parentId = request.getParameter("parentId");
		model.addAttribute("parentId", parentId);
		
		return "saler/salerDeclareForm";
	}
	
	@RequestMapping(value="addSalerDeclare",method=RequestMethod.POST)
	public String addSalerDeclare(Model model, @ModelAttribute("form") SalerDeclare salerDeclare) {
		try{
			if(!StringUtils.isEmpty(Integer.toString(salerDeclare.getDeclareNumber()))) {
				salerDeclare.setDeclareName(this.getCurrentUserName());
				salerDeclare.setState("1");
				salerDeclare.setSellerId(this.getCurrentUserFranchiseesIdId());
				salerDeclareService.insertSalerDeclare(salerDeclare);
			}
		}catch(Exception e) {
			logger.error("SalerDeclareController err addSalerDeclare", e);
		}
		
		return "redirect:/salerDeclare/salerDeclareList/" + salerDeclare.getParentId();
	}
	
	@RequestMapping(value="verifySaler/{salerDeclareId}",method=RequestMethod.POST)
	public String verifySaler(@PathVariable("salerDeclareId") Long salerDeclareId,Model model,HttpServletRequest request) {
		String parentId = request.getParameter("parentId");
		try{
			SalerDeclare salerDeclare = salerDeclareService.selectSalerDeclareById(salerDeclareId);
			
			if("1".equals(salerDeclare.getState())) {
				Saler saler = new Saler();
				saler.setSellerId(this.getCurrentUserFranchiseesIdId());
				saler.setOrdIndex("1");
				saler.setIsDeleteFlag(false);
				saler.setCreateDateTime(new Date());
				saler.setCreaterId(this.getCurrentUserId());
				saler.setCreaterName(this.getCurrentUserName());
				
				salerService.saveSaler(saler, salerDeclare.getDeclareNumber(), salerDeclareId);
			}
		}catch(Exception e) {
			logger.error("SalerController err addSaler", e);
		}
		
		return "redirect:/salerDeclare/salerDeclareList/" + parentId;
	}
	
	@RequestMapping(value = "close/{parentId}", method = RequestMethod.GET)
	public String close(@PathVariable("parentId") Long parentId) {
		return "redirect:/salerDeclare/salerDeclareList/" + parentId;
	}
}
