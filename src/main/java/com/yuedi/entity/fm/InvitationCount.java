package com.yuedi.entity.fm;

import java.util.Date;

public class InvitationCount {
    private Long id;

    private Long sellerId;

    private Long defaultCount;	//默认数量

    private Long addCount;	//后期增加的数量
    private Long residueCount;	//剩余数量
    private Long allCount;	//总数（默认数量+后期增加的数量）
    private Long yetCount;	//已用数量(总数-剩余数量)


    private Long createUser;

    private Date createTime;

    private String remark;

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

    public Long getDefaultCount() {
        return defaultCount;
    }

    public void setDefaultCount(Long defaultCount) {
        this.defaultCount = defaultCount;
    }

    public Long getAddCount() {
        return addCount;
    }

    public void setAddCount(Long addCount) {
        this.addCount = addCount;
    }

    public Long getResidueCount() {
        return residueCount;
    }

    public void setResidueCount(Long residueCount) {
        this.residueCount = residueCount;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getAllCount() {
		return allCount;
	}

	public void setAllCount(Long allCount) {
		this.allCount = allCount;
	}

	public Long getYetCount() {
		return yetCount;
	}

	public void setYetCount(Long yetCount) {
		this.yetCount = yetCount;
	}
}