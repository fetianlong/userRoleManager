package com.yuedi.dao;

import java.util.List;

import com.yuedi.entity.MyPage;
import com.yuedi.entity.Permission;

@MyBatisRepository
public interface PermissionDao {
    int deleteByPrimaryKey(Long id);

    int insertPermission(Permission record);

    int insertPermissionSelective(Permission record);

    Permission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);
    
    /**
	 * 分页获取权限数据
	 * @param page 分页器
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年7月9日 上午10:49:52
	 */
	public List<Permission> selectPermissionLimit(MyPage<Permission> page);


    /**
     * 根据用户ID获取权限
     * @param id
     * @return  
     * @description   
     * @version currentVersion  
     * @author pujh  
     * @createtime 2015年7月23日 下午2:51:16
     */
	List<Permission> getPermissionByUserId(Long id);
	
	List<Permission> selectUnassignButtonByRoleId(Long roleId);
	
	List<Permission> selectAssignButtonByRoleId(Long roleId);
 }