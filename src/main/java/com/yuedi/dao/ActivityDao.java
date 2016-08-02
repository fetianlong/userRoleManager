package com.yuedi.dao;

import java.util.List;

import com.yuedi.entity.Activity;
import com.yuedi.entity.MyPage;

@MyBatisRepository
public interface ActivityDao {
	Integer insertActivity(Activity model);

	Integer update(Activity model);

	Activity findActivityById(Integer id);

	Integer deleteActivityById(Integer id);

	int findCountByActivity(Activity model);

	List<Activity> findListActivityByLimit(MyPage<Activity> page);

	String selectAcImgById(Integer id);

	Integer updateReviewStatus(Activity model);

	String selectAcGroupIdById(Integer id);
}
