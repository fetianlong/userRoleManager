package com.yuedi.entity;

import java.io.Serializable;

public class Permission implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 7473503267506156130L;

	private Integer id;

    private String name;

    private String value;
    
    private Long parentId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
}