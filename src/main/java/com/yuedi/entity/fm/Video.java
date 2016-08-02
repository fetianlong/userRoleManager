package com.yuedi.entity.fm;

import java.util.Date;

public class Video {
    private Integer id;
    
    private Integer createUser;

    private String videoInfo;
    
    private String voidTitle;
    
    private Integer voidIndex;

    private String videoUrl;

    private boolean isOpen;

    private String videoImg;

    private Date ctime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl == null ? null : videoUrl.trim();
    }
   

	public boolean getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}

	public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

	public String getVideoInfo() {
		return videoInfo;
	}

	public void setVideoInfo(String videoInfo) {
		this.videoInfo = videoInfo;
	}

	public String getVideoImg() {
		return videoImg;
	}

	public void setVideoImg(String videoImg) {
		this.videoImg = videoImg;
	}

	public Integer getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}

	public String getVoidTitle() {
		return voidTitle;
	}

	public void setVoidTitle(String voidTitle) {
		this.voidTitle = voidTitle;
	}

	public Integer getVoidIndex() {
		return voidIndex;
	}

	public void setVoidIndex(Integer voidIndex) {
		this.voidIndex = voidIndex;
	}

    
}