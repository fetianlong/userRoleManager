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
import com.yuedi.entity.Menu;
import com.yuedi.entity.MyPage;
import com.yuedi.entity.StudentInfo;
import com.yuedi.entity.UserInfo;
import com.yuedi.entity.dto.StudentInfoDto;
import com.yuedi.service.AreasService;
import com.yuedi.service.StudentInfoService;
import com.yuedi.service.UserInfoService;
import com.yuedi.web.activityLine.ActivityLineController;
import com.yuedi.web.common.SupperController;

@Controller
@RequestMapping(value = "/studentInfo")
public class StudentInfoController extends SupperController{
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
			
			MyPage<StudentInfo> page = new MyPage<StudentInfo>();
			page.setNumber(pageNumber);
			
			String name = request.getParameter("name");
			String customerCode = request.getParameter("customerCode");
			String qq = request.getParameter("qq");
			String microChart = request.getParameter("microChart");
			String mobilePhone = request.getParameter("mobilePhone");
			String startEntranceDate = request.getParameter("startEntranceDate");
			String endEntranceDate = request.getParameter("endEntranceDate");
			String franchiseesId = request.getParameter("franchiseesId");
			
			if(name != null && !"".equals(name)) {
				page.getParams().put("name", name);
				model.addAttribute("name", name);
			}
			if(customerCode != null && !"".equals(customerCode)) {
				page.getParams().put("customerCode", customerCode);
				model.addAttribute("customerCode", customerCode);
			}
			if(qq != null && !"".equals(qq)) {
				page.getParams().put("qq", qq);
				model.addAttribute("qq", qq);
			}
			if(microChart != null && !"".equals(microChart)) {
				page.getParams().put("microChart", microChart);
				model.addAttribute("microChart", microChart);
			}
			if(mobilePhone != null && !"".equals(mobilePhone)) {
				page.getParams().put("mobilePhone", mobilePhone);
				model.addAttribute("mobilePhone", mobilePhone);
			}
			if(startEntranceDate != null && !"".equals(startEntranceDate)) {
				page.getParams().put("startEntranceDate", startEntranceDate);
				model.addAttribute("startEntranceDate", startEntranceDate);
			}
			if(endEntranceDate != null && !"".equals(endEntranceDate)) {
				page.getParams().put("endEntranceDate", endEntranceDate);
				model.addAttribute("endEntranceDate", endEntranceDate);
			}
			
			if(null != franchiseesId){
				page.getParams().put("franchiseesId", franchiseesId);
				model.addAttribute("franchiseesId", franchiseesId);
			}else{
				if(this.getCurrentUserFranchiseesIdId() != 3){	//当前不是总部的，就需要加机构ID进行查询
					page.getParams().put("franchiseesId", franchiseesId);
					model.addAttribute("franchiseesId", this.getCurrentUserFranchiseesIdId());	//当前机构ID
				}
			}
			
			List<StudentInfoDto> studentList = studentInfoService.selectStudentInfoByLimit(page);
			model.addAttribute("studentList", studentList);
			model.addAttribute("pageData", page);
		} catch (Exception e) {
			logger.error("StudentInfoController err list", e);
		}
		return "studentInfo/studentInfoList";
	}
	
	
	@RequestMapping(value="add",method = RequestMethod.GET)
	public String add(Model model,HttpServletRequest request){
		//获取当前用户所拥有的菜单
		/*List<Menu> menuList = this.getFirstMenuByUserId();
		model.addAttribute("menuList", menuList);*/
		String parentId = request.getParameter("parentId");
		model.addAttribute("parentId", parentId);
		
		List<UserInfo> userInfoList = userInfoService.selectAllUserInfo(this.getCurrentUserFranchiseesIdId());
		model.addAttribute("userInfoList", userInfoList);
		List<Areas> provinceList = areasService.findProvince();
		model.addAttribute("provinceList", provinceList);
		
		return "studentInfo/studentInfoForm";
	}
	
	@RequestMapping(value="addStudentInfo",method=RequestMethod.POST)
	public String addStudentInfo(Model model, @ModelAttribute("form") StudentInfoDto studentInfoDto) {
		try{
			if(studentInfoDto.getId() == null) {
				if(!StringUtils.isEmpty(studentInfoDto.getName())) {
					studentInfoDto.setFranchiseesId(this.getCurrentUserFranchiseesIdId());
					studentInfoDto.setCreateId(this.getCurrentUserId());
					studentInfoDto.setCreaterName(this.getCurrentUserName());
					studentInfoDto.setCreateTimeDate(new Date());
					
					studentInfoService.insertStudentInfo(studentInfoDto);
				}
			}else {
				if(!StringUtils.isEmpty(studentInfoDto.getName())) {
					studentInfoDto.setFranchiseesId(this.getCurrentUserFranchiseesIdId());
					studentInfoDto.setCreateId(this.getCurrentUserId());
					studentInfoDto.setCreaterName(this.getCurrentUserName());
					studentInfoDto.setCreateTimeDate(new Date());
					
					studentInfoService.updateStudentInfoById(studentInfoDto);
				}
			}
		}catch(Exception e) {
			logger.error("StudentInfoController err addStudentInfo", e);
		}
		
		return "redirect:/studentInfo/list/" + studentInfoDto.getParentId();
	}
	
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model,HttpServletRequest request) {
		try{
			String parentId = request.getParameter("parentId");
			model.addAttribute("parentId", parentId);
			//获取当前用户所拥有的菜单
			/*List<Menu> menuList = this.getFirstMenuByUserId();
			model.addAttribute("menuList", menuList);*/
			
			StudentInfoDto studentInfoDto = studentInfoService.selectStudentInfoById(id);
			model.addAttribute("studentInfoDto", studentInfoDto);
			
			List<UserInfo> userInfoList = userInfoService.selectAllUserInfo(this.getCurrentUserFranchiseesIdId());
			model.addAttribute("userInfoList", userInfoList);
			List<Areas> provinceList = areasService.findProvince();
			model.addAttribute("provinceList", provinceList);
		}catch(Exception e) {
			logger.error("StudentInfoController err updateForm", e);
		}
		
		return "studentInfo/studentInfoForm";
	}
	
	@RequestMapping(value = "close/{parentId}", method = RequestMethod.GET)
	public String close(@PathVariable("parentId") Long parentId) {
		return "redirect:/studentInfo/list/" + parentId;
	}
}
