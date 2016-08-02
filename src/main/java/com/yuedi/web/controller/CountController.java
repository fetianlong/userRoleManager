package com.yuedi.web.controller;

import java.text.NumberFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuedi.entity.Menu;
import com.yuedi.service.CountSysService;
import com.yuedi.service.MenuService;
import com.yuedi.vo.ClassCntVo;
import com.yuedi.vo.ClipCntVo;
import com.yuedi.vo.OrderCntVo;
import com.yuedi.web.common.SupperController;

/**
 * 
 * @author wzx
 * 统计管理控制器
 *
 */
@Controller
@RequestMapping(value = "/count")
public class CountController extends SupperController{

	@Autowired
	private MenuService menuService;
	
	@Autowired
	private CountSysService countSysService;
	
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(CountController.class);
	
	/*
	 * 课程统计列表———默认
	 */
	@RequestMapping(value = "ClassList/{parentId}",method = RequestMethod.GET)
	public String ClassList(@PathVariable("parentId") Long parentId, Model model,
			HttpServletRequest request) {
		
		// 获取当前用户所拥有的菜单
		/*List<Menu> menuList = this.getFirstMenuByUserId();
		model.addAttribute("menuList", menuList);*/
		// 获取二级菜单
		List<Menu> towMenuList = this.getTwoMenuByParentId(parentId);
		model.addAttribute("towMenuList", towMenuList);
		
		Integer statusi=0;
		String status = request.getParameter("status");
		if(!StringUtils.isEmpty(status)){
			 statusi = Integer.parseInt(status);
			 model.addAttribute("status", statusi);
		}
		
		List<ClassCntVo> valueList=countSysService.ClassList(statusi);
        model.addAttribute("listCount", valueList);
		
		String titleName = "课程统计";
		if(!StringUtils.isEmpty(request.getParameter("titleName"))){
			titleName = request.getParameter("titleName");
		}
		model.addAttribute("titleName", titleName);
	    
		return "count/ClassList";
		
	}
	
	/*
	 * 课程获取数据
	 */
	@ResponseBody
	@RequestMapping(value = "ClassCount",method = RequestMethod.GET,produces="application/json;charset=UTF-8")
	public List<ClassCntVo> ClassCount(HttpServletRequest request) {
		Integer status = Integer.parseInt(request.getParameter("status"));
		
		List<ClassCntVo> valueList=countSysService.ClassList(status);
		
		return valueList;
	}
	
	/*
	 * 作业统计列表
	 */
	@RequestMapping(value = "JobList/{parentId}",method = RequestMethod.GET)
	public String JobList(@PathVariable("parentId") Long parentId, Model model,
			HttpServletRequest request) {
		
		// 获取当前用户所拥有的菜单
		/*List<Menu> menuList = this.getFirstMenuByUserId();
		model.addAttribute("menuList", menuList);*/
		// 获取二级菜单
		List<Menu> towMenuList = this.getTwoMenuByParentId(parentId);
		model.addAttribute("towMenuList", towMenuList);
		
		//获取课程访问和作业完成数
		List<ClipCntVo> classNum = countSysService.classNum();
		List<ClipCntVo> workNum = countSysService.workNum();
		String scale = this.getScaleNum(classNum.get(0).getNum(),workNum.get(0).getNum());
		model.addAttribute("scale", scale);
		return "count/JobList";
		
	}
	
	/*
	 * 课程作业数据
	 */
	@ResponseBody
	@RequestMapping(value = "WorkCount",method = RequestMethod.GET,produces="application/json;charset=UTF-8")
	public List<ClassCntVo> WorkCount(HttpServletRequest request) {
		Integer status = Integer.parseInt(request.getParameter("status"));
		List<ClassCntVo> valueList=countSysService.WorkList(status);
		return valueList;
	}
	
	
	/*
	 * 痛点统计列表
	 */
	@RequestMapping(value = "OrderList/{parentId}",method = RequestMethod.GET)
	public String OrderList(@PathVariable("parentId") Long parentId, Model model,
			HttpServletRequest request) {
		
		// 获取当前用户所拥有的菜单
		/*List<Menu> menuList = this.getFirstMenuByUserId();
		model.addAttribute("menuList", menuList);*/
		// 获取二级菜单
		List<Menu> towMenuList = this.getTwoMenuByParentId(parentId);
		model.addAttribute("towMenuList", towMenuList);
		
		Integer statusi=0;
		String status = request.getParameter("status");
		if(!StringUtils.isEmpty(status)){
			 statusi = Integer.parseInt(status);
			 model.addAttribute("status", statusi);
		}
		
		List<OrderCntVo> OrderList=countSysService.OrderList(statusi);
		model.addAttribute("listCount", OrderList);
		
		String titleName = "痛点统计";
		if(!StringUtils.isEmpty(request.getParameter("titleName"))){
			titleName = request.getParameter("titleName");
		}
		model.addAttribute("titleName", titleName);
	    
		return "count/OrderList";
		
	}
	
	/*
	 * 痛点获取数据
	 */
	@ResponseBody
	@RequestMapping(value = "OrderCount",method = RequestMethod.GET,produces="application/json;charset=UTF-8")
	public List<OrderCntVo> OrderCount(HttpServletRequest request) {
		
		Integer status = Integer.parseInt(request.getParameter("status"));
		
		List<OrderCntVo> OrderList=countSysService.OrderList(status);
		return OrderList;
	}
	
	
	/*
	 * 测试统计列表
	 */
	@RequestMapping(value = "TestList/{parentId}",method = RequestMethod.GET)
	public String TestList(@PathVariable("parentId") Long parentId, Model model,
			HttpServletRequest request) {
		
		// 获取当前用户所拥有的菜单
		/*List<Menu> menuList = this.getFirstMenuByUserId();
		model.addAttribute("menuList", menuList);*/
		// 获取二级菜单
		List<Menu> towMenuList = this.getTwoMenuByParentId(parentId);
		model.addAttribute("towMenuList", towMenuList);
		
		List<ClipCntVo> TestNum=countSysService.TestList();
		model.addAttribute("listCount", TestNum);
	    
		String titleName = "测试次数统计";
		if(!StringUtils.isEmpty(request.getParameter("titleName"))){
			titleName = request.getParameter("titleName");
		}
		model.addAttribute("titleName", titleName);
		
		return "count/TestList";
		
	}
	
	/*
	 * 测试人数数据
	 */
	@ResponseBody
	@RequestMapping(value = "TestCount",method = RequestMethod.GET,produces="application/json;charset=UTF-8")
	public List<ClipCntVo> TestCount(HttpServletRequest request) {
		
		List<ClipCntVo> TestNum=countSysService.TestList();
		
		return TestNum;
	}
	
	
	/*
	 * 剪辑统计列表
	 */
	@RequestMapping(value = "ClipList/{parentId}",method = RequestMethod.GET)
	public String ClipList(@PathVariable("parentId") Long parentId, Model model,
			HttpServletRequest request) {
		
		// 获取当前用户所拥有的菜单
		/*List<Menu> menuList = this.getFirstMenuByUserId();
		model.addAttribute("menuList", menuList);*/
		// 获取二级菜单
		List<Menu> towMenuList = this.getTwoMenuByParentId(parentId);
		model.addAttribute("towMenuList", towMenuList);
	    
		
		List<ClipCntVo> ClipNum=countSysService.ClipList();
		model.addAttribute("listCount", ClipNum);
	    
		String titleName = "剪辑总制作次数统计";
		if(!StringUtils.isEmpty(request.getParameter("titleName"))){
			titleName = request.getParameter("titleName");
		}
		model.addAttribute("titleName", titleName);
		
		return "count/ClipList";
		
	}
	
	
	/*
	 * 剪辑数据
	 */
	@ResponseBody
	@RequestMapping(value = "clipCount",method = RequestMethod.GET,produces="application/json;charset=UTF-8")
	public List<ClipCntVo> clipCount(HttpServletRequest request) {
		
		List<ClipCntVo> ClipNum=countSysService.ClipList();
		return ClipNum;
	}
	
	
	/*
	 * 剪辑人数数据
	 */
	@ResponseBody
	@RequestMapping(value = "popCount",method = RequestMethod.GET,produces="application/json;charset=UTF-8")
	public List<ClipCntVo> popCount(HttpServletRequest request) {
		
		List<ClipCntVo> PopNum=countSysService.PopList();
		
		return PopNum;
	}
	
	/*
	 * 获取两个数据百分比
	 */
	private String getScaleNum(Integer classNum,Integer workNum) {
		NumberFormat numberFormat = NumberFormat.getInstance();
		numberFormat.setMaximumFractionDigits(2); //设置精确到小数点后2位
		String result = numberFormat.format((float) workNum / (float) classNum * 100); 
		return result;
	}
}
