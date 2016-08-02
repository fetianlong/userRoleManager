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
import com.yuedi.entity.fm.Srespiration;
import com.yuedi.service.SellerService;
import com.yuedi.service.fm.SrespirationService;
import com.yuedi.web.common.SupperController;

/**
 * 模拟训练数据查询
 * @description   
 * @version currentVersion(1.0)  
 * @author pujh  
 * @createtime 2015年12月18日16:39:44
 */
@Controller
@RequestMapping(value = "/srespiration")
public class SrespirationController extends SupperController{

	private static final Logger logger = Logger.getLogger(SrespirationController.class);

	@Autowired
	private SrespirationService srespirationService;
	@Autowired
	private SellerService sellerService;
	
	/**
	 * 查询
	 * @param parentId
	 * @param model
	 * @param pageNumber
	 * @param request
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年12月18日 下午4:39:29
	 */
	@RequestMapping(value = "/list/{parentId}", method = RequestMethod.GET)
	public String list(@PathVariable("parentId") Long parentId, Model model, @RequestParam(value = "page", defaultValue = "1") int pageNumber,
			HttpServletRequest request){
		try{
		
			MyPage<Srespiration> page = new MyPage<Srespiration>();
			page.setNumber(pageNumber);
			
			String userName = request.getParameter("userName");
			page.getParams().put("userName", userName);
			
			String nowTime = request.getParameter("nowTime");
			page.getParams().put("nowTime", nowTime);
			
			List<Srespiration> srespirationList = srespirationService.selectCountSrespiration(page);
			List<Srespiration> list = new ArrayList<Srespiration>();
			//APP用户的用户名转码
			if(!srespirationList.isEmpty() && srespirationList.size()>0){
				for (Srespiration Srespiration : srespirationList) {
					String un = java.net.URLDecoder.decode(Srespiration.getUserName(),"UTF-8");
					Srespiration.setUserName(un);
					list.add(Srespiration);
				}
			}
			
			model.addAttribute("srespirationList", list);
			model.addAttribute("pageData", page);
			model.addAttribute("parentId", parentId);
			
//			request.getSession().setAttribute("parentId", parentId);
			
			String titleName = "模拟训练数据查询";
			if(!StringUtils.isEmpty(request.getParameter("titleName"))){
				titleName = request.getParameter("titleName");
			}
			model.addAttribute("titleName", titleName);
			model.addAttribute("userName", userName);
			model.addAttribute("nowTime", nowTime);
		}catch(Exception e) {
			logger.error("/srespiration/list", e);
		}
		return "srespiration/srespirationList";
	}
	
}
