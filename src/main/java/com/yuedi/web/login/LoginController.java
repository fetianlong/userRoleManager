package com.yuedi.web.login;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
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
import org.springframework.web.multipart.MultipartFile;
import org.springside.modules.security.utils.Digests;
import org.springside.modules.utils.Encodes;
import org.springside.modules.web.MediaTypes;

import com.yuedi.entity.ActionManagerment;
import com.yuedi.entity.Areas;
import com.yuedi.entity.IpLoginLog;
import com.yuedi.entity.Menu;
import com.yuedi.entity.MyPage;
import com.yuedi.entity.SalerUserinfo;
import com.yuedi.entity.Seller;
import com.yuedi.entity.UserInfo;
import com.yuedi.service.ActionManagermentService;
import com.yuedi.service.AreasService;
import com.yuedi.service.LoginLogService;
import com.yuedi.service.MenuService;
import com.yuedi.service.SalerUserinfoService;
import com.yuedi.service.SellerService;
import com.yuedi.service.UserInfoService;
import com.yuedi.util.BaiduAPI;
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
	private SellerService sellerService;
	@Autowired
	private ActionManagermentService actionManagermentService;
	@Autowired
	private SalerUserinfoService salerUserinfoService;
	@Autowired
	private MenuService menuService;
	@Autowired
	private UserInfoService userInfoservice;
	@Autowired
	private AreasService areasService;
	@Autowired
	private LoginLogService loginlongService;
	@Autowired
	private HttpServletRequest request;
	
	@RequestMapping(method = RequestMethod.GET)
	public String login() {
		return "account/login";
	}

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

		Calendar cal = Calendar.getInstance();// 使用日历类
		int year = cal.get(Calendar.YEAR);// 得到年
		int month = cal.get(Calendar.MONTH) + 1;// 得到月，因为从0开始的，所以要加1
		int day = cal.get(Calendar.DAY_OF_MONTH);// 得到天
		String date = year + "年" + month + "月" + day + "日";
		int hour=cal.get(Calendar.HOUR_OF_DAY);
		int min=cal.get(Calendar.MINUTE);
		int second=cal.get(Calendar.SECOND);
		String newSecond="0";
		if (second<10) {
			newSecond="0"+second;
		}
		String dateHMS = year + "年" + month + "月" + day + "日"+" "+hour+":"+min+":"+newSecond;
		model.addAttribute("date", date);

		UserInfo userInfo = userInfoservice.getUserInfoById(this.getCurrentUserId());
		if ("1".equals(userInfo.getResetPwdFlag())) {
			model.addAttribute("userInfo", userInfo);
			return "home/changePwd";
		} else {
			// model.addAttribute("userImg", userInfo.get);
			/*
			 * try{ Seller seller =
			 * sellerService.selectSellerById(this.getCurrentUserFranchiseesIdId
			 * ()); model.addAttribute("seller", seller);
			 * 
			 * SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			 * MyPage<ActionManagerment> page = new MyPage<ActionManagerment>();
			 * page.setSize(6); page.getParams().put("sellerId",
			 * this.getCurrentUserFranchiseesIdId()); List<ActionManagerment>
			 * actionManagermentList =
			 * actionManagermentService.selectActionManagermentLimit(page);
			 * 
			 * model.addAttribute("actionManagermentList",
			 * actionManagermentList); model.addAttribute("isSecondMenu", 0);
			 * //获取二维码统计信息 MyPage<SalerUserinfo> salerUserinfoPage = new
			 * MyPage<SalerUserinfo>(); salerUserinfoPage.setSize(6); Calendar
			 * calendar = Calendar.getInstance(); calendar.setTime(new Date());
			 * calendar.add(Calendar.MONTH, -1);
			 * salerUserinfoPage.getParams().put("sellerId",
			 * this.getCurrentUserFranchiseesIdId());
			 * salerUserinfoPage.getParams().put("beginDateTime",
			 * calendar.getTime());
			 * salerUserinfoPage.getParams().put("endDateTime", new Date());
			 * List<SalerUserinfo> salerUserinfoList =
			 * this.salerUserinfoService.
			 * findSalerUserinfoBySellIdAndDate(salerUserinfoPage); for(int i=0;
			 * i<salerUserinfoList.size(); i++) { Date createDateTime =
			 * salerUserinfoList.get(i).getCreateDateTime(); if(createDateTime
			 * != null) { String createDateTimeString =
			 * sdf.format(createDateTime);
			 * salerUserinfoList.get(i).setCreateDateTimeString
			 * (createDateTimeString); } }
			 * model.addAttribute("salerUserinfoList", salerUserinfoList);
			 * 
			 * // model.addAttribute("menuList", menuList); }catch(Exception e)
			 * { logger.error("LoginController err loginIndex", e); }
			 */
			// return "home/header";
			addLoginLog(dateHMS);
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
			Seller seller = sellerService.selectSellerById(this
					.getCurrentUserFranchiseesIdId());
			model.addAttribute("seller", seller);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			MyPage<ActionManagerment> page = new MyPage<ActionManagerment>();
			page.setSize(6);
			page.getParams().put("sellerId",
					this.getCurrentUserFranchiseesIdId());
			List<ActionManagerment> actionManagermentList = actionManagermentService
					.selectActionManagermentLimit(page);

			model.addAttribute("actionManagermentList", actionManagermentList);
			model.addAttribute("isSecondMenu", 0);
			// 获取二维码统计信息
			MyPage<SalerUserinfo> salerUserinfoPage = new MyPage<SalerUserinfo>();
			salerUserinfoPage.setSize(6);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.MONTH, -1);
			salerUserinfoPage.getParams().put("sellerId",
					this.getCurrentUserFranchiseesIdId());
			salerUserinfoPage.getParams().put("beginDateTime",
					calendar.getTime());
			salerUserinfoPage.getParams().put("endDateTime", new Date());
			List<SalerUserinfo> salerUserinfoList = this.salerUserinfoService
					.findSalerUserinfoBySellIdAndDate(salerUserinfoPage);
			for (int i = 0; i < salerUserinfoList.size(); i++) {
				Date createDateTime = salerUserinfoList.get(i)
						.getCreateDateTime();
				if (createDateTime != null) {
					String createDateTimeString = sdf.format(createDateTime);
					salerUserinfoList.get(i).setCreateDateTimeString(
							createDateTimeString);
				}
			}
			model.addAttribute("salerUserinfoList", salerUserinfoList);

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
			}
		} catch (Exception e) {
			logger.error("LoginController err changePwd", e);
		}

		return "redirect:/login/index";
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

	@RequestMapping(value = "update", method = RequestMethod.GET)
	public String updateForm(Model model, HttpServletRequest request) {
		try {
			String parentId = request.getParameter("parentId");
			model.addAttribute("parentId", parentId);
			// 获取当前用户所拥有的菜单
			List<Menu> menuList = this.getFirstMenuByUserId();
			model.addAttribute("menuList", menuList);

			Seller seller = sellerService.selectSellerById(this.getCurrentUserFranchiseesIdId());
			model.addAttribute("seller", seller);

			List<Areas> areasList = areasService.findProvince();
			model.addAttribute("areasList", areasList);

			String selledcnt = request.getParameter("selledcnt");
			model.addAttribute("selledcnt", selledcnt);
			String sellersign = request.getParameter("sellersign");
			model.addAttribute("sellersign", sellersign);

		} catch (Exception e) {
			logger.error("SellerController err updateForm", e);
		}

		return "home/sellerForm";
	}

	@RequestMapping(value = "addSeller", method = RequestMethod.POST)
	public String addSeller(
			@RequestParam(value = "file", required = false) MultipartFile file,
			Model model, @ModelAttribute("form") Seller seller,
			HttpServletRequest request) {
		try {
			if (!StringUtils.isEmpty(seller.getId().toString())
					&& !StringUtils.isEmpty(seller.getName())
					&& !StringUtils.isEmpty(seller.getProvince())
					&& !StringUtils.isEmpty(seller.getCity())
					&& !StringUtils.isEmpty(seller.getArea())) {
				List<Areas> listsheng = areasService.findAreas(Integer.parseInt(seller.getProvince()));
				String areassheng = listsheng.get(0).getName();
				List<Areas> listshi = areasService.findAreas(Integer.parseInt(seller.getCity()));
				String areasshi = listshi.get(0).getName();
				List<Areas> listqu = areasService.findAreas(Integer
						.parseInt(seller.getArea()));
				String areasqu = listqu.get(0).getName();
				String areas = areassheng + areasshi + areasqu;
				BaiduAPI getLatAndLngByBaidu = new BaiduAPI();
				if (seller.getOfficeAddr() != null) {
					Object[] o = getLatAndLngByBaidu.getCoordinate(areas
							+ seller.getOfficeAddr());
					seller.setLongitude(new Double(o[0].toString()));// 添加经度
					seller.setLatitude(new Double(o[1].toString()));// 添加纬度
				}
				seller.setObligationOrg("责任机构");// 责任机构
				seller.setBusinessScope("经营品类");
				seller.setIsDeleteFlag(false);
				seller.setCharterEffectiveDate(new Date());// 营业执照有效日
				seller.setCharterExpiredDate(new Date());// 营业执照失效日

				// 上传了新的图片
				if (file != null && file.getSize() > 0) {
					String fileName = file.getOriginalFilename();
					String realName = String.format("%1$s%2$s",
							UUID.randomUUID(),
							fileName.substring(fileName.lastIndexOf(".")));
					seller.setImg("shangjia/" + realName);

					int result = sellerService.updateSellerbyId(seller);
					// 编辑成功，删除本地目录下之前上传的图片。
					if (result > 0) {
						// String path = String.format("%1$s/%2$s",
						// request.getSession().getServletContext().getInitParameter(RESOURCE_ROOT_DIR),
						// relativePathSeller);
						String oldImgPath = relativePathSeller
								+ seller.getImg();

						File oldImgFile = new File(oldImgPath);
						if (oldImgFile.exists()) {
							oldImgFile.delete();
						}
					}
				} else {
					seller.setImg(seller.getImg());// 未上传新的图片
					sellerService.updateSellerbyId(seller);
				}
			}

		} catch (Exception e) {
			logger.error("sellerController err addSeller", e);
		}

		return "redirect:/login/index";
	}
	
	/**
	 * 登录信息查询
	 * 
	 * @author Lam
	 *
	 * @date 2015年12月25日
	 */
	@RequestMapping(value = "/listLoginLog/{parentId}", method = RequestMethod.GET)
	public String listCountInvitation(@PathVariable("parentId") Long parentId, Model model, @RequestParam(value = "page", defaultValue = "1") int pageNumber,HttpServletRequest request){
		MyPage<IpLoginLog> page = new MyPage<IpLoginLog>();
		page.setNumber(pageNumber);
				
		try {
			Long fid = this.getCurrentUserFranchiseesIdId();
			if(fid != 3){
				page.getParams().put("pid", fid);
			}
			
			String userName = request.getParameter("userName");
			if(!StringUtils.isEmpty(userName)){
				page.getParams().put("userName", userName);
			}
			model.addAttribute("userName", userName);
			
			String sellername = request.getParameter("sellerName");
			if(!StringUtils.isEmpty(sellername)){
				page.getParams().put("sellerName", sellername);
			}
			model.addAttribute("sellername", sellername);
			
			String starTime = request.getParameter("beginDateTime");
			page.getParams().put("fmSTime", starTime);
			model.addAttribute("beginDateTime", starTime);
			
			String endTime = request.getParameter("endDateTime");
			page.getParams().put("fmETime", endTime);
			model.addAttribute("endDateTime", endTime);
			
			String searchParams = null;
			searchParams = "userName=" + userName + "&sellerName=" + sellername + "&beginDateTime=" + starTime + "&endDateTime=" + endTime;
			if (!StringUtils.isEmpty(userName)||!StringUtils.isEmpty(sellername)||!StringUtils.isEmpty(starTime)||!StringUtils.isEmpty(endTime)) {
				model.addAttribute("searchParams", searchParams);
			}
			
			
			List<IpLoginLog> list = loginlongService.getLoginLogList(page);
			model.addAttribute("listCount", list);
			model.addAttribute("pageData", page);
			model.addAttribute("parentId", parentId);
						
			String titleName = "登录信息查询";
			if(!StringUtils.isEmpty(request.getParameter("titleName"))){
				titleName = request.getParameter("titleName");
			}
			model.addAttribute("titleName", titleName);
		} catch (Exception e) {
			logger.error("listLoginLog err queryLoginlog", e);
		}
		
		return "loginlog/loginlog";
	}
	

	/**
	 * 记录用户登录信息
	 * 
	 * @param date 登录日期
	 * 
	 * @author Lam
	 *
	 * @date 2015年12月24日
	 */
	private void addLoginLog(String date) {
		IpLoginLog loginLog = new IpLoginLog();
		loginLog.setUserName(this.getCurrentUserName());// 获取登录的用户名
		loginLog.setLoginIp(getIpAddr(request));// 获取登录者的真实IP
		loginLog.setLoginTime(date);// 获取登录时间
		loginLog.setSellerId(getCurrentUserFranchiseesIdId());
		loginLog.setLoginSource("悦迪云平台");// 登录系统
		loginLog.setCtime(new Date());
		// 查询商家的详细信息
		Seller sellerInfo = sellerService.selectSellerById(this.getCurrentUserFranchiseesIdId());
		if (null != sellerInfo) {
			loginLog.setSellerName(sellerInfo.getName());// 获取商家的名称
		}
		loginlongService.addLoginLog(loginLog);
	}

//	/**
//	 * 更新用户安全退出时间
//	 * 
//	 * @author Lam
//	 *
//	 * @date 2015年12月24日
//	 */
//	private void updateLoginLog(String loginLogName) {
//		System.out.println("---------"+loginLogName);
//		List<IpLoginLog> loginLogList = loginlongService.getUserLoginLog(loginLogName);
//		if (loginLogList.size() > 0) {
//			IpLoginLog loginLog = new IpLoginLog();
//			Long loginlogId = loginLogList.get(0).getId();
//			loginLog.setId(loginlogId);
//			loginLog.setLogoutTime(this.getCurrentDate());
//			loginlongService.updateLoginLog(loginLog);
//		}
//	}

}
