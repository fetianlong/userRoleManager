package com.yuedi.entity;

import java.util.Date;

public class InvitationCount {
    private Long id;

    private Long sellerId;

    private Long defaultCount;

    private Long addCount;

    private Long residueCount;

    private Long createUser;

    private Date createTime;

    private String remark;

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
}