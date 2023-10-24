package com.pj.project.lottery.lottery_forecast;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Model: lottery_forecast -- 号码预测
 * @author lizhihao 
 */
@Data
@Accessors(chain = true)
public class LotteryForecast implements Serializable {

	// ---------- 模块常量 ----------
	/**
	 * 序列化版本id 
	 */
	private static final long serialVersionUID = 1L;	
	/**
	 * 此模块对应的表名 
	 */
	public static final String TABLE_NAME = "lottery_forecast";	
	/**
	 * 此模块对应的权限码 
	 */
	public static final String PERMISSION_CODE = "lottery-forecast";	


	// ---------- 表中字段 ----------
	/**
	 * 主键id 
	 */
	public Long id;	

	/**
	 * 预测红球 
	 */
	public String red;	

	/**
	 * 奇偶比 
	 */
	public String redParityRatio;	

	/**
	 * 分区比 
	 */
	public String redRangeRatio;	

	/**
	 * 红球和值 
	 */
	public String redSum;	

	/**
	 * 连号个数 
	 */
	public Integer consecutiveNumbersCount;

	/**
	 * 最大连号数 
	 */
	public Integer maxConsecutiveNumbersCount;

	/**
	 * 九转连环-09 
	 */
	public String nineTurn09;	

	/**
	 * 九转连环-17 
	 */
	public String nineTurn17;	

	/**
	 * 九转连环-33 
	 */
	public String nineTurn33;	

	/**
	 * 预测期号 
	 */
	public String code;	

	/**
	 * 预测中奖号码 
	 */
	public String forecastRed;	

	/**
	 * 预测蓝球 
	 */
	public String blue;	





	


}
