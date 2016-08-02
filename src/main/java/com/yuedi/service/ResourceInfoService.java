package com.yuedi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yuedi.dao.ResourceInfoDao;
import com.yuedi.entity.ResourceInfo;

@Component
@Transactional
public class ResourceInfoService {
	@Autowired
	ResourceInfoDao resouceInfoDao;
	
	public int insertSelective(ResourceInfo record){
		return resouceInfoDao.insertSelective(record);
	}
	public int updateByPrimaryKeySelective(ResourceInfo record){
		return resouceInfoDao.updateByPrimaryKeySelective(record);
	}
	
	public ResourceInfo selectByPrimaryKey(Long id){
		return resouceInfoDao.selectByPrimaryKey(id);
	}
	public ResourceInfo selectSeriesNameById(ResourceInfo resourceInfo) {
		// TODO Auto-generated method stub
		return resouceInfoDao.selectSeriesNameById(resourceInfo);
	}
}
