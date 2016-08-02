package com.yuedi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuedi.dao.ActivityDao;
import com.yuedi.entity.Activity;
import com.yuedi.entity.MyPage;

@Service
@Transactional
public class ActivityService {
	@Autowired
	private ActivityDao dActivityDao;

	public Integer add(Activity model) {
		return dActivityDao.insertActivity(model);
	}

	public Integer update(Activity model) {
		return dActivityDao.update(model);
	}

	public Activity findActivityById(Integer id) {
		return dActivityDao.findActivityById(id);

	}

	public Integer deleteById(Integer id) {
		return dActivityDao.deleteActivityById(id);

	}

	public Integer findCountByActivity(Activity model) {
		return dActivityDao.findCountByActivity(model);
	}

	public List<Activity> findListActivityByLimit(MyPage<Activity> page) {
		return dActivityDao.findListActivityByLimit(page);
	}

	public String selectAcImgById(Integer id) {
		return dActivityDao.selectAcImgById(id);
	}

	public Integer updateReviewStatus(Activity model) {
		return dActivityDao.updateReviewStatus(model);
	}

	public String selectAcGroupIdById(Integer id) {
		return dActivityDao.selectAcGroupIdById(id);
	}
	
	

}
