package com.yuedi.dao.fm;

import java.util.List;

import com.yuedi.dao.MyBatisRepository;
import com.yuedi.entity.MyPage;
import com.yuedi.entity.fm.Invitation;

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