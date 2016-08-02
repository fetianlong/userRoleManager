package com.yuedi.service.workFlow;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yuedi.dao.workFlow.WorkProcessDao;
import com.yuedi.entity.MyPage;
import com.yuedi.entity.workFlow.WorkProcess;

@Component
@Transactional
public class WorkProcessService {
	@Autowired
	private WorkProcessDao workFlowDao;
	
	public int deleteWorkProcess(Long id){
		return workFlowDao.deleteWorkProcess(id);
	}

	public int insert(WorkProcess record){
		return workFlowDao.insert(record);
	}

	public int insertSelective(WorkProcess record){
		return workFlowDao.insertSelective(record);
	}

	public WorkProcess selectWorkProcess(Long id){
		return workFlowDao.selectWorkProcess(id);
	}

	public int updateWorkProcessSelective(WorkProcess record){
		return workFlowDao.updateWorkProcessSelective(record);
	}

	public int updateWorkProcess(WorkProcess record){
		return workFlowDao.updateWorkProcess(record);
	}

	/**
	 * 分页获取信息
	 * @param page
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年7月30日 上午10:31:39
	 */
	public List<WorkProcess> selectWorkProcessByPage(MyPage<WorkProcess> page) {
		return workFlowDao.selectWorkProcessByPage(page);
	}
}
