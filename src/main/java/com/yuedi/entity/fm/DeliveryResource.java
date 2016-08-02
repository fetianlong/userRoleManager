package com.yuedi.entity.fm;

import java.io.Serializable;

public class DeliveryResource implements Serializable {
    private Integer id;

    private Integer deliveryId;

    private Integer resourceId;
    
    private Long brandId;//品牌Id
    
    private Long seriesId;//系列Id
        
    private String seriesName;//系列名称
    /*
     * 订制内容描述
     */
    private String  tags;
    
    /**
	 * 资源编号
	 */
	private String resourceCode;
	/**
	 * 外部名称
	 */
	private String displayName; 
	/**
	 * 内部名称
	 */
	private String resourceName; 
	/**
	 * 1-音乐、2-图片、3-文档、4-视频
	 */
	private Integer resourceType;
	
	
    public String getSeriesName() {
		return seriesName;
	}

	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}

	public Long getSeriesId() {
		return seriesId;
	}

	public void setSeriesId(Long seriesId) {
		this.seriesId = seriesId;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	

	public String getResourceCode() {
		return resourceCode;
	}

	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public Integer getResourceType() {
		return resourceType;
	}

	public void setResourceType(Integer resourceType) {
		this.resourceType = resourceType;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

   
    public Integer getDeliveryId() {
		return deliveryId;
	}

	public void setDeliveryId(Integer deliveryId) {
		this.deliveryId = deliveryId;
	}

	public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }
}