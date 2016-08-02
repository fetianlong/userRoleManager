package com.yuedi.dao;

import java.util.List;

import com.yuedi.entity.ActivityLine;
import com.yuedi.entity.MyPage;

@MyBatisRepository
public interface ActivityLineDao {

  public long insertActivityLine(ActivityLine activityLine);

  public Integer updateActivityLine(ActivityLine activityLine);

  public ActivityLine findActivityLineById(Long id);

  public Integer deleteActivityLineById(Long id);

  public List<ActivityLine> findListActivityLineByLimit(MyPage<ActivityLine> activityLinePage);
}