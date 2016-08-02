/**
 * @type com.yuedi.web.entity.customer.StudentInfo
 */
package com.yuedi.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author sky
 *
 * @date 2014年10月26日
 *
 */
public class StudentInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public StudentInfo() {
	}
	
	private Long id;
	private String name;
	private String nickName;
	private Integer sex;
	private Long customerInfoId;
	private String customerInfoCode;
	private String studentNo;
	private Long franchiseesId;
	private String entranceDate;
	private String email;
	private String mobilePhone;
	private String fixedPhone;
	private String qqAccount;
	private String microChartAccount;
	private Long province;
	private Long city;
	private Long district;
	private String specificationAddress;
	private String contacts;
	private String contactsPhone;
	private String specification;
	private String cardCode;
	private String sellerName;
	/**
	 * 取自员工表，推荐人、招生顾问
	 */
	private Long saler;
	private Boolean isDeleteFlag;
	private Long createrId;
	private String createrName;
	private Timestamp createDateTime;

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
	 * @return the customerInfoId
	 */
	public Long getCustomerInfoId() {
		return customerInfoId;
	}
	/**
	 * @param customerInfoId the customerInfoId to set
	 */
	public void setCustomerInfoId(Long customerInfoId) {
		this.customerInfoId = customerInfoId;
	}
	/**
	 * @return the customerInfoCode
	 */
	public String getCustomerInfoCode() {
		return customerInfoCode;
	}
	/**
	 * @param customerInfoCode the customerInfoCode to set
	 */
	public void setCustomerInfoCode(String customerInfoCode) {
		this.customerInfoCode = customerInfoCode;
	}
	/**
	 * @return the studentNo
	 */
	public String getStudentNo() {
		return studentNo;
	}
	/**
	 * @param studentNo the studentNo to set
	 */
	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}
	/**
	 * @return the franchiseesId
	 */
	public Long getFranchiseesId() {
		return franchiseesId;
	}
	/**
	 * @param franchiseesId the franchiseesId to set
	 */
	public void setFranchiseesId(Long franchiseesId) {
		this.franchiseesId = franchiseesId;
	}
	
	public String getEntranceDate() {
		return entranceDate;
	}
	public void setEntranceDate(String entranceDate) {
		this.entranceDate = entranceDate;
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
	 * @return the fixedPhone
	 */
	public String getFixedPhone() {
		return fixedPhone;
	}
	/**
	 * @param fixedPhone the fixedPhone to set
	 */
	public void setFixedPhone(String fixedPhone) {
		this.fixedPhone = fixedPhone;
	}
	/**
	 * @return the qqAccount
	 */
	public String getQqAccount() {
		return qqAccount;
	}
	/**
	 * @param qqAccount the qqAccount to set
	 */
	public void setQqAccount(String qqAccount) {
		this.qqAccount = qqAccount;
	}
	/**
	 * @return the microChartAccount
	 */
	public String getMicroChartAccount() {
		return microChartAccount;
	}
	/**
	 * @param microChartAccount the microChartAccount to set
	 */
	public void setMicroChartAccount(String microChartAccount) {
		this.microChartAccount = microChartAccount;
	}
	/**
	 * @return the province
	 */
	public Long getProvince() {
		return province;
	}
	/**
	 * @param province the province to set
	 */
	public void setProvince(Long province) {
		this.province = province;
	}
	/**
	 * @return the city
	 */
	public Long getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(Long city) {
		this.city = city;
	}
	/**
	 * @return the district
	 */
	public Long getDistrict() {
		return district;
	}
	/**
	 * @param district the district to set
	 */
	public void setDistrict(Long district) {
		this.district = district;
	}
	/**
	 * @return the specificationAddress
	 */
	public String getSpecificationAddress() {
		return specificationAddress;
	}
	/**
	 * @param specificationAddress the specificationAddress to set
	 */
	public void setSpecificationAddress(String specificationAddress) {
		this.specificationAddress = specificationAddress;
	}
	/**
	 * @return the contacts
	 */
	public String getContacts() {
		return contacts;
	}
	/**
	 * @param contacts the contacts to set
	 */
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	/**
	 * @return the contactsPhone
	 */
	public String getContactsPhone() {
		return contactsPhone;
	}
	/**
	 * @param contactsPhone the contactsPhone to set
	 */
	public void setContactsPhone(String contactsPhone) {
		this.contactsPhone = contactsPhone;
	}
	/**
	 * @return the specification
	 */
	public String getSpecification() {
		return specification;
	}
	/**
	 * @param specification the specification to set
	 */
	public void setSpecification(String specification) {
		this.specification = specification;
	}
	/**
	 * @return the saler
	 */
	public Long getSaler() {
		return saler;
	}
	/**
	 * @param saler the saler to set
	 */
	public void setSaler(Long saler) {
		this.saler = saler;
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
	public Timestamp getCreateDateTime() {
		return createDateTime;
	}
	/**
	 * @param createDateTime the createDateTime to set
	 */
	public void setCreateDateTime(Timestamp createDateTime) {
		this.createDateTime = createDateTime;
	}
	public String getCardCode() {
		return cardCode;
	}
	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}
	public String getSellerName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
}
