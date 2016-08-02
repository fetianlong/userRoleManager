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

import com.yuedi.entity.FeedBack;
import com.yuedi.entity.Invitation;
import com.yuedi.entity.MyPage;
import com.yuedi.service.FeedBackService;
import com.yuedi.service.InvitationService;
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
	
	private String beginDateTime;
	private String endDateTime;
	private String fmType;
	private String painratio;
	
	@RequestMapping(value = "/list/{parentId}", method = RequestMethod.GET)
	public String list(@PathVariable("parentId") Long parentId, Model model, @RequestParam(value = "page", defaultValue = "1") int pageNumber,
			HttpServletRequest request){
		try{
		
			MyPage<FeedBack> page = new MyPage<FeedBack>();
			page.setNumber(pageNumber);
			
			page = getNewPage(page, request);
			
			List<FeedBack> feedBackList = feedBackService.selectFeedBackLimit(page);
			
			model.addAttribute("feedBackList", feedBackList);
			model.addAttribute("pageData", page);
			model.addAttribute("parentId", parentId);
			
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
	private MyPage<FeedBack> getNewPage(MyPage<FeedBack> page, HttpServletRequest request) {
		String fmType = request.getParameter("fmType");	//分娩方式
		page.getParams().put("fmType", fmType);
		
		String starTime = request.getParameter("beginDateTime");
		page.getParams().put("fmSTime", starTime);
		
		String endTime = request.getParameter("endDateTime");
		page.getParams().put("fmETime", endTime);
		
		String painratio = request.getParameter("painratio");
		page.getParams().put("painratio", painratio);
		
		return page;
	}

	public String getBeginDateTime() {
		return beginDateTime;
	}

	public void setBeginDateTime(String beginDateTime) {
		this.beginDateTime = beginDateTime;
	}

	public String getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(String endDateTime) {
		this.endDateTime = endDateTime;
	}

	public String getFmType() {
		return fmType;
	}

	public void setFmType(String fmType) {
		this.fmType = fmType;
	}

	public String getPainratio() {
		return painratio;
	}

	public void setPainratio(String painratio) {
		this.painratio = painratio;
	}

}
