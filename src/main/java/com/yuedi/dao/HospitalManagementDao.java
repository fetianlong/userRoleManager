package com.yuedi.dao;

import java.util.List;

import com.yuedi.entity.HospitalManagement;

@MyBatisRepository
public interface HospitalManagementDao {
	List<HospitalManagement> selectHospitalManagementAll();
}
