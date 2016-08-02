package com.yuedi.entity;

import java.util.Date;

public class MessageMoney {
	private Long id;
    private Long sellerId;
    private String sellerName;
    private Long messageCount;
    private Long surplusMessageCount;
    private Float money;
    private String createName;
    private Date createDateTime;
    private String createDateTimeString;
    private String updateName;
    private Date updateDateTime;
    private String spec;
    
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
	public String getSellerName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	public Long getMessageCount() {
		return messageCount;
	}
	public void setMessageCount(Long messageCount) {
		this.messageCount = messageCount;
	}
	public Long getSurplusMessageCount() {
		return surplusMessageCount;
	}
	public void setSurplusMessageCount(Long surplusMessageCount) {
		this.surplusMessageCount = surplusMessageCount;
	}
	public Float getMoney() {
		return money;
	}
	public void setMoney(Float money) {
		this.money = money;
	}
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	public Date getCreateDateTime() {
		return createDateTime;
	}
	public String getUpdateName() {
		return updateName;
	}
	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}
	public Date getUpdateDateTime() {
		return updateDateTime;
	}
	public void setUpdateDateTime(Date updateDateTime) {
		this.updateDateTime = updateDateTime;
	}
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public String getCreateDateTimeString() {
		return createDateTimeString;
	}
	public void setCreateDateTimeString(String createDateTimeString) {
		this.createDateTimeString = createDateTimeString;
	}
}
