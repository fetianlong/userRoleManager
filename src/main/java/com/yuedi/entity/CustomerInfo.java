/**
 * @type com.yuedi.web.entity.customer.CustomerInfo
 */
package com.yuedi.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 客户信息
 * 
 * @author sky
 *
 * @date 2014年10月26日
 *
 */
public class CustomerInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public CustomerInfo() {
	}
	
	private Long id;
	private String code;
	private String name;
	private String nickName;
	private Integer sex;
	private String mobilePhone;
	private String email;
	private String bornDate;
	private String cardCode;
	private Integer source;
	private Long franchiseesId;
	private Boolean isStudent;
	private Boolean isDeleteFlag;
	private Long createrId;
	private String createrName;
	private Date createDateTime;
	private String createDateTimeString;
	private String sellerName;
	private Integer qq;
	private String weChat;
	
	private String parentId;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the nickName
	 */
	public String getNickName() {
		return nickName;
	}
	/**
	 * @param nickName the nickName to set
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	/**
	 * @return the sex
	 */
	public Integer getSex() {
		return sex;
	}
	/**
	 * @param sex the sex to set
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	/**
	 * @return the mobilePhone
	 */
	public String getMobilePhone() {
		return mobilePhone;
	}
	/**
	 * @param mobilePhone the mobilePhone to set
	 */
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getBornDate() {
		return bornDate;
	}
	public void setBornDate(String bornDate) {
		this.bornDate = bornDate;
	}
	/**
	 * @return the cardCode
	 */
	public String getCardCode() {
		return cardCode;
	}
	/**
	 * @param cardCode the cardCode to set
	 */
	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}
	/**
	 * @return the source
	 */
	public Integer getSource() {
		return source;
	}
	public Long getFranchiseesId() {
		return franchiseesId;
	}
	public void setFranchiseesId(Long franchiseesId) {
		this.franchiseesId = franchiseesId;
	}
	/**
	 * @param source the source to set
	 */
	public void setSource(Integer source) {
		this.source = source;
	}
	/**
	 * @return the isStudent
	 */
	public Boolean getIsStudent() {
		return isStudent;
	}
	/**
	 * @param isStudent the isStudent to set
	 */
	public void setIsStudent(Boolean isStudent) {
		this.isStudent = isStudent;
	}
	/**
	 * @return the isDeleteFlag
	 */
	public Boolean getIsDeleteFlag() {
		return isDeleteFlag;
	}
	/**
	 * @param isDeleteFlag the isDeleteFlag to set
	 */
	public void setIsDeleteFlag(Boolean isDeleteFlag) {
		this.isDeleteFlag = isDeleteFlag;
	}
	/**
	 * @return the createrId
	 */
	public Long getCreaterId() {
		return createrId;
	}
	/**
	 * @param createrId the createrId to set
	 */
	public void setCreaterId(Long createrId) {
		this.createrId = createrId;
	}
	/**
	 * @return the createrName
	 */
	public String getCreaterName() {
		return createrName;
	}
	/**
	 * @param createrName the createrName to set
	 */
	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}
	/**
	 * @return the createDateTime
	 */
	public Date getCreateDateTime() {
		return createDateTime;
	}
	/**
	 * @param createDateTime the createDateTime to set
	 */
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
	public String getSellerName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	public Integer getQq() {
		return qq;
	}
	public void setQq(Integer qq) {
		this.qq = qq;
	}
	public String getWeChat() {
		return weChat;
	}
	public void setWeChat(String weChat) {
		this.weChat = weChat;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getCreateDateTimeString() {
		return createDateTimeString;
	}
	public void setCreateDateTimeString(String createDateTimeString) {
		this.createDateTimeString = createDateTimeString;
	}
}
