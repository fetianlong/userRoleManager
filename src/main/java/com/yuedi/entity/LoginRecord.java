package com.yuedi.entity;

import java.util.Date;

/**
 * APP用户登录记录
 * @description   
 * @version currentVersion(1.0)  
 * @author pujh  
 * @createtime 2015年12月23日 上午11:31:34
 */
public class LoginRecord {
    private Integer id;

    private Long userid;

    private String terminal;

    private String version;

    private Date ctime;

    private Date updatetime;

    private Date loginday;
    
    private String userName;
    private String nickName;

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

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal == null ? null : terminal.trim();
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Date getLoginday() {
        return loginday;
    }

    public void setLoginday(Date loginday) {
        this.loginday = loginday;
    }

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
}