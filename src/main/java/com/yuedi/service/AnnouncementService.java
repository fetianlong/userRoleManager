package com.yuedi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yuedi.dao.AnnouncementDao;
import com.yuedi.entity.ActionManagerment;
import com.yuedi.entity.Announcement;
import com.yuedi.entity.MyPage;

@Component
@Transactional
public class AnnouncementService {
	
	@Autowired
	private AnnouncementDao announcementDao;
	
	public long insertAnnouncement(Announcement announcement){
		
		return announcementDao.insertAnnouncement(announcement); 
		
	}
	public Integer updateAnnouncement(Announcement announcement){
		
		return announcementDao.updateAnnouncement(announcement);
		
	}

	public Announcement selectAnnouncementByPrimaryKey(Integer id){
		
		return announcementDao.selectAnnouncementById(id);
	}

	public Integer deleteAnnouncementById(Integer id){
		
		return announcementDao.deleteAnnouncementById(id);
	}
	
	public List<Announcement> selectAnnouncementLimit(
			MyPage<Announcement> page) {
		List<Announcement> announcements = announcementDao.selectAnnouncementLimit(page);
		return announcements;
		
	}

}
