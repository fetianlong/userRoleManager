package com.yuedi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yuedi.dao.OrderTypeDao;
import com.yuedi.entity.Announcement;
import com.yuedi.entity.MyPage;
import com.yuedi.entity.OrderType;
@Component
@Transactional
public class OrderTypeService {
	
	@Autowired
	private OrderTypeDao orderTypeDao;
	
    public int deleteOrderTypeByPrimaryKey(Integer id){
    	return orderTypeDao.deleteOrderTypeByPrimaryKey(id);
    }

    public int insertOrderType(OrderType orderType){
		return orderTypeDao.insertOrderType(orderType);
    	
    }
   
    public OrderType selectOrderTypeByPrimaryKey(Integer id){
    	return orderTypeDao.selectOrderTypeByPrimaryKey(id);
    }

   public  int updateOrderTypeByPrimaryKey(OrderType orderType){
    	return orderTypeDao.updateOrderTypeByPrimaryKey(orderType);
    }
    public List<OrderType> selectOrderTypeLimit(
			MyPage<OrderType> page) {
    	
    	List<OrderType> orderTypes=orderTypeDao.selectOrderTypeLimit(page);
    	return orderTypes;
    }
    public List<OrderType> selectAllOrderType(){
    	return orderTypeDao.selectAllOrderType();
    }
}