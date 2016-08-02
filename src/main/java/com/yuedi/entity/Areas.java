/**
 * @type com.yuedi.web.entity.Areas
 */
package com.yuedi.entity;

import java.io.Serializable;

/**
 * @author sky
 *
 * @date 2014年11月23日
 *
 */
public class Areas implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public Areas() {
	}

	private Integer id;
	private Integer parentId;
	private String name;
	private String zipCode;
	private String namePy;
	private Integer level;
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the parentId
	 */
	public Integer getParentId() {
		return parentId;
	}
	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
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
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}
	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	/**
	 * @return the namePy
	 */
	public String getNamePy() {
		return namePy;
	}
	/**
	 * @param namePy the namePy to set
	 */
	public void setNamePy(String namePy) {
		this.namePy = namePy;
	}
	
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	
}
