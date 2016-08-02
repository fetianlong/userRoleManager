package com.yuedi.quickstart;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.Assert;
import org.springside.modules.security.utils.Digests;
import org.springside.modules.utils.Encodes;

import com.yuedi.dao.UserInfoDao;
import com.yuedi.entity.MyPage;
import com.yuedi.entity.UserInfo;

public class LoginTest {

	private ClassPathXmlApplicationContext context;
	private UserInfoDao userInfoDao;
	
	@Before
	public void setUpBeforeClass() throws Exception {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		userInfoDao = (UserInfoDao) context.getBean("userInfoDao");
	}

	@Test
	public void test() {
		MyPage<UserInfo> page = new MyPage<UserInfo>();
		page.setNumber(1);
//		page.getParams().put("username", "admin");
		List<UserInfo> list = userInfoDao.selectUserInfoLimit(page);
		UserInfo uf = list.get(0);
		System.out.println(uf.getId());
		/*
		//1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		//2、得到SecurityManager实例并绑定给SecurityUtils
		org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		//3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("zhuwei", "ec4734eaf83c9c5e89552a889644e1a181cc0987");
		try {
		//4、登录，即身份验证
			subject.login(token);
			System.out.println(" 登录成功");
		} catch (AuthenticationException e) {
			System.out.println(" 登录失败");
			e.printStackTrace();
		//5、身份验证失败
		}
//		Assert.assertEquals(true, subject.isAuthenticated()); //断言用户已经登录
		//6、退出
		subject.logout();
		*/
		/*
		String userName = "admin";
		String newPassword = "AdminYuedi";
		UserInfo userInfo = userInfoDao.selectUserInfoByUserName(userName);
		System.out.println(userInfo.getSalt() + "  密码：" + userInfo.getPwd());
		getSaltAndPassWord(userInfo,newPassword);
		*/
	}

	
	/**
	 * 把用户原来的密码重新加密
	 * @param userInfo  
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @param newPassword 
	 * @createtime 2015年7月9日 下午3:51:37
	 */
	public void getSaltAndPassWord(UserInfo userInfo, String newPassword){
		byte[] salt = Digests.generateSalt(8);
		userInfo.setSalt(Encodes.encodeHex(salt));
		
		if("".equals(newPassword)){
			newPassword = userInfo.getPwd();
		}
		byte[] hashPassword = Digests.sha1(newPassword.getBytes(), salt, 1024);
		String newP = Encodes.encodeHex(hashPassword);
		userInfo.setPwd(Encodes.encodeHex(hashPassword));
		
		if(userInfoDao.updateUserInfo(userInfo)>0){
			System.out.println("更新成功！");
			System.out.println("新密码：" + newP);
		}
	}
	
	/**
	 * 根据盐值和密码进行加密
	 * @param salt
	 * @param password  
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年7月9日 下午3:52:15
	 */
	public void getPassWordBySalt(String s, String password){
		System.out.println("老盐值：" + s);
		byte[] salt = Encodes.decodeHex(s);
		byte[] hashPassword = Digests.sha1(password.getBytes(), salt, 1024);
		String p = Encodes.encodeHex(hashPassword);
		System.out.println("密码：" + p);
	}
}
