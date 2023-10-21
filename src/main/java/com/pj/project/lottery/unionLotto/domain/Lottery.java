package com.pj.project.lottery.unionLotto.domain;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Model: lottery -- 
 * @author lizhihao 
 */
@Data
@Accessors(chain = true)
public class Lottery implements Serializable {

	// ---------- 模块常量 ----------
	/**
	 * 序列化版本id 
	 */
	private static final long serialVersionUID = 1L;	
	/**
	 * 此模块对应的表名 
	 */
	public static final String TABLE_NAME = "lottery";	
	/**
	 * 此模块对应的权限码 
	 */
	public static final String PERMISSION_CODE = "lottery";	


	// ---------- 表中字段 ----------
	/**
	 * 期号 
	 */
	public String code;	

	/**
	 * 开奖日期 
	 */
	public String date;	

	/**
	 * 中奖号码：红 
	 */
	public String red;	

	/**
	 * 中奖号码：蓝 
	 */
	public String blue;	

	/**
	 * 总销售额 
	 */
	public String sales;	

	/**
	 * 一等奖注数 
	 */
	public String oneTypeNum;	

	/**
	 * 一等奖中奖金额 
	 */
	public String oneTypeMoney;	

	/**
	 * 二等奖注数 
	 */
	public String twoTypeNum;	

	/**
	 * 二等奖中奖金额 
	 */
	public String twoTypeMoney;	

	/**
	 * 三等奖注数 
	 */
	public String threeTypeNum;	

	/**
	 * 三等奖中奖金额 
	 */
	public String threeTypeMoney;	

	/**
	 * 奖金池 
	 */
	public String poolmoney;	





	


}
