/**
 * @type com.yuedi.web.dao.AreasDao
 */
package com.yuedi.dao;

import java.util.List;

import com.yuedi.entity.Areas;

/**
 * @author sky
 *
 * @date 2014年11月23日
 *
 */
@MyBatisRepository
public interface AreasDao {
	/**
	 * 获取省市县信息
	 * 
	 * @param parentId 父ID
	 * @return
	 */
	List<Areas> selectAreasByParentId(Integer parentId);
	/**
	 * 获取id获取地区信息
	 * 
	 * @param id
	 * @return
	 */
	List<Areas> selectAreas(Integer id);
	
	/**
	 *  根据地区的级别获取所有信息，用来自动生成圈子
	 * @param level
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author 兼画  
	 * @createtime 2015年5月1日 上午11:04:18
	 */
	List<Areas> findAreasByLevel(int level);
	
	/**
	 * 根据名称获取信息
	 * @param name
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @param level 
	 * @createtime 2015年7月2日 上午10:31:58
	 */
	Areas findAreasByName(String name, int level);
	
	
	List<Areas> findListAreasByName(Areas areas);
	Areas findNameById(Integer id);
}
