package com.pj.utils;


import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Nullable;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Date;

/**
 * 用于测试用时
 * @author kong
 *
 */
@Slf4j
public class Ttime {

	private long start=0;	//开始时间
	private long end=0;		//结束时间

	public static Ttime t = new Ttime();	//static快捷使用
	private static String DATE_TIME_FORMAT_14 = "yyyy-MM-dd HH:mm:ss";
	private static String DATE_TIME_FORMAT_8 = "yyyy-MM-dd";

	/**
	 * 开始计时
	 * @return
	 */
	public Ttime start() {
		start=System.currentTimeMillis();
		return this;
	}


	/**
	 * 结束计时
	 */
	public Ttime end() {
		end=System.currentTimeMillis();
		return this;
	}


	/**
	 * 返回所用毫秒数
	 */
	public long returnMs() {
		return end-start;
	}

	/**
	 * 格式化输出结果
	 */
	public void outTime() {
		System.out.println(this.toString());
	}

	/**
	 * 结束并格式化输出结果
	 */
	public void endOutTime() {
		this.end().outTime();
	}

	@Override
	public String toString() {
		return (returnMs() + 0.0) / 1000 + "s";		// 格式化为：0.01s
	}

	public static boolean isBefore(String dateTimeA, LocalDateTime localDateTime){
		// 定义日期时间格式
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_14);
		LocalDateTime localDateTimeA = LocalDateTime.parse(dateTimeA, formatter);
		// 将字符串日期时间字段转换为 LocalDateTime 对象
		LocalDateTime databaseTime = LocalDateTime.parse(dateTimeA, formatter);
		// 比较两个时间
		return databaseTime.isBefore(localDateTime);
	}

	/**
	 * 获取指定 月 前时间
	 * @param month
	 * @return
	 */
	public static LocalDateTime getLocalDateTime(int month){
		// 获取当前时间
		LocalDateTime now = LocalDateTime.now();
		return now.minus(month, ChronoUnit.MONTHS);
	}

	public static String getStartOfDay(){
		// 获取当前日期时间的凌晨时间
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime dateOnly = now.toLocalDate().atStartOfDay();
		// 定义日期时间格式
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_14);
		// 格式化日期时间
		String formattedDateTime = dateOnly.format(formatter);
		// 输出格式化后的日期时间
		return formattedDateTime;
	}

	/**
	 * date 格式必须是 XXXX-XX-XX
	 * @param date
	 * @return
	 */
	public static int getYear(String date){
		return Integer.parseInt(getDateArrays( date)[0]);
	}

	public static int getMonth(String date){
		return Integer.parseInt(getDateArrays( date)[1]);
	}

	public static String getWeek(String date){
		String dateArray = getDateArrays(date)[2];
		String s = dateArray.substring(dateArray.indexOf("(")+1, dateArray.indexOf(")"));
		return s;
	}

	public static String getYearMonthDay(String date){
		if(date == null || "".equals(date)){
			return null;
		}
		return date.substring(0,date.indexOf("("));
	}

	public static String getWeekOfYear(String dateStr) {
		//获取一个Calendar对象
		Calendar calendar = Calendar.getInstance();
		//设置星期一为一周开始的第一天
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		//设置在一年中第一个星期所需最少天数
		calendar.setMinimalDaysInFirstWeek(4);
		//格式化日期
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_TIME_FORMAT_8);
		Date parse = null;
		try {
			parse = simpleDateFormat.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException("日期格式不正确");
		}
		calendar.setTime(parse);
		int weekYear = calendar.getWeekYear();
		return weekYear+"-"+calendar.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * date 格式必须是 XXXX-XX-XX
	 * @param date
	 * @return
	 */
	@Nullable
	private static String[] getDateArrays(String date) {
		if(date == null || "".equals(date)){
			return null;
		}
		String[] split = date.split("-");
		return split;
	}

	public static String addDay(String dateStr,int day){
		SimpleDateFormat df=new SimpleDateFormat(DATE_TIME_FORMAT_8);
		try {
			Date d = df.parse(dateStr);
			return df.format(new Date(d.getTime() + day * 24 * 60 * 60 * 1000));
		} catch (ParseException e) {
			log.error(e.getMessage());
			throw new RuntimeException(e);
		}
	}

	public static String devideDay(String dateStr,int day){
		SimpleDateFormat df=new SimpleDateFormat(DATE_TIME_FORMAT_8);
		try {
			Date d = df.parse(dateStr);
			return df.format(new Date(d.getTime() - day * 24 * 60 * 60 * 1000));
		} catch (ParseException e) {
			log.error(e.getMessage());
			throw new RuntimeException(e);
		}
	}


}