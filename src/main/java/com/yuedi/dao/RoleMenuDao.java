/**
 * @type com.yuedi.web.dao.impl.RoleUserInfoDaoImpl
 */
package com.yuedi.dao;

import java.util.List;

import com.yuedi.entity.RoleMenu;

/**
 * @author sky
 *
 * @date 2014年2月14日
 *
 */
@MyBatisRepository
public interface RoleMenuDao {
	
	public int insertRoleMenu(List<RoleMenu> list);	

	public int deleteRoleMenuById(Long id);
}
