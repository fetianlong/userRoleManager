package com.yuedi.entity;

import java.util.Date;

public class Message {
	private Long id;
    private Long sellerId;
    private String messageContext;
    private String applyReason;
    private String notgoReason;
    private String state;
    private Date createDateTime;
    private String createrName;
    private Date applyDateTime;
    private String applyDateTimeString;
    private Date messageDateTime;
    private String messageDateTimeString;
    private int personCount;
    private Long province;
    private String provinceName;
    private Long city;
    private String cityName;
    private Long area;
    private String isSuccess;
    private String areaName;
    
    private String sellerName;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getSellerId() {
		return sellerId;
	}
	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}
	public String getMessageContext() {
		return messageContext;
	}
	public void setMessageContext(String messageContext) {
		this.messageContext = messageContext;
	}
	public String getApplyReason() {
		return applyReason;
	}
	public void setApplyReason(String applyReason) {
		this.applyReason = applyReason;
	}
	public String getNotgoReason() {
		return notgoReason;
	}
	public void setNotgoReason(String notgoReason) {
		this.notgoReason = notgoReason;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCreaterName() {
		return createrName;
	}
	public Date getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
	public Date getApplyDateTime() {
		return applyDateTime;
	}
	public void setApplyDateTime(Date applyDateTime) {
		this.applyDateTime = applyDateTime;
	}
	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}
	public String getSellerName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	public Long getProvince() {
		return province;
	}
	public void setProvince(Long province) {
		this.province = province;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public Long getCity() {
		return city;
	}
	public void setCity(Long city) {
		this.city = city;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public Long getArea() {
		return area;
	}
	public void setArea(Long area) {
		this.area = area;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public Date getMessageDateTime() {
		return messageDateTime;
	}
	public void setMessageDateTime(Date messageDateTime) {
		this.messageDateTime = messageDateTime;
	}
	public int getPersonCount() {
		return personCount;
	}
	public void setPersonCount(int personCount) {
		this.personCount = personCount;
	}
	public String getApplyDateTimeString() {
		return applyDateTimeString;
	}
	public void setApplyDateTimeString(String applyDateTimeString) {
		this.applyDateTimeString = applyDateTimeString;
	}
	public String getMessageDateTimeString() {
		return messageDateTimeString;
	}
	public void setMessageDateTimeString(String messageDateTimeString) {
		this.messageDateTimeString = messageDateTimeString;
	}
	public String getIsSuccess() {
		return isSuccess;
	}
	public void setIsSuccess(String isSuccess) {
		this.isSuccess = isSuccess;
	}
}
