package com.yuedi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yuedi.dao.HospitalManagementDao;
import com.yuedi.entity.HospitalManagement;

@Component
@Transactional
public class HospitalManagementService {
	@Autowired
	private HospitalManagementDao hospitalManagementDao;
	
	public List<HospitalManagement> selectHospitalManagementAll(){
		return hospitalManagementDao.selectHospitalManagementAll();
	}
}
