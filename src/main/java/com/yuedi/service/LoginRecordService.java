package com.yuedi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yuedi.dao.LoginRecordDao;
import com.yuedi.entity.LoginRecord;
import com.yuedi.entity.MyPage;

@Component
@Transactional
public class LoginRecordService {
	
	@Autowired
	LoginRecordDao loginRecordDao;
	
	public List<LoginRecord> selectLoginRecordLimit(MyPage<LoginRecord> page){
		return loginRecordDao.selectLoginRecordLimit(page);
	}
}
