package com.yuedi.web.login;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springside.modules.security.utils.Digests;
import org.springside.modules.utils.Encodes;
import org.springside.modules.web.MediaTypes;

import com.yuedi.entity.Menu;
import com.yuedi.entity.Role;
import com.yuedi.entity.UserInfo;
import com.yuedi.log.SystemControllerLog;
import com.yuedi.service.MenuService;
import com.yuedi.service.RoleService;
import com.yuedi.service.UserInfoService;
import com.yuedi.web.common.SupperController;

/**
 * LoginController负责打开登录页面(GET请求)和登录出错页面(POST请求)，
 * 
 * 真正登录的POST请求由Filter完成,
 * 
 * @author calvin
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController extends SupperController {

	private static String relativePathSeller = "D:/yuedi-resource/resources/picture/shangjia";

	private static final Logger logger = Logger.getLogger(LoginController.class);

	@Autowired
	private MenuService menuService;
	@Autowired
	private UserInfoService userInfoservice;
//	@Autowired
//	private AreasService areasService;
//	@Autowired
//	private LoginLogService loginlongService;
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String login() {
		return "account/login";
	}
	
	@SystemControllerLog(description = "退出系统")
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout() {
		SecurityUtils.getSubject().logout();
		return "account/login";
	}
	
	@SystemControllerLog(description = "登录成功")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String loginIndex(Model model) {
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		// 获取当前用户所拥有的菜单
		Object obj = session.getAttribute("menuList");
		if (null == obj) {
			List<Menu> menuList = getFirstMenuByUserId();
			session.setAttribute("menuList", menuList);
			session.setAttribute("sellerName", getCurrentUserFranchiseesName());
		}
		
		List<Role> roleList = roleService.findAllRole();
		session.setAttribute("roleList", roleList);
		
		Calendar cal = Calendar.getInstance();// 使用日历类
		int year = cal.get(Calendar.YEAR);// 得到年
		int month = cal.get(Calendar.MONTH) + 1;// 得到月，因为从0开始的，所以要加1
		int day = cal.get(Calendar.DAY_OF_MONTH);// 得到天
		String date = year + "年" + month + "月" + day + "日";
		model.addAttribute("date", date);
		
//		int hour=cal.get(Calendar.HOUR_OF_DAY);
//		int min=cal.get(Calendar.MINUTE);
//		int second=cal.get(Calendar.SECOND);
//		String newSecond="0";
//		if (second<10) {
//			newSecond="0"+second;
//		}
//		String dateHMS = year + "年" + month + "月" + day + "日"+" "+hour+":"+min+":"+newSecond;

		UserInfo userInfo = userInfoservice.getUserInfoById(this.getCurrentUserId());
		if ("1".equals(userInfo.getResetPwdFlag())) {
			model.addAttribute("userInfo", userInfo);
			return "home/changePwd";
		} else {
			return "home/index";
		}
	}

	/**
	 * 获取二级菜单
	 * 
	 * @param parentId
	 * @param model
	 * @return
	 * @description
	 * @version currentVersion
	 * @author pujh
	 * @createtime 2015年12月23日 下午5:07:23
	 */
	@ResponseBody
	@RequestMapping(value = "/getTwoMenu/{parentId}", method = RequestMethod.GET, produces = MediaTypes.JSON_UTF_8)
	public List<Menu> getTwoMenu(@PathVariable("parentId") Long parentId,
			Model model) {
		// model.addAttribute("parentId", parentId);
		// List<Menu> towMenuList = this.getTwoMenuByParentId(parentId);
		List<Menu> towMenuList = this.getTwoMenuByUserInfoId(parentId);
		if (!towMenuList.isEmpty()) {
			Menu menu = towMenuList.get(0);
			menu.setCreaterName("current");
		}
		// model.addAttribute("towMenuList", towMenuList);
		return towMenuList;
	}

	/**
	 * 获取首页信息内容
	 * 
	 * @param model
	 * @return
	 * @description
	 * @version currentVersion
	 * @author pujh
	 * @createtime 2015年11月9日 上午11:37:14
	 */
	@RequestMapping(value = "/getIndex", method = RequestMethod.GET)
	public String getIndex(Model model) {

		try {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.MONTH, -1);

		} catch (Exception e) {
			logger.error("LoginController err loginIndex", e);
		}
		// return "home/header";
		return "home/home";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String fail(
			@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String userName,
			Model model) {
		model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM,
				userName);
		return "account/login";
	}

	@RequestMapping(value = "/help", method = RequestMethod.GET)
	public String help() {
		return "help/help";
	}

	@RequestMapping(value = "/changePwdUI", method = RequestMethod.GET)
	public String changePwdUI(Model model) {
		model.addAttribute("date", this.getCurrentDate());
		// 获取当前用户所拥有的菜单
		List<Menu> menuList = this.getFirstMenuByUserId();
		model.addAttribute("menuList", menuList);
		UserInfo userInfo = userInfoservice.getUserInfoById(this
				.getCurrentUserId());
		model.addAttribute("userInfo", userInfo);

		return "home/changePwd";
	}

	@ResponseBody
	@RequestMapping(value = "/changePwd", method = RequestMethod.POST)
	public String changePwd(Model model,
			@ModelAttribute("form") UserInfo userInfo) {
		try {
			UserInfo userInfoParam = userInfoservice.getUserInfoById(this
					.getCurrentUserId());

			String oldpwd = userInfo.getPwd();
			String salt = userInfoParam.getSalt();
			String newpassword = userInfo.getNewpassword();

			userInfo.setPlainPassword(newpassword);
			userInfo.setId(this.getCurrentUserId());

			byte[] sa = Encodes.decodeHex(salt);

			byte[] hashPassword = Digests.sha1(oldpwd.getBytes(), sa, 1024);

			String pw = Encodes.encodeHex(hashPassword);
			userInfo.setResetPwdFlag("0");
			if (pw.equals(userInfoParam.getPwd())) {
				userInfoservice.resetPwdById(userInfo);
			} else {
				logger.debug("密码不正确！");
				return "{\"success\":false,\"message\":\"密码不正确\"}";
			}
		} catch (Exception e) {
			logger.error("LoginController err changePwd", e);
			return "{\"success\":false,\"message\":\"失败\"}";
		}
		return "{\"success\":true,\"message\":\"成功\"}";
//		return "redirect:/login/index";
	}

	/**
	 * 验证密码
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "validatePwd", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String validatePwd(HttpServletRequest request) {
		String oldpwd = request.getParameter("pwd");
		UserInfo userInfoParam = userInfoservice.getUserInfoById(this.getCurrentUserId());

		String salt = userInfoParam.getSalt();

		byte[] sa = Encodes.decodeHex(salt);

		byte[] hashPassword = Digests.sha1(oldpwd.getBytes(), sa, 1024);

		String pw = Encodes.encodeHex(hashPassword);
		if (pw.equals(userInfoParam.getPwd())) {
			return "{\"success\":true,\"message\":\"成功\"}";
		} else {
			return "{\"success\":false,\"message\":\"初始密码不正确\"}";
		}
	}

	@RequestMapping(value = "close", method = RequestMethod.GET)
	public String close() {
		return "redirect:/login/index";
	}


	

}
