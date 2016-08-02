package com.yuedi.dao;

import java.util.List;

import com.yuedi.entity.MyPage;
import com.yuedi.entity.Saler;
import com.yuedi.entity.SalerUserinfo;

@MyBatisRepository
public interface SalerUserinfoDao {
	public List<SalerUserinfo> findSalerUserinfoBySellIdAndDate(MyPage<SalerUserinfo> page);
	
	public List<SalerUserinfo> findSalerUserinfoBySalerId(Long salerId);
	
	public int updateUserInfoBySalerId(SalerUserinfo salerUserinfo);
	
	public long insertSalerUserinfo(SalerUserinfo salerUserinfo);
	
	//根据二维码ID查找已经分配的用户名
	public abstract SalerUserinfo selectSalerBySalerID(Long salerId);
}
