package com.yuedi.service.workFlow;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yuedi.dao.workFlow.ProcessNodeDao;
import com.yuedi.entity.MyPage;
import com.yuedi.entity.workFlow.ProcessNode;

@Component
@Transactional
public class ProcessNodeService {
	@Autowired
	private ProcessNodeDao processNodeDao;
	
	public int deleteProcessNode(Long id){
		return processNodeDao.deleteProcessNode(id);
	}

	public int insert(ProcessNode record){
		return processNodeDao.insert(record);
	}

	public int insertSelective(ProcessNode record){
		return processNodeDao.insertSelective(record);
	}

	public ProcessNode selectProcessNode(Long id){
		return processNodeDao.selectProcessNode(id);
	}

	public int updateProcessNodeSelective(ProcessNode record){
		return processNodeDao.updateProcessNodeSelective(record);
	}

	public int updateProcessNode(ProcessNode record){
		return processNodeDao.updateProcessNode(record);
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
	public List<ProcessNode> selectProcessNodeByPage(MyPage<ProcessNode> page) {
		return processNodeDao.selectProcessNodeByPage(page);
	}
}
