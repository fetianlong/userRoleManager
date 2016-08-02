package com.yuedi.dao;

import java.util.List;

import com.yuedi.entity.Announcement;
import com.yuedi.entity.MyPage;


@MyBatisRepository
public interface AnnouncementDao {
	
	 int insertAnnouncement(Announcement record);
	
     int deleteAnnouncementById(Integer id);

     int updateAnnouncement(Announcement record);

    Announcement selectAnnouncementById(Integer id);

    List<Announcement> selectAnnouncementLimit(MyPage<Announcement> page);
}