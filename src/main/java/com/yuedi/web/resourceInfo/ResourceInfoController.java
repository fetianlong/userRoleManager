package com.yuedi.web.resourceInfo;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yuedi.entity.ResourceInfo;
import com.yuedi.entity.Series;
import com.yuedi.service.CodeSequencyService;
import com.yuedi.service.ResourceInfoService;
import com.yuedi.util.ResourceType;
import com.yuedi.web.common.SupperController;

@Controller
@RequestMapping(value = "/resourceInfo")
public class ResourceInfoController extends SupperController{

	private static final Logger logger = Logger.getLogger(ResourceInfoController.class);
	
	@Autowired
	ResourceInfoService resourceInfoService;
	
	@Autowired
	private CodeSequencyService codeSequencyService;

	
	@RequestMapping(value = "/list/{parentId}", method = RequestMethod.GET)
	public String list(@PathVariable("parentId") Long parentId,Model model, 
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			HttpServletRequest request){
		model.addAttribute("parentId", parentId);
		
		
		
		return "resourceInfo/resourceInfoAppList";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model, HttpServletRequest request) {
		String parentId = request.getParameter("parentId");
		
		model.addAttribute("action", "addPost");
		model.addAttribute("parentId", parentId);
		return "resourceInfo/resourceInfoAppFrom";
	}
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
		String parentId = request.getParameter("parentId");
		ResourceInfo resourceInfo = resourceInfoService.selectByPrimaryKey(id);
		ResourceInfo resourceInfo1 = resourceInfoService.selectSeriesNameById(resourceInfo);
		resourceInfo.setName(resourceInfo1.getName());
		model.addAttribute("resourceInfo", resourceInfo);
		model.addAttribute("action", "updatePost");
		model.addAttribute("parentId", parentId);
//		model.addAttribute("parentId", parentId);
		return "resourceInfo/resourceInfoAppFrom";
	}
	
	
	@RequestMapping(value = "/addPost", method = RequestMethod.POST)
	public String addPost(@Valid @ModelAttribute("resourceInfo") ResourceInfo resourceInfo ,Model model, @RequestParam(value = "myFile") MultipartFile file, HttpServletRequest request) throws IllegalStateException, IOException {
		String parentId = request.getParameter("parentId");
		
		String resourceCode = codeSequencyService.musicCodeGenerator();
		
		resourceInfo.setResourceurl(file.getOriginalFilename());
		resourceInfo.setExpandedname(file.getContentType());
		resourceInfo.setResourcesize(file.getSize());
		resourceInfo.setResourcecode(resourceCode);
//		resourceInfo.setResourcetype(ResourceType.MUSIC.getValue());
		resourceInfo.setIsdeleteflag(false);
		resourceInfo.setCreatedatetime(new Date());
		resourceInfo.setCreaterid(super.getCurrentUserId());
		resourceInfo.setCreatername(super.getCurrentUserName());
		
		String nativeUrl = null;
		Integer rs = resourceInfo.getResourcetype();
		if(rs==1){
			nativeUrl = "D://yuedi-resource/resources/music/";
		}else if(rs==2){
			nativeUrl = "D://yuedi-resource/resources/picture/";
		}else if(rs==3){
			nativeUrl = "D://yuedi-resource/resources/doc/";
		}else if(rs==4){
			nativeUrl = "D://yuedi-resource/resources/video/";
		}
		
		File fl = new File(nativeUrl+resourceInfo.getResourceurl());
		file.transferTo(fl);
		resourceInfoService.insertSelective(resourceInfo);
		model.addAttribute("parentId", parentId);
		return "redirect:/resourceInfo/list/" + parentId;
	}
	
	@RequestMapping(value = "/updatePost", method = RequestMethod.POST)
	public String updatePost(@Valid @ModelAttribute("resourceInfo") ResourceInfo resourceInfo ,Model model, HttpServletRequest request){
		String parentId = request.getParameter("parentId");
		
//		String resourceCode = codeSequencyService.musicCodeGenerator();
		
//		resourceInfo.setResourceurl(file.getOriginalFilename());
//		resourceInfo.setExpandedname(file.getContentType());
//		resourceInfo.setResourcesize(file.getSize());
//		resourceInfo.setResourcecode(resourceCode);
//		resourceInfo.setResourcetype(ResourceType.MUSIC.getValue());
		resourceInfo.setIsdeleteflag(false);
		resourceInfo.setCreatedatetime(new Date());
		resourceInfo.setCreaterid(super.getCurrentUserId());
		resourceInfo.setCreatername(super.getCurrentUserName());
		
//		String nativeUrl = null;
//		Integer rs = resourceInfo.getResourcetype();
//		if(rs==1){
//			nativeUrl = "D://yuedi-resource/resources/music/";
//		}else if(rs==2){
//			nativeUrl = "D://yuedi-resource/resources/picture/";
//		}else if(rs==3){
//			nativeUrl = "D://yuedi-resource/resources/doc/";
//		}else if(rs==4){
//			nativeUrl = "D://yuedi-resource/resources/video/";
//		}
		
//		File fl = new File(nativeUrl+resourceInfo.getResourceurl());
//		file.transferTo(fl);
		resourceInfoService.updateByPrimaryKeySelective(resourceInfo);
		model.addAttribute("parentId", parentId);
		return "redirect:/resourceInfo/list/" + parentId;
	}
	
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
		ResourceInfo resourceInfo = new ResourceInfo();
		resourceInfo.setId(id);
		resourceInfo.setIsdeleteflag(true);
		resourceInfoService.updateByPrimaryKeySelective(resourceInfo);
		return "redirect:/resourceInfo/list/" + request.getParameter("parentId");
	}
	
}
