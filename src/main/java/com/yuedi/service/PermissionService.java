package com.yuedi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yuedi.dao.PermissionDao;
import com.yuedi.dao.RolePermissionDao;
import com.yuedi.entity.MyPage;
import com.yuedi.entity.Permission;
import com.yuedi.entity.RolePermission;

@Component
@Transactional
public class PermissionService {

	@Autowired
	private PermissionDao permissionDao;
	@Autowired
	private RolePermissionDao rolePermissionDao;
	
	public int deletePermissionById(Long id){
		return permissionDao.deleteByPrimaryKey(id);
	}

    public int addPermission(Permission record){
    	return permissionDao.insertPermission(record);
    }

    public int insertPermissionSelective(Permission record){
    	return permissionDao.insertPermissionSelective(record);
    }

    public Permission getPermissionById(Long id){
    	return permissionDao.selectByPrimaryKey(id);
    }

    public int updatePermission(Permission record){
    	return permissionDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(Permission record){
    	return permissionDao.updateByPrimaryKey(record);
    }
    
    /**
	 * 分页获取权限数据
	 * @param page 分页器
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年7月9日 上午10:49:52
	 */
	public List<Permission> selectPermissionLimit(MyPage<Permission> page) {
		return permissionDao.selectPermissionLimit(page);
	}

    /**
     * 根据用户ID获取权限
     * @param id
     * @return  
     * @description   
     * @version currentVersion  
     * @author pujh  
     * @createtime 2015年7月23日 下午2:50:20
     */
	public List<Permission> getPermissionByUserId(Long id) {
		return permissionDao.getPermissionByUserId(id);
	}
	
	public List<Permission> selectUnassignButtonByRoleId(Long roleId) {
		return permissionDao.selectUnassignButtonByRoleId(roleId);
	}
	
	public List<Permission> selectAssignButtonByRoleId(Long roleId) {
		return permissionDao.selectAssignButtonByRoleId(roleId);
	}
	
	public Long assignPermissionToRole(RolePermission rolePermission) {
		return rolePermissionDao.insertRolePermission(rolePermission);
	}
	
	public int removePermissionFromRole(Long rolePermissionId) {
		return rolePermissionDao.deleteRolePermissionById(rolePermissionId);
	}
}
