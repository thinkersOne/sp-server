package com.pj.project.lottery.lottery_config;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Model: lottery_config -- 配置表
 * @author lizhihao 
 */
@Data
@Accessors(chain = true)
public class LotteryConfig implements Serializable {

	// ---------- 模块常量 ----------
	/**
	 * 序列化版本id 
	 */
	private static final long serialVersionUID = 1L;	
	/**
	 * 此模块对应的表名 
	 */
	public static final String TABLE_NAME = "lottery_config";	
	/**
	 * 此模块对应的权限码 
	 */
	public static final String PERMISSION_CODE = "lottery-config";	


	// ---------- 表中字段 ----------
	/**
	 * 主键id 
	 */
	public Long id;	

	/**
	 * 红球比例 
	 */
	public Double redRate;	

	/**
	 * 奇偶比例 
	 */
	public Double redParityRate;	

	/**
	 * 区间比例 
	 */
	public Double redRangeRate;	

	/**
	 * 和值比例 
	 */
	public Double redSumRate;	

	/**
	 * 连号个数比例 
	 */
	public Double consecutiveNumbersCountRate;	

	/**
	 * 最大连号数比例 
	 */
	public Double maxConsecutiveNumbersRate;	

	/**
	 * 1:年 2:月 3:周  4:类型 
	 */
	public Integer type;	

	public Double redNineTurn09Rate;
	public Double redNineTurn17Rate;
	public Double redNineTurn33Rate;



	


}
