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
import com.yuedi.entity.workFlow.ProcessNode;
import com.yuedi.service.workFlow.ProcessNodeService;
import com.yuedi.web.common.SupperController;

/**
 * Task管理的Controller, 使用Restful风格的Urls:
 * 
 * List page : GET /processNode/
 * Create page : GET /processNode/create
 * Create action : POST /processNode/create
 * Update page : GET /processNode/update/{id}
 * Update action : POST /processNode/update
 * Delete action : GET /processNode/delete/{id}
 * 
 * @author calvin
 */
@Controller
@RequestMapping(value = "/processNode")
public class ProcessNodeController extends SupperController{

	private static final String PAGE_SIZE = "10";

	@Autowired
	ProcessNodeService processNodeService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, @RequestParam(value = "page", defaultValue = "1") int pageNumber){
		MyPage<ProcessNode> page = new MyPage<ProcessNode>();
		page.setNumber(pageNumber);
		List<ProcessNode> processNodeList = processNodeService.selectProcessNodeByPage(page);
		
		model.addAttribute("processNodeList", processNodeList);
		model.addAttribute("pageData", page);
		return "processNode/processNodeList";
	}
	
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		ProcessNode processNode = processNodeService.selectProcessNode(id);
		model.addAttribute("processNode", processNode);
		model.addAttribute("action", "update");
		return "processNode/processNodeForm";
	}
	
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@ModelAttribute("processNode") ProcessNode processNode, RedirectAttributes redirectAttributes) {
		if(processNodeService.updateProcessNode(processNode)>0){
			redirectAttributes.addFlashAttribute("message", "更新任务成功");
		}
		return "redirect:/processNode/";
	}

	@RequestMapping(value = "delete/{id}")
	public String delete(@RequestParam(value = "page") int pageNumber,
			@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		processNodeService.deleteProcessNode(id);
		redirectAttributes.addFlashAttribute("message", "删除任务成功");
		return "redirect:/processNode/?page=" + pageNumber;
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
			model.addAttribute("processNode", processNodeService.selectProcessNode(id));
		}
	}

}
