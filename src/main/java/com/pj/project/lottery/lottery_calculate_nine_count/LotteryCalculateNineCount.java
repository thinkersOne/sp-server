package com.pj.project.lottery.lottery_calculate_nine_count;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Model: lottery_calculate_nine_count -- 统计九转中四行数据每行上出现个数的统计表
 * @author lizhihao 
 */
@Data
@Accessors(chain = true)
public class LotteryCalculateNineCount implements Serializable {

	// ---------- 模块常量 ----------
	/**
	 * 序列化版本id 
	 */
	private static final long serialVersionUID = 1L;	
	/**
	 * 此模块对应的表名 
	 */
	public static final String TABLE_NAME = "lottery_calculate_nine_count";	
	/**
	 * 此模块对应的权限码 
	 */
	public static final String PERMISSION_CODE = "lottery-calculate-nine-count";	


	// ---------- 表中字段 ----------
	/**
	 * 主键id 
	 */
	public Long id;	

	/**
	 * 统计出现的个数 
	 */
	public Integer count;	

	/**
	 * 分组统计个数 
	 */
	public Integer nine;	

	/**
	 * 类型：9、17、33 
	 */
	public Integer type;	

	/**
	 * 方位类型 1、2、3、4 
	 */
	public Integer locationType;	





	


}
