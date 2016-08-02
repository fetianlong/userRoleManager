package com.yuedi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yuedi.dao.OrderManagementMechDao;
import com.yuedi.entity.MyPage;
import com.yuedi.entity.OrderManagementMech;
import com.yuedi.entity.UserInfo;

@Component
@Transactional
public class OrderManagementMechService {
	@Autowired
	OrderManagementMechDao orderManagementMechDao;
	
	public List<OrderManagementMech> selectOrderManagementMechLimit(MyPage<OrderManagementMech> page) {
		return orderManagementMechDao.selectOrderManagementMechLimit(page);
	}
	
	public OrderManagementMech selectOrderManagementMechByOrderId(String orderId){
		return orderManagementMechDao.selectOrderManagementMech(orderId);
	}
	
	public OrderManagementMech selectOrderManagementMechById(Integer id){
		return orderManagementMechDao.selectByPrimaryKey(id);
	}
}
