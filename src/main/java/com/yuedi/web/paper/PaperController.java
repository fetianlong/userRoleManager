package com.yuedi.web.paper;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yuedi.entity.Announcement;
import com.yuedi.entity.Menu;
import com.yuedi.entity.MyPage;
import com.yuedi.entity.Paper;
import com.yuedi.entity.OrderType;
import com.yuedi.entity.Role;
import com.yuedi.service.PaperService;
import com.yuedi.service.OrderTypeService;
import com.yuedi.web.common.SupperController;

@Controller
@RequestMapping(value = "/paper")
public class PaperController extends SupperController {
	@Autowired
	private OrderTypeService orderTypeService;
	@Autowired
	private PaperService paperService;

	private static final Logger logger = Logger
			.getLogger(PaperController.class);

	/*
	 * 获取测试题列表
	 */
	@RequestMapping(value = "list/{parentId}", method = RequestMethod.GET)
	public String list(@PathVariable("parentId") Long parentId, HttpServletRequest request,Model model,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber) {

		try {
			model.addAttribute("parentId", parentId);
			model.addAttribute("date", this.getCurrentDate());
			// 获取当前用户所拥有的菜单
			/*List<Menu> menuList = this.getFirstMenuByUserId();
			model.addAttribute("menuList", menuList);*/
			// 获取二级菜单
			List<Menu> towMenuList = this.getTwoMenuByParentId(parentId);
			model.addAttribute("towMenuList", towMenuList);

			MyPage<Paper> page = new MyPage<Paper>();
			String title = request.getParameter("title");
			model.addAttribute("title", title);
			if(title != null && !"".equals(title)) {
				page.getParams().put("title", title+ "%");
			}
			String type = request.getParameter("type");
			model.addAttribute("type", type);
			if(type != null && !"".equals(type)) {
				page.getParams().put("type", type);
			}
			page.setNumber(pageNumber);
			List<Paper> paperList = paperService
					.selectPaperLimit(page);
			model.addAttribute("paperList", paperList);
			model.addAttribute("pageData", page);
		} catch (Exception e) {
			logger.error("PaperController err list", e);
			e.printStackTrace();
		}

		return "paper/paperList";
	}
	/*
	 * 前往新增功能，初始化数据
	 */
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(Model model, HttpServletRequest request) {

		// 获取当前用户所拥有的菜单
		/*List<Menu> menuList = this.getFirstMenuByUserId();
		model.addAttribute("menuList", menuList);*/
		String parentId = request.getParameter("parentId");
		model.addAttribute("parentId", parentId);
		List<OrderType> orderTypeList=orderTypeService.selectAllOrderType();
		model.addAttribute("orderTypeList", orderTypeList);
		return "paper/paperForm";
	}
	/*
	 * 新增测试题
	 */
	@RequestMapping(value = "/addPaper", method = RequestMethod.POST)
	public String addAnnouncement(Model model,@ModelAttribute("form") Paper paper,HttpServletRequest request) {
		String parentId = request.getParameter("parentId");
		paper.setCtime(new Date());
		try {
			
			if(paper.getId()==null){
				paperService.insertPaper(paper);
			}else{
				paperService.updatePaperByPrimaryKey(paper);
			}
			
		} catch (Exception e) {
			logger.error("MenuController err addMenu", e);
			e.printStackTrace();
		}

		return "redirect:/paper/list/" + parentId;
	}
	/*
	 * 前往编辑功能，初始化数据
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Integer id, Model model,
			HttpServletRequest request) {

		try {
			String parentId = request.getParameter("parentId");
			model.addAttribute("parentId", parentId);
			// 获取当前用户所拥有的菜单
			/*List<Menu> menuList = this.getFirstMenuByUserId();
			model.addAttribute("menuList", menuList);*/
			Paper paper = paperService
					.selectPaperByPrimaryKey(id);
			model.addAttribute("paper", paper);
			List<OrderType> orderTypeList=orderTypeService.selectAllOrderType();
			model.addAttribute("orderTypeList", orderTypeList);
		} catch (Exception e) {
			logger.error("PaperController err updateForm", e);
			e.printStackTrace();
		}

		return "paper/paperForm";
	}
	/*
	 * 删除测试题
	 */
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Integer id, Model model,
			HttpServletRequest request) {
		String parentId = request.getParameter("parentId");
		try {
			// 获取当前用户所拥有的菜单
			/*List<Menu> menuList = this.getFirstMenuByUserId();
			model.addAttribute("menuList", menuList);*/
			paperService.deletePaperByPrimaryKey(id);
		} catch (Exception e) {
			logger.error("PaperController err delete", e);
		}

		return "redirect:/paper/list/" + parentId;
	}
}
