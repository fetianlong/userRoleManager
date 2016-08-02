package com.yuedi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuedi.dao.ActivityLineDao;
import com.yuedi.entity.ActivityLine;
import com.yuedi.entity.MyPage;

@Service
@Transactional
public class ActivityLineService {
	@Autowired
	private ActivityLineDao activityLineDao;

	public long insertActivityLine(ActivityLine activityLine){
		return activityLineDao.insertActivityLine(activityLine);
	}

	  public Integer updateActivityLine(ActivityLine activityLine){
		  return activityLineDao.updateActivityLine(activityLine);
	  }

	  public ActivityLine findActivityLineById(Long id){
		  return activityLineDao.findActivityLineById(id);
	  }

	  public Integer deleteActivityLineById(Long id){
		  return activityLineDao.deleteActivityLineById(id);
	  }

	  public List<ActivityLine> findListActivityLineByLimit(MyPage<ActivityLine> activityLinePage){
		  return activityLineDao.findListActivityLineByLimit(activityLinePage);
	  }
}