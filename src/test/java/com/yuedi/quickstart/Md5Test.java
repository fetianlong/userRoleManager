package com.yuedi.quickstart;

import java.io.IOException;

import org.springside.modules.security.utils.Digests;
import org.springside.modules.utils.Encodes;


public class Md5Test {

	public static void main(String[] args) throws IOException {
		
		String password = "123456";
		
//		getPassWordBySalt(salt, password);
	
		getSaltAndPassWord(password);
	}
	
	/**
	 * 生成随机盐值并根据密码加密
	 * @param password  
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年7月9日 下午3:51:37
	 */
	public static void getSaltAndPassWord(String password){
		/*
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		user.setSalt(Encodes.encodeHex(salt));

		byte[] hashPassword = Digests.sha1(user.getPlainPassword().getBytes(), salt, HASH_INTERATIONS);
		user.setPassword(Encodes.encodeHex(hashPassword));
		*/
		byte[] salt = Digests.generateSalt(8);
		String s = Encodes.encodeHex(salt);
		System.out.println("新盐值：" + s);
		byte[] hashPassword = Digests.sha1(password.getBytes(), salt, 1024);
		String p = Encodes.encodeHex(hashPassword);
		System.out.println("密码：" + p);
	}
	
	/**
	 * 根据盐值和密码进行加密
	 * @param salt
	 * @param password  
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年7月9日 下午3:52:15
	 */
	public static void getPassWordBySalt(String s, String password){
		System.out.println("老盐值：" + s);
		byte[] salt = Encodes.decodeHex(s);
		byte[] hashPassword = Digests.sha1(password.getBytes(), salt, 1024);
		String p = Encodes.encodeHex(hashPassword);
		System.out.println("密码：" + p);
	}

}
