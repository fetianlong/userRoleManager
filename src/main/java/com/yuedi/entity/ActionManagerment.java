package com.yuedi.entity;

import java.io.Serializable;
import java.util.Date;

public class ActionManagerment implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6057709876191015589L;
	private Long id;
	private String actionTitle;
	private String contacts;
	private String actionType;
	private String state;
	private String location;
	private String accomplishResults;
	private String isClose;
	private Long sellerId;
	private Long executorId;
	private String executorName;
	private String clientName;
	private String beginDateTime;
	private String beginDateTimeString;
	private String endDateTime;
	private String endDateTimeString;
	private String spec;
	private Long businessOpportunityId;
	private Long userInfoId;
	
	private Long parentId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getActionTitle() {
		return actionTitle;
	}
	public void setActionTitle(String actionTitle) {
		this.actionTitle = actionTitle;
	}
	
	public String getContacts() {
		return contacts;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getAccomplishResults() {
		return accomplishResults;
	}
	public void setAccomplishResults(String accomplishResults) {
		this.accomplishResults = accomplishResults;
	}
	public String getIsClose() {
		return isClose;
	}
	public void setIsClose(String isClose) {
		this.isClose = isClose;
	}
	public Long getExecutorId() {
		return executorId;
	}
	public void setExecutorId(Long executorId) {
		this.executorId = executorId;
	}
	public String getExecutorName() {
		return executorName;
	}
	public void setExecutorName(String executorName) {
		this.executorName = executorName;
	}
	
	public String getBeginDateTime() {
		return beginDateTime;
	}
	public void setBeginDateTime(String beginDateTime) {
		this.beginDateTime = beginDateTime;
	}
	public String getEndDateTime() {
		return endDateTime;
	}
	public void setEndDateTime(String endDateTime) {
		this.endDateTime = endDateTime;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public Long getSellerId() {
		return sellerId;
	}
	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}
	public String getBeginDateTimeString() {
		return beginDateTimeString;
	}
	public void setBeginDateTimeString(String beginDateTimeString) {
		this.beginDateTimeString = beginDateTimeString;
	}
	public String getEndDateTimeString() {
		return endDateTimeString;
	}
	public void setEndDateTimeString(String endDateTimeString) {
		this.endDateTimeString = endDateTimeString;
	}
	public Long getBusinessOpportunityId() {
		return businessOpportunityId;
	}
	public void setBusinessOpportunityId(Long businessOpportunityId) {
		this.businessOpportunityId = businessOpportunityId;
	}
	public Long getUserInfoId() {
		return userInfoId;
	}
	public void setUserInfoId(Long userInfoId) {
		this.userInfoId = userInfoId;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
}
