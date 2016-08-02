package com.yuedi.entity;

import java.io.Serializable;

public class Position implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3382142411939932252L;
	private Long id;
	private String name;
	private Long parentId;
	private Long level;
	private String code;
	private String remark;
	private String createTime;
	private Long createUserId;
	private String updateTime;
	private Long updateUserId;
	private Integer isFlag;
	private String standby1;
	private String standby2;
	private String standby3;
	private String standby4;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public Long getLevel() {
		return level;
	}
	public void setLevel(Long level) {
		this.level = level;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Long getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public Long getUpdateUserId() {
		return updateUserId;
	}
	public void setUpdateUserId(Long updateUserId) {
		this.updateUserId = updateUserId;
	}
	public Integer getIsFlag() {
		return isFlag;
	}
	public void setIsFlag(Integer isFlag) {
		this.isFlag = isFlag;
	}
	public String getStandby1() {
		return standby1;
	}
	public void setStandby1(String standby1) {
		this.standby1 = standby1;
	}
	public String getStandby2() {
		return standby2;
	}
	public void setStandby2(String standby2) {
		this.standby2 = standby2;
	}
	public String getStandby3() {
		return standby3;
	}
	public void setStandby3(String standby3) {
		this.standby3 = standby3;
	}
	public String getStandby4() {
		return standby4;
	}
	public void setStandby4(String standby4) {
		this.standby4 = standby4;
	}
}
