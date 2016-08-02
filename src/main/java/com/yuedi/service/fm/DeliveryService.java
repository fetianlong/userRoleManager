package com.yuedi.service.fm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yuedi.dao.fm.DeliveryDao;
import com.yuedi.entity.MyPage;
import com.yuedi.entity.fm.Delivery;

@Component
@Transactional
public class DeliveryService {
	@Autowired
	private DeliveryDao deliveryDao;

	public int deleteDeliveryByPrimaryKey(Integer id) {
		return deliveryDao.deleteDeliveryByPrimaryKey(id);
	}

	public int insertDelivery(Delivery record) {
		return deliveryDao.insertDelivery(record);
	}

	public Delivery selectDeliveryByPrimaryKey(Integer id) {
		return deliveryDao.selectDeliveryByPrimaryKey(id);
	}

	public int updateDeliveryByPrimaryKey(Delivery record) {
		return deliveryDao.updateDeliveryByPrimaryKey(record);
	}
	public int updateByPrimaryKeySelective(Delivery record) {
		return deliveryDao.updateByPrimaryKeySelective(record);
	}

	public List<Delivery> selectDeliveryLimit(
			MyPage<Delivery> page) {
		return deliveryDao.selectDeliveryLimit(page);
	}
}