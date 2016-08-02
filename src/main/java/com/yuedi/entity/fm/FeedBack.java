package com.yuedi.entity.fm;

import java.util.Date;

public class FeedBack {
    private Integer id;

    private Long userid;

    private Integer type;	//分娩方式： 1顺产 2顺剖  3剖宫产

    private Integer painnum;	//疼痛总次数

    private Integer paintime;	//疼痛总时间 单位秒

    private Integer processtime;	//第一产程时间 单位秒

    private Integer painratio;	//1-6个疼痛等级

    private Date ctime;	//

    private Date fmTime;	//分娩日期
    
    private String birthday;
    private String userName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPainnum() {
        return painnum;
    }

    public void setPainnum(Integer painnum) {
        this.painnum = painnum;
    }

    public Integer getPaintime() {
        return paintime;
    }

    public void setPaintime(Integer paintime) {
        this.paintime = paintime;
    }

    public Integer getProcesstime() {
        return processtime;
    }

    public void setProcesstime(Integer processtime) {
        this.processtime = processtime;
    }

    public Integer getPainratio() {
        return painratio;
    }

    public void setPainratio(Integer painratio) {
        this.painratio = painratio;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getFmTime() {
        return fmTime;
    }

    public void setFmTime(Date fmTime) {
        this.fmTime = fmTime;
    }

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}