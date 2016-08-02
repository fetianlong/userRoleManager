/**
 * @type com.yuedi.web.dto.customer.StudentInfoDto
 */
package com.yuedi.entity.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author sky
 *
 * @date 2014年11月17日
 *
 */
public class StudentInfoDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public StudentInfoDto() {
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
	private Long saler;
	
	private String bornDate;
	private String cardCode;
	private Integer source;
	
	private Long gestationInfoId;
	private String lastMensesDate;
	private Integer mensesDays;
	private String expectedDate;
	private String rellyBornDate;
	private Integer bodySize;
	
	private String sellerName;
	private Long createId;
	private String createrName;
	private Date createTimeDate;
	private Long parentId;
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
	/**
	 * @param source the source to set
	 */
	public void setSource(Integer source) {
		this.source = source;
	}
	/**
	 * @return the gestationInfoId
	 */
	public Long getGestationInfoId() {
		return gestationInfoId;
	}
	/**
	 * @param gestationInfoId the gestationInfoId to set
	 */
	public void setGestationInfoId(Long gestationInfoId) {
		this.gestationInfoId = gestationInfoId;
	}
	
	public String getLastMensesDate() {
		return lastMensesDate;
	}
	public void setLastMensesDate(String lastMensesDate) {
		this.lastMensesDate = lastMensesDate;
	}
	public void setExpectedDate(String expectedDate) {
		this.expectedDate = expectedDate;
	}
	public void setRellyBornDate(String rellyBornDate) {
		this.rellyBornDate = rellyBornDate;
	}
	/**
	 * @return the mensesDays
	 */
	public Integer getMensesDays() {
		return mensesDays;
	}
	/**
	 * @param mensesDays the mensesDays to set
	 */
	public void setMensesDays(Integer mensesDays) {
		this.mensesDays = mensesDays;
	}
	
	/**
	 * @return the bodySize
	 */
	public Integer getBodySize() {
		return bodySize;
	}
	/**
	 * @param bodySize the bodySize to set
	 */
	public void setBodySize(Integer bodySize) {
		this.bodySize = bodySize;
	}
	/**
	 * @return the bornProvince
	 */
	public Long getBornProvince() {
		return bornProvince;
	}
	/**
	 * @param bornProvince the bornProvince to set
	 */
	public void setBornProvince(Long bornProvince) {
		this.bornProvince = bornProvince;
	}
	/**
	 * @return the bornCity
	 */
	public Long getBornCity() {
		return bornCity;
	}
	/**
	 * @param bornCity the bornCity to set
	 */
	public void setBornCity(Long bornCity) {
		this.bornCity = bornCity;
	}
	/**
	 * @return the bornHospital
	 */
	public String getBornHospital() {
		return bornHospital;
	}
	/**
	 * @param bornHospital the bornHospital to set
	 */
	public void setBornHospital(String bornHospital) {
		this.bornHospital = bornHospital;
	}
	/**
	 * @return the parturitionMode
	 */
	public Integer getParturitionMode() {
		return parturitionMode;
	}
	/**
	 * @param parturitionMode the parturitionMode to set
	 */
	public void setParturitionMode(Integer parturitionMode) {
		this.parturitionMode = parturitionMode;
	}
	/**
	 * @return the otherSpecification
	 */
	public String getOtherSpecification() {
		return otherSpecification;
	}
	/**
	 * @param otherSpecification the otherSpecification to set
	 */
	public void setOtherSpecification(String otherSpecification) {
		this.otherSpecification = otherSpecification;
	}

	public Long getCreateId() {
		return createId;
	}
	public void setCreateId(Long createId) {
		this.createId = createId;
	}
	
	public String getCreaterName() {
		return createrName;
	}
	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}
	public Date getCreateTimeDate() {
		return createTimeDate;
	}
	public void setCreateTimeDate(Date createTimeDate) {
		this.createTimeDate = createTimeDate;
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

	public String getSellerName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getExpectedDate() {
		return expectedDate;
	}
	public String getRellyBornDate() {
		return rellyBornDate;
	}

	private Long bornProvince;
	private Long bornCity;
	private String bornHospital;
	private Integer parturitionMode;
	private String otherSpecification;
}
