package com.yuedi.entity;

import java.util.Date;

public class AppRegister{

	private String userName;//app用户名称
	private Long userId;//业务员Id
	private Long sellerId;//商家id
	private Long salerId;//二维码
	private String sellerName;//商家名称
	private Date createDateTime;//注册时间
	private String tel;//电话
	private Date birthday;//预产期
	private Date registerStartTime;
	private Date registerEndTime;
	private Date loginTime;//登录时间
	private Date loginStartTime;
	private Date loginEndTime;
	private int sex;//性别
	private Long hospital;//医院id
	private String hospitalName;//医院名字
	private int sellerBySelect;//商家id
	private int status;//状态
	private int origin;//来源  二维码 或手机app注册
	private int provinceId;//省id
	private int cityId;//市id
	private String provinceName;//省name
	private String cityName;//市name
	
	
	
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	public Date getRegisterStartTime() {
		return registerStartTime;
	}
	public void setRegisterStartTime(Date registerStartTime) {
		this.registerStartTime = registerStartTime;
	}
	public Date getRegisterEndTime() {
		return registerEndTime;
	}
	public void setRegisterEndTime(Date registerEndTime) {
		this.registerEndTime = registerEndTime;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	public Date getLoginStartTime() {
		return loginStartTime;
	}
	public void setLoginStartTime(Date loginStartTime) {
		this.loginStartTime = loginStartTime;
	}
	public Date getLoginEndTime() {
		return loginEndTime;
	}
	public void setLoginEndTime(Date loginEndTime) {
		this.loginEndTime = loginEndTime;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public Long getHospital() {
		return hospital;
	}
	public void setHospital(Long hospital) {
		this.hospital = hospital;
	}
	
	
	public int getSellerBySelect() {
		return sellerBySelect;
	}
	public void setSellerBySelect(int sellerBySelect) {
		this.sellerBySelect = sellerBySelect;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getOrigin() {
		return origin;
	}
	public void setOrigin(int origin) {
		this.origin = origin;
	}
	public int getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	public Long getSalerId() {
		return salerId;
	}
	public void setSalerId(Long salerId) {
		this.salerId = salerId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
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
	public Date getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
	

	
	
	
}
