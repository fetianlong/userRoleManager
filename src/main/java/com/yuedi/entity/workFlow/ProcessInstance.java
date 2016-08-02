package com.yuedi.entity.workFlow;

public class ProcessInstance {
    private Long id;

    private String processName;

    private Long processId;

    private Long nodeId;

    private String nodeName;
    
    private Long nodeOrder;

    private Long formId;

    private Long approver;

    private String approverName;

    private String approvalOpinion;

    private String approvalTime;

    private Boolean isGetBack;	//是否能取回

    private Long status;	//0：审核不通过，1：审核通过，2：审核中，3：退回申请人，4：未发送

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName == null ? null : processName.trim();
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

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName == null ? null : nodeName.trim();
    }

	public Long getNodeOrder() {
		return nodeOrder;
	}

	public void setNodeOrder(Long nodeOrder) {
		this.nodeOrder = nodeOrder;
	}

	public Long getFormId() {
        return formId;
    }

    public void setFormId(Long formId) {
        this.formId = formId;
    }

    public Long getApprover() {
        return approver;
    }

    public void setApprover(Long approver) {
        this.approver = approver;
    }

    public String getApproverName() {
        return approverName;
    }

    public void setApproverName(String approverName) {
        this.approverName = approverName == null ? null : approverName.trim();
    }

    public String getApprovalOpinion() {
        return approvalOpinion;
    }

    public void setApprovalOpinion(String approvalOpinion) {
        this.approvalOpinion = approvalOpinion == null ? null : approvalOpinion.trim();
    }

    public String getApprovalTime() {
        return approvalTime;
    }

    public void setApprovalTime(String approvalTime) {
        this.approvalTime = approvalTime == null ? null : approvalTime.trim();
    }

    public Boolean getIsGetBack() {
        return isGetBack;
    }

    public void setIsGetBack(Boolean isGetBack) {
        this.isGetBack = isGetBack;
    }

    public Long getStatus() {
        return status;
    }
    /**
     * 0：审核不通过，1：审核通过，2：审核中，3：退回申请人，4：未发送
     * @param status  
     * @description   
     * @version currentVersion  
     * @author pujh  
     * @createtime 2015年8月4日 上午10:01:43
     */
    public void setStatus(Long status) {
        this.status = status;
    }
}