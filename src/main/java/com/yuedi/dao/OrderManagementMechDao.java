package com.yuedi.dao;

import java.util.List;

import com.yuedi.entity.MyPage;
import com.yuedi.entity.OrderManagementMech;

@MyBatisRepository
public interface OrderManagementMechDao {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderManagementMech record);

    int insertOrderManagementMech(OrderManagementMech record);

    OrderManagementMech selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderManagementMech record);

    int updateByPrimaryKey(OrderManagementMech record);

	OrderManagementMech selectOrderManagementMech(String orderId);

	List<OrderManagementMech> selectOrderManagementMechLimit(MyPage<OrderManagementMech> page);
}