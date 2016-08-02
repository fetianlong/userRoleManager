package com.yuedi.entity;

import java.util.Date;

public class Activity{
    private Integer id;
    private Integer Category;
    private String ActivityName;
    private Integer ActivityType;
    private String Address;
    private String Details;
    private String Img;
    private Integer ReviewStatus;
    private String StartTime;
    private String EndTime;
    private Integer province;
    private Integer city;
    private Integer area;
    private Date CreateDateTime;
    private String CreaterName;
    private String Groupid;
    private Long sellerId;
    private String sellerName;
    
    private Long parentId;

    public String getGroupid() {
		return Groupid;
	}

	public void setGroupid(String groupid) {
		Groupid = groupid;
	}

	public void setId(int id){
        this.id = id;
    }

    public Integer getId(){
        return this.id;
    }

    public void setCategory(Integer Category){
        this.Category = Category;
    }

    public Integer getCategory(){
        return this.Category;
    }

    public void setActivityName(String ActivityName){
        this.ActivityName = ActivityName;
    }

    public String getActivityName(){
        return this.ActivityName;
    }

    public void setActivityType(int ActivityType){
        this.ActivityType = ActivityType;
    }

    public Integer getActivityType(){
        return this.ActivityType;
    }

    public void setAddress(String Address){
        this.Address = Address;
    }

    public String getAddress(){
        return this.Address;
    }

    public void setDetails(String Details){
        this.Details = Details;
    }

    public String getDetails(){
        return this.Details;
    }

    public void setImg(String Img){
        this.Img = Img;
    }

    public String getImg(){
        return this.Img;
    }

    public void setReviewStatus(int ReviewStatus){
        this.ReviewStatus = ReviewStatus;
    }

    public Integer getReviewStatus(){
        return this.ReviewStatus;
    }

    public String getStartTime() {
		return StartTime;
	}

	public void setStartTime(String startTime) {
		StartTime = startTime;
	}

	public String getEndTime() {
		return EndTime;
	}

	public void setEndTime(String endTime) {
		EndTime = endTime;
	}

	public void setProvince(Integer province){
        this.province = province;
    }

    public Integer getProvince(){
        return this.province;
    }

    public void setCity(Integer city){
        this.city = city;
    }

    public Integer getCity(){
        return this.city;
    }

    public void setArea(Integer area){
        this.area = area;
    }

    public Integer getArea(){
        return this.area;
    }

    public void setCreateDateTime(Date CreateDateTime){
        this.CreateDateTime = CreateDateTime;
    }

    public Date getCreateDateTime(){
        return this.CreateDateTime;
    }

    public void setCreaterName(String CreaterName){
        this.CreaterName = CreaterName;
    }

    public String getCreaterName(){
        return this.CreaterName;
    }

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setActivityType(Integer activityType) {
		ActivityType = activityType;
	}

	public void setReviewStatus(Integer reviewStatus) {
		ReviewStatus = reviewStatus;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
}