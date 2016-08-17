/**
 * @type com.yuedi.demo.service.UserInfoService
 */
package com.yuedi.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.security.utils.Digests;
import org.springside.modules.utils.Encodes;

import com.yuedi.dao.UserInfoDao;
import com.yuedi.entity.MyPage;
import com.yuedi.entity.RoleUserInfo;
import com.yuedi.entity.UserInfo;


/**
 * @author sky
 * 
 * @date 2013-10-26
 * 
 */
//@Service("userInfoService")
@Component
@Transactional
public class UserInfoService {
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	private static final int SALT_SIZE = 8;
	
	private UserInfoDao userInfoDao;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	public void setUserInfoDao(UserInfoDao userInfoDao) {
		this.userInfoDao = userInfoDao;
	}


	/**
	 * 分页获取用户数据
	 * @param page 分页器
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年7月9日 上午10:49:52
	 */
	public List<UserInfo> selectUserInfoLimit(MyPage<UserInfo> page) {
		return userInfoDao.selectUserInfoLimit(page);
	}
	
	public List<UserInfo> selectUnassignUserByRoleId(Long roleId) {
		return userInfoDao.selectUnassignUserByRoleId(roleId);
	}
	
	public List<UserInfo> selectAssignUserByRoleId(Long roleId) {
		return userInfoDao.selectAssignUserByRoleId(roleId);
	}

	/**
	 * 根据用户名获取用户信息
	 * @param username
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年7月9日 上午10:54:26
	 */
	public UserInfo selectUserInfoByUserName(String username) {
		return userInfoDao.selectUserInfoByUserName(username);
	}

	/**
	 * 根据ID获取用户信息
	 * @param id
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年7月16日 上午11:39:55
	 */
	public UserInfo getUserInfoById(Long id) {
		return userInfoDao.getUserInfoById(id);
	}


	public void deleteUserInfo(Long id) {
		userInfoDao.deleteUserInfo(id);
	}


	public int saveUserInfo(UserInfo user) {
		entryptPassword(user);
		Integer userId = userInfoDao.insertUserInfo(user);
		
		RoleUserInfo ruinfo = new RoleUserInfo();
		ruinfo .setRoleId(user.getRoleId().longValue());
		ruinfo.setUserInfoId(userId.longValue());
		roleService.assignUserToRole(ruinfo);
		
		return userId;
	}


	public int updateUserInfo(UserInfo user) {
		return userInfoDao.updateUserInfo(user);
	}

	public int freezeUser(Long userInfoId){
		UserInfo user = new UserInfo();
		user.setId(userInfoId);
		user.setState(2);
		return userInfoDao.updateUserInfoStateById(user);
	}
	
	public int recover(Long userInfoId) {
		UserInfo user = new UserInfo();
		user.setId(userInfoId);
		user.setState(1);
		return userInfoDao.updateUserInfoStateById(user);
	}
	
	public int resetPwdById(UserInfo userInfo) {
		entryptPassword(userInfo);
		return userInfoDao.updatePwd(userInfo);
	}
	
	public boolean checkExistByUserName(String userName) {
		UserInfo user = userInfoDao.selectUserInfoByUserName(userName);
		return (user != null);
	}

	/**
	 * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
	 */
	private void entryptPassword(UserInfo user) {
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		
		user.setSalt(Encodes.encodeHex(salt));

		byte[] hashPassword = Digests.sha1(user.getPlainPassword().getBytes(), salt, HASH_INTERATIONS);
		user.setPwd(Encodes.encodeHex(hashPassword));
	}
	
	/**
	 * 加载未绑定二维码的业务员数据
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author 兼画  
	 * @createtime 2015年4月27日 下午3:38:58
	 */
	public List<UserInfo> queryUserSaler(Long sellerId) {
		return userInfoDao.queryUserSaler(sellerId);
	}
	
}
