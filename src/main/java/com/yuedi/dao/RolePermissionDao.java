package com.yuedi.dao;

import com.yuedi.entity.RolePermission;


@MyBatisRepository
public interface RolePermissionDao {
	public long insertRolePermission(RolePermission rolePermission);	

	public int deleteRolePermissionById(Long id);
}
