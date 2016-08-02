package com.yuedi.service;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yuedi.dao.MessageUserinfo2cDao;
import com.yuedi.entity.Dictionary;
import com.yuedi.entity.Message;
import com.yuedi.entity.MessageUserinfo2c;
import com.yuedi.entity.Seller;
import com.yuedi.util.activityMessage.ActivityMessage;
import com.yuedi.util.activityMessage.DirectSendDTO;

@Component
@Transactional
public class MessageUserinfo2cService {
	public static final String APIKEY = "be314c48a08ea45b701b413e2c31da34";
	@Autowired
	MessageUserinfo2cDao messageUserinfo2cDao;
	@Autowired
	DictionaryService dictionaryService;
	@Autowired
	MessageService messageService;
	@Autowired
	SellerService sellerService;
	
	public long insertMessageUserinfo2c(MessageUserinfo2c messageUserinfo2c){
		return messageUserinfo2cDao.insertMessageUserinfo2c(messageUserinfo2c);
	}
	
	public List<MessageUserinfo2c> findUndistributedUserinfo2c(MessageUserinfo2c messageUserinfo2c){
		//查询该条短信已分配的用户
		List<MessageUserinfo2c> messageUserinfo2cParam = messageUserinfo2cDao.findUserinfo2cByMessageId(messageUserinfo2c.getMessageId());
		//代表该条短信曾经分配过用户
		if(messageUserinfo2cParam != null && messageUserinfo2cParam.size() != 0) {
			//从数据字典表里面得到允许短信间隔发送时间
			Dictionary dictionary = dictionaryService.getDictionaryByCode("messageIntervalTime");
			String content = dictionary.getContent();
			//间隔时间
			Integer messageIntervalTime = Integer.parseInt(content.split("/")[0]);
			//间隔天数
			Integer day = messageIntervalTime/24;
	        Calendar c = Calendar.getInstance();
	        c.add(Calendar.DATE, -day);
	        messageUserinfo2c.setMessageDateTime(c.getTime());
	        //查找在规定小时里再没有发短信的用户
	        return messageUserinfo2cDao.findUndistributedUserinfo2c(messageUserinfo2c);
	    //代表该条短信从未分配用户
		}else {
			return messageUserinfo2cDao.findAllUndistributedUserinfo2c(messageUserinfo2c.getSellerId());
		}
	}
	
	public List<MessageUserinfo2c> findDistributedUserinfo2c(MessageUserinfo2c messageUserinfo2c){
		//从数据字典表里面得到允许短信间隔发送时间
		Dictionary dictionary = dictionaryService.getDictionaryByCode("messageIntervalTime");
		String content = dictionary.getContent();
		//间隔时间
		Integer messageIntervalTime = Integer.parseInt(content.split("/")[0]);
		//间隔天数
		Integer day = messageIntervalTime/24;
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -day);
        messageUserinfo2c.setMessageDateTime(c.getTime());
        
		return messageUserinfo2cDao.findDistributedUserinfo2c(messageUserinfo2c);
	}
	
	public MessageUserinfo2c findUserinfo2cByMessageIdAndUserinfo2cName(MessageUserinfo2c messageUserinfo2c){
		return messageUserinfo2cDao.findUserinfo2cByMessageIdAndUserinfo2cName(messageUserinfo2c);
	}
	
	public Integer updateMessageUserinfo2cByMessageIdAndUserinfo2cName(MessageUserinfo2c messageUserinfo2c){
		return messageUserinfo2cDao.updateMessageUserinfo2cByMessageIdAndUserinfo2cName(messageUserinfo2c);
	}
	
	public void distributedUserinfo2cForm(MessageUserinfo2c messageUserinfo2c) {
		String[] userinfo2cNames = messageUserinfo2c.getUserinfo2cName().split(",");
		if(userinfo2cNames != null && userinfo2cNames.length != 0) {
			for(int i=0; i<userinfo2cNames.length; i++) {
				MessageUserinfo2c messageUserinfo2cParam = new MessageUserinfo2c();
				messageUserinfo2cParam.setMessageId(messageUserinfo2c.getMessageId());
				messageUserinfo2cParam.setUserinfo2cName(userinfo2cNames[i]);
				messageUserinfo2cParam.setState("1");
				messageUserinfo2cParam.setHobby("1");
				//查找该条短信分配的某个用户
				MessageUserinfo2c messageUserinfo2cParam1 = messageUserinfo2cDao.findUserinfo2cByMessageIdAndUserinfo2cName(messageUserinfo2cParam);
				//如果该条短信已经分配了该用户就更新该用户状态为创建状态
				if(messageUserinfo2cParam1 != null) {
					messageUserinfo2cDao.updateMessageUserinfo2cByMessageIdAndUserinfo2cName(messageUserinfo2cParam);
				//如果该条短信没有分配了该用户就新增
				}else {
					messageUserinfo2cDao.insertMessageUserinfo2c(messageUserinfo2cParam);
				}
			}
			//查找该条短信分配的用户
			List<MessageUserinfo2c> distributedUserinfo2cList = messageUserinfo2cDao.findDistributedUserinfo2c(messageUserinfo2c);
			Message message = new Message();
			message.setId(messageUserinfo2c.getMessageId());
			message.setPersonCount(distributedUserinfo2cList.size());
			//记录短信所发人数
			messageService.updateMessagePersonCount(message);
			
		}
	}
	
	public void removeUserinfo2c(MessageUserinfo2c messageUserinfo2c) {
		String[] userinfo2cNames = messageUserinfo2c.getUserinfo2cName().split(",");
		if(userinfo2cNames != null && userinfo2cNames.length != 0) {
			for(int i=0; i<userinfo2cNames.length; i++) {
				MessageUserinfo2c messageUserinfo2cParam = new MessageUserinfo2c();
				messageUserinfo2cParam.setMessageId(messageUserinfo2c.getMessageId());
				messageUserinfo2cParam.setUserinfo2cName(userinfo2cNames[i]);
				messageUserinfo2cParam.setState("2");
				//标示该用户为未分配
				messageUserinfo2cDao.updateMessageUserinfo2cByMessageIdAndUserinfo2cName(messageUserinfo2cParam);
			}
			
			//查找该条短信分配的用户
			List<MessageUserinfo2c> distributedUserinfo2cList = messageUserinfo2cDao.findDistributedUserinfo2c(messageUserinfo2c);
			Message message = messageService.findMessageById(messageUserinfo2c.getMessageId());
			int personCount = distributedUserinfo2cList.size();
			message.setPersonCount(personCount);
			//更新申请短信人数
			messageService.updateMessagePersonCount(message);
		}
		
		/*List<MessageUserinfo2c> distributedUserinfo2cList = messageUserinfo2cDao.findDistributedUserinfo2c(messageUserinfo2c);
		if(distributedUserinfo2cList.size() == 0) {
			Message message = new Message();
			message.setId(messageUserinfo2c.getMessageId());
			message.setState("1");
			messageService.updateMessageState(message);
		}*/
	}
	
	public void adoptMessage(MessageUserinfo2c messageUserinfo2c) throws IOException {
		//发短信
		List<MessageUserinfo2c> messageUserinfo2cList = messageUserinfo2cDao.findUserinfo2cListByMessageId(messageUserinfo2c.getMessageId());
		Message message = messageService.findMessageById(messageUserinfo2c.getMessageId());
		Seller seller = sellerService.selectSellerById(message.getSellerId());
		if(messageUserinfo2cList != null && messageUserinfo2cList.size() != 0 && 
				seller != null && seller.getMessageCount() != null && seller.getMessageCount() != 0) {
			//机构短信数
			Long messageCount = seller.getMessageCount();
			//判断该商户是否有多余短信
			if( messageCount >= messageUserinfo2cList.size()) {
				Long messageCountParam = messageCount -messageUserinfo2cList.size();
				StringBuffer sb = new StringBuffer();
				//获取客户所有电话号码并且以;分割
				for(int i=0; i<messageUserinfo2cList.size(); i++) {
					MessageUserinfo2c messageUserinfo2cParam = messageUserinfo2cList.get(i);
					String tel = messageUserinfo2cParam.getTel();
					sb.append(tel+";");
					/*messageUserinfo2cParam.setMessageDateTime(new Date());
					messageUserinfo2cDao.updateMessageUserinfo2cMessageDateTime(messageUserinfo2cParam);*/
				}
				//发送短信
				String messageContext = message.getMessageContext();
				DirectSendDTO directSendDTO = new DirectSendDTO();
				directSendDTO.setPhones(sb.toString());
				String str = java.net.URLEncoder.encode("["+seller.getName()+"]"+messageContext+"APP下载请访问mumfans.com（回T退订）【妈妈范】","UTF-8");
				directSendDTO.setContent(str);
				String flag = ActivityMessage.directSend(directSendDTO);
				
				//重置短信状态为审核通过
				message.setState("4");
				message.setMessageDateTime(new Date());
				message.setIsSuccess(flag);
				messageService.updateMessageStateAndMessageDateTime(message);
				//重置该商家短信数
				Seller sellerParam = new Seller();
				sellerParam.setId(seller.getId());
				sellerParam.setMessageCount(messageCountParam);
				sellerService.updateSellerMessageCountBySellerId(sellerParam);
			}
		}
	}
}
