package com.yuedi.dao;

import java.util.List;

import com.yuedi.entity.MyPage;
import com.yuedi.entity.OrderResource;

@MyBatisRepository
public interface OrderResourceDao {


	int deleteOrderResourceByPrimaryKey(Integer orderResourceId);

	int insertOrderResource(OrderResource or);

	List<OrderResource> selectNoResourcebyOrderId(OrderResource or);

	List<OrderResource> selectSeriesByBrandCategory(OrderResource or);

	List<OrderResource> selectResourcebyOrderId(MyPage<OrderResource> page);
	
//	List<OrderResource> allSelectResource(OrderResource or);
	

}