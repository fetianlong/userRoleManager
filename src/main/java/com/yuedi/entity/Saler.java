package com.yuedi.entity;

import java.io.Serializable;
import java.util.Date;

public class Saler implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long sellerId;
	private Long createrId;
	private Long useId;
	private String salerCode;
	private String tdCodeURL;
	private String ordIndex;
	private String tel;
	private String name;
	private String createrName;
	private String cardCode;
	private String email;
	private String qq;
	private String weiChat;
	private String contacts;
	private Integer sex;
	private String contactsPhone;
	private String spec;
	private Boolean isDeleteFlag;
	private Date createDateTime;
	private String createDateTimeString;
	private String twocodeurl;
	
	private String sjname;
	
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
	public Long getCreaterId() {
		return createrId;
	}
	public void setCreaterId(Long createrId) {
		this.createrId = createrId;
	}
	public Long getUseId() {
		return useId;
	}
	public void setUseId(Long useId) {
		this.useId = useId;
	}
	public String getSalerCode() {
		return salerCode;
	}
	public void setSalerCode(String salerCode) {
		this.salerCode = salerCode;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
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
	public String getCardCode() {
		return cardCode;
	}
	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getWeiChat() {
		return weiChat;
	}
	public void setWeiChat(String weiChat) {
		this.weiChat = weiChat;
	}
	public String getContacts() {
		return contacts;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getContactsPhone() {
		return contactsPhone;
	}
	public void setContactsPhone(String contactsPhone) {
		this.contactsPhone = contactsPhone;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public Boolean getIsDeleteFlag() {
		return isDeleteFlag;
	}
	public void setIsDeleteFlag(Boolean isDeleteFlag) {
		this.isDeleteFlag = isDeleteFlag;
	}
	public Date getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
	public String getTdCodeURL() {
		return tdCodeURL;
	}
	public void setTdCodeURL(String tdCodeURL) {
		this.tdCodeURL = tdCodeURL;
	}
	public String getOrdIndex() {
		return ordIndex;
	}
	public void setOrdIndex(String ordIndex) {
		this.ordIndex = ordIndex;
	}
	public String getTwocodeurl() {
		return twocodeurl;
	}
	public void setTwocodeurl(String twocodeurl) {
		this.twocodeurl = twocodeurl;
	}
	public String getSjname() {
		return sjname;
	}
	public void setSjname(String sjname) {
		this.sjname = sjname;
	}
	public String getCreateDateTimeString() {
		return createDateTimeString;
	}
	public void setCreateDateTimeString(String createDateTimeString) {
		this.createDateTimeString = createDateTimeString;
	}
}
