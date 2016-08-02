package com.yuedi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yuedi.dao.GestationInfoDao;
import com.yuedi.entity.GestationInfo;

@Component
@Transactional
public class GestationInfoService {
	@Autowired
	private GestationInfoDao gestationInfoDao;
	
	public Long insertGestationInfo(GestationInfo gestationInfo){
		return gestationInfoDao.insertGestationInfo(gestationInfo);
	}

	public Integer updateGestationInfoById(GestationInfo gestationInfo){
		return gestationInfoDao.updateGestationInfoById(gestationInfo);
	}

	public GestationInfo selectGestationInfoById(Long id){
		return gestationInfoDao.selectGestationInfoById(id);
	}

	public List<GestationInfo> selectGestationInfoByStudentInfoId(Long studentsInfoId){
		return gestationInfoDao.selectGestationInfoByStudentInfoId(studentsInfoId);
	}
}
