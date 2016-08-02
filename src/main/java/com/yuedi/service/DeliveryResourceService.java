package com.yuedi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yuedi.dao.DeliveryResourceDao;
import com.yuedi.dao.DeliveryResourceDao;
import com.yuedi.entity.MyPage;
import com.yuedi.entity.DeliveryResource;

@Component
@Transactional
public class DeliveryResourceService {
	@Autowired
	private DeliveryResourceDao deliveryResourceDao;


	public int deleteDeliveryResourceByPrimaryKey(Integer DeliveryResourceId) {
		return deliveryResourceDao.deleteDeliveryResourceByPrimaryKey(DeliveryResourceId);
		
	}

	public int insertDeliveryResource(DeliveryResource or) {
		return deliveryResourceDao.insertDeliveryResource(or);
	}

	public List<DeliveryResource> selectNoResourcebyOrderId(DeliveryResource or) {
		// TODO Auto-generated method stub
		return deliveryResourceDao.selectNoResourcebyDeliveryId(or);
	}


	public List<DeliveryResource> selectSeriesByBrandCategory(DeliveryResource or) {
		// TODO Auto-generated method stub
		return deliveryResourceDao.selectSeriesByBrandCategory(or);
	}

	public List<DeliveryResource> selectResourcebyOrderId(
			MyPage<DeliveryResource> page) {
		return deliveryResourceDao.selectResourcebyDeliveryId(page);
	}

}