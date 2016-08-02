package com.yuedi.dao;

import java.util.List;

import com.yuedi.entity.IpLoginLog;
import com.yuedi.entity.MyPage;
import com.yuedi.entity.UserInfo;

/**
 * 通过@MapperScannerConfigurer扫描目录中的所有接口, 动态在Spring Context中生成实现.
 * 方法名称必须与Mapper.xml中保持一致.
 * 
 * @author calvin
 */
@MyBatisRepository
public interface UserInfoDao {

	UserInfo selectUserInfoByUserName(String userName);

	List<UserInfo> selectUserInfoLimit(MyPage<UserInfo> page);

	int updateUserInfo(UserInfo userInfo);

	UserInfo getUserInfoById(Long id);

	void deleteUserInfo(Long id);

	int insertUserInfo(UserInfo user);
	
	int updateUserInfoStateById(UserInfo user);
	int updatePwd(UserInfo user);
	List<UserInfo> selectUnassignUserByRoleId(Long roleId);
	
	List<UserInfo> selectAssignUserByRoleId(Long roleId);
	UserInfo getUserInfoByPhone(String telephone);
	
	List<UserInfo> queryUserSaler(Long sellerId);
	
	List<UserInfo> queryUserBySellerId(Long sellerId);
	
	List<UserInfo> selectAllUserInfo(Long sellerId);

}
