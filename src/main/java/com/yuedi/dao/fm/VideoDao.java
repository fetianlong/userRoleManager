package com.yuedi.dao.fm;

import java.util.List;

import com.yuedi.dao.MyBatisRepository;
import com.yuedi.entity.MyPage;
import com.yuedi.entity.fm.Video;
@MyBatisRepository
public interface VideoDao {
	int deleteVideoByPrimaryKey(Integer id);
    int insertVideo(Video record);
    Video selectVideoByPrimaryKey(Integer id);
    int updateVideoByPrimaryKey(Video record);
    List<Video> selectVideoLimit(MyPage<Video> page);
	int updateByPrimaryKeySelective(Video video);
}