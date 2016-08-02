package com.yuedi.web.alipay.direct;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuedi.alipay.config.AlipayConfig;
import com.yuedi.alipay.util.AlipayNotify;
import com.yuedi.alipay.util.AlipaySubmit;
import com.yuedi.entity.OrderManagementMech;
import com.yuedi.entity.fm.InvitationCount;
import com.yuedi.service.UserInfoService;
import com.yuedi.service.fm.InvitationService;
import com.yuedi.web.common.SupperController;

@Controller
@RequestMapping(value = "/alipayDirect")
public class AlipayDirectController extends SupperController {

	@Autowired
	private InvitationService invitationService;
	
	@Autowired
	UserInfoService userInfoService;
	
	@RequestMapping(value = "/web/deposit", method = RequestMethod.POST)
//	public String deposit(Model model, HttpServletRequest request) {
	public ResponseEntity<HttpEntity> deposit(Model model, HttpServletRequest request) {
		String invitationAllPrice = request.getParameter("invitationAllPrice");
		String parentId = request.getParameter("parentId");
		String rmak = "购买邀请码";
		String up = request.getParameter("up");
		Date date = new Date();
		Map<String, String> sParaTemp = new HashMap<String, String>();
		// 支付类型
		// 必填，不能修改
		String payment_type = "1";
		// 服务器异步通知页面路径
		// 需http://格式的完整路径，不能加?id=123这类自定义参数
		String notify_url = "http://www.mumfans.com:8083/yuedi-web/alipayDirect/async";
//		String notify_url = "http://192.168.0.251:8080/yuedi-web/alipayDirect/async";
//		String notify_url = "";
		// 页面跳转同步通知页面路径
		// 需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/
//		String return_url = "http://192.168.0.251:8080/yuedi-web/alipayDirect/return_url";
//		String return_url = "";
		String return_url = "http://www.mumfans.com:8083/yuedi-web/alipayDirect/return_url";
		// 商户订单号.
		// 商户网站订单系统中唯一订单号，必填
		String out_trade_no = "SJ_YQM_" + date.getTime() + System.currentTimeMillis() + "";
		// 订单名称
		// 必填
		String subject = "邀请码购买";
		// 防钓鱼时间戳
		// 若要使用请调用类文件submit中的query_timestamp函数
		String anti_phishing_key = "";
		// 客户端的IP地址
		// 非局域网的外网IP地址，如：221.0.0.1
		String exter_invoke_ip = "101.200.172.162";
		sParaTemp.put("service", "create_direct_pay_by_user");
		sParaTemp.put("partner", AlipayConfig.partner);
		sParaTemp.put("seller_email", AlipayConfig.seller_email);
		sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("payment_type", payment_type);
		sParaTemp.put("notify_url", notify_url);
		sParaTemp.put("return_url", return_url);
		sParaTemp.put("out_trade_no", out_trade_no); // 订单号
		sParaTemp.put("subject", subject); // 订单名称
		sParaTemp.put("total_fee", invitationAllPrice); // 付款金额
		sParaTemp.put("body", rmak); // 订单描述
		sParaTemp.put("show_url", ""); //商品展示地址
		sParaTemp.put("anti_phishing_key", anti_phishing_key);
		sParaTemp.put("exter_invoke_ip", exter_invoke_ip);
		
		String addCount = request.getParameter("addCount");
//		sParaTemp.put("invitationCount", addCount);
//		sParaTemp.put("parentId", parentId);
//		sParaTemp.put("createUserId", super.getCurrentUserId().toString());
//		sParaTemp.put("sellerId", super.getCurrentUserFranchiseesIdId().toString());

		String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "post", "确认");
		
		OrderManagementMech od = new OrderManagementMech();
		od.setComName("邀请码");
		od.setBuyMoney(invitationAllPrice);
		od.setBuyNum(Integer.parseInt(addCount));
		od.setBuyType(0);
		od.setOrderId(out_trade_no);
		od.setpWay(0);
		od.setRemark(rmak);
		od.setSellerId(getCurrentUserFranchiseesIdId());
		od.setState(0);
		od.setuPrice(up);
		od.setBuyTime(date);
		od.setUserId(getCurrentUserId());
		userInfoService.insertOrderManagementMech(od);
		
		model.addAttribute("sHtmlText", sHtmlText);
		// model.addAttribute("fundDetail", fundDetail);
//		return null;
		return new ResponseEntity(model, HttpStatus.OK);
	}
	
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping(value = "/async", method = RequestMethod.POST)
	public String async(HttpServletRequest request) throws UnsupportedEncodingException {
		System.out.println("异步调用。。。。。。。。。。。。。。。。。。");
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			params.put(name, valueStr);
		}
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		//商户订单号
		String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

		//支付宝交易号
		String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

		//交易状态
		String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
		// String notifyId = request.getParameter("notify_id");
		if (AlipayNotify.verify(params)) {// 验证成功
			if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {
				System.out.println(">>>>>充值成功>>>>>充值成功>>>>>充值成功>>>>>充值成功>>>>>充值成功>>>>>充值成功");
				// 要写的逻辑。自己按自己的要求写
				//查询当前机构是否存在记录
				OrderManagementMech od = userInfoService.selectOrderManagementMech(out_trade_no);
				if(null != od && od.getId()>0){
					Long sellerId = od.getSellerId();
					Integer invitationC = od.getBuyNum();
//					String parentId = request.getParameter("parentId");
					String createUserId = request.getParameter("createUserId");
					
					System.out.println("机构ID" + sellerId + "      数量：" + invitationC + "    用户Id：" + createUserId);
					InvitationCount invc = invitationService.getInvitationCountBySellerId(sellerId);
					
					if(null != invc && invc.getId()>0){	//如果有记录，就更新下数量
						invc.setAddCount(invc.getAddCount() + invitationC);
						invc.setResidueCount(invc.getResidueCount() + invitationC);
						invitationService.updateInvitationCountSelective(invc);
					}else{	//否则就新增
						InvitationCount invitationCount = new InvitationCount();
						invitationCount.setCreateTime(new Date());
						invitationCount.setCreateUser(Long.parseLong(createUserId));
						invitationCount.setResidueCount(new Long(invitationC));
						invitationService.insertInvitationCount(invitationCount);
					}
					od.setState(1);
					userInfoService.updateOrderManagementMech(od);
				}
				System.out.println(">>>>>充值成功" + trade_status);
			}
			return "invitation/buySuccess";
		} else {// 验证失败
			return "invitation/buyFail";
		}
	}
	@RequestMapping(value = "/testUrl/{id}")
	public String testUrl(@PathVariable("id") Integer id){
		invitationService.deleteByPrimaryKey(id);
		return "invitation/buySuccess";
	}
	@RequestMapping(value = "/testUrl1/{id}")
	public String testUrl1(@PathVariable("id") Long id){
		userInfoService.deleteUserInfo(id);
		return "invitation/buySuccess";
	}

	@RequestMapping(value = "/return_url")
	public String Return_url(HttpServletRequest request, HttpServletResponse response) {
		String parentId = request.getParameter("parentId");
		if(StringUtils.isEmpty(parentId)){
			parentId = "267";
		}
		System.out.println("parentId=============" + parentId);
		return "redirect:/invitation/listCountInvitationBySellerOfMy/"+parentId;
	}
	
}
