package com.yuedi.service;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yuedi.dao.MessageMoneyDao;
import com.yuedi.entity.MessageMoney;
import com.yuedi.entity.MyPage;
import com.yuedi.entity.Seller;

@Component
@Transactional
public class MessageMoneyService {
	@Autowired
	private MessageMoneyDao messageMoneyDao;
	@Autowired
	private SellerService sellerService;
	
	public long insertMessageMoney(MessageMoney messageMoney){
		Seller seller = sellerService.selectSellerById(messageMoney.getSellerId());
		Long messageCount = 0L;
		if(seller.getMessageCount() == null) {
			messageCount = messageMoney.getMessageCount();
		}else {
			messageCount = seller.getMessageCount() + messageMoney.getMessageCount();
		}
		messageMoney.setSurplusMessageCount(messageCount);
		
		messageMoneyDao.insertMessageMoney(messageMoney);
		
		Seller sellerParam = new Seller();
		sellerParam.setId(messageMoney.getSellerId());
		sellerParam.setMessageCount(messageCount);
		sellerService.updateSellerMessageCountBySellerId(sellerParam);
		
		return 1L;
	}
	 
	public List<MessageMoney> findMessageMoneyByLimit(MyPage<MessageMoney> page){
		List<MessageMoney> messageMoneyList = messageMoneyDao.findMessageMoneyByLimit(page);
		for(int i=0; i<messageMoneyList.size(); i++) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			MessageMoney messageMoney = messageMoneyList.get(i);
			messageMoneyList.get(i).setCreateDateTimeString(sdf.format(messageMoney.getCreateDateTime()));
		}
		return messageMoneyList;
	}

	public MessageMoney findMessageMoneyById(Long id){
		return messageMoneyDao.findMessageMoneyById(id);
	}

	public Integer updateMessageMoney(MessageMoney messageMonty){
		return messageMoneyDao.updateMessageMoney(messageMonty);
	}
}
