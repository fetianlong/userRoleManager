package com.yuedi.web.common;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.yuedi.entity.Menu;
import com.yuedi.entity.Seller;
import com.yuedi.service.MenuService;
import com.yuedi.service.SellerService;
import com.yuedi.shiro.ShiroDbRealm.ShiroUser;

@Controller
@SuppressWarnings("unused")
public class SupperController {

	private static final Logger logger = Logger.getLogger(SupperController.class);
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private SellerService sellerService;

	private Long nowSize = 0L;
	/**
	 * 资源文件存储目录
	 */

	private static final String RESOURCE_ROOT_DIR = "resourceRootDir";

	/**
	 * 取出Shiro中的当前用户Id.
	 */
	protected Long getCurrentUserId() {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		
		for (int i = 0; i < 500; i++) {
			Thread th = new Thread();
			th.start();
			Thread th1 = new Thread(); 
			th1.start();
			Thread th2 = new Thread();
			th2.start();
			Seller seller = new Seller();
			seller.setName(user.sellerName);
		}
//		nowSize ++;
//		System.err.println("当前调用用户ID次数：" + nowSize);
		return user.id;
	}

	/**
	 * 取出Shiro中的当前用户加盟商id.
	 */
	protected Long getCurrentUserFranchiseesIdId() {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return user.franchiseesId;
	}

	/**
	 * 取出Shiro中的当前用户加盟商名称.
	 */
	protected String getCurrentUserFranchiseesName() {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return user.sellerName;
	}
	
	/**
	 * 取出Shiro中的当前用户名字.
	 */
	protected String getCurrentUserName() {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return user.loginName;
	}

	/**
	 * 取出Shiro中的当前用户.
	 */
	protected ShiroUser getCurrentUser() {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return user;
	}

	/**
	 * 获取当前用户所拥有的一级菜单
	 */
	protected List<Menu> getFirstMenuByUserId() {
		List<Menu> menuList = menuService.selectMenuByUserInfoId(this
				.getCurrentUserId());
		return menuList;
	}

	/**
	 * 根据父菜单查询子菜单
	 */
	protected List<Menu> getTwoMenuByParentId(Long parentId) {
		List<Menu> menuList = menuService.selectMenuByParentId(parentId);
		return menuList;
	}

	/**
	 * 根据当前一级菜单获取当前用户的二级菜单
	 * 
	 * @param userId
	 * @return
	 * @description
	 * @version currentVersion
	 * @author pujh
	 * @createtime 2015年12月23日 下午3:44:32
	 */
	protected List<Menu> getTwoMenuByUserInfoId(Long parentId) {
		Menu menu = new Menu();
		menu.setCreaterId(getCurrentUserId());
		menu.setParentId(parentId);
		List<Menu> menuList = menuService.selectSellerMenuByUserInfoId(menu);
		return menuList;
	}

	/**
	 * 获取当前用户所拥有的菜单
	 */

	/**
	 * 获取当前日期
	 * 
	 * @return
	 * @description
	 * @version currentVersion
	 * @author pujh
	 * @createtime 2015年12月23日 下午3:38:17
	 */
	protected String getCurrentDate() {
		// 获取当前日期
		Calendar cal = Calendar.getInstance();// 使用日历类
		int year = cal.get(Calendar.YEAR);// 得到年
		int month = cal.get(Calendar.MONTH) + 1;// 得到月，因为从0开始的，所以要加1
		int day = cal.get(Calendar.DAY_OF_MONTH);// 得到天
		String date = year + "年" + month + "月" + day + "日";
		return date;
	}
	
	/**
	 * 获取登录用户的真实IP
	 * 
	 * @author Lam
	 *
	 * @date 2015年12月24日
	 */

	protected String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Real-IP");
		if (null != ip && !"".equals(ip.trim())
				&& !"unknown".equalsIgnoreCase(ip)) {
			return ip;
		}
		ip = request.getHeader("X-Forwarded-For");
		if (null != ip && !"".equals(ip.trim())
				&& !"unknown".equalsIgnoreCase(ip)) {
			// get first ip from proxy ip
			int index = ip.indexOf(',');
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		}
		return request.getRemoteAddr();
	}

}
