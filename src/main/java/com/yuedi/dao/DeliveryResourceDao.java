package com.yuedi.dao;

import java.util.List;

import com.yuedi.entity.MyPage;
import com.yuedi.entity.DeliveryResource;

@MyBatisRepository
public interface DeliveryResourceDao {


	int deleteDeliveryResourceByPrimaryKey(Integer DeliveryResourceId);

	int insertDeliveryResource(DeliveryResource or);

	List<DeliveryResource> selectNoResourcebyDeliveryId(DeliveryResource or);

	List<DeliveryResource> selectSeriesByBrandCategory(DeliveryResource or);

	List<DeliveryResource> selectResourcebyDeliveryId(MyPage<DeliveryResource> page);
	
//	List<DeliveryResource> allSelectResource(DeliveryResource or);
	

}