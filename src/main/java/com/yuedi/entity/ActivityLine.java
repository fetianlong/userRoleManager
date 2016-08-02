package com.yuedi.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ActivityLine implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7908725710188848684L;
	
	private Long id;
    private String activityName;
    private String activityType;
    private String activityAddr;
    private String activityPurpose;
    private BigDecimal expectedTotalCost;
    private long expectedCluesNumber;
    private long sellerId;
    private long excutedId;
    private String excutedName;
    private String beginDateTime;
    private String endDateTime;
    private Date createDateTime;
    private String createName;
    private Date updateDateTime;
    private String updateName;
    private String spec;
    private Long userInfoId;
    
    private Long parentId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public String getActivityType() {
		return activityType;
	}
	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}
	public String getActivityPurpose() {
		return activityPurpose;
	}
	public void setActivityPurpose(String activityPurpose) {
		this.activityPurpose = activityPurpose;
	}
	public BigDecimal getExpectedTotalCost() {
		return expectedTotalCost;
	}
	public void setExpectedTotalCost(BigDecimal expectedTotalCost) {
		this.expectedTotalCost = expectedTotalCost;
	}
	public long getExpectedCluesNumber() {
		return expectedCluesNumber;
	}
	public void setExpectedCluesNumber(long expectedCluesNumber) {
		this.expectedCluesNumber = expectedCluesNumber;
	}
	public long getSellerId() {
		return sellerId;
	}
	public void setSellerId(long sellerId) {
		this.sellerId = sellerId;
	}
	public long getExcutedId() {
		return excutedId;
	}
	public void setExcutedId(long excutedId) {
		this.excutedId = excutedId;
	}
	public String getExcutedName() {
		return excutedName;
	}
	public void setExcutedName(String excutedName) {
		this.excutedName = excutedName;
	}
	public Date getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	public Date getUpdateDateTime() {
		return updateDateTime;
	}
	public void setUpdateDateTime(Date updateDateTime) {
		this.updateDateTime = updateDateTime;
	}
	public String getUpdateName() {
		return updateName;
	}
	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public String getActivityAddr() {
		return activityAddr;
	}
	public void setActivityAddr(String activityAddr) {
		this.activityAddr = activityAddr;
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
}
