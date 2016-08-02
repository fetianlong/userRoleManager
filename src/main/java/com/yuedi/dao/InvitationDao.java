package com.yuedi.dao;

import java.util.List;

import com.yuedi.entity.Invitation;
import com.yuedi.entity.MyPage;

@MyBatisRepository
public interface InvitationDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Invitation record);

    int insertSelective(Invitation record);

    Invitation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Invitation record);

    int updateByPrimaryKey(Invitation record);

	List<Invitation> selectInvitationLimit(MyPage<Invitation> page);

	List<Invitation> selectInvitationCountLimit(MyPage<Invitation> page);
}