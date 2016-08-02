package com.yuedi.dao.workFlow;

import java.util.List;

import com.yuedi.dao.MyBatisRepository;
import com.yuedi.entity.MyPage;
import com.yuedi.entity.workFlow.ProcessNode;

@MyBatisRepository
public interface ProcessNodeDao {
    int deleteProcessNode(Long id);

    int insert(ProcessNode record);

    int insertSelective(ProcessNode record);

    ProcessNode selectProcessNode(Long id);

    int updateProcessNodeSelective(ProcessNode record);

    int updateProcessNode(ProcessNode record);

	List<ProcessNode> selectProcessNodeByPage(MyPage<ProcessNode> page);

	List<ProcessNode> getProcessNode(ProcessNode pn);

}