package com.yuedi.dao.fm;

import java.util.List;

import com.yuedi.dao.MyBatisRepository;
import com.yuedi.entity.MyPage;
import com.yuedi.entity.fm.FeedBack;

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