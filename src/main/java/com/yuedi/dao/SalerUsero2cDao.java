package com.yuedi.dao;

import com.yuedi.entity.SalerUsero2c;

@MyBatisRepository
public interface SalerUsero2cDao {
	public long findClientUserCount(SalerUsero2c salerUsero2c);
}
