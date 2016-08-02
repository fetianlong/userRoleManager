/**
 * @type com.yuedi.web.dto.RoleUserInfoDto
 */
package com.yuedi.dto;

import java.io.Serializable;

/**
 * @author sky
 *
 * @date 2014年2月14日
 *
 */
public class RoleUserInfoDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id; 
	private String userName; 
	private String franchiseesName;
	private Long userInfoId;
	private Long roleId;
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
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the franchiseesName
	 */
	public String getFranchiseesName() {
		return franchiseesName;
	}
	/**
	 * @param franchiseesName the franchiseesName to set
	 */
	public void setFranchiseesName(String franchiseesName) {
		this.franchiseesName = franchiseesName;
	}
	/**
	 * @return the userInfoId
	 */
	public Long getUserInfoId() {
		return userInfoId;
	}
	/**
	 * @param userInfoId the userInfoId to set
	 */
	public void setUserInfoId(Long userInfoId) {
		this.userInfoId = userInfoId;
	}
	/**
	 * @return the roleId
	 */
	public Long getRoleId() {
		return roleId;
	}
	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
}
