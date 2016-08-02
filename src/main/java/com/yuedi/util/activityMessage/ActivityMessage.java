package com.yuedi.util.activityMessage;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;


public class ActivityMessage {
	private static final String USER_ID = "823566";
	private static final String ACCOUNT_ID = "admin";
	private static final String PASSWORD = "823566";
	private static final String SEND_TYPE = "1";
	private static final String URL = "http://www.mxtong.net.cn/GateWay/Services.asmx/DirectSend?";
	
	// 调用接口DirectGetStockDetails
	public String directGetStockDetails(String url) {
		return excute(url);
	}

	// 调用接口DirectFetchSMS.
	public String directFetchSMS(String url) {
		return excute(url);
	}

	// 调用接口DirectSend,没有参数为中文的url时可调用如下方法.
	public String excute(String url) {
		HttpClient httpClient = new HttpClient();
		GetMethod getMethod = new GetMethod(url);
		try {
			getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler());
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: "
						+ getMethod.getStatusLine());
			}
			byte[] responseBody = getMethod.getResponseBody();
			String str = new String(responseBody, "GBK");
			if (str.contains("GBK")) {
				str = str.replaceAll("GBK", "utf-8");
			}
			int beginPoint = str.indexOf("<RetCode>");
			int endPoint = str.indexOf("</RetCode>");
			String result = "RetCode=";
			return result + str.substring(beginPoint + 9, endPoint);
		} catch (HttpException e) {
			return "1";
		} catch (IOException e) {
			return "2";
		}

		finally {
			getMethod.releaseConnection();
		}
	}

	// 调用接口DirectSend,对于参数为中文的调用采用以下方法,为防止中文参数为乱码.
	public static String directSend(DirectSendDTO directSendDTO) {
		// String response = "";
		directSendDTO.setUserID(USER_ID);
		directSendDTO.setAccountID(ACCOUNT_ID);
		directSendDTO.setPassword(PASSWORD);
		directSendDTO.setSendType(SEND_TYPE);
		
		HttpClient httpClient = new HttpClient();
		httpClient.getParams().setBooleanParameter(
				"http.protocol.expect-continue", false);
		// String responseValue;
		PostMethod getMethod = new UTF8PostMethod(URL);
		NameValuePair[] data = {
				new NameValuePair("UserID", directSendDTO.getUserID()),
				new NameValuePair("Account", directSendDTO.getAccountID()),
				new NameValuePair("Password", directSendDTO.getPassword()),
				new NameValuePair("Phones", directSendDTO.getPhones()),
				new NameValuePair("SendType", directSendDTO.getSendType()),
				new NameValuePair("SendTime", directSendDTO.getSendTime()),
				new NameValuePair("PostFixNumber", directSendDTO
						.getPostFixNumber()),
				new NameValuePair("Content", directSendDTO.getContent()) };
		getMethod.setRequestBody(data);
		getMethod.addRequestHeader("Connection", "close");
		try {
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: "
						+ getMethod.getStatusLine());
			}
			byte[] responseBody = getMethod.getResponseBody();
			String str = new String(responseBody, "GBK");
			java.net.URLEncoder.encode(str,"GB2312");
			if (str.contains("GBK")) {
				str = str.replaceAll("GBK", "utf-8");
			}
			DomTest dom=new DomTest();
			String retCodeValue="";
			try {
				retCodeValue=dom.readXMLString4TagName(str, "RetCode");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return retCodeValue;
		} catch (HttpException e) {
			return "1";
		} catch (IOException e) {
			return "2";
		} finally {
			getMethod.releaseConnection();
		}

	}

	public static class UTF8PostMethod extends PostMethod {
		public UTF8PostMethod(String url) {
			super(url);
		}

		@Override
		public String getRequestCharSet() {
			// return Charset.defaultCharset().name();
			// return super.getRequestCharSet();
			return "UTF-8";
		}
	}

	/*public static void main(String[] args) {
		HttpClientTest httpClientTest = new HttpClientTest();
		// 调用DirectGetStockDetails和DirectFetchSMS接口传入的参数
		String userID = "900008";
		String accountID = "769116630s";
		String password = "769116630s";
		String userID = "823566";
		String accountID = "admin";
		String password = "823566";
		// 调用DirectSend接口传入的参数
		DirectSendDTO directSendDTO = new DirectSendDTO();
		directSendDTO.setUserID(userID);
		directSendDTO.setAccountID(accountID);
		directSendDTO.setPassword(password);
		directSendDTO.setPhones("15201049721;");
		String str = null;
		try {
			str = java.net.URLEncoder.encode("【妈妈范】[青岛中心] 起价减半！15元起送、5元运费、39元免运费！天渐凉多下单，足部出户尝新鲜！APP下载请访问mumfans.com（回T退订）","UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		directSendDTO.setContent(str);
		directSendDTO.setSendType("1");
		// 调用接口DirectSend
		System.out.println("-----DirectSend begin-----");
		System.out.println(httpClientTest.directSend(
				"http://www.mxtong.net.cn/GateWay/Services.asmx/DirectSend?",
				directSendDTO));
		System.out.println("-----DirectSend end-----");
		
	}*/
}
