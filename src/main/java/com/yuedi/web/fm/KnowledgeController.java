package com.yuedi.web.fm;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yuedi.dao.fm.KnowledgeDao;
import com.yuedi.entity.Announcement;
import com.yuedi.entity.Menu;
import com.yuedi.entity.MyPage;
import com.yuedi.entity.OrderType;
import com.yuedi.entity.Role;
import com.yuedi.entity.fm.Knowledge;
import com.yuedi.service.OrderTypeService;
import com.yuedi.service.fm.KnowledgeService;
import com.yuedi.web.common.SupperController;

@Controller
@RequestMapping(value ="/knowledge")
public class KnowledgeController extends SupperController {

	@Autowired
	private KnowledgeService knowledgeService;
	private static String resource = "http://www.mumfans.com/yuedi-resource/resources/picture/knowledge/";//正式路径
	private static String imageResource = "http://www.mumfans.com/yuedi-resource/resources/picture/";//正式路径
//	private static String resource = "D://yuedi-resource/resources/picture/knowledge/";//本机路径
//	private static String imageResource="D://yuedi-resource/resources/picture/";//本机路径
	private static final Logger logger = Logger
			.getLogger(KnowledgeController.class);

	/**
	 * 分娩知识列表
	 * @param parentId
	 * @param request
	 * @param model
	 * @param pageNumber
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年11月19日 下午2:57:12
	 */
	@RequestMapping(value = "list/{parentId}", method = RequestMethod.GET)
	public String list(@PathVariable("parentId") Long parentId, HttpServletRequest request,Model model,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber) {

		try {
			model.addAttribute("parentId", parentId);
			model.addAttribute("imgResource", imageResource);
			model.addAttribute("date", this.getCurrentDate());

			MyPage<Knowledge> page = new MyPage<Knowledge>();
			page.setNumber(pageNumber);
			
			List<Knowledge> knowledgeList = knowledgeService.selectKnowledgeLimit(page);
			
			String titleName = "分娩知识";
			if(!StringUtils.isEmpty(request.getParameter("titleName"))){
				titleName = request.getParameter("titleName");
			}
			model.addAttribute("titleName", titleName);
			model.addAttribute("knowledgeList", knowledgeList);
			model.addAttribute("pageData", page);
		} catch (Exception e) {
			logger.error("knowledgeController err list", e);
			e.printStackTrace();
		}

		return "knowledge/knowledgeList";
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
		return "knowledge/knowledgeForm";
	}
	/*
	 * 新增订制内容
	 */
	
	@RequestMapping(value = "/addKnowledge", method = RequestMethod.POST)
	public String addKnowledge(Model model,@ModelAttribute("form") Knowledge knowledge,@RequestParam(value = "imgFile") MultipartFile file,HttpServletRequest request) throws IllegalStateException, IOException {
		String parentId = request.getParameter("parentId");
		String image=request.getParameter("image");
		knowledge.setCtime(new Date());
		String fileName = file.getOriginalFilename();  
        System.out.println("fileName-----------------"+fileName);
        
        //上传了文件
        if(fileName!=null&&!("").equals(fileName)){
        	fileName=String.format("%1$s%2$s", UUID.randomUUID(), fileName.substring(fileName.lastIndexOf(".")));
        	knowledge.setImg("knowledge/"+fileName);
        	File kFile=new File(resource);
        	if(!kFile.exists()){
        		kFile.mkdirs();
        		File f=new File(resource+fileName);
        		file.transferTo(f);
        	}else{
        		File f=new File(resource+fileName);
        		file.transferTo(f);
        	}
        	try {
    			if(knowledge.getId()==null){
    				knowledgeService.insertKnowledge(knowledge);
    			}else{
    				knowledgeService.updateKnowledgeByPrimaryKey(knowledge);
    				String oldImgPath = imageResource+ image;
    				File oldImgFile = new File(oldImgPath);
    				if (oldImgFile.exists()) {
    					oldImgFile.delete();
    					}
    				}
    		} catch (Exception e) {
    			logger.error("MenuController err addMenu", e);
    			e.printStackTrace();
    		}

        
        }//未上传新的图片
        else{
        	knowledge.setImg(image);
        	knowledgeService.updateKnowledgeByPrimaryKey(knowledge);
        }
		
		return "redirect:/knowledge/list/" + parentId;
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
			Knowledge knowledge = knowledgeService
					.selectKnowledgeByPrimaryKey(id);
			model.addAttribute("knowledge", knowledge);
		} catch (Exception e) {
			logger.error("knowledgeController err updateForm", e);
			e.printStackTrace();
		}

		return "knowledge/knowledgeForm";
	}
	/*
	 * 删除订制内容
	 */
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Integer id, Model model,
			HttpServletRequest request) {
		String parentId = request.getParameter("parentId");
		try {
			// 获取当前用户所拥有的菜单
			/*List<Menu> menuList = this.getFirstMenuByUserId();
			model.addAttribute("menuList", menuList);*/
			Knowledge knowledge = knowledgeService
					.selectKnowledgeByPrimaryKey(id);
			knowledgeService.deleteKnowledgeByPrimaryKey(id);
			String oldImgPath = imageResource+ knowledge.getImg();
			File oldImgFile = new File(oldImgPath);
			if (oldImgFile.exists()) {
				oldImgFile.delete();
				}
		} catch (Exception e) {
			logger.error("knowledgeController err delete", e);
		}

		return "redirect:/knowledge/list/" + parentId;
	}
}
