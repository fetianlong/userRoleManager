package com.yuedi.web.video;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.yuedi.entity.Menu;
import com.yuedi.entity.MyPage;
import com.yuedi.entity.Video;
import com.yuedi.service.VideoService;
import com.yuedi.util.Ffmpeg;
import com.yuedi.web.common.SupperController;

@Controller
@RequestMapping(value ="/video")
public class VideoController extends SupperController {

	@Autowired
	private VideoService videoService;
//	private static String resource = "D://yuedi-resource/resources/video/delivery/";//正式服务器路径
//	private static String imageResource="D://yuedi-resource/resources/video/covert/";//正式服务器路径
//	private static String utilUrl="http://www.mumfans.com/yuedi-resource/resources/video/";//正式服务器路径
	
	private static String imageUrl="http://www.mumfans.com/yuedi-resource/resources/picture/";//正式服务器路径
	private static String videolUrl="http://www.mumfans.com/yuedi-resource/resources/video/";//正式服务器路径
	
//	private static String imageUrl="D://yuedi-resource/resources/picture/";//本机路径
//	private static String videolUrl="D://yuedi-resource/resources/video/";//本机路径
	private static final Logger logger = Logger
			.getLogger(VideoController.class);

	/*
	 * 获取订制内容列表
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

			MyPage<Video> page = new MyPage<Video>();
			page.setNumber(pageNumber);
			List<Video> videoList = videoService.selectVideoLimit(page);
			
			String titleName = "分娩视频管理";
			if(!StringUtils.isEmpty(request.getParameter("titleName"))){
				titleName = request.getParameter("titleName");
			}
			model.addAttribute("titleName", titleName);
			
			model.addAttribute("videoList", videoList);
			model.addAttribute("pageData", page);
			model.addAttribute("imageUrl", imageUrl);
			model.addAttribute("videolUrl", videolUrl);
		} catch (Exception e) {
			logger.error("videoController err list", e);
			e.printStackTrace();
		}

		return "video/videoList";
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
		return "video/videoForm";
	}
	
	@RequestMapping(value = "/addVideo", method = RequestMethod.POST)
	public String addVideo(Model model,@ModelAttribute("form") Video video, HttpServletRequest request) throws IllegalStateException,
			IOException {
		String parentId = request.getParameter("parentId");
		String videoImg=request.getParameter("videoImg");
		String videoMp4=request.getParameter("videoMp4");
		try {
			// 创建一个通用的多部分解析器
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
			// 判断 request 是否有文件上传,即多部分请求
			if (multipartResolver.isMultipart(request)) {
				// 转换成多部分request
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				// 取得request中的所有文件名
				Iterator<String> iter = multiRequest.getFileNames();
				while (iter.hasNext()) {
					// 记录上传过程起始时的时间，用来计算上传时间
					int pre = (int) System.currentTimeMillis();
					// 取得上传文件
					MultipartFile file = multiRequest.getFile(iter.next());
					if (file != null) {
						// 取得当前上传文件的文件名称
						String myFileName = file.getOriginalFilename();
						
						// 如果名称不为“”,说明该文件存在，否则说明该文件不存在
						if (myFileName.trim() != "") {
							System.out.println(myFileName);
							// 重命名上传后的文件名
							String fileName = null;
							fileName = String.format("%1$s%2$s", UUID.randomUUID(), myFileName.substring(myFileName.lastIndexOf(".")));
							String covertName = fileName.substring(0,fileName.lastIndexOf("."))+".jpg";
							if(file.getName().equals("videoFile")){	//视频文件
								video.setVideoUrl("delivery/"+fileName);
								File videoFile = createFile(videolUrl+"delivery");	//创建视频文件夹
								File f = new File(videolUrl+"delivery/"+fileName);	// 定义上传路径
								file.transferTo(f);
							}
							if(file.getName().equals("videoImgs")){	//图片
								video.setVideoImg("covert/"+covertName);
								File convertFile = createFile(imageUrl+"covert");	//创建图片文件夹
								File f = new File(imageUrl+"covert/"+fileName);	// 定义上传路径
								file.transferTo(f);	
							}
						}
					}
					// 记录上传该文件后的时间
	//				int finaltime = (int) System.currentTimeMillis();s
				}
				video.setCtime(new Date());
        		video.setCreateUser(super.getCurrentUserId().intValue());
    			if(video.getId()==null){
    				videoService.insertVideo(video);
    			}else{
    				videoService.updateVideoByPrimaryKey(video);
    				String oldImgPath = imageUrl+ videoImg;
    				deleteFile(oldImgPath);
    				String oldVideoPath = videolUrl+ videoMp4;
    				deleteFile(oldVideoPath);
				}
			}else{
				video.setVideoUrl(videoMp4);
	        	video.setVideoImg(videoImg);
	        	videoService.updateVideoByPrimaryKey(video);
			}
		} catch (Exception e) {
			logger.error("MenuController err addMenu", e);
			e.printStackTrace();
		}
		return "redirect:/video/list/" + parentId;
	}

	/*
	@RequestMapping(value = "/addVideo", method = RequestMethod.POST)
	public String addvideo(Model model,@ModelAttribute("form") Video video,@RequestParam(value = "videoFile") MultipartFile file,HttpServletRequest request) throws IllegalStateException, IOException {
		String parentId = request.getParameter("parentId");
		String videoImg=request.getParameter("videoImg");
		String videoMp4=request.getParameter("videoMp4");
		String fileName = file.getOriginalFilename();  
        System.out.println("fileName------前-----------"+fileName);
        
        //上传文件
        if(fileName!=null&&!("").equals(fileName)){
        	fileName=String.format("%1$s%2$s", UUID.randomUUID(), fileName.substring(fileName.lastIndexOf(".")));
        	System.out.println("fileName-------后----------"+fileName);
        	String covertName=fileName.substring(0,fileName.lastIndexOf("."))+".jpg";
        	video.setVideoUrl("delivery/"+fileName);
        	File videoFile=createFile(videolUrl+"delivery");
        	File convertFile=createFile(imageUrl+"covert");
        	File f=new File(videolUrl+"delivery/"+fileName);
    		file.transferTo(f);
        	Ffmpeg.processImg(videolUrl+"delivery/"+fileName,imageUrl+"covert/"+covertName, videolUrl+"ffmpeg.exe");
        	video.setVideoImg("covert/"+covertName);
        	try {
        		video.setCtime(new Date());
        		video.setCreateUser(super.getCurrentUserId().intValue());
    			if(video.getId()==null){
    				videoService.insertVideo(video);
    			}else{
    				videoService.updateVideoByPrimaryKey(video);
    				String oldImgPath = imageUrl+ videoImg;
    				deleteFile(oldImgPath);
    				String oldVideoPath = videolUrl+ videoMp4;
    				deleteFile(oldVideoPath);
				}
    		} catch (Exception e) {
    			logger.error("MenuController err addMenu", e);
    			e.printStackTrace();
    		}

        
        } else{	//未上传新的视频
        	video.setVideoUrl(videoMp4);
        	video.setVideoImg(videoImg);
        	videoService.updateVideoByPrimaryKey(video);
        }
		
		return "redirect:/video/list/" + parentId;
	}
	*/
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
			Video video = videoService.selectVideoByPrimaryKey(id);
			model.addAttribute("video", video);
			model.addAttribute("imageUrl", imageUrl);
			model.addAttribute("videolUrl", videolUrl);
		} catch (Exception e) {
			logger.error("videoController err updateForm", e);
			e.printStackTrace();
		}

		return "video/videoForm";
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
			Video video = videoService
					.selectVideoByPrimaryKey(id);
			videoService.deleteVideoByPrimaryKey(id);
			String oldImgPath = imageUrl+ video.getVideoImg();
			deleteFile(oldImgPath);
			String oldVideoPath =videolUrl+ video.getVideoUrl();
			deleteFile(oldVideoPath);
		} catch (Exception e) {
			logger.error("videoController err delete", e);
		}

		return "redirect:/video/list/" + parentId;
	}
	public File createFile(String fileUrl){
		File f=new File(fileUrl);
    	if(!f.exists()){
    		f.mkdirs();
    		
    	}
    	return f;
	}
	public void deleteFile(String fileUrl){
		File f = new File(fileUrl);
		if (f.exists()) {
			f.delete();
		}
	}
	
}

