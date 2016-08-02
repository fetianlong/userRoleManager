package com.yuedi.entity;

import java.util.Date;

public class Paper {
    private Integer id;

    private String title;

    private String first;

    private String second;

    private String third;

    private Integer type;

    private Date ctime;
    
    private Integer orderTypeId;//试题所属的订制分类。
    
    private String orderTypeName;
    
    

    public Integer getOrderTypeId() {
		return orderTypeId;
	}

	public void setOrderTypeId(Integer orderTypeId) {
		this.orderTypeId = orderTypeId;
	}

	public String getOrderTypeName() {
		return orderTypeName;
	}

	public void setOrderTypeName(String orderTypeName) {
		this.orderTypeName = orderTypeName;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first == null ? null : first.trim();
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second == null ? null : second.trim();
    }

    public String getThird() {
        return third;
    }

    public void setThird(String third) {
        this.third = third == null ? null : third.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
}