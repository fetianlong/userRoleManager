/**
 * @type com.yuedi.util.DateUtils
 */
package com.yuedi.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author sky
 *
 * @date 2014年10月12日
 *
 */
public class DateUtils {
	
	public static final String FORMAT_SHORT_DATE = "yyyy-MM-dd";
	public static final String FORMAT_SHORT_DATE_ZONE  = "yyyy-MM-dd Z";
	public static final String FORMAT_LONG_DATE = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMAT_LONG_DATE_ZONE = "yyyy-MM-dd HH:mm:ss Z";
	
	/**
	 * 格式化日期为字符串
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String format(Date date, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(date);
	}
	
	/**
	 * 转换成短日期字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String format2ShortDate(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_SHORT_DATE);
		return formatter.format(date);
	}
	
	/**
	 * 格式化成带时区的日期字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String format2ShortDateZone(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_SHORT_DATE_ZONE);
		return formatter.format(date);
	}
	
	/**
	 * 转换成长日期字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String format2LongDate(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_LONG_DATE);
		return formatter.format(date);
	}
	
	/**
	 * 转换成带时区的字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String format2LongDateZone(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_LONG_DATE_ZONE);
		return formatter.format(date);
	}
	
	/**
	 * 将时间戳转换成字符串
	 * 
	 * @param timestamp
	 * @param format
	 * @return
	 */
	public static String format(Timestamp timestamp, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(timestamp);
	}
	
	/**
	 * 转换短日期字符串为日期
	 * 
	 * @param source
	 * @return
	 * @exception ParseException
	 */
	public static Date parse(String source, String format) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.parse(source);
	}
	
	/**
	 * 转换成时间戳
	 * 
	 * @param source
	 * @return
	 * @exception ParseException 
	 */
	public static Timestamp parseTimestamp(String source, String format) throws ParseException {
		Date date = parse(source, format);
		return new Timestamp(date.getTime());
	}
	
	/**
	 * 转换短日期字符串为日期
	 * 
	 * @param source
	 * @return
	 * @exception ParseException
	 */
	public static Date parse2ShortDate(String source) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_SHORT_DATE);
		return formatter.parse(source);
	}
	
	/**
	 * 转换长日期字符串为日期
	 * 
	 * @param source
	 * @return
	 * @exception ParseException
	 */
	public static Date parse2LongDate(String source) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_LONG_DATE);
		return formatter.parse(source);
	}
	
	/**
	 * 获取日期为当前星期的第几天
	 * 星期的开始为星期一
	 * 
	 * @param date
	 * @return
	 */
	public static int getDayOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.setFirstDayOfWeek(Calendar.SUNDAY);
		return calendar.get(Calendar.DAY_OF_WEEK);
	}
	
	/**
	 * 获取日期为当年的第几个星期
	 * 
	 * @param date 日期
	 * @return
	 */
	public static int getWeekOfYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.setFirstDayOfWeek(Calendar.SUNDAY);
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}
	
	/**
	 * 日期加减
	 * 
	 * @param date 日期
	 * @param field 参照<code>Calendar</code>的field
	 * @param value 加则传入正值，减则传入负值
	 * @return
	 */
	public static Date addDate(Date date, int field, int value) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.setFirstDayOfWeek(Calendar.SUNDAY);
		calendar.add(field, value);
		return calendar.getTime();
	}
	
	/**
	 * 获取时间段内的随机日期
	 * @param beginDate
	 * @param endDate
	 * @return  java.util.Date
	 * @description   格式yyyy-MM-dd
	 * @version currentVersion  
	 * @author 兼画  
	 * @createtime 2015年5月2日 下午4:29:23
	 */
	public static Date randomDate(String beginDate, String endDate) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date start = format.parse(beginDate);// 开始日期
			Date end = format.parse(endDate);// 结束日期
			if (start.getTime() >= end.getTime()) {
				return null;
			}
			long date = random(start.getTime(), end.getTime());

			return new Date(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static long random(long begin, long end) {
		long rtnn = begin + (long) (Math.random() * (end - begin));
		if (rtnn == begin || rtnn == end) {
			return random(begin, end);
		}
		return rtnn;
	}
}
