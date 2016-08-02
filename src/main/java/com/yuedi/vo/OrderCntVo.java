package com.yuedi.vo;

import java.io.Serializable;

public class OrderCntVo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4768105464795572602L;

	private String OrderName;  //孕症分类
	
	private Integer num;	   //数量
	

	public String getOrderName() {
		return OrderName;
	}

	public void setOrderName(String orderName) {
		OrderName = orderName;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}
	
	
}
