package com.yuedi.entity;

import java.io.Serializable;
import java.util.Date;

public class SalerUsero2c implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -439926006036862195L;
	
	private Long id;
	private Long salerId;
	private Long sellerId;
	private Long userId;
	private Integer isDeleteFlag;
	private Date createDateTime;
	private Date beginDateTime;
	private Date endDateTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getSalerId() {
		return salerId;
	}
	public void setSalerId(Long salerId) {
		this.salerId = salerId;
	}
	public Long getSellerId() {
		return sellerId;
	}
	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Integer getIsDeleteFlag() {
		return isDeleteFlag;
	}
	public void setIsDeleteFlag(Integer isDeleteFlag) {
		this.isDeleteFlag = isDeleteFlag;
	}
	public Date getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
	public Date getBeginDateTime() {
		return beginDateTime;
	}
	public void setBeginDateTime(Date beginDateTime) {
		this.beginDateTime = beginDateTime;
	}
	public Date getEndDateTime() {
		return endDateTime;
	}
	public void setEndDateTime(Date endDateTime) {
		this.endDateTime = endDateTime;
	}
}
