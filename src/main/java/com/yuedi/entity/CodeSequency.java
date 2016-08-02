/**
 * @type com.yuedi.web.entity.CodeSequency
 */
package com.yuedi.entity;

import java.io.Serializable;

/**
 * @author sky
 *
 * @date 2013-11-3
 *
 */
public class CodeSequency implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private String category; 
	private String prefix; 
	private Long sequency; 
	private Long increment;
	private String remark;
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
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	/**
	 * @return the prefix
	 */
	public String getPrefix() {
		return prefix;
	}
	/**
	 * @param prefix the prefix to set
	 */
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	/**
	 * @return the sequency
	 */
	public Long getSequency() {
		return sequency;
	}
	/**
	 * @param sequency the sequency to set
	 */
	public void setSequency(Long sequency) {
		this.sequency = sequency;
	}
	/**
	 * @return the increment
	 */
	public Long getIncrement() {
		return increment;
	}
	/**
	 * @param increment the increment to set
	 */
	public void setIncrement(Long increment) {
		this.increment = increment;
	}
	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
