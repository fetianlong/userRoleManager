package com.yuedi.web.report;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuedi.entity.Menu;
import com.yuedi.entity.MyPage;
import com.yuedi.entity.SalerUserinfo;
import com.yuedi.service.SalerUserinfoService;
import com.yuedi.web.activityLine.ActivityLineController;
import com.yuedi.web.common.SupperController;

@Controller
@RequestMapping(value = "/sallerReport")
public class SallerReportController extends SupperController{
	@Autowired
	private SalerUserinfoService salerUserinfoService;
	
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
				
				MyPage<SalerUserinfo> page = new MyPage<SalerUserinfo>();
				page.setNumber(pageNumber);
				
				String beginDateTime = request.getParameter("beginDateTime");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(new Date());
				calendar.add(Calendar.MONTH, -1);
				
				model.addAttribute("beginDateTimeString", sdf.format(calendar.getTime()));
				model.addAttribute("endDateTimeString", sdf.format(new Date()));
				
				page.getParams().put("beginDateTime", calendar.getTime());
				if (beginDateTime != null) {
					page.getParams().put("beginDateTime", beginDateTime);
					model.addAttribute("beginDateTime", beginDateTime);
				}
				String endDateTime = request.getParameter("endDateTime");
				page.getParams().put("endDateTime", new Date());
				if (endDateTime != null) {
					page.getParams().put("endDateTime", endDateTime);
					model.addAttribute("endDateTime", endDateTime);
				}
			
				page.getParams().put("sellerId", this.getCurrentUserFranchiseesIdId());
				
				List<SalerUserinfo> salerUserinfoList = this.salerUserinfoService.findSalerUserinfoBySellIdAndDate(page);
				model.addAttribute("salerUserinfoList", salerUserinfoList);
				model.addAttribute("pageData", page);
		} catch (Exception e) {
			logger.error("SallerReportController err list", e);
		}
		return "report/sallerReport";
	}
	
	/**
	 * 获取二维码饼状图信息
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="listchart",method = RequestMethod.GET,produces="application/json;charset=UTF-8")
	public List<SalerUserinfo> listchart(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			HttpServletRequest request) {
		List<SalerUserinfo> salerUserinfoList = new ArrayList<SalerUserinfo>();
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			MyPage<SalerUserinfo> page = new MyPage<SalerUserinfo>();
			page.setNumber(pageNumber);
			
			String beginDateTime = request.getParameter("beginDateTime");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.MONTH, -1);
			
			if (beginDateTime != null) {
				page.getParams().put("beginDateTime", sdf.parse(beginDateTime));
			}else {
				page.getParams().put("beginDateTime", calendar.getTime());
			}
			
			String endDateTime = request.getParameter("endDateTime");
			if (endDateTime != null) {
				page.getParams().put("endDateTime", sdf.parse(endDateTime));
			}else {
				page.getParams().put("endDateTime", new Date());
			}
		
			page.getParams().put("sellerId", this.getCurrentUserFranchiseesIdId());
			
			salerUserinfoList = this.salerUserinfoService.findSalerUserinfoBySellIdAndDate(page);
			
		}catch(Exception e) {
			logger.error("SallerReportController err listchart", e);
		}
		return salerUserinfoList;
	}
}
