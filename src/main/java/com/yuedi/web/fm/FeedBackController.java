package com.yuedi.web.fm;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;

import com.yuedi.entity.MyPage;
import com.yuedi.entity.fm.FeedBack;
import com.yuedi.service.fm.FeedBackService;
import com.yuedi.web.common.SupperController;

/**
 * 分娩数据查询
 * @description   
 * @version currentVersion(1.0)  
 * @author pujh  
 * @createtime 2015年12月15日 上午11:02:47
 */
@Controller
@RequestMapping(value = "/feedBack")
public class FeedBackController extends SupperController{

	private static final Logger logger = Logger.getLogger(FeedBackController.class);

	@Autowired
	private FeedBackService feedBackService;
	
	@RequestMapping(value = "/list/{parentId}", method = RequestMethod.GET)
	public String list(@PathVariable("parentId") Long parentId, Model model, @RequestParam(value = "page", defaultValue = "1") int pageNumber,
			HttpServletRequest request){
		try{
		
			MyPage<FeedBack> page = new MyPage<FeedBack>();
			page.setNumber(pageNumber);
			
			page = getNewPage(page, request,model);
			
			List<FeedBack> feedBackList = feedBackService.selectFeedBackLimit(page);
			List<FeedBack> list = new ArrayList<FeedBack>();
			if(!feedBackList.isEmpty() && feedBackList.size()>0){
				for (FeedBack feedBack : feedBackList) {
//					System.out.println("解码前：" + feedBack.getUserName());
					String un = java.net.URLDecoder.decode(feedBack.getUserName(),"UTF-8");
//					System.out.println("解码后：" + un);
					feedBack.setUserName(un);
					list.add(feedBack);
				}
			}
			
			model.addAttribute("feedBackList", list);
			model.addAttribute("pageData", page);
			model.addAttribute("parentId", parentId);
//			model.addAttribute("searchParams", searchParams);
			
			request.getSession().setAttribute("parentId", parentId);
			
			String titleName = "分娩结果查询";
			if(!StringUtils.isEmpty(request.getParameter("titleName"))){
				titleName = request.getParameter("titleName");
			}
			model.addAttribute("titleName", titleName);
		}catch(Exception e) {
			logger.error("/feedBack/list", e);
		}
		return "feedBack/feedBackList";
	}

	/**
	 * 查询条件
	 * @param page
	 * @param request
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年12月15日 上午11:09:17
	 */
	private MyPage<FeedBack> getNewPage(MyPage<FeedBack> page, HttpServletRequest request,Model model) {
		String fmType = request.getParameter("fmType");	//分娩方式
		if(!StringUtils.isEmpty(fmType)){
			page.getParams().put("fmType", fmType);
			model.addAttribute("fmType", fmType);
		}else{
			fmType = "";
		}
		
		String starTime = request.getParameter("beginDateTime");
		if(!StringUtils.isEmpty(starTime)){
			page.getParams().put("fmSTime", starTime);
			model.addAttribute("beginDateTime", starTime);
		}else{
			starTime = "";
		}
		
		String endTime = request.getParameter("endDateTime");
		if(!StringUtils.isEmpty(endTime)){
			page.getParams().put("fmETime", endTime);
			model.addAttribute("endDateTime", endTime);
		}else{
			endTime = "";
		}
		
		String painratio = request.getParameter("painratio");
		if(!StringUtils.isEmpty(painratio)){
			page.getParams().put("painratio", painratio);
			model.addAttribute("painratio", painratio);
		}else{
			painratio = "";
		}
		
		String searchParams = null;
		searchParams = "fmType=" + fmType + "&painratio=" + painratio + "&beginDateTime=" + starTime + "&endDateTime=" + endTime;
		model.addAttribute("searchParams", searchParams);
		
		return page;
	}


}
