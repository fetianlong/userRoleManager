package com.yuedi.dao;

import java.util.List;

import com.yuedi.entity.Announcement;
import com.yuedi.entity.MyPage;
import com.yuedi.entity.OrderType;

@MyBatisRepository
public interface OrderTypeDao{
	
    int deleteOrderTypeByPrimaryKey(Integer id);

    int insertOrderType(OrderType record);

    OrderType selectOrderTypeByPrimaryKey(Integer id);

    int updateOrderTypeByPrimaryKey(OrderType record);
    
    List<OrderType> selectOrderTypeLimit(MyPage<OrderType> page);
    List<OrderType> selectAllOrderType();
}