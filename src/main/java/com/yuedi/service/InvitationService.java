package com.yuedi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yuedi.dao.InvitationCountDao;
import com.yuedi.dao.InvitationDao;
import com.yuedi.entity.Invitation;
import com.yuedi.entity.InvitationCount;
import com.yuedi.entity.MyPage;

@Component
@Transactional
public class InvitationService {
	
	@Autowired
	private InvitationDao invitationDao;
	@Autowired
	private InvitationCountDao invitationCountDao;
	
	public int deleteByPrimaryKey(Integer id) {
		return invitationDao.deleteByPrimaryKey(id);
	}

	public int insertInvitationCount(InvitationCount record) {
		return invitationCountDao.insertSelective(record);
	}
	
	public int insert(Invitation record) {
		return invitationDao.insert(record);
	}

	public Invitation selectByPrimaryKey(Integer id) {
		return invitationDao.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKey(Invitation record) {
		return invitationDao.updateByPrimaryKey(record);
	}
	public int updateByPrimaryKeySelective(Invitation record) {
		return invitationDao.updateByPrimaryKeySelective(record);
	}

	public List<Invitation> selectInvitationLimit(MyPage<Invitation> page) {
		return invitationDao.selectInvitationLimit(page);
	}

	public List<Invitation> selectInvitationCountLimit(MyPage<Invitation> page) {
		return invitationDao.selectInvitationCountLimit(page);
	}
}