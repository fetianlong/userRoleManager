package com.yuedi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yuedi.dao.PositionDao;
import com.yuedi.entity.MyPage;
import com.yuedi.entity.Position;

@Component
@Transactional
public class PositionService {
	@Autowired
	private PositionDao positionDao;
	
	public long insertPosition(Position position){
		return positionDao.insertPosition(position);
	}

	public int updatePositionById(Position position){
		return positionDao.updatePositionById(position);
	}
	
	public int deletePositionById(Long id){
		return positionDao.deletePositionById(id);
	}
	
	public List<Position> selectPositionLimit(MyPage<Position> page){
		return positionDao.selectPositionLimit(page);
	}
	
	public Position selectPositionById(Long id){
		return positionDao.selectPositionById(id);
	}
	
	public List<Position> selectMyPosition(Long userId){
		return positionDao.selectMyPosition(userId);
	}
}
