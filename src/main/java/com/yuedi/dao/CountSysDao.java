package com.yuedi.dao;

import java.util.List;

import com.yuedi.vo.ClassCntVo;
import com.yuedi.vo.ClipCntVo;
import com.yuedi.vo.OrderCntVo;

@MyBatisRepository
public interface CountSysDao {

	/**
	 * 获取课程完成状态
	 * @param stauts
	 * @return
	 */
	List<ClassCntVo> Classcnt(Integer status);
	
	
	/**
	 * 获取孕症完成状态
	 * @param stauts
	 * @return
	 */
	List<OrderCntVo> Ordercnt(Integer status);
	
	
	/**
	 * 获取剪辑数
	 * @param 
	 * @return
	 */
	List<ClipCntVo> Clipcnt();
	
	
	/**
	 * 获取剪辑人数
	 * @param 
	 * @return
	 */
	List<ClipCntVo> Popcnt();
	
	/**
	 * 获取测试人数
	 * @param 
	 * @return
	 */
	List<ClipCntVo> Testcnt();
	
	/**
	 * 课程作业统计
	 * @param stauts
	 * @return
	 */
	List<ClassCntVo> Workcnt(Integer status);
	
	
	/**
	 * 获取课程访问数
	 * @param 
	 * @return
	 */
	List<ClipCntVo> classNum();
	
	/**
	 * 获取作业访问数
	 * @param 
	 * @return
	 */
	List<ClipCntVo> workNum();
}
