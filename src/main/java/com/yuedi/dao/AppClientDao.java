package com.yuedi.dao;

import java.util.List;

import com.yuedi.entity.AppRegister;
import com.yuedi.entity.MyPage;

@MyBatisRepository
public interface AppClientDao {
	//分页
	public abstract List<AppRegister> selectUserLimit(AppRegister ar);
	public List<AppRegister> getAllAppRegister(MyPage<AppRegister> page);
	public abstract long selectCountAppRegiter(AppRegister ar);
	public abstract List<AppRegister> getAllAppRegisterBySellerId(MyPage<AppRegister> page);
	public abstract long selectCountAppRegiterBySellerId(AppRegister ar);
	public abstract List<AppRegister> getAllAppRegisterByUserId(AppRegister ar);
	public abstract long selectCountAppRegiterByUserId(AppRegister ar);
	public abstract AppRegister getTimeByUserId(AppRegister ar);
}
