package com.yuedi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuedi.dao.SalerUsero2cDao;
import com.yuedi.entity.SalerUsero2c;

@Service
@Transactional
public class SalerUsero2cService {
	@Autowired
	public SalerUsero2cDao salerUsero2cDao;

	public long findClientUserCount(SalerUsero2c salerUsero2c) {
		return salerUsero2cDao.findClientUserCount(salerUsero2c);
	}
}
