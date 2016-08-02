/**
 * @type com.yuedi.util.Constants
 */
package com.yuedi.util;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 常量类
 * 
 * @author sky
 *
 * @date 2013-11-2
 *
 */
public class Constants implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String LOGIN_USER = "loginUser";
	
	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 * @return 空返回true,否则返回false
	 */
	public static boolean stringIsEmpty(String str) {
		return (str == null || str.equals(""));
	}
	
	
	
	/**
	 * 日期格式化
	 * 
	 * @param format
	 * @return
	 */
	public static String dateFormat(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat (format);
		return sdf.format(date);
	}
	
	/**
	 * 指定格式字符的串转换成日期
	 * 
	 * @param dateStr
	 * @param formatStr
	 * @return
	 */
	public static Date stringToDate(String dateStr,String formatStr){
		DateFormat dd=new SimpleDateFormat(formatStr);
		Date date=null;
		try {
			date = dd.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
}
