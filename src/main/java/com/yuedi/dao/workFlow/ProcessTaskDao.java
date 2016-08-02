package com.yuedi.dao.workFlow;

import java.util.List;

import com.yuedi.dao.MyBatisRepository;
import com.yuedi.entity.workFlow.ProcessTask;

@MyBatisRepository
public interface ProcessTaskDao {
    int deleteByPrimaryKey(Long id);

    int insert(ProcessTask record);

    int insertSelective(ProcessTask record);

    ProcessTask selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProcessTask record);

    int updateByPrimaryKey(ProcessTask record);

    /**
     * 根据条件查询信息
     * @param pt
     * @return  
     * @description   
     * @version currentVersion  
     * @author pujh  
     * @createtime 2015年8月7日 下午2:24:56
     */
	ProcessTask getProcessTask(ProcessTask pt);
	
	/**
	 * 根据条件查询信息
	 * @param pt
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年8月7日 下午3:11:39
	 */
	List<ProcessTask> selectProcessTask(ProcessTask pt);
}