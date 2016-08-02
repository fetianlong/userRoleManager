/**
 * @type com.yuedi.web.entity.customer.GestationInfo
 */
package com.yuedi.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author sky
 *
 * @date 2014年10月26日
 *
 */
public class GestationInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public GestationInfo() {
	}
	
	private Long id;
	private Long studentInfoId;
	private String lastMensesDate;
	private Integer mensesDays;
	private String expectedDate;
	private String rellyBornDate;
	private Integer bodySize;
	private Long bornProvince;
	private Long bornCity;
	private String bornHospital;
	private Integer parturitionMode;
	private String otherSpecification;
	private Boolean isDeleteFlag;
	private Long createrId;
	private String createrName;
	private Date createDateTime;

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
	 * @return the studentInfoId
	 */
	public Long getStudentInfoId() {
		return studentInfoId;
	}
	/**
	 * @param studentInfoId the studentInfoId to set
	 */
	public void setStudentInfoId(Long studentInfoId) {
		this.studentInfoId = studentInfoId;
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
	
	public String getLastMensesDate() {
		return lastMensesDate;
	}
	public void setLastMensesDate(String lastMensesDate) {
		this.lastMensesDate = lastMensesDate;
	}
	public String getExpectedDate() {
		return expectedDate;
	}
	public void setExpectedDate(String expectedDate) {
		this.expectedDate = expectedDate;
	}
	public String getRellyBornDate() {
		return rellyBornDate;
	}
	public void setRellyBornDate(String rellyBornDate) {
		this.rellyBornDate = rellyBornDate;
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
}
