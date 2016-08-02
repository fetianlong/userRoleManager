package com.yuedi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yuedi.dao.AppClientDao;
import com.yuedi.entity.AppRegister;
import com.yuedi.entity.MyPage;

@Component
@Transactional
public class AppClientService {
	@Autowired
	private AppClientDao appClientDao;
	
	//分页
	public List<AppRegister> selectUserLimit(AppRegister ar){
		return appClientDao.selectUserLimit(ar);
	}
	public List<AppRegister> getAllAppRegister(MyPage<AppRegister> page){
		return appClientDao.getAllAppRegister(page);
	}
	public long selectCountAppRegiter(AppRegister ar){
		return appClientDao.selectCountAppRegiter(ar);
	}
	public List<AppRegister> getAllAppRegisterBySellerId(MyPage<AppRegister> page){
		return appClientDao.getAllAppRegisterBySellerId(page);
	}
	public long selectCountAppRegiterBySellerId(AppRegister ar){
		return appClientDao.selectCountAppRegiterBySellerId(ar);
	}
	public List<AppRegister> getAllAppRegisterByUserId(AppRegister ar){
		return appClientDao.getAllAppRegisterByUserId(ar);
	}
	public long selectCountAppRegiterByUserId(AppRegister ar){
		return appClientDao.selectCountAppRegiterByUserId(ar);
	}
	public AppRegister getTimeByUserId(AppRegister ar){
		return appClientDao.getTimeByUserId(ar);
	}
}
