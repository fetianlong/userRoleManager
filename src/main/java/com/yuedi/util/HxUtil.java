package com.yuedi.util;


import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HxUtil {
	/**
	 * 返回带有安全证书的的HttpClient
	 * 
	 * @author Sayi
	 *
	 */
	public static CloseableHttpClient createSSLInsecureClient() {
		try {
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(
					null, new TrustStrategy() {
						// 信任所有
						public boolean isTrusted(X509Certificate[] chain,
								String authType) throws CertificateException {
							return true;
						}
					}).build();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
					sslContext);
			return HttpClients.custom().setSSLSocketFactory(sslsf).build();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		}
		return HttpClients.createDefault();
	}

	/**
	 * 返回环信的 token
	 * 
	 * @author linqingchuang
	 *
	 * @date 2015年6月25日
	 */
	@SuppressWarnings("all")
	public static String HttpPostHxtoken() {
		String access_token = null;
		try {
			HttpClient httpclient = new DefaultHttpClient();
			String uri = "https://a1.easemob.com/yuedi/mumfans/token";
			HttpPost httppost = new HttpPost(uri);
			// 添加http头信息
			httppost.addHeader("Authorization", "your token"); // 认证token
			httppost.addHeader("Content-Type", "application/json");
			httppost.addHeader("User-Agent", "imgfornote");
			JSONObject obj = new JSONObject();
			obj.put("grant_type", "client_credentials");
			obj.put("client_id", "YXA6T3HxMBmfEeWLq_06WNfXgg");
			obj.put("client_secret", "YXA6IDgCO6aWz5O2klVyIvrDsonlFCc");
			httppost.setEntity(new StringEntity(obj.toString()));
			HttpResponse response;
			response = createSSLInsecureClient().execute(httppost);
			// 检验状态码，如果成功接收数据

			int code = response.getStatusLine().getStatusCode();
			if (code == 200) {
				String rev = EntityUtils.toString(response.getEntity());
				JSONObject revJson = JSONObject.fromObject(rev);
				access_token = (String) revJson.get("access_token");
			}
		} catch (ClientProtocolException e) {
			return null;
		} catch (IOException e) {
			return null;
		} catch (Exception e) {
			return null;
		}
		return access_token;
	}

	/**
	 * 
	 * @param token
	 * 
	 * @param userName
	 *            环信用户名
	 * 
	 * @param pwd
	 *            环信密码
	 * 
	 * @author linqingchuang
	 *
	 * @date 2015年6月25日
	 */

	@SuppressWarnings("all")
	public static String HttpPostDataHxUser(String token, String userName,
			String pwd) {
		String application = null;
		try {
			HttpClient httpclient = new DefaultHttpClient();
			String uri = "https://a1.easemob.com/yuedi/mumfans/users";
			HttpPost httppost = new HttpPost(uri);
			// 添加http头信息
			httppost.addHeader("Authorization", "Bearer " + token); // 认证token
			httppost.addHeader("Content-Type", "application/json");
			httppost.addHeader("User-Agent", "imgfornote");
			JSONObject obj = new JSONObject();
			obj.put("username", userName);
			obj.put("password", pwd);
			httppost.setEntity(new StringEntity(obj.toString()));
			HttpResponse response;
			response = createSSLInsecureClient().execute(httppost);
			// 检验状态码，如果成功接收数据
			int code = response.getStatusLine().getStatusCode();
			if (code == 200) {
				String rev = EntityUtils.toString(response.getEntity());
				JSONObject revJson = JSONObject.fromObject(rev);
				application = (String) revJson.get("application");
			}
		} catch (ClientProtocolException e) {
			return null;
		} catch (IOException e) {
			return null;
		} catch (Exception e) {
			return null;
		}
		return application;
	}
	
	/**
	 * 
	 * @param token
	 * 
	 * @param groupname
	 *            群组名称
	 * 
	 * @param owner
	 *            环信群组管理员
	 * 
	 * @return groupid 群组ID
	 * 
	 * @author linqingchuang
	 *
	 * @date 2015年6月25日
	 */

	@SuppressWarnings("all")
	public static String HttpPostDataHxGroup(String token, String groupname,
			String owner,String desc) {
		String groupid = null;
		try {
			HttpClient httpclient = new DefaultHttpClient();
			String uri = "https://a1.easemob.com/yuedi/mumfans/chatgroups";
			HttpPost httppost = new HttpPost(uri);
			// 添加http头信息
			httppost.addHeader("Authorization", "Bearer " + token); // 认证token
			httppost.addHeader("Content-Type", "application/json");
			httppost.addHeader("User-Agent", "imgfornote");
			// http post的json数据格式： {"name": "your name","parentId":
			// "id_of_parent"}
			JSONObject obj = new JSONObject();
			obj.put("groupname", groupname);
			obj.put("owner", owner);
			obj.put("public", true);
			obj.put("maxusers", 500);
			obj.put("approval", false);
			obj.put("desc", desc);

			httppost.setEntity(new StringEntity(obj.toString(), "UTF-8"));
			HttpResponse response;
			response = createSSLInsecureClient().execute(httppost);
			// 检验状态码，如果成功接收数据
			int code = response.getStatusLine().getStatusCode();
			if (code == 200) {
				String rev = EntityUtils.toString(response.getEntity());
				JSONObject Json = JSONObject.fromObject(rev);
				try {
					JSONObject Json1 = JSONObject.fromObject(Json.get("data"));
					groupid = Json1.get("groupid").toString();
				} catch (Exception e) {
					groupid = null;
				}
			}
		} catch (ClientProtocolException e) {
			return null;
		} catch (IOException e) {
			return null;
		} catch (Exception e) {
			return null;
		}
		return groupid;
	}
	
	/**
	 * 根据环信群组ID删除环信的群组
	 * 
	 * @author linqingchuang
	 *
	 * @date 2015年7月4日
	 */

		@SuppressWarnings("all")
		public static String HttpPostDataDelHxGroup(String token, String groupId) {
			String success = null;
			try {
				HttpClient httpclient = new DefaultHttpClient();
				String uri = "https://a1.easemob.com/yuedi/mumfans/chatgroups/"+groupId;
				System.out.println("删除的URL是"+uri);
				HttpDelete delete = new HttpDelete(uri);
				// 添加http头信息
				delete.addHeader("Authorization", "Bearer " + token); // 认证token
				delete.addHeader("Content-Type", "application/json");
				delete.addHeader("User-Agent", "imgfornote");
				HttpResponse response;
				response = createSSLInsecureClient().execute(delete);
				// 检验状态码，如果成功接收数据
				int code = response.getStatusLine().getStatusCode();
				if (code == 200) {
					 String rev = EntityUtils.toString(response.getEntity());
					JSONObject Json = JSONObject.fromObject(rev);
					try {
						JSONObject Json1 = JSONObject.fromObject(Json.get("data"));
						success = Json1.get("success").toString();
					} catch (Exception e) {
						success = null;
					}
				}
			} catch (ClientProtocolException e) {
				return null;
			} catch (IOException e) {
				return null;
			} catch (Exception e) {
				return null;
			}
			return success;
		}


	/**
	 * 注册的环信用户名
	 * 
	 * @author linqingchuang
	 *
	 * @date 2015年6月26日
	 */
	public static String HxUserName(Long id) {
		return "yuedi_" + String.valueOf(id);

	}

	/**
	 * 注册的环信的密码
	 * 
	 * @author linqingchuang
	 *
	 * @date 2015年6月26日
	 */
	public static String HxPwd() {
		return "123456";

	}

	// public static void main(String[] args) {
	//
	// //System.out.println(HttpPostHxtoken());
	//
	// if (HttpPostHxtoken()!=null) {
	// String userName="lamTracy00220";
	// String pwd="123456";
	// if (HttpPostDataHxUser(HttpPostHxtoken(),userName,pwd)!=null) {
	// System.out.println("成功");
	// }else{
	// System.out.println("失败");
	// }
	// }
	// // HttpPostDataHxUser();
	//
	// }
}
