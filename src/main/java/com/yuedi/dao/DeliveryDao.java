package com.yuedi.dao;

import java.util.List;

import com.yuedi.entity.MyPage;
import com.yuedi.entity.Delivery;
@MyBatisRepository
public interface DeliveryDao {
	int deleteDeliveryByPrimaryKey(Integer id);
    int insertDelivery(Delivery record);
    Delivery selectDeliveryByPrimaryKey(Integer id);
    int updateDeliveryByPrimaryKey(Delivery record);
    int updateByPrimaryKeySelective(Delivery record);
    List<Delivery> selectDeliveryLimit(MyPage<Delivery> page);
}