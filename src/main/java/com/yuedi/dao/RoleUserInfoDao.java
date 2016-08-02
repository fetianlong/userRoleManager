/**
 * @type com.yuedi.web.dao.impl.RoleUserInfoDaoImpl
 */
package com.yuedi.dao;

import com.yuedi.entity.RoleUserInfo;

/**
 * @author sky
 *
 * @date 2014年2月14日
 *
 */
@MyBatisRepository
public interface RoleUserInfoDao {
	
	public long insertRoleUserInfo(RoleUserInfo roleUserInfo);	

	public int deleteRoleUserInfoById(Long roleUserInfoId);
}
