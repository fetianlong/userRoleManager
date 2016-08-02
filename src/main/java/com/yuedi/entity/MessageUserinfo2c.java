package com.yuedi.entity;

import java.util.Date;

public class MessageUserinfo2c {
	private Long id;
    private Long messageId;
    private String userinfo2cName;
    private String hobby;
    private Date messageDateTime;
    private String messageName;
    private String state;
    
    private String tel;
    
    private Long sellerId;
    
    private Long parentId;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getMessageId() {
		return messageId;
	}
	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}
	public String getUserinfo2cName() {
		return userinfo2cName;
	}
	public void setUserinfo2cName(String userinfo2cName) {
		this.userinfo2cName = userinfo2cName;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public Date getMessageDateTime() {
		return messageDateTime;
	}
	public void setMessageDateTime(Date messageDateTime) {
		this.messageDateTime = messageDateTime;
	}
	public String getMessageName() {
		return messageName;
	}
	public void setMessageName(String messageName) {
		this.messageName = messageName;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Long getSellerId() {
		return sellerId;
	}
	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
}
