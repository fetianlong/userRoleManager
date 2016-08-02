package com.yuedi.entity;

import java.io.Serializable;
import java.util.Date;

public class SalerUserinfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4914100743102535805L;
	private Long id;
	private Long userId;
	private Long sallerId;
	private Date createDateTime;
	private String createDateTimeString;
	private String createrName;
	private Date updateDateTime;
	private String upadateName;
	private String cardCode;
	private String userName;
	private Long clientCount;
	private String isUsed;
	
	
	private Long parentId;
	private String spec;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getSallerId() {
		return sallerId;
	}
	public void setSallerId(Long sallerId) {
		this.sallerId = sallerId;
	}
	public Date getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
	public String getCreaterName() {
		return createrName;
	}
	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}
	public Date getUpdateDateTime() {
		return updateDateTime;
	}
	public void setUpdateDateTime(Date updateDateTime) {
		this.updateDateTime = updateDateTime;
	}
	public String getUpadateName() {
		return upadateName;
	}
	public void setUpadateName(String upadateName) {
		this.upadateName = upadateName;
	}
	public String getCardCode() {
		return cardCode;
	}
	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getClientCount() {
		return clientCount;
	}
	public void setClientCount(Long clientCount) {
		this.clientCount = clientCount;
	}
	public String getCreateDateTimeString() {
		return createDateTimeString;
	}
	public void setCreateDateTimeString(String createDateTimeString) {
		this.createDateTimeString = createDateTimeString;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public String getIsUsed() {
		return isUsed;
	}
	public void setIsUsed(String isUsed) {
		this.isUsed = isUsed;
	}
}
