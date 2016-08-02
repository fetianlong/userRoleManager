package com.yuedi.service.fm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yuedi.dao.fm.VideoDao;
import com.yuedi.entity.MyPage;
import com.yuedi.entity.fm.Video;

@Component
@Transactional
public class VideoService {
	@Autowired
	private VideoDao videoDao;

	public int deleteVideoByPrimaryKey(Integer id) {
		return videoDao.deleteVideoByPrimaryKey(id);
	}

	public int insertVideo(Video record) {
		return videoDao.insertVideo(record);
	}

	public Video selectVideoByPrimaryKey(Integer id) {
		return videoDao.selectVideoByPrimaryKey(id);
	}

	public int updateVideoByPrimaryKey(Video record) {
		return videoDao.updateVideoByPrimaryKey(record);
	}

	public List<Video> selectVideoLimit(
			MyPage<Video> page) {
		return videoDao.selectVideoLimit(page);
	}

	public int updateByPrimaryKeySelective(Video video) {
		return videoDao.updateByPrimaryKeySelective(video);
	}
}