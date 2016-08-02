package com.yuedi.dao.workFlow;

import com.yuedi.dao.MyBatisRepository;
import com.yuedi.entity.workFlow.ApprovalOpinion;

@MyBatisRepository
public interface ApprovalOpinionDao {
    int deleteByPrimaryKey(Long id);

    int insert(ApprovalOpinion record);

    int insertSelective(ApprovalOpinion record);

    ApprovalOpinion selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ApprovalOpinion record);

    int updateByPrimaryKey(ApprovalOpinion record);
}