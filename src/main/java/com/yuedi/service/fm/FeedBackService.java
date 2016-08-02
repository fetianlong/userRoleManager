package com.yuedi.service.fm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yuedi.dao.fm.FeedBackDao;
import com.yuedi.entity.MyPage;
import com.yuedi.entity.fm.FeedBack;

@Component
@Transactional
public class FeedBackService {
	
	@Autowired
	FeedBackDao feedBackDao;
	
	/**
	 * 分页查询分娩结果
	 * @param page
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年12月14日 下午4:24:46
	 */
	public List<FeedBack> selectFeedBackLimit(MyPage<FeedBack> page) {
		return feedBackDao.selectFeedBackLimit(page);
	}
}
