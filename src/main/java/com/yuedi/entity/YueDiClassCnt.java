package com.yuedi.entity;

import java.io.Serializable;
import java.util.Date;

public class YueDiClassCnt implements Serializable{


	/**
	 * wangzx
	 */
	private static final long serialVersionUID = 1309603397437938487L;

	private Integer id;
	
	private Integer user_id;
	
	private Integer class_id;
	
	private Integer status;
	
	private Long is_finish;
	
	private Integer class_type;
	
	private Date ctime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getClass_id() {
		return class_id;
	}

	public void setClass_id(Integer class_id) {
		this.class_id = class_id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getIs_finish() {
		return is_finish;
	}

	public void setIs_finish(Long is_finish) {
		this.is_finish = is_finish;
	}

	public Integer getClass_type() {
		return class_type;
	}

	public void setClass_type(Integer class_type) {
		this.class_type = class_type;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
}
