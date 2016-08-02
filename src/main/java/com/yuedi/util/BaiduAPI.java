package com.yuedi.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;

/** 
* 获取经纬度
* 
* @author jueyue 返回格式：Map<String,Object> map map.put("status", 
* reader.nextString());//状态 map.put("result", list);//查询结果 
* list<map<String,String>> 
* 密钥:f247cdb592eb43ebac6ccd27f796e2d2 
*/ 
public class BaiduAPI { 
	
	/** 
	* @param addr 
	* 查询的地址 
	* @return 
	* @throws IOException 
	*/ 
	public Object[] getCoordinate(String addr) throws IOException { 
		String lng = null;//经度
		String lat = null;///纬度
		String address = null; 
		try { 
			address = java.net.URLEncoder.encode(addr, "UTF-8"); 
		}catch (UnsupportedEncodingException e1) { 
			e1.printStackTrace(); 
		} 
		String key = "f247cdb592eb43ebac6ccd27f796e2d2"; 
		String url = String .format("http://api.map.baidu.com/geocoder?address=%s&output=json&key=%s", address, key); 
		URL myURL = null; 
		URLConnection httpsConn = null; 
		try { 
			myURL = new URL(url); 
		} catch (MalformedURLException e) { 
			e.printStackTrace(); 
		} 
		InputStreamReader insr = null;
		BufferedReader br = null;
		try { 
			httpsConn = (URLConnection) myURL.openConnection();// 不使用代理 
			if (httpsConn != null) { 
				insr = new InputStreamReader( httpsConn.getInputStream(), "UTF-8"); 
				br = new BufferedReader(insr); 
				String data = null; 
				int count = 1;
				while((data= br.readLine())!=null){ 
					if(count==5){
						lng = (String)data.subSequence(data.indexOf(":")+1, data.indexOf(","));//经度
						count++;
					}else if(count==6){
						lat = data.substring(data.indexOf(":")+1);//纬度
						count++;
					}else{
						count++;
					}
				} 
			} 
		} catch (IOException e) { 
			e.printStackTrace(); 
		} finally {
			if(insr!=null){
				insr.close();
			}
			if(br!=null){
				br.close();
			}
		}
		return new Object[]{lng,lat}; 
	} 

	/**
	 * 根据IP获取当前地理位置信息
	 * @param ip
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年6月11日 上午10:41:10
	 */
	public String getAddByIp(String ip) {
		String add = "";
		try {
			String key = "f247cdb592eb43ebac6ccd27f796e2d2";
			String url = "http://api.map.baidu.com/location/ip?coor=bd09ll&ak="+key+"&ip=" + ip;
			JSONObject json = readJsonFromUrl(url);
//			System.out.println(((JSONObject) json.get("content")).get("address"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return add;
	}
	
	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
	    InputStream is = new URL(url).openStream();
	    try {
	    	BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	    	String jsonText = readAll(rd);
	    	JSONObject json = new JSONObject(jsonText);
	    	return json;
	    } finally {
	    	is.close();
	     // System.out.println("同时 从这里也能看出 即便return了，仍然会执行finally的！");
	    }
	}
}