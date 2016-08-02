package com.yuedi.entity;

import java.io.Serializable;

public class SalerDeclare implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2164151740558680617L;
	
	private Long id;
	private Long sellerId;
	private String declareName;
	private Long declareTel;
	private int declareNumber;
	private String state;
	private String spec;
	private Long parentId;
	
	private String name;
	
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
	public String getDeclareName() {
		return declareName;
	}
	public void setDeclareName(String declareName) {
		this.declareName = declareName;
	}
	public Long getDeclareTel() {
		return declareTel;
	}
	public void setDeclareTel(Long declareTel) {
		this.declareTel = declareTel;
	}
	public int getDeclareNumber() {
		return declareNumber;
	}
	public void setDeclareNumber(int declareNumber) {
		this.declareNumber = declareNumber;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
