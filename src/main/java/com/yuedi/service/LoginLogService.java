package com.yuedi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yuedi.dao.LoginLogDao;
import com.yuedi.entity.IpLoginLog;
import com.yuedi.entity.MyPage;


@Component
@Transactional
public class LoginLogService {
	@Autowired
	public LoginLogDao dao;

	/**
	 * 记录用户登录信息
	 * 
	 * @author Lam
	 *
	 * @date 2015年12月24日
	 */
	public int addLoginLog(IpLoginLog loginLog) {
		return dao.insertLoginLog(loginLog);
	}

	/**
	 * 根据用户名倒序查询这个用户的所有登录信息
	 * 
	 * @author Lam
	 *
	 * @date 2015年12月24日
	 */
	public List<IpLoginLog> getUserLoginLog(String userName) {
		return dao.selectLoginLogByUsername(userName);
	}
	
	/**
	 * 记录用户登录信息
	 * 
	 * @author Lam
	 *
	 * @date 2015年12月24日
	 */
	public int updateLoginLog(IpLoginLog loginLog) {
		return dao.updateLoginLogother(loginLog);
	}
	
	/**
	 * 分页查询登录信息
	 * 
	 * @author Lam
	 *
	 * @date 2015年12月25日
	 */
	public List<IpLoginLog> getLoginLogList(MyPage<IpLoginLog> page) {
		return dao.selectLoginLogLimit(page);
	}
	

}
