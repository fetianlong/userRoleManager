package com.yuedi.service;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yuedi.dao.ActionManagermentDao;
import com.yuedi.entity.ActionManagerment;
import com.yuedi.entity.MyPage;

@Component
@Transactional
public class ActionManagermentService {
	@Autowired
	private ActionManagermentDao actionManagermentDao;

	public long insertActionManagerment(ActionManagerment actionManager){
		return actionManagermentDao.insertActionManagerment(actionManager);
	}
	
	public ActionManagerment selectActionManagermentById(Long id){
		return actionManagermentDao.selectActionManagermentById(id);
	}
		
	public int updateActionManagermenById(ActionManagerment actionManagerment){
		return actionManagermentDao.updateActionManagermenById(actionManagerment);
	}
		
	public int deleteActionManagermentbyId(long id){
		return actionManagermentDao.deleteActionManagermentbyId(id);
	}
	
	public List<ActionManagerment> selectActionManagermentLimit(
			MyPage<ActionManagerment> page) {
		List<ActionManagerment> actionManagerments = actionManagermentDao.selectActionManagermentLimit(page);
		return actionManagerments;
	}
}
