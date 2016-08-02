package com.yuedi.vo;

import java.io.Serializable;

public class ClassCntVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 515152416364607673L;
	
	
	private String ClassName;  //课程分类
	
	private Integer num;	   //数量

	public String getClassName() {
		return ClassName;
	}

	public void setClassName(String className) {
		ClassName = className;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}
	
	
}
