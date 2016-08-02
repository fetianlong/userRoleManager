package com.yuedi.dao.fm;

import java.util.List;

import com.yuedi.dao.MyBatisRepository;
import com.yuedi.entity.MyPage;
import com.yuedi.entity.fm.Invitation;
import com.yuedi.entity.fm.InvitationCount;

@MyBatisRepository
public interface InvitationCountDao {
    int deleteByPrimaryKey(Long id);

    int insert(InvitationCount record);

    int insertSelective(InvitationCount record);

    InvitationCount selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(InvitationCount record);

    int updateByPrimaryKey(InvitationCount record);

	InvitationCount selectInvitationCountBySellerId(Long sellerId);

	List<InvitationCount> selectInvitationCountBySellerLimit(MyPage<Invitation> page);
}