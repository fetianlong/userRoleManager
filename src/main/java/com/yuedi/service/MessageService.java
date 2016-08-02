package com.yuedi.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yuedi.dao.MessageDao;
import com.yuedi.entity.Message;
import com.yuedi.entity.MyPage;

@Component
@Transactional
public class MessageService {
	@Autowired
	private MessageDao messageDao;
	@Autowired
	private MessageUserinfo2cService messageUserinfo2cService;
	
	 public long insertMessage(Message message){
		 return messageDao.insertMessage(message);
	 }

	 public Integer updateMessage(Message message){
		 return messageDao.updateMessage(message);
	 }

	 public Message findMessageById(Long id){
		 return messageDao.findMessageById(id);
	 }

	 public Integer deleteMessageById(Long id){
		 return messageDao.deleteMessageById(id);
	 }

	 public List<Message> findMessageByLimit(MyPage<Message> page){
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 List<Message> messageList = messageDao.findMessageByLimit(page);
		 for(int i=0;i<messageList.size();i++) {
			 if(messageList.get(i).getApplyDateTime() != null) {
				 Date applyDateTime = messageList.get(i).getApplyDateTime();
				 messageList.get(i).setApplyDateTimeString(sdf.format(applyDateTime));
			 }
			 if(messageList.get(i).getMessageDateTime() != null) {
				 Date messageDateTime = messageList.get(i).getMessageDateTime();
				 messageList.get(i).setMessageDateTimeString(sdf.format(messageDateTime));
			 }
			 
		 }
		 return messageList;
	 }
	 
	 public List<Message> findMessageByCenterLimit(MyPage<Message> page){
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 List<Message> messageList = messageDao.findMessageByCenterLimit(page);
		 for(int i=0;i<messageList.size();i++) {
			 if(messageList.get(i).getApplyDateTime() != null) {
				 Date applyDateTime = messageList.get(i).getApplyDateTime();
				 messageList.get(i).setApplyDateTimeString(sdf.format(applyDateTime));
			 }
			 if(messageList.get(i).getMessageDateTime() != null) {
				 Date messageDateTime = messageList.get(i).getMessageDateTime();
				 messageList.get(i).setMessageDateTimeString(sdf.format(messageDateTime));
			 }
			 
		 }
		 return messageList;
	 }
	
	 public Integer updateMessageState(Message message){
		 return messageDao.updateMessageState(message);
	 }
	 
	 public Integer updateMessageStateAndMessageDateTime(Message message){
		 return messageDao.updateMessageStateAndMessageDateTime(message);
	 }
	 
	 public Integer updateMessageStateAndAllpyDateTime(Message message){
		 return messageDao.updateMessageStateAndAllpyDateTime(message);
	 }
	 
	 public Integer updateMessageStateAndNotgoReason(Message message){
		 return messageDao.updateMessageStateAndNotgoReason(message);
	 }
	 
	 public Integer updateMessagePersonCount(Message message){
		 return messageDao.updateMessagePersonCount(message);
	 }
}
