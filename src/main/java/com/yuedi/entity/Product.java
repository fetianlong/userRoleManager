package com.yuedi.entity;

import java.io.Serializable;
import java.util.Date;

public class Product implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/*
	 * `ProductID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ProductName` varchar(200) DEFAULT NULL,
  `SellerID` bigint(20) DEFAULT NULL,
  `ProductDesc` varchar(800) DEFAULT NULL,
  `Price` decimal(10,0) DEFAULT NULL,
  `Image` varchar(100) DEFAULT NULL,
  `Stock` int(11) DEFAULT NULL,
  `IsDeleteFlag` tinyint(1) DEFAULT '0',
  `CreateUser` varchar(50) DEFAULT NULL COMMENT '用户登录名',
  `CreateTime` datetime DEFAULT NULL,
  `UpdateUser` varchar(50) DEFAULT NULL COMMENT '用户登录名',
  `UpdateTime` datetime DEFAULT NULL,
  `PriceOld` decimal(10,0) DEFAULT NULL,
  `Sellersign` int(11) DEFAULT NULL,
	 * */
	private Long productId;
	private Long sellerId;
	private String productName; 
	private String productDesc; 
	private Double price; //现价
	private Double priceOld;
	private String image;
	private String createUser; 
	private String updateUser; 
	private Integer stock;
	private Integer isdeleteFlag;
	private Integer sellersign;//1=商家 0=加盟商
	private Date createTime;
	private Date updateTime;
	
	private Long parentId;
	
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Long getSellerId() {
		return sellerId;
	}
	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getPriceOld() {
		return priceOld;
	}
	public void setPriceOld(Double priceOld) {
		this.priceOld = priceOld;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public Integer getIsdeleteFlag() {
		return isdeleteFlag;
	}
	public void setIsdeleteFlag(Integer isdeleteFlag) {
		this.isdeleteFlag = isdeleteFlag;
	}
	public Integer getSellersign() {
		return sellersign;
	}
	public void setSellersign(Integer sellersign) {
		this.sellersign = sellersign;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
}
