package com.pj.project.lottery.lottery_strategy_record;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Model: lottery_strategy_record -- 策略记录表
 * @author lizhihao 
 */
@Data
@Accessors(chain = true)
@Builder
public class LotteryStrategyRecord implements Serializable {

	// ---------- 模块常量 ----------
	/**
	 * 序列化版本id 
	 */
	private static final long serialVersionUID = 1L;	
	/**
	 * 此模块对应的表名 
	 */
	public static final String TABLE_NAME = "lottery_strategy_record";	
	/**
	 * 此模块对应的权限码 
	 */
	public static final String PERMISSION_CODE = "lottery-strategy-record";	


	// ---------- 表中字段 ----------
	/**
	 * 主键id 
	 */
	public Long id;	

	/**
	 * 策略编号-雪花 
	 */
	public String strategyNo;
	public String code;

	/**
	 * 统计数据量 
	 */
	public Integer total;	

	/**
	 * 是否命中 
	 */
	public boolean enableContain;

	/**
	 * 策略 
	 */
	public String strategy;	





	


}
