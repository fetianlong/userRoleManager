package com.yuedi.dao.fm;

import java.util.List;

import com.yuedi.dao.MyBatisRepository;
import com.yuedi.entity.MyPage;
import com.yuedi.entity.fm.Delivery;
@MyBatisRepository
public interface DeliveryDao {
	int deleteDeliveryByPrimaryKey(Integer id);
    int insertDelivery(Delivery record);
    Delivery selectDeliveryByPrimaryKey(Integer id);
    int updateDeliveryByPrimaryKey(Delivery record);
    int updateByPrimaryKeySelective(Delivery record);
    List<Delivery> selectDeliveryLimit(MyPage<Delivery> page);
}