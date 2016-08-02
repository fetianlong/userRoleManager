package com.yuedi.service.workFlow;

import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yuedi.dao.workFlow.ProcessInstanceDao;
import com.yuedi.dao.workFlow.ProcessNodeDao;
import com.yuedi.dao.workFlow.ProcessTaskDao;
import com.yuedi.dao.workFlow.WorkProcessDao;
import com.yuedi.entity.MyPage;
import com.yuedi.entity.workFlow.ProcessInstance;
import com.yuedi.entity.workFlow.ProcessNode;
import com.yuedi.entity.workFlow.ProcessTask;
import com.yuedi.entity.workFlow.WorkProcess;
import com.yuedi.service.ServiceException;
import com.yuedi.shiro.ShiroDbRealm.ShiroUser;
import com.yuedi.util.DateUtils;

@Component
@Transactional
public class ProcessInstanceService {
	
	private static Logger logger = LoggerFactory.getLogger(ProcessInstanceService.class);
	@Autowired
	private ProcessInstanceDao processInstanceDao;
	
	@Autowired
	ProcessNodeDao processNodeDao;
	
	@Autowired
	WorkProcessDao workProcessDao;
	
	@Autowired
	ProcessTaskDao processTaskDao;
	
	public int deleteProcessInstance(Long id){
		return processInstanceDao.deleteProcessInstance(id);
	}

	public int insert(ProcessInstance record){
		return processInstanceDao.insert(record);
	}

	public int insertSelective(ProcessInstance record){
		return processInstanceDao.insertSelective(record);
	}

	public ProcessInstance selectProcessInstance(Long id){
		return processInstanceDao.selectProcessInstance(id);
	}

	public int updateProcessInstanceSelective(ProcessInstance record){
		return processInstanceDao.updateProcessInstanceSelective(record);
	}

	public int updateProcessInstance(ProcessInstance record){
		return processInstanceDao.updateProcessInstance(record);
	}

	/**
	 * 分页获取信息
	 * @param page
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年7月30日 上午10:31:39
	 */
	public List<ProcessInstance> selectProcessInstanceByPage(MyPage<ProcessInstance> page) {
		return processInstanceDao.selectProcessInstanceByPage(page);
	}
	
	/**
	 * 流程开始
	 * @param processId  
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年8月3日 上午10:39:08
	 */
	public void start(Long processId, Long formId){
		try {
			WorkProcess workProcess = workProcessDao.selectWorkProcess(processId);	//获取流程ID
			if(null != workProcess && workProcess.getId()>0){
				ProcessNode pn = getProcessNode(processId, new Long(1));
				if(pn.getId() > 0){
					ProcessInstance pi = new ProcessInstance();
					pi.setFormId(formId);
					pi.setNodeId(pn.getId());
					pi.setNodeName(pn.getNodeName());
					pi.setProcessId(processId);
					pi.setNodeOrder(pn.getNodeOrder());
					ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
					pi.setProcessName(workProcess.getName());
					pi.setApprover(user.id);
					pi.setStatus(new Long(2));
					
					processInstanceDao.insert(pi);
					
					addProcessTask(processId,formId,pn.getId());
					
				}else{
					logger.warn("根据流程ID和节点顺序{}获取流程节点", processId);
					throw new ServiceException("没有获取到流程节点");
				}
			}else{
				logger.warn("根据流程ID{}获取流程", processId);
				throw new ServiceException("没有获取到流程");
			}
		} catch (Exception e) {
			throw new ServiceException("保存流程实例失败", e.getCause());
		}
	}	
	
	/**
	 * 添加节点任务
	 * @param processId 流程ID
	 * @param formId 表单id
	 * @param nodeId  节点id
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年8月7日 上午11:56:54
	 */
	private void addProcessTask(Long processId, Long formId, Long nodeId) {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		
		//当前人的任务，已办
		ProcessTask pt = new ProcessTask();
		pt.setCreateUser(user.id);
		pt.setFormId(formId);
		pt.setNodeId(nodeId);
		
		ProcessTask processTask = processTaskDao.getProcessTask(pt);
		if(null != processTask && processTask.getId()>0){
			pt.setCreateTime(DateUtils.format2LongDate(new Date()));
			pt.setStauts(1);
			processTaskDao.updateByPrimaryKey(pt);
		}else{
			pt.setCreateTime(DateUtils.format2LongDate(new Date()));
			pt.setStauts(1);
			processTaskDao.insert(pt);
		}
		
		//获取下一环节，审批人的任务，待办
		ProcessNode prn = getProcessNode(processId, nodeId +1);
		if(null != prn && prn.getApprover()>0){
			ProcessTask pt1 = new ProcessTask();
			pt1.setCreateUser(prn.getApprover());
			pt1.setFormId(formId);
			pt1.setNodeId(prn.getId());
			pt1.setCreateTime(DateUtils.format2LongDate(new Date()));
			pt1.setStauts(1);
			processTaskDao.insert(pt1);
		}else{
			logger.warn("当前节点{}没有审批人", "节点ID：" + prn.getId() + "   节点名称：" + prn.getNodeName());
			throw new ServiceException("当前节点ID：" + prn.getId() + "   节点名称：" + prn.getNodeName() + ",没有审批人");
		}
	}

	/**
	 * 下一节点
	 * @param processId	流程ID
	 * @param formId 表单ID
	 * @param nodeId  节点ID
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年8月4日 上午10:41:34
	 */
	public void nextNode(Long processId, Long formId){
		try {
			if(null != formId && formId>0){
				ProcessInstance pi = new ProcessInstance();
				pi.setFormId(formId);
				pi.setProcessId(processId);
				pi = processInstanceDao.getProcessInstanceByNodeMax(pi);
				
				if(pi.getId()>0){
					ProcessNode pn = new ProcessNode();
					pn.setProcessId(processId);
					pn.setNodeOrder(pi.getNodeOrder()+1);
					List<ProcessNode> listpn = processNodeDao.getProcessNode(pn);
					pn = listpn.get(0);
					
					ProcessInstance pro = new ProcessInstance();
					pro.setFormId(formId);
					pro.setNodeId(pn.getId());
					pro.setNodeName(pi.getNodeName());
					pro.setNodeOrder(pn.getNodeOrder());
					
					pro.setProcessId(pi.getProcessId());
					pro.setProcessName(pi.getProcessName());
					
					pro.setApprover(pn.getApprover());
					pro.setApprovalTime(DateUtils.format2LongDate(new Date()));
					pro.setStatus(new Long(2));
					
					processInstanceDao.insert(pro);
					
					addProcessTask(processId,formId,pn.getId());
					
					pi.setStatus(new Long(1));;
					processInstanceDao.insert(pi);
				}
			}else{
				logger.warn("表单的ID{}不能没有值", formId);
				throw new ServiceException("表单的ID不能没有值");
			}
		} catch (Exception e) {
			throw new ServiceException("保存流程实例失败", e.getCause());
		}
	}
	
	/**
	 * 退回
	 * @param formId 表单ID
	 * @param userId  退回给谁
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年8月7日 下午3:02:04
	 */
	public void goBack(Long formId, Long userId){
		ProcessInstance pi = new ProcessInstance();
		pi.setFormId(formId);
		pi = processInstanceDao.getProcessInstanceByNodeMax(pi);
		
		ProcessInstance pro = new ProcessInstance();
		pro.setFormId(formId);
		pro.setNodeId(pi.getNodeId());
		pro.setNodeName(pi.getNodeName());
		pro.setProcessId(pi.getProcessId());
		pro.setNodeOrder(pi.getNodeOrder());
		pro.setProcessName(pi.getProcessName());
		
		pro.setApprover(userId);
		pro.setApprovalTime(DateUtils.format2LongDate(new Date()));
		pro.setStatus(new Long(0));
		
		processInstanceDao.insert(pro);
		
		/**
		 * 更新任务的待办已办
		 */
		ProcessTask pt = new ProcessTask();
		pt.setFormId(formId);
		pt.setNodeId(pi.getNodeId());
		
		ProcessTask processTask = processTaskDao.getProcessTask(pt);
		if(null != processTask && processTask.getId()>0){
			processTask.setStauts(1);	//把当前节点的任务更改为已办
			processTaskDao.updateByPrimaryKey(processTask);
			
			pt.setNodeId(pi.getNodeId()-1);
			ProcessTask processTaskUp = processTaskDao.getProcessTask(pt);
			if(null != processTaskUp && processTaskUp.getId()>0){
				processTaskUp.setStauts(0);	//把上一节点的任务更改为待办
				processTaskDao.updateByPrimaryKey(processTaskUp);
			}
		}
	}
	
	/**
	 * 取回当前表单（只有申请人才能取回,并且只有其他人没有处理才能取回）
	 * @param formId  
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年8月7日 下午3:05:33
	 */
	public void getBackProcess(Long formId, Long userId){
		ProcessTask pt = new ProcessTask();
		pt.setFormId(formId);
		List<ProcessTask> list = processTaskDao.selectProcessTask(pt);
		if(list.size()>1){
			logger.warn("当前已经{}不能没有值", formId);
			throw new ServiceException("表单的ID不能没有值");
		}
	}

	/**
	 * 根据流程ID和节点顺序获取
	 * @param processId	流程ID
	 * @param order	节点顺序
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年8月3日 上午10:31:35
	 */
	private ProcessNode getProcessNode(Long processId, Long order) {
		ProcessNode pn = new ProcessNode();
		pn.setProcessId(processId);
		pn.setNodeOrder(order);
		List<ProcessNode> listpn = processNodeDao.getProcessNode(pn);
		if(listpn.size()>0){
			for (int i = 0; i < listpn.size(); i++) {
				pn = listpn.get(i);
			}
		}
		return pn;
	}
	
}
