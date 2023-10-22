package com.pj.project.lottery.lottery_calculate_nine;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Model: lottery_calculate_nine -- 九转连环图统计表
 * @author lizhihao 
 */
@Data
@Accessors(chain = true)
public class LotteryCalculateNine implements Serializable {

	// ---------- 模块常量 ----------
	/**
	 * 序列化版本id 
	 */
	private static final long serialVersionUID = 1L;	
	/**
	 * 此模块对应的表名 
	 */
	public static final String TABLE_NAME = "lottery_calculate_nine";	
	/**
	 * 此模块对应的权限码 
	 */
	public static final String PERMISSION_CODE = "lottery-calculate-nine";	


	// ---------- 表中字段 ----------
	/**
	 * 主键id 
	 */
	public Long id;	

	/**
	 * 年份 
	 */
	public Integer year;	

	/**
	 * 月份 
	 */
	public Integer month;	

	/**
	 * 类型  1：年份  2：月份 
	 */
	public Integer calType;	

	/**
	 * 区号区间 
	 */
	public String codes;	

	/**
	 * 九转连环分布比 
	 */
	public String nineTurn;	

	/**
	 * 统计个数 
	 */
	public Integer nineTurnCount;	

	/**
	 * 09,17,33 
	 */
	public Integer nineTurnType;	

	/**
	 * 九转连环轴上最大个数 
	 */
	public Integer nineTurnMax;	

	/**
	 * 九转连环轴上最小个数 
	 */
	public Integer nineTurnMin;	





	


}
