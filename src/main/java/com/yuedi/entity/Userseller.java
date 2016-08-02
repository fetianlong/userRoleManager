package com.yuedi.entity;

import java.io.Serializable;
import java.util.Date;

public class Userseller implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -439926006036862195L;
	
	private Long id;
	private Long sellerID;
	private Long userID;
	private Integer rType;
	private Long tDCodeID;
	private Integer ordIndex;
	private Date createDateTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getSellerID() {
		return sellerID;
	}
	public void setSellerID(Long sellerID) {
		this.sellerID = sellerID;
	}
	public Long getUserID() {
		return userID;
	}
	public void setUserID(Long userID) {
		this.userID = userID;
	}
	public Integer getrType() {
		return rType;
	}
	public void setrType(Integer rType) {
		this.rType = rType;
	}
	public Long gettDCodeID() {
		return tDCodeID;
	}
	public void settDCodeID(Long tDCodeID) {
		this.tDCodeID = tDCodeID;
	}
	public Integer getOrdIndex() {
		return ordIndex;
	}
	public void setOrdIndex(Integer ordIndex) {
		this.ordIndex = ordIndex;
	}
	public Date getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
}
