package com.yuedi.dao;

import java.util.List;

import com.yuedi.entity.MyPage;
import com.yuedi.entity.Video;
import com.yuedi.entity.Video;
@MyBatisRepository
public interface VideoDao {
	int deleteVideoByPrimaryKey(Integer id);
    int insertVideo(Video record);
    Video selectVideoByPrimaryKey(Integer id);
    int updateVideoByPrimaryKey(Video record);
    List<Video> selectVideoLimit(MyPage<Video> page);
}