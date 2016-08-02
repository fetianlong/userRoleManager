package com.yuedi.dao;

import java.util.List;

import com.yuedi.entity.MyPage;
import com.yuedi.entity.Position;
import com.yuedi.entity.Role;

@MyBatisRepository
public interface PositionDao {
	/**
	 * 添加岗位
	 * @param role
	 * @return
	 */
	public abstract long insertPosition(Position position);
	
	/**
	 * 更新岗位
	 * @param role
	 * @return
	 */
	public abstract int updatePositionById(Position position);
	
	/**
	 * 删除岗位
	 * @param id
	 * @return
	 */
	public abstract int deletePositionById(Long id);
	
	/**
	 * 分页获取岗位数据
	 * @param page 分页器
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年7月9日 上午10:49:52
	 */
	public List<Position> selectPositionLimit(MyPage<Position> page);
	
	/**
	 * 根据id获得岗位
	 * @param id
	 * @return
	 */
	public abstract Position selectPositionById(Long id);
	
	/**
	 * 根据用户ID获取用户当前岗位
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author 兼画  
	 * @createtime 2015年4月16日 下午5:54:10
	 */
	public List<Position> selectMyPosition(Long userId);
}
