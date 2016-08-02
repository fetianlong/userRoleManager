package com.yuedi.dao.workFlow;

import java.util.List;

import com.yuedi.dao.MyBatisRepository;
import com.yuedi.entity.MyPage;
import com.yuedi.entity.workFlow.ProcessInstance;

@MyBatisRepository
public interface ProcessInstanceDao {
    int deleteProcessInstance(Long id);

    int insert(ProcessInstance record);

    int insertSelective(ProcessInstance record);

    ProcessInstance selectProcessInstance(Long id);

    int updateProcessInstanceSelective(ProcessInstance record);

    int updateProcessInstance(ProcessInstance record);

	List<ProcessInstance> selectProcessInstanceByPage(MyPage<ProcessInstance> page);

	/**
	 * 根据条件获取流程实例
	 * @param pi
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年8月3日 下午5:36:30
	 */
	List<ProcessInstance> getProcessInstance(ProcessInstance pi);

	/**
	 * 获取当前节点做大的流程实例
	 * @param pi
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年8月4日 上午9:36:56
	 */
	ProcessInstance getProcessInstanceByNodeMax(ProcessInstance pi);
}