package com.yuedi.web.workFlow;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yuedi.entity.MyPage;
import com.yuedi.entity.workFlow.WorkProcess;
import com.yuedi.service.workFlow.WorkProcessService;
import com.yuedi.web.common.SupperController;

/**
 * 流程管理的Controller, 使用Restful风格的Urls:
 * 
 * List page : GET /workFlow/
 * Create page : GET /workFlow/create
 * Create action : POST /workFlow/create
 * Update page : GET /workFlow/update/{id}
 * Update action : POST /workFlow/update
 * Delete action : GET /workFlow/delete/{id}
 * 
 * @author calvin
 */
@Controller
@RequestMapping(value = "/workFlow")
public class WorkProcessController extends SupperController{

	private static final String PAGE_SIZE = "10";

	@Autowired
	WorkProcessService workProcessService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, @RequestParam(value = "page", defaultValue = "1") int pageNumber){
		MyPage<WorkProcess> page = new MyPage<WorkProcess>();
		page.setNumber(pageNumber);
		List<WorkProcess> workProcessList = workProcessService.selectWorkProcessByPage(page);
		
		model.addAttribute("workProcessList", workProcessList);
		model.addAttribute("pageData", page);
		return "workProcess/workProcessList";
	}
	
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("workProcess", new WorkProcess());
		model.addAttribute("action", "create");
		return "workProcess/workProcessForm";
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(WorkProcess newWorkProcess, RedirectAttributes redirectAttributes) {

		if(workProcessService.insert(newWorkProcess)>0){
			redirectAttributes.addFlashAttribute("message", "创建任务成功");
		}else{
			redirectAttributes.addFlashAttribute("message", "创建任务失败");
		}
		return "redirect:/task/";
	}
	
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		WorkProcess workProcess = workProcessService.selectWorkProcess(id);
		model.addAttribute("workProcess", workProcess);
		model.addAttribute("action", "update");
		return "workProcess/workProcessForm";
	}
	
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@ModelAttribute("workProcess") WorkProcess workProcess, RedirectAttributes redirectAttributes) {
		if(workProcessService.updateWorkProcess(workProcess)>0){
			redirectAttributes.addFlashAttribute("message", "更新任务成功");
		}
		return "redirect:/workFlow/";
	}

	@RequestMapping(value = "delete/{id}")
	public String delete(@RequestParam(value = "page") int pageNumber,
			@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		workProcessService.deleteWorkProcess(id);
		redirectAttributes.addFlashAttribute("message", "删除任务成功");
		return "redirect:/workFlow/?page=" + pageNumber;
	}
	
	/**
	 * 增加节点页面跳转
	 * @param id
	 * @param model
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年7月30日 下午4:23:29
	 */
	@RequestMapping(value = "addNodeForm/{id}", method = RequestMethod.GET)
	public String addNodeForm(@PathVariable("id") Long id, Model model) {
		WorkProcess workProcess = workProcessService.selectWorkProcess(id);
		model.addAttribute("workProcess", workProcess);
		model.addAttribute("action", "add");
		return "workProcess/processNodeForm";
	}
	
	/*
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("task", new UserInfo());
		model.addAttribute("action", "create");
		return "task/taskForm";
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(@Valid UserInfo newUserInfo, RedirectAttributes redirectAttributes) {
		User user = new User(getCurrentUserId());
		newUserInfo.setUser(user);

		taskService.saveTask(newTask);
		redirectAttributes.addFlashAttribute("message", "创建任务成功");
		return "redirect:/task/";
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("task", taskService.getTask(id));
		model.addAttribute("action", "update");
		return "task/taskForm";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("task") Task task, RedirectAttributes redirectAttributes) {
		taskService.saveTask(task);
		redirectAttributes.addFlashAttribute("message", "更新任务成功");
		return "redirect:/task/";
	}

	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		taskService.deleteTask(id);
		redirectAttributes.addFlashAttribute("message", "删除任务成功");
		return "redirect:/task/";
	}
*/
	/**
	 * 所有RequestMapping方法调用前的Model准备方法, 实现Struts2 Preparable二次部分绑定的效果,先根据form的id从数据库查出Task对象,再把Form提交的内容绑定到该对象上。
	 * 因为仅update()方法的form中有id属性，因此仅在update时实际执行.
	 */
	@ModelAttribute
	public void getTask(@RequestParam(value = "id", defaultValue = "-1") Long id, Model model) {
		if (id != -1) {
			model.addAttribute("workProcess", workProcessService.selectWorkProcess(id));
		}
	}

}
