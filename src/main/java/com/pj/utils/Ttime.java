package com.pj.utils;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * 用于测试用时
 * @author kong
 *
 */
public class Ttime {

	private long start=0;	//开始时间
	private long end=0;		//结束时间

	public static Ttime t = new Ttime();	//static快捷使用
	private static String DATE_TIME_FORMAT_14 = "yyyy-MM-dd HH:mm:ss";

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

	public static void main(String[] args) {
//		String dateTimeA = "2022-09-12 10:28:43";
//		LocalDateTime localDateTime = getLocalDateTime(1);
//		System.out.println(localDateTime.toString());
//		boolean after = isBefore(dateTimeA, localDateTime);
//		System.out.println(after);

		String startOfDay = getStartOfDay();
		System.out.println(startOfDay);

		System.out.println(0 % 2);
	}


}