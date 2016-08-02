package com.yuedi.web.clientManagerment;

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

import com.yuedi.entity.Areas;
import com.yuedi.entity.CustomerInfo;
import com.yuedi.entity.Menu;
import com.yuedi.entity.MyPage;
import com.yuedi.entity.UserInfo;
import com.yuedi.entity.dto.StudentInfoDto;
import com.yuedi.service.AreasService;
import com.yuedi.service.CustomerInfoService;
import com.yuedi.service.StudentInfoService;
import com.yuedi.service.UserInfoService;
import com.yuedi.web.activityLine.ActivityLineController;
import com.yuedi.web.common.SupperController;

@Controller
@RequestMapping(value = "/customerInfo")
public class CustomerInfoController extends SupperController{
	@Autowired
	private CustomerInfoService customerInfoService;
	@Autowired
	private StudentInfoService studentInfoService;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private AreasService areasService;
	
	private static final Logger logger = Logger.getLogger(ActivityLineController.class);
	
	@RequestMapping(value="list/{parentId}",method = RequestMethod.GET)
	public String list(@PathVariable("parentId") Long parentId,Model model, 
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			HttpServletRequest request){
		try {
			model.addAttribute("parentId", parentId);
			model.addAttribute("date", this.getCurrentDate());
			//获取当前用户所拥有的菜单
			/*List<Menu> menuList = this.getFirstMenuByUserId();
			model.addAttribute("menuList", menuList);*/
			//获取二级菜单
			List<Menu> towMenuList = this.getTwoMenuByParentId(parentId);
			model.addAttribute("towMenuList", towMenuList);
			
			MyPage<CustomerInfo> page = new MyPage<CustomerInfo>();
			page.setNumber(pageNumber);
			
			String name = request.getParameter("name");
			String franchiseesId = request.getParameter("franchiseesId");
			String isStudent = request.getParameter("isStudent");
			
			if(name != null && !"".equals(name)) {
				page.getParams().put("name", name);
				model.addAttribute("name", name);
			}
			
			if(null != franchiseesId){
				page.getParams().put("franchiseesId", franchiseesId);
				model.addAttribute("franchiseesId", franchiseesId);
			}else{
				if(this.getCurrentUserFranchiseesIdId() != 3){	//当前不是总部的，就需要加机构ID进行查询
					page.getParams().put("franchiseesId", franchiseesId);
					model.addAttribute("franchiseesId", franchiseesId);	//当前机构ID
				}
			}
			
			if(isStudent != null && !"".equals(isStudent)){
				page.getParams().put("isStudent", isStudent);
				model.addAttribute("isStudent", isStudent);	//当前机构ID
			}
			
			List<CustomerInfo> customerInfoList = customerInfoService.selectCustomerInfoByLimit(page);
			model.addAttribute("customerInfoList", customerInfoList);
			model.addAttribute("pageData", page);

		} catch (Exception e) {
			logger.error("CustomerInfoController err list", e);
		}
		
		return "customerInfo/customerInfoList";
	}
	
	
	@RequestMapping(value="add",method = RequestMethod.GET)
	public String add(Model model,HttpServletRequest request){
		//获取当前用户所拥有的菜单
		/*List<Menu> menuList = this.getFirstMenuByUserId();
		model.addAttribute("menuList", menuList);*/
		String parentId = request.getParameter("parentId");
		model.addAttribute("parentId", parentId);
		
		return "customerInfo/customerInfoForm";
	}
	
	@RequestMapping(value="addCustomerInfo",method=RequestMethod.POST)
	public String addCustomerInfo(Model model, @ModelAttribute("form") CustomerInfo customerInfo) {
		try{
			if(customerInfo.getId() == null) {
				if(!StringUtils.isEmpty(customerInfo.getName())) {
					customerInfo.setFranchiseesId(this.getCurrentUserFranchiseesIdId());
					customerInfo.setSource(1);
					customerInfo.setIsStudent(false);
					customerInfo.setCreateDateTime(new Date());
					customerInfo.setCreaterId(this.getCurrentUserId());
					customerInfo.setCreaterName(this.getCurrentUserName());
					
					customerInfoService.insertCustomerInfo(customerInfo);
				}
			}else {
				if(!StringUtils.isEmpty(customerInfo.getName())) {
					customerInfo.setFranchiseesId(this.getCurrentUserFranchiseesIdId());
					customerInfo.setSource(1);
					customerInfo.setIsStudent(false);
					customerInfo.setCreateDateTime(new Date());
					customerInfo.setCreaterId(this.getCurrentUserId());
					customerInfo.setCreaterName(this.getCurrentUserName());
					
					customerInfoService.updateCustomerInfoById(customerInfo);
				}
			}
		}catch(Exception e) {
			logger.error("CustomerInfoController err addCustomerInfo", e);
		}
		
		return "redirect:/customerInfo/list/" + customerInfo.getParentId();
	}
	
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model,HttpServletRequest request) {
		try{
			String parentId = request.getParameter("parentId");
			model.addAttribute("parentId", parentId);
			//获取当前用户所拥有的菜单
			/*List<Menu> menuList = this.getFirstMenuByUserId();
			model.addAttribute("menuList", menuList);*/
			
			CustomerInfo customerInfo = customerInfoService.selectCustomerInfoById(id);
			model.addAttribute("customerInfo", customerInfo);
		}catch(Exception e) {
			logger.error("CustomerInfoController err updateForm", e);
		}
		
		return "customerInfo/customerInfoForm";
	}
	
	@RequestMapping(value = "convert/{id}", method = RequestMethod.GET)
	public String convert(@PathVariable("id") Long id, Model model,HttpServletRequest request) {
		try{
			String parentId = request.getParameter("parentId");
			model.addAttribute("parentId", parentId);
			//获取当前用户所拥有的菜单
			/*List<Menu> menuList = this.getFirstMenuByUserId();
			model.addAttribute("menuList", menuList);*/
			
			CustomerInfo customerInfo = customerInfoService.selectCustomerInfoById(id);
			model.addAttribute("customerInfo", customerInfo);
			
			List<UserInfo> userInfoList = userInfoService.selectAllUserInfo(this.getCurrentUserFranchiseesIdId());
			model.addAttribute("userInfoList", userInfoList);
			List<Areas> provinceList = areasService.findProvince();
			model.addAttribute("provinceList", provinceList);
		}catch(Exception e) {
			logger.error("CustomerInfoController err convert", e);
		}
		
		return "customerInfo/studentForm";
	}
	
	@RequestMapping(value = "convertStudent", method = RequestMethod.POST)
	public String convertStudent(Model model,HttpServletRequest request,
			@ModelAttribute("form") StudentInfoDto studentInfoDto) {
		try{
			if(!StringUtils.isEmpty(studentInfoDto.getName())) {
				studentInfoDto.setFranchiseesId(this.getCurrentUserFranchiseesIdId());
				studentInfoDto.setCreateId(this.getCurrentUserId());
				studentInfoDto.setCreaterName(this.getCurrentUserName());
				studentInfoDto.setCreateTimeDate(new Date());
				
				studentInfoService.insertStudentInfo(studentInfoDto);
			}
		}catch(Exception e) {
			logger.error("CustomerInfoController err convertStudent", e);
		}
		
		return "redirect:/customerInfo/list/" + studentInfoDto.getParentId();
	}
	
	@RequestMapping(value = "close/{parentId}", method = RequestMethod.GET)
	public String close(@PathVariable("parentId") Long parentId) {
		return "redirect:/customerInfo/list/" + parentId;
	}
}
