package com.yuedi.web.position;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
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
import com.yuedi.entity.Position;
import com.yuedi.service.PositionService;
import com.yuedi.web.common.SupperController;
import com.yuedi.web.system.RoleController;

@Controller
@RequestMapping(value = "/position")
public class PositionController extends SupperController{
	@Autowired
	private PositionService positionService;
	
	private static final Logger logger = Logger.getLogger(RoleController.class);
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, @RequestParam(value = "page", defaultValue = "1") int pageNumber){
		try{
			MyPage<Position> page = new MyPage<Position>();
			page.setNumber(pageNumber);
			List<Position> roleList = positionService.selectPositionLimit(page);
			model.addAttribute("roleList", roleList);
			model.addAttribute("pageData", page);
		}catch(Exception e) {
			logger.error("PositionController err list", e);
		}
		
		return "position/positionList";
	}
	
	@RequestMapping(value="add",method = RequestMethod.GET)
	public String add(Model model){
		model.addAttribute("action", "addPosition");
		return "position/positionForm";
	}
	
	@RequestMapping(value="addPosition",method=RequestMethod.POST)
	public String addPosition(Model model, @ModelAttribute("form") Position position) {
		try{
			if(!StringUtils.isEmpty(position.getName())) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				position.setCreateTime(sdf.format(new Date()));
				position.setCreateUserId(this.getCurrentUserId());
				positionService.insertPosition(position);
			}
		}catch(Exception e) {
			logger.error("PositionController err addPosition", e);
		}
	
		return "redirect:/position/";
	}
	
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		try{
			Position position = positionService.selectPositionById(id);
			model.addAttribute("position", position);
			model.addAttribute("action", "update");
		}catch(Exception e) {
			logger.error("PositionController err updateForm", e);
		}
		
		return "position/positionForm";
	}
	
	@RequestMapping(value="update",method=RequestMethod.POST)
	public String update(Model model, @ModelAttribute("form") Position position) {
		try{
			if(!StringUtils.isEmpty(position.getName())) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				position.setId(position.getId());
				position.setUpdateTime(sdf.format(new Date()));
				position.setUpdateUserId(this.getCurrentUserId());
				
				positionService.updatePositionById(position);
			}
		}catch(Exception e) {
			logger.error("PositionController err update", e);
		}
		
		return "redirect:/position/";
	}
	
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		try{
			positionService.deletePositionById(id);
			redirectAttributes.addFlashAttribute("message", "删除任务成功");
		}catch(Exception e) {
			logger.error("PositionController err delete", e);
		}
		
		return "redirect:/position/";
	}
}
