package com.yuedi.entity;

import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Seller implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long parentId;

    private String loginName;	//登陆名

    private String orgCode;

    private String name;

    private String remark;

    private Integer category;
    
    private Integer dictionaryId;

    private String businessScope;

    private String tel;

    private String province;

    private String city;

    private String area;

    private String officeAddr;

    private Integer authentication;

    private String obligationOrg;

    private String regAddr;

    private String charterCode;

    private Date charterEffectiveDate;

    private Date charterExpiredDate;

    private Boolean isDeleteFlag;

    private Long createrId;

    private String createrName;

    private Date createDateTime;

    private Integer sellersign;	//商家标识	0:商家，1:加盟商

    private Double longitude;	//经度

    private Double latitude;	//纬度

    private Integer selledcnt;	//是否是潜在商家，0是，1已经是正式商家

    private Integer level;

    private String phone;

    private String img;
    private String img2;
    private String navigation;
    
    private Long userInfoId;
	
    private String  areaName;//省
	private String  areaName1;//市
	private String  areaName2;//区
	
	private Long messageCount;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCreaterId() {
		return createrId;
	}
	public void setCreaterId(Long createrId) {
		this.createrId = createrId;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCreaterName() {
		return createrName;
	}
	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}

	public String getBusinessScope() {
		return businessScope;
	}
	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getOfficeAddr() {
		return officeAddr;
	}
	public void setOfficeAddr(String officeAddr) {
		this.officeAddr = officeAddr;
	}
	public Integer getAuthentication() {
		return authentication;
	}
	public void setAuthentication(Integer authentication) {
		this.authentication = authentication;
	}
	public String getObligationOrg() {
		return obligationOrg;
	}
	public void setObligationOrg(String obligationOrg) {
		this.obligationOrg = obligationOrg;
	}
	public String getRegAddr() {
		return regAddr;
	}
	public void setRegAddr(String regAddr) {
		this.regAddr = regAddr;
	}
	public String getCharterCode() {
		return charterCode;
	}
	public void setCharterCode(String charterCode) {
		this.charterCode = charterCode;
	}
	public Boolean getIsDeleteFlag() {
		return isDeleteFlag;
	}
	public void setIsDeleteFlag(Boolean isDeleteFlag) {
		this.isDeleteFlag = isDeleteFlag;
	}
	public Date getCharterEffectiveDate() {
		return charterEffectiveDate;
	}
	public void setCharterEffectiveDate(Date charterEffectiveDate) {
		this.charterEffectiveDate = charterEffectiveDate;
	}
	public Date getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
	public Date getCharterExpiredDate() {
		return charterExpiredDate;
	}
	public void setCharterExpiredDate(Date charterExpiredDate) {
		this.charterExpiredDate = charterExpiredDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getCategory() {
		return category;
	}
	public void setCategory(Integer category) {
		this.category = category;
	}
	public Integer getSellersign() {
		return sellersign;
	}
	public void setSellersign(Integer sellersign) {
		this.sellersign = sellersign;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public Integer getSelledcnt() {
		return selledcnt;
	}
	public void setSelledcnt(Integer selledcnt) {
		this.selledcnt = selledcnt;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public Long getUserInfoId() {
		return userInfoId;
	}
	public void setUserInfoId(Long userInfoId) {
		this.userInfoId = userInfoId;
	}
	public String getImg2() {
		return img2;
	}
	public void setImg2(String img2) {
		this.img2 = img2;
	}
	public String getNavigation() {
		return navigation;
	}
	public void setNavigation(String navigation) {
		this.navigation = navigation;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getAreaName1() {
		return areaName1;
	}
	public void setAreaName1(String areaName1) {
		this.areaName1 = areaName1;
	}
	public String getAreaName2() {
		return areaName2;
	}
	public void setAreaName2(String areaName2) {
		this.areaName2 = areaName2;
	}
	public Integer getDictionaryId() {
		return dictionaryId;
	}
	public void setDictionaryId(Integer dictionaryId) {
		this.dictionaryId = dictionaryId;
	}
	public Long getMessageCount() {
		return messageCount;
	}
	public void setMessageCount(Long messageCount) {
		this.messageCount = messageCount;
	}
}
