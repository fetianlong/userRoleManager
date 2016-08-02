package com.yuedi.web.loginRecord;

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

import com.yuedi.entity.LoginRecord;
import com.yuedi.entity.MyPage;
import com.yuedi.service.LoginRecordService;
import com.yuedi.web.common.SupperController;

/**
 * app用户登录查询
 * @description   
 * @version currentVersion(1.0)  
 * @author pujh  
 * @createtime 2015年12月23日 上午11:41:27
 */
@Controller
@RequestMapping(value = "/loginRecord")
public class LoginRecordController extends SupperController {
	private static final Logger logger = Logger.getLogger(LoginRecordController.class);
	
	@Autowired
	LoginRecordService loginRecordService;
	
	@RequestMapping(value = "list/{parentId}", method = RequestMethod.GET)
	public String list(@PathVariable("parentId") Long parentId,Model model, 
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			HttpServletRequest request){
		try {
			
			//初始化分页器
			MyPage<LoginRecord> page = new MyPage<LoginRecord>();
			page.setNumber(pageNumber);	//设置当前页
			
			String userId = request.getParameter("userId");	//获取查询条件
			String loginTime = request.getParameter("loginTime");	//获取查询条件
			
			page.getParams().put("userId", userId);	//设置查询条件
			page.getParams().put("loginTime", loginTime);	//设置查询条件
			model.addAttribute("userId", userId);
			model.addAttribute("loginTime", loginTime);
			
			
			List<LoginRecord> list = loginRecordService.selectLoginRecordLimit(page);	//分页查询数据
			List<LoginRecord> loginRecordList = new ArrayList<LoginRecord>();
			if(!list.isEmpty() && list.size()>0){	//查询有数据
				for (LoginRecord loginRecord : list) {
					//把数据中的别名UTF-8转码，处理特殊符号的昵称
					String un = java.net.URLDecoder.decode(loginRecord.getNickName(),"UTF-8");	
					loginRecord.setNickName(un);
					loginRecordList.add(loginRecord);	//新list中添加转码后的数据
				}
			}
			
			//页面中的导航标签
			String titleName = "APP用户登录记录";
			if(!StringUtils.isEmpty(request.getParameter("titleName"))){
				titleName = request.getParameter("titleName");
			}
			model.addAttribute("titleName", titleName);
			
			model.addAttribute("pageData", page);
			model.addAttribute("loginRecordList", loginRecordList);
		} catch (Exception e) {
			logger.debug("app用户登录查询错误", e);
		}
		model.addAttribute("parentId", parentId);
		return "loginRecord/loginRecordList";
	}
}
