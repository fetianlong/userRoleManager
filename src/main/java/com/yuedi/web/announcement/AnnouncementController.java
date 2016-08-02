package com.yuedi.web.announcement;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yuedi.entity.Announcement;
import com.yuedi.entity.Menu;
import com.yuedi.entity.MyPage;
import com.yuedi.service.AnnouncementService;
import com.yuedi.web.common.SupperController;

@Controller
@RequestMapping(value = "/announcement")
public class AnnouncementController extends SupperController {

	@Autowired
	private AnnouncementService announcementService;

	private static final Logger logger = Logger
			.getLogger(AnnouncementController.class);

	@RequestMapping(value = "list/{parentId}", method = RequestMethod.GET)
	public String list(@PathVariable("parentId") Long parentId, Model model,
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

			MyPage<Announcement> page = new MyPage<Announcement>();
			page.setNumber(pageNumber);
			List<Announcement> announcementList = announcementService
					.selectAnnouncementLimit(page);
			model.addAttribute("announcementList", announcementList);
			model.addAttribute("pageData", page);
		} catch (Exception e) {
			logger.error("AnnouncementController err list", e);
		}

		return "announcement/announcementList";
	}

	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(Model model, HttpServletRequest request) {

		// 获取当前用户所拥有的菜单
		/*List<Menu> menuList = this.getFirstMenuByUserId();
		model.addAttribute("menuList", menuList);*/
		String parentId = request.getParameter("parentId");
		model.addAttribute("parentId", parentId);

		return "announcement/announcementForm";
	}

	@RequestMapping(value = "/addAnnouncement", method = RequestMethod.POST)
	public String addAnnouncement(Model model,@ModelAttribute("form") Announcement announcement,HttpServletRequest request) {
		String parentId = request.getParameter("parentId");
		try {
			announcement.setSellerId(this.getCurrentUserFranchiseesIdId());
			announcement.setCreaterName(this.getCurrentUserName());
			announcement.setCreateDateTime(this.getCurrentDate());
			if(announcement.getId()==null){
				announcementService.insertAnnouncement(announcement);
			}else{
				announcementService.updateAnnouncement(announcement);
			}
			
		} catch (Exception e) {
			logger.error("MenuController err addMenu", e);
		}

		return "redirect:/announcement/list/" + parentId;
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Integer id, Model model,
			HttpServletRequest request) {

		try {
			String parentId = request.getParameter("parentId");
			model.addAttribute("parentId", parentId);
			// 获取当前用户所拥有的菜单
			/*List<Menu> menuList = this.getFirstMenuByUserId();
			model.addAttribute("menuList", menuList);*/
			Announcement announcement = announcementService
					.selectAnnouncementByPrimaryKey(id);
			model.addAttribute("announcement", announcement);
		} catch (Exception e) {
			logger.error("AnnouncementController err updateForm", e);
		}

		return "announcement/announcementForm";
	}

	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Integer id, Model model,
			HttpServletRequest request) {
		String parentId = request.getParameter("parentId");
		try {
			// 获取当前用户所拥有的菜单
			/*List<Menu> menuList = this.getFirstMenuByUserId();
			model.addAttribute("menuList", menuList);*/
			announcementService.deleteAnnouncementById(id);
		} catch (Exception e) {
			logger.error("AnnouncementController err delete", e);
		}

		return "redirect:/announcement/list/" + parentId;
	}
}
