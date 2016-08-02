package com.yuedi.entity.fm;

import java.util.Date;

public class Delivery {
    private Integer id;

    private Integer birthProcess;

    private Integer cm;

    private Boolean isPain;

    private Date ctime;

    private Long timeLong;

    private Long createUserId;

    private Boolean isSimulation;

    private Long hospitalId;

    private Integer week;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBirthProcess() {
        return birthProcess;
    }

    public void setBirthProcess(Integer birthProcess) {
        this.birthProcess = birthProcess;
    }

    public Integer getCm() {
        return cm;
    }

    public void setCm(Integer cm) {
        this.cm = cm;
    }

    public Boolean getIsPain() {
        return isPain;
    }

    public void setIsPain(Boolean isPain) {
        this.isPain = isPain;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Long getTimeLong() {
        return timeLong;
    }

    public void setTimeLong(Long timeLong) {
        this.timeLong = timeLong;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

	public Boolean getIsSimulation() {
		return isSimulation;
	}

	public void setIsSimulation(Boolean isSimulation) {
		this.isSimulation = isSimulation;
	}

	public Long getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Long hospitalId) {
        this.hospitalId = hospitalId;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }
}