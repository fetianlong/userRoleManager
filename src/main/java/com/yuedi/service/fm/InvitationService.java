package com.yuedi.service.fm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yuedi.dao.fm.InvitationCountDao;
import com.yuedi.dao.fm.InvitationDao;
import com.yuedi.entity.MyPage;
import com.yuedi.entity.fm.Invitation;
import com.yuedi.entity.fm.InvitationCount;

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

	public InvitationCount getInvitationCountBySellerId( Long sellerId) {
		return invitationCountDao.selectInvitationCountBySellerId(sellerId);
	}

	public int updateInvitationCountSelective(InvitationCount invc) {
		return invitationCountDao.updateByPrimaryKeySelective(invc);
	}

	/**
	 * 根据机构获取邀请码数量统计
	 * @param page
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年12月25日 下午4:08:03
	 */
	public List<InvitationCount> selectInvitationCountBySellerLimit(MyPage<Invitation> page) {
		// TODO Auto-generated method stub
		return invitationCountDao.selectInvitationCountBySellerLimit(page);
	}
}