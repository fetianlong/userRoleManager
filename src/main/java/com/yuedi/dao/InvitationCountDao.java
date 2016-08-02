package com.yuedi.dao;

import com.yuedi.entity.InvitationCount;

@MyBatisRepository
public interface InvitationCountDao {
    int deleteByPrimaryKey(Long id);

    int insert(InvitationCount record);

    int insertSelective(InvitationCount record);

    InvitationCount selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(InvitationCount record);

    int updateByPrimaryKey(InvitationCount record);
}