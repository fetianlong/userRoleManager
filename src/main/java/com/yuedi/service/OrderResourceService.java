package com.yuedi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yuedi.dao.OrderResourceDao;
import com.yuedi.entity.MyPage;
import com.yuedi.entity.OrderResource;

@Component
@Transactional
public class OrderResourceService {
	@Autowired
	private OrderResourceDao orderResourceDao;


	public int deleteOrderResourceByPrimaryKey(Integer orderResourceId) {
		return orderResourceDao.deleteOrderResourceByPrimaryKey(orderResourceId);
		
	}

	public int insertOrderResource(OrderResource or) {
		return orderResourceDao.insertOrderResource(or);
	}

	public List<OrderResource> selectNoResourcebyOrderId(OrderResource or) {
		// TODO Auto-generated method stub
		return orderResourceDao.selectNoResourcebyOrderId(or);
	}


	public List<OrderResource> selectSeriesByBrandCategory(OrderResource or) {
		// TODO Auto-generated method stub
		return orderResourceDao.selectSeriesByBrandCategory(or);
	}

	public List<OrderResource> selectResourcebyOrderId(
			MyPage<OrderResource> page) {
		return orderResourceDao.selectResourcebyOrderId(page);
	}
//	public List<OrderResource> allSelectResource(OrderResource or) {
//		// TODO Auto-generated method stub
//		return orderResourceDao.allSelectResource(or);
//	}

}