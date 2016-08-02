package com.yuedi.entity;

import java.util.Date;

public class Invitation {
    private Integer id;

    private String invitationcode;	//邀请码 数字或者字母随机生成5位

    private Long proposerid;	//申请人ID
    
    private String proposerName;	//申请人userName

    private Long institutionid;	//申请机构ID
    
    private String institutionName;	//申请机构名称

    private Long userid;	//妈妈范用户ID

    private String tel;	//妈妈范APP用户的手机号

    private Integer iseffe;	//是否有效 0否 1是

    private Date ctime;	//申请时间（创建时间）

    private Date btime;	//绑定用户时间

    private String remark;	//备注
    
    private Integer allCount;
    private Integer countId;
    
    private String name;
    private String userName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInvitationcode() {
        return invitationcode;
    }

    public void setInvitationcode(String invitationcode) {
        this.invitationcode = invitationcode == null ? null : invitationcode.trim();
    }

    public Long getProposerid() {
        return proposerid;
    }

    public void setProposerid(Long proposerid) {
        this.proposerid = proposerid;
    }

    public Long getInstitutionid() {
        return institutionid;
    }

    public void setInstitutionid(Long institutionid) {
        this.institutionid = institutionid;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public Integer getIseffe() {
        return iseffe;
    }

    public void setIseffe(Integer iseffe) {
        this.iseffe = iseffe;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getBtime() {
        return btime;
    }

    public void setBtime(Date btime) {
        this.btime = btime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public String getProposerName() {
		return proposerName;
	}

	public void setProposerName(String proposerName) {
		this.proposerName = proposerName;
	}

	public String getInstitutionName() {
		return institutionName;
	}

	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}

	public Integer getAllCount() {
		return allCount;
	}

	public void setAllCount(Integer allCount) {
		this.allCount = allCount;
	}

	public Integer getCountId() {
		return countId;
	}

	public void setCountId(Integer countId) {
		this.countId = countId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}