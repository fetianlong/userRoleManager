package com.yuedi.entity.workFlow;

public class ProcessTask {
    private Long id;

    private Long processId;

    private Long nodeId;

    private Long formId;

    private Integer stauts;	//0：待办，1：已办，2：取回

    private String createTime;

    private Long createUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProcessId() {
        return processId;
    }

    public void setProcessId(Long processId) {
        this.processId = processId;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public Long getFormId() {
        return formId;
    }

    public void setFormId(Long formId) {
        this.formId = formId;
    }

    public Integer getStauts() {
        return stauts;
    }

    /**
     * 0：待办，1：已办，2：取回
     * @param stauts  
     * @description   
     * @version currentVersion  
     * @author pujh  
     * @createtime 2015年8月7日 上午11:48:54
     */
    public void setStauts(Integer stauts) {
        this.stauts = stauts;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }
}