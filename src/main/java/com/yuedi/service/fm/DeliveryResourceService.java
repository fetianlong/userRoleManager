package com.yuedi.service.fm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yuedi.dao.fm.DeliveryResourceDao;
import com.yuedi.entity.MyPage;
import com.yuedi.entity.fm.DeliveryResource;

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

	/**
	 * 获取APP端资源
	 * @param or
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年12月31日 下午7:45:39
	 */
	public List<DeliveryResource> selectAppResourceInfo(DeliveryResource or) {
		// TODO Auto-generated method stub
		return deliveryResourceDao.selectAppResourceInfo(or);
	}

}