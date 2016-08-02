package com.yuedi.dao;

import java.util.List;

import com.yuedi.entity.Message;
import com.yuedi.entity.MyPage;
import com.yuedi.entity.Seller;

@MyBatisRepository
public interface MessageDao {
	
	 public long insertMessage(Message message);

	 public Integer updateMessage(Message message);

	 public Message findMessageById(Long id);

	 public Integer deleteMessageById(Long id);

	 public List<Message> findMessageByLimit(MyPage<Message> page);
	 
	 public List<Message> findMessageByCenterLimit(MyPage<Message> page);
	 
	 public Integer updateMessageState(Message message);
	 
	 public Integer updateMessageStateAndMessageDateTime(Message message);
	 
	 public Integer updateMessageStateAndAllpyDateTime(Message message);
	 
	 public Integer updateMessageStateAndNotgoReason(Message message);
	 /**
	  * 更新申请短信人数
	  * @param message
	  * @return
	  */
	 public Integer updateMessagePersonCount(Message message);
}
