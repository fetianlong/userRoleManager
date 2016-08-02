package com.yuedi.web.clientManagerment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yuedi.entity.AppRegister;
import com.yuedi.entity.Areas;
import com.yuedi.entity.HospitalManagement;
import com.yuedi.entity.Menu;
import com.yuedi.entity.MyPage;
import com.yuedi.entity.Seller;
import com.yuedi.service.AppClientService;
import com.yuedi.service.AreasService;
import com.yuedi.service.HospitalManagementService;
import com.yuedi.service.SellerService;
import com.yuedi.service.UserInfoService;
import com.yuedi.web.actionManagerment.ActionManagermentController;
import com.yuedi.web.common.SupperController;

@Controller
@RequestMapping(value = "/appClient")
public class AppClientController extends SupperController {
	@Autowired
	private AppClientService appClientService;
	@Autowired
	private AreasService areasService;
	@Autowired
	private HospitalManagementService hospitalManagementService;
	@Autowired
	private SellerService sellerService;
	@Autowired
	private UserInfoService userInfoService;

	private static final Logger logger = Logger
			.getLogger(ActionManagermentController.class);

	@RequestMapping(value = "list/{parentId}", method = RequestMethod.GET)
	public String list(@PathVariable("parentId") Long parentId, Model model,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			HttpServletRequest request) {
		String userName = request.getParameter("userName");
		String tel = request.getParameter("tel");
		String birthday = request.getParameter("birthday");
		String registerStartTime = request.getParameter("registerStartTime");
		String registerEndTime = request.getParameter("registerEndTime");
		String sex = request.getParameter("sex");
		String hospital = request.getParameter("hospital");
		String seller = request.getParameter("seller");
		String status = request.getParameter("status");
		String origin = request.getParameter("origin");
		String provinceId = request.getParameter("provinceId");
		String cityId = request.getParameter("cityId");
		Long sellerId = this.getCurrentUserFranchiseesIdId();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		try {
			model.addAttribute("parentId", parentId);
			model.addAttribute("date", this.getCurrentDate());
			// 获取当前用户所拥有的菜单
			/*List<Menu> menuList = this.getFirstMenuByUserId();
			model.addAttribute("menuList", menuList);*/
			// 获取二级菜单
			List<Menu> towMenuList = this.getTwoMenuByParentId(parentId);
			model.addAttribute("towMenuList", towMenuList);

			MyPage<AppRegister> page = new MyPage<AppRegister>();
			page.setNumber(pageNumber);
			String actionTitle = request.getParameter("actionTitle");
			model.addAttribute("actionTitle", actionTitle);
			if (actionTitle != null && !"".equals(actionTitle)) {
				page.getParams().put("actionTitle", actionTitle + "%");
			}
			String executorId = request.getParameter("executorId");
			model.addAttribute("executorId", executorId);
			if (executorId != null && !"".equals(executorId)) {
				page.getParams().put("executorId", executorId);
			}
			String state = request.getParameter("state");
			model.addAttribute("state", state);
			if (state != null && !"".equals(state)) {
				page.getParams().put("state", state);
			}
			
			if(null != userName && !(("").equals(userName))) {
				page.getParams().put("userName", userName + "%");
				model.addAttribute("userName", userName);
			}
			if(null != tel && !(("").equals(tel))) {
				page.getParams().put("tel", tel);
				model.addAttribute("tel", tel);
			}
			if (null != birthday && !(("").equals(birthday))) {
				page.getParams().put("birthday", sd.parse(birthday));
				model.addAttribute("birthday", birthday);
			}
			if (null != registerStartTime && !(("").equals(registerStartTime))) {
				page.getParams().put("registerStartTime", sdf.parse(registerStartTime));
				model.addAttribute("registerStartTime", sdf.parse(registerStartTime));
			}
			if (null != registerEndTime && !(("").equals(registerEndTime))) {
				page.getParams().put("registerEndTime", sdf.parse(registerEndTime));
				model.addAttribute("registerEndTime", sdf.parse(registerEndTime));
			}
			if (null != sex && !(("").equals(sex))) {
				page.getParams().put("sex", sex);
				model.addAttribute("sex", sex);
			}
			if (null != status && !(("").equals(status))) {
				page.getParams().put("status", status);
				model.addAttribute("status", status);
			}
			if (null != hospital && !(("").equals(hospital))) {
				page.getParams().put("hospital", Long.parseLong(hospital));
				model.addAttribute("hospitalId", hospital);
			}
			if (null != seller && !(("").equals(seller))) {
				page.getParams().put("seller", Integer.parseInt(seller));
				model.addAttribute("seller", seller);
			}
			if (null != provinceId && !(("").equals(provinceId))) {
				page.getParams().put("provinceId", Integer.parseInt(provinceId));
				model.addAttribute("provinceId", provinceId);
			}
			if (null != cityId && !(("").equals(cityId))) {
				page.getParams().put("cityId", Integer.parseInt(cityId));
				model.addAttribute("cityId", cityId);
			}
			page.getParams().put("sellerId", sellerId);
			if (origin != null && !(("").equals(origin))
					&& Integer.parseInt(origin) == 1) {
				page.getParams().put("salerId", new Long(1));
				model.addAttribute("salerId", new Long(1));
			} else if (origin != null && !(("").equals(origin))
					&& Integer.parseInt(origin) == 2) {
				page.getParams().put("salerId", new Long(2));
				model.addAttribute("salerId", new Long(2));
			} else {
				page.getParams().put("salerId", new Long(0));
				model.addAttribute("salerId", new Long(0));
			}
			List<HospitalManagement> hospitalList= hospitalManagementService.selectHospitalManagementAll();
			model.addAttribute("hospitalList", hospitalList);
			List<Seller> sellerlist= sellerService.selectSimpleSeller();
			model.addAttribute("sellerlist", sellerlist);
			List<Areas> provinceList = areasService.findProvince();
			model.addAttribute("provinceList", provinceList);
			if (sellerId == 3) {
				List<AppRegister> list = appClientService.getAllAppRegister(page);
				List<AppRegister> arList = new ArrayList<AppRegister>();
				for (AppRegister appr : list) {
					Long salerCode = appr.getSalerId();
					if (null != salerCode) {
						appr.setOrigin(1);
					} else {
						appr.setOrigin(2);
					}
					int proId = appr.getProvinceId();
					int cId = appr.getCityId();
					if (proId != 0 && cId != 0) {
						Areas a = areasService.findNameById(proId);
						appr.setProvinceName(a.getName());
						a = areasService.findNameById(cId);
						appr.setCityName(a.getName());
					} else {
						appr.setProvinceName("");
						appr.setCityName("");
					}
					arList.add(appr);
				}
				model.addAttribute("arList", arList);
				model.addAttribute("pageData", page);
			} else {
				List<AppRegister> list = appClientService.getAllAppRegisterBySellerId(page);
				List<AppRegister> arList = new ArrayList<AppRegister>();
				for (AppRegister appr : list) {
					Long salerCode = appr.getSalerId();
					if (null != salerCode) {
						appr.setOrigin(1);
					} else {
						appr.setOrigin(2);
					}
					int proId = appr.getProvinceId();
					int cId = appr.getCityId();
					if (proId != 0 && cId != 0) {
						Areas a = areasService.findNameById(proId);
						appr.setProvinceName(a.getName());
						a = areasService.findNameById(cId);
						appr.setCityName(a.getName());
						;
					} else {
						appr.setProvinceName("");
						appr.setCityName("");
					}
					arList.add(appr);
				}
				model.addAttribute("arList", arList);
				model.addAttribute("pageData", page);
			}
		} catch (Exception e) {
			logger.error("AppClientController err list", e);
		}

		return "appClient/appClientList";
	}
}
