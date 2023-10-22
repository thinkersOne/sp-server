package com.pj.project.lottery.lottery_calculate_per;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Model: lottery_calculate_per -- 统计计算每期双色球表
 * @author lizhihao 
 */
@Data
@Accessors(chain = true)
public class LotteryCalculatePer implements Serializable {

	// ---------- 模块常量 ----------
	/**
	 * 序列化版本id 
	 */
	private static final long serialVersionUID = 1L;	
	/**
	 * 此模块对应的表名 
	 */
	public static final String TABLE_NAME = "lottery_calculate_per";	
	/**
	 * 此模块对应的权限码 
	 */
	public static final String PERMISSION_CODE = "lottery-calculate-per";	


	// ---------- 表中字段 ----------
	/**
	 * 主键id 
	 */
	public Long id;	

	/**
	 * 期号 
	 */
	public String code;	

	/**
	 * 红色球 
	 */
	public String red;	

	/**
	 * 篮球 
	 */
	public String blue;	

	/**
	 * 红球奇数/偶数比例 
	 */
	public String redParityRatio;	

	/**
	 * 红球分区比例 
	 */
	public String redRangeRatio;	

	/**
	 * 红球和 
	 */
	public Integer redSum;	

	/**
	 * 蓝球奇数/偶数 
	 */
	public String blueParity;	

	/**
	 * 蓝球大/小 
	 */
	public String blueBigSmall;	

	/**
	 * 蓝球分区 
	 */
	public Integer blueRange;	

	/**
	 * 年 
	 */
	public Integer year;	

	/**
	 * 月 
	 */
	public Integer month;	

	/**
	 * 年-第几周 
	 */
	public String week;	

	/**
	 * 九转连环图-09 
	 */
	public String nineTurn09;	

	/**
	 * 九转连环图17 
	 */
	public String nineTurn17;	

	/**
	 * 九转连环图33 
	 */
	public String nineTurn33;	

	/**
	 * 最大连号数 
	 */
	public Integer maxConsecutiveNumbers;	

	/**
	 * 连号个数统计 
	 */
	public Integer consecutiveNumbersCount;	





	


}
