package com.yuedi.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yuedi.dao.SalerUserinfoDao;
import com.yuedi.entity.MyPage;
import com.yuedi.entity.SalerUserinfo;
import com.yuedi.entity.SalerUsero2c;

@Component
@Transactional
public class SalerUserinfoService{
	@Autowired
	private SalerUserinfoDao salerUserinfoDao;
	@Autowired
	private SalerUsero2cService salerUsero2cService;
	
	public List<SalerUserinfo> querySalerUserinfoBySalerId(Long salerId){
		return salerUserinfoDao.findSalerUserinfoBySalerId(salerId);
	}
	
	public int updateUserInfoBySalerId(SalerUserinfo salerUserinfo){
		return salerUserinfoDao.updateUserInfoBySalerId(salerUserinfo);
	}
	
	public long insertSalerUserinfo(SalerUserinfo salerUserinfo){
		return salerUserinfoDao.insertSalerUserinfo(salerUserinfo);
	}

	public List<SalerUserinfo> findSalerUserinfoBySellIdAndDate(
			MyPage<SalerUserinfo> page) {
		List<SalerUserinfo> salerUserinfos = salerUserinfoDao.findSalerUserinfoBySellIdAndDate(page);
		if(salerUserinfos != null && salerUserinfos.size() != 0){
			for(int i=0;i<salerUserinfos.size();i++) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date createDateTime = salerUserinfos.get(i).getCreateDateTime();
				salerUserinfos.get(i).setCreateDateTimeString(sdf.format(createDateTime));
				if(salerUserinfos.get(i).getUpdateDateTime() == null) {
					if(((Date)page.getParams().get("beginDateTime")).getTime() <= salerUserinfos.get(i).getCreateDateTime().getTime()) {
						SalerUsero2c salerUsero2c = new SalerUsero2c();
						salerUsero2c.setSalerId(Long.parseLong(salerUserinfos.get(i).getCardCode()));
						salerUsero2c.setBeginDateTime(salerUserinfos.get(i).getCreateDateTime());
						salerUsero2c.setEndDateTime((Date)page.getParams().get("beginDateTime"));
						long clientCount = salerUsero2cService.findClientUserCount(salerUsero2c);
						salerUserinfos.get(i).setClientCount(clientCount);
					}
				}else {
					if(((Date)page.getParams().get("beginDateTime")).getTime() <= salerUserinfos.get(i).getCreateDateTime().getTime()) {
						SalerUsero2c salerUsero2c = new SalerUsero2c();
						salerUsero2c.setSalerId(Long.parseLong(salerUserinfos.get(i).getCardCode()));
						salerUsero2c.setBeginDateTime((Date)page.getParams().get("beginDateTime"));
						salerUsero2c.setEndDateTime((Date)page.getParams().get("endDateTime"));
						long clientCount = salerUsero2cService.findClientUserCount(salerUsero2c);
						salerUserinfos.get(i).setClientCount(clientCount);
					}else if(((Date)page.getParams().get("beginDateTime")).getTime() <= salerUserinfos.get(i).getCreateDateTime().getTime() 
							&& ((Date)page.getParams().get("endDateTime")).getTime() <= salerUserinfos.get(i).getUpdateDateTime().getTime()) {
						SalerUsero2c salerUsero2c = new SalerUsero2c();
						salerUsero2c.setSalerId(Long.parseLong(salerUserinfos.get(i).getCardCode()));
						salerUsero2c.setBeginDateTime(salerUserinfos.get(i).getCreateDateTime());
						salerUsero2c.setEndDateTime((Date)page.getParams().get("endDateTime"));
						long clientCount = salerUsero2cService.findClientUserCount(salerUsero2c);
						salerUserinfos.get(i).setClientCount(clientCount);
					}else if(((Date)page.getParams().get("beginDateTime")).getTime() > salerUserinfos.get(i).getCreateDateTime().getTime() 
							&& ((Date)page.getParams().get("endDateTime")).getTime() <= salerUserinfos.get(i).getUpdateDateTime().getTime()) {
						SalerUsero2c salerUsero2c = new SalerUsero2c();
						salerUsero2c.setSalerId(Long.parseLong(salerUserinfos.get(i).getCardCode()));
						salerUsero2c.setBeginDateTime(salerUserinfos.get(i).getCreateDateTime());
						salerUsero2c.setEndDateTime((Date)page.getParams().get("endDateTime"));
						long clientCount = salerUsero2cService.findClientUserCount(salerUsero2c);
						salerUserinfos.get(i).setClientCount(clientCount);
					}else if(((Date)page.getParams().get("beginDateTime")).getTime() > salerUserinfos.get(i).getCreateDateTime().getTime() 
							&& ((Date)page.getParams().get("beginDateTime")).getTime() <= salerUserinfos.get(i).getUpdateDateTime().getTime()
							&& ((Date)page.getParams().get("endDateTime")).getTime() > salerUserinfos.get(i).getUpdateDateTime().getTime()) {
						SalerUsero2c salerUsero2c = new SalerUsero2c();
						salerUsero2c.setSalerId(Long.parseLong(salerUserinfos.get(i).getCardCode()));
						salerUsero2c.setBeginDateTime(salerUserinfos.get(i).getCreateDateTime());
						salerUsero2c.setEndDateTime((Date)page.getParams().get("endDateTime"));
						long clientCount = salerUsero2cService.findClientUserCount(salerUsero2c);
						salerUserinfos.get(i).setClientCount(clientCount);
					}else if(((Date)page.getParams().get("beginDateTime")).getTime() >= salerUserinfos.get(i).getCreateDateTime().getTime() 
							&& ((Date)page.getParams().get("endDateTime")).getTime() >= salerUserinfos.get(i).getUpdateDateTime().getTime()) {
						SalerUsero2c salerUsero2c = new SalerUsero2c();
						salerUsero2c.setSalerId(Long.parseLong(salerUserinfos.get(i).getCardCode()));
						salerUsero2c.setBeginDateTime(salerUserinfos.get(i).getCreateDateTime());
						salerUsero2c.setEndDateTime(salerUserinfos.get(i).getUpdateDateTime());
						long clientCount = salerUsero2cService.findClientUserCount(salerUsero2c);
						salerUserinfos.get(i).setClientCount(clientCount);
					}else if(((Date)page.getParams().get("beginDateTime")).getTime() < salerUserinfos.get(i).getCreateDateTime().getTime() 
							&& ((Date)page.getParams().get("endDateTime")).getTime() > salerUserinfos.get(i).getUpdateDateTime().getTime()) {
						SalerUsero2c salerUsero2c = new SalerUsero2c();
						salerUsero2c.setSalerId(Long.parseLong(salerUserinfos.get(i).getCardCode()));
						salerUsero2c.setBeginDateTime(salerUserinfos.get(i).getCreateDateTime());
						salerUsero2c.setEndDateTime(salerUserinfos.get(i).getUpdateDateTime());
						long clientCount = salerUsero2cService.findClientUserCount(salerUsero2c);
						salerUserinfos.get(i).setClientCount(clientCount);
					}
				}
			}
		}
		
		return salerUserinfos;
	}
}
