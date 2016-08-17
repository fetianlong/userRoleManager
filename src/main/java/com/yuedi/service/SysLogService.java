package com.yuedi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yuedi.dao.SysLogDao;
import com.yuedi.entity.SysLog;

@Component
@Transactional
public class SysLogService {
	
	@Autowired
	SysLogDao sysLogDao;
	
	public int insert(SysLog log) {
		return sysLogDao.insert(log);
	}
}
