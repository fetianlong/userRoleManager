package com.yuedi.entity.fm;

import java.util.Date;

public class Srespiration {
    private Integer id;

    private Integer group;

    private Integer breathingtype;	////呼吸类型: 1 廓清式呼吸（每组6次）2 胸式呼吸（每组8次）3 加速呼吸（每组10次）4 浅呼吸（每组12次）

    private Integer duration;	//时长

    private Long userid;

    private Date starttime;

    private Date endtime;

    private Integer day;	//所在孕周

    private Date ctime;	//日期
    
    private String userName;	//app用户名
    private Integer score;	//分数
    private Integer types;	//呼吸总数
    private Integer shichang;	//时长总数

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroup() {
        return group;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }

    public Integer getBreathingtype() {
        return breathingtype;
    }

    public void setBreathingtype(Integer breathingtype) {
        this.breathingtype = breathingtype;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getTypes() {
		return types;
	}

	public void setTypes(Integer types) {
		this.types = types;
	}

	public Integer getShichang() {
		return shichang;
	}

	public void setShichang(Integer shichang) {
		this.shichang = shichang;
	}
}