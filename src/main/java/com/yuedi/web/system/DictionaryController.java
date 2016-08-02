package com.yuedi.web.system;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import com.yuedi.entity.Dictionary;
import com.yuedi.entity.Menu;
import com.yuedi.entity.MyPage;
import com.yuedi.service.DictionaryService;
import com.yuedi.web.common.SupperController;

@Controller
@RequestMapping(value = "/dictionary")
public class DictionaryController  extends SupperController{
	
	@Autowired
	private DictionaryService dictionaryService;
	
	private static final Logger logger = Logger.getLogger(DictionaryController.class);
	
	@RequestMapping(value="/list/{parentId}",method = RequestMethod.GET)
	public String list(@PathVariable("parentId") Long parentId,Model model,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber){
		try{
			model.addAttribute("parentId", parentId);
			model.addAttribute("date", this.getCurrentDate());
			//获取当前用户所拥有的菜单
			/*List<Menu> menuList = this.getFirstMenuByUserId();
			model.addAttribute("menuList", menuList);*/
			//获取二级菜单
			List<Menu> towMenuList = this.getTwoMenuByParentId(parentId);
			model.addAttribute("towMenuList", towMenuList);
			
			MyPage<Dictionary> page = new MyPage<Dictionary>();
			page.setNumber(pageNumber);
			model.addAttribute("searchParams","parentId="+parentId);
			List<Dictionary> dictionaryList = dictionaryService.selectDictionaryLimit(page);
			model.addAttribute("dictionaryList", dictionaryList);
			model.addAttribute("pageData", page);
		}catch(Exception e) {
			logger.error("MenuController err list", e);
		}
		
		return "dictionary/dictionaryList";
	}
	
	@RequestMapping(value="add",method = RequestMethod.GET)
	public String add(Model model,HttpServletRequest request){
		//获取当前用户所拥有的菜单
		/*List<Menu> menuList = this.getFirstMenuByUserId();
		model.addAttribute("menuList", menuList);*/
		String parentId = request.getParameter("parentId");
		model.addAttribute("parentId", parentId);
		
		List<Dictionary> parentDictionaryList = dictionaryService.getParentDictionary();
		model.addAttribute("parentDictionaryList", parentDictionaryList);
		return "dictionary/dictionaryForm";
	}
	
	@RequestMapping(value="addDictionary",method=RequestMethod.POST)
	public String addDictionary(Model model, @ModelAttribute("form") Dictionary dictionary) {
		try{
			if(dictionary.getId() == null) {
				if(!StringUtils.isEmpty(dictionary.getCode())) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					dictionary.setCreateTime(sdf.format(new Date()));
					dictionary.setCreateUserId(this.getCurrentUserId());
					
					dictionaryService.add(dictionary);
				}
			}else {
				if(!StringUtils.isEmpty(dictionary.getCode())) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					dictionary.setUpdateTime(sdf.format(new Date()));
					dictionaryService.update(dictionary);
				}
			}
		}catch(Exception e) {
			logger.error("MenuController err addMenu", e);
		}
		
		return "redirect:/dictionary/list/" + dictionary.getParentId();
	}
	
	@RequestMapping(value = "close/{parentId}", method = RequestMethod.GET)
	public String close(@PathVariable("parentId") Long parentId) {
		return "redirect:/dictionary/list/" + parentId;
	}
	
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model,HttpServletRequest request) {
		try{
			String parentId = request.getParameter("parentId");
			model.addAttribute("parentId", parentId);
			//获取当前用户所拥有的菜单
			/*List<Menu> menuList = this.getFirstMenuByUserId();
			model.addAttribute("menuList", menuList);*/
			
			Dictionary dictionary = dictionaryService.getDictionaryById(id);
			model.addAttribute("dictionary", dictionary);
			
			List<Dictionary> parentDictionaryList = dictionaryService.getParentDictionary();
			model.addAttribute("parentDictionaryList", parentDictionaryList);
		}catch(Exception e) {
			logger.error("MenuController err updateForm", e);
		}
		
		return "dictionary/dictionaryForm";
	}
	
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Long id,Model model,HttpServletRequest request) {
		String parentId = request.getParameter("parentId");
		try{
			//获取当前用户所拥有的菜单
			/*List<Menu> menuList = this.getFirstMenuByUserId();
			model.addAttribute("menuList", menuList);*/
			dictionaryService.deleteById(id);
		}catch(Exception e) {
			logger.error("MenuController err delete", e);
		}
		
		return "redirect:/dictionary/list/" + parentId;
	}
}
