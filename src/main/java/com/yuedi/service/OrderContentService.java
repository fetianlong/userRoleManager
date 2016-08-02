package com.yuedi.service;

import java.util.List;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yuedi.dao.OrderContentDao;
import com.yuedi.entity.MyPage;
import com.yuedi.entity.OrderContent;
@Component
@Transactional
public class OrderContentService {
	@Autowired
	private OrderContentDao orderContentDao;
	
	 public int deleteOrderContentByPrimaryKey(Integer id){
		 return  orderContentDao.deleteOrderContentByPrimaryKey(id);
	 }

	 public int insertOrderContent(OrderContent record){
	    	return orderContentDao.insertOrderContent(record);
	    }

	 public  OrderContent selectOrderContentByPrimaryKey(Integer id){
	    	return orderContentDao.selectOrderContentByPrimaryKey(id);
	    }

	  public int updateOrderContentByPrimaryKey(OrderContent record){
	    	return orderContentDao.updateOrderContentByPrimaryKey(record);
	    }
	    
	   public  List<OrderContent> selectOrderContentLimit(MyPage<OrderContent> page){
	    	List<OrderContent> orderContents=orderContentDao.selectOrderContentLimit(page);
	    		 return orderContents;
	    }
}