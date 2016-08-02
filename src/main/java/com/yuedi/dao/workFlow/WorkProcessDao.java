package com.yuedi.dao.workFlow;

import java.util.List;

import com.yuedi.dao.MyBatisRepository;
import com.yuedi.entity.MyPage;
import com.yuedi.entity.workFlow.WorkProcess;

@MyBatisRepository
public interface WorkProcessDao {
    int deleteWorkProcess(Long id);

    int insert(WorkProcess record);

    int insertSelective(WorkProcess record);

    WorkProcess selectWorkProcess(Long id);

    int updateWorkProcessSelective(WorkProcess record);

    int updateWorkProcess(WorkProcess record);

	List<WorkProcess> selectWorkProcessByPage(MyPage<WorkProcess> page);
}