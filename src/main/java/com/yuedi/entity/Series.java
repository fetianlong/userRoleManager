/**
 * @type com.yuedi.web.entity.Series
 */
package com.yuedi.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author sky
 *
 * @date 2014年2月15日
 *
 */
public class Series implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private Long brandId;
	private Integer category;
	private Integer ordIndex;
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
	 * @return the brandId
	 */
	public Long getBrandId() {
		return brandId;
	}
	/**
	 * @param brandId the brandId to set
	 */
	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}
	/**
	 * @return the category
	 */
	public Integer getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(Integer category) {
		this.category = category;
	}
	/**
	 * @return the ordIndex
	 */
	public Integer getOrdIndex() {
		return ordIndex;
	}
	/**
	 * @param ordIndex the ordIndex to set
	 */
	public void setOrdIndex(Integer ordIndex) {
		this.ordIndex = ordIndex;
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
