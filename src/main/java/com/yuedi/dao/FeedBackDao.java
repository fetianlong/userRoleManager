package com.yuedi.dao;

import java.util.List;

import com.yuedi.entity.FeedBack;
import com.yuedi.entity.MyPage;

@MyBatisRepository
public interface FeedBackDao {
    int deleteByPrimaryKey(Integer id);

    int insert(FeedBack record);

    int insertSelective(FeedBack record);

    FeedBack selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FeedBack record);

    int updateByPrimaryKey(FeedBack record);

	List<FeedBack> selectFeedBackLimit(MyPage<FeedBack> page);
}