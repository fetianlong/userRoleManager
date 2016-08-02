package com.yuedi.entity;

import java.io.Serializable;
import java.util.Date;

public class ContactPerson implements Serializable{
    /**  
	 * @fields serialVersionUID  
	*/  
		
	private static final long serialVersionUID = 1L;
	private Long id;
    private String fullName;
    private long sellerId;
    private String businessType;
    private long sonSellerId;
    private String sellerName;
    private String birthday;
    private long sex;
    private long keyPerson;	//0：关键人，1：非关键人
    private String branch;	//部门
    private String position;	//职务
    private String telephone;	//联系电话
    private String instantMessage;	//即时通讯
    private String email;
    private String personalWebsite;	//个人网站
    private String postalCode;	//邮编
    private String address;	//地址
    private String hobby;	//爱好
    private String remark;	//备注
    private long createUser;
    private String createName;
    private Date createTime;
    private long updateUser;
    private String updateName;
    private Date updateTime;
    private Long userInfoId;
    
    private Long parentId;

    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return this.id;
    }

    public void setFullName(String fullName){
        this.fullName = fullName;
    }

    public String getFullName(){
        return this.fullName;
    }

    public void setSellerId(long sellerId){
        this.sellerId = sellerId;
    }

    public long getSellerId(){
        return this.sellerId;
    }

    public void setSellerName(String sellerName){
        this.sellerName = sellerName;
    }

    public String getSellerName(){
        return this.sellerName;
    }

    public void setBirthday(String birthday){
        this.birthday = birthday;
    }

    public String getBirthday(){
        return this.birthday;
    }

    public void setSex(long sex){
        this.sex = sex;
    }

    public long getSex(){
        return this.sex;
    }

    public void setKeyPerson(long keyPerson){
        this.keyPerson = keyPerson;
    }

    public long getKeyPerson(){
        return this.keyPerson;
    }

    public void setBranch(String branch){
        this.branch = branch;
    }

    public String getBranch(){
        return this.branch;
    }

    public void setPosition(String position){
        this.position = position;
    }

    public String getPosition(){
        return this.position;
    }

    public void setTelephone(String telephone){
        this.telephone = telephone;
    }

    public String getTelephone(){
        return this.telephone;
    }

    public void setInstantMessage(String instantMessage){
        this.instantMessage = instantMessage;
    }

    public String getInstantMessage(){
        return this.instantMessage;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return this.email;
    }

    public void setPersonalWebsite(String personalWebsite){
        this.personalWebsite = personalWebsite;
    }

    public String getPersonalWebsite(){
        return this.personalWebsite;
    }

    public void setPostalCode(String postalCode){
        this.postalCode = postalCode;
    }

    public String getPostalCode(){
        return this.postalCode;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getAddress(){
        return this.address;
    }

    public void setHobby(String hobby){
        this.hobby = hobby;
    }

    public String getHobby(){
        return this.hobby;
    }

    public void setRemark(String remark){
        this.remark = remark;
    }

    public String getRemark(){
        return this.remark;
    }

    public void setCreateUser(long createUser){
        this.createUser = createUser;
    }

    public long getCreateUser(){
        return this.createUser;
    }

    public void setCreateName(String createName){
        this.createName = createName;
    }

    public String getCreateName(){
        return this.createName;
    }

    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }

    public Date getCreateTime(){
        return this.createTime;
    }

    public void setUpdateUser(long updateUser){
        this.updateUser = updateUser;
    }

    public long getUpdateUser(){
        return this.updateUser;
    }

    public void setUpdateName(String updateName){
        this.updateName = updateName;
    }

    public String getUpdateName(){
        return this.updateName;
    }

    public void setUpdateTime(Date updateTime){
        this.updateTime = updateTime;
    }

    public Date getUpdateTime(){
        return this.updateTime;
    }

	public long getSonSellerId() {
		return sonSellerId;
	}

	public void setSonSellerId(long sonSellerId) {
		this.sonSellerId = sonSellerId;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
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