package com.yuedi.dao;

import java.util.List;
import com.yuedi.entity.MessageMoney;
import com.yuedi.entity.MyPage;

@MyBatisRepository
public interface MessageMoneyDao {
	 public long insertMessageMoney(MessageMoney messageMoney);
	 
	 public List<MessageMoney> findMessageMoneyByLimit(MyPage<MessageMoney> page);

	 public MessageMoney findMessageMoneyById(Long id);

	 public Integer updateMessageMoney(MessageMoney messageMonty);
}
