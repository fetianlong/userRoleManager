package com.yuedi.entity;

import java.io.Serializable;
import java.util.Date;

public class YueDiPaperCount implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2085764020347893956L;

	private Integer id;
	
	private Integer user_id;
	
	private Integer title_id;
	
	private Integer option;
	
	private Integer num;
	
	private Integer days;
	
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

	public Integer getTitle_id() {
		return title_id;
	}

	public void setTitle_id(Integer title_id) {
		this.title_id = title_id;
	}

	public Integer getOption() {
		return option;
	}

	public void setOption(Integer option) {
		this.option = option;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}
	
	
}
