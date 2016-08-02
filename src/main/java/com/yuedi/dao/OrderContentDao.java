package com.yuedi.dao;

import java.util.List;

import com.yuedi.entity.MyPage;
import com.yuedi.entity.OrderContent;

@MyBatisRepository
public interface OrderContentDao {
	
	 	int deleteOrderContentByPrimaryKey(Integer id);

	    int insertOrderContent(OrderContent record);

	    OrderContent selectOrderContentByPrimaryKey(Integer id);

	    int updateOrderContentByPrimaryKey(OrderContent record);
	    
	    List<OrderContent> selectOrderContentLimit(MyPage<OrderContent> page);
}