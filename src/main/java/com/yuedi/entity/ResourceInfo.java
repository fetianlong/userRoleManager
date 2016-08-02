package com.yuedi.entity;

import java.util.Date;

public class ResourceInfo {
    private Long id;

    private String resourcecode;

    private String displayname;

    private String resourcename;

    private Long brandId;

    private Long seriesId;

    private Integer resourcetype;

    private String expandedname;

    private Long resourcesize;

    private String resourceurl;

    private String tags;

    private String remark;

    private String usescene;

    private Boolean isdeleteflag;

    private Long createrid;

    private String creatername;

    private Date createdatetime;

    private String lyrics;

    private Integer resourceTeam;
    
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResourcecode() {
        return resourcecode;
    }

    public void setResourcecode(String resourcecode) {
        this.resourcecode = resourcecode == null ? null : resourcecode.trim();
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname == null ? null : displayname.trim();
    }

    public String getResourcename() {
        return resourcename;
    }

    public void setResourcename(String resourcename) {
        this.resourcename = resourcename == null ? null : resourcename.trim();
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Long getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(Long seriesId) {
        this.seriesId = seriesId;
    }

    public Integer getResourcetype() {
        return resourcetype;
    }

    public void setResourcetype(Integer resourcetype) {
        this.resourcetype = resourcetype;
    }

    public String getExpandedname() {
        return expandedname;
    }

    public void setExpandedname(String expandedname) {
        this.expandedname = expandedname == null ? null : expandedname.trim();
    }

    public Long getResourcesize() {
        return resourcesize;
    }

    public void setResourcesize(Long resourcesize) {
        this.resourcesize = resourcesize;
    }

    public String getResourceurl() {
        return resourceurl;
    }

    public void setResourceurl(String resourceurl) {
        this.resourceurl = resourceurl == null ? null : resourceurl.trim();
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags == null ? null : tags.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getUsescene() {
        return usescene;
    }

    public void setUsescene(String usescene) {
        this.usescene = usescene == null ? null : usescene.trim();
    }

    public Boolean getIsdeleteflag() {
        return isdeleteflag;
    }

    public void setIsdeleteflag(Boolean isdeleteflag) {
        this.isdeleteflag = isdeleteflag;
    }

    public Long getCreaterid() {
        return createrid;
    }

    public void setCreaterid(Long createrid) {
        this.createrid = createrid;
    }

    public String getCreatername() {
        return creatername;
    }

    public void setCreatername(String creatername) {
        this.creatername = creatername == null ? null : creatername.trim();
    }

    public Date getCreatedatetime() {
        return createdatetime;
    }

    public void setCreatedatetime(Date createdatetime) {
        this.createdatetime = createdatetime;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics == null ? null : lyrics.trim();
    }

    public Integer getResourceTeam() {
        return resourceTeam;
    }

    public void setResourceTeam(Integer resourceTeam) {
        this.resourceTeam = resourceTeam;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}