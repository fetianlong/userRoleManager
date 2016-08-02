package com.yuedi.dao;

import java.util.List;
import com.yuedi.entity.IpLoginLog;
import com.yuedi.entity.MyPage;



@MyBatisRepository
public interface LoginLogDao {
	
	/**
	 * 记录用户登录的信息
	 * 
	 */
	int insertLoginLog(IpLoginLog loginLog);
	
	/**
	 * 根据用户名倒序查询这个用户的所有登录信息
	 * 
	 * @author Lam
	 *
	 * @date 2015年12月24日
	 */
	List<IpLoginLog>selectLoginLogByUsername(String userName);
	
	/**
	 * 更新用户登录信息
	 * 
	 * @author Lam
	 *
	 * @date 2015年12月24日
	 */
	int updateLoginLogother(IpLoginLog loginLog);
	
	/**
	 * 分页查询登录信息
	 * 
	 * @author Lam
	 *
	 * @date 2015年12月25日
	 */
	List<IpLoginLog> selectLoginLogLimit(MyPage<IpLoginLog> page);
	
}
