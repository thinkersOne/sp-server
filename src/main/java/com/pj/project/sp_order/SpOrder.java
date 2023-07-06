package com.pj.project.sp_order;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Model: sp_order -- 订单信息表
 * @author lizhihao 
 */
@Data
@Accessors(chain = true)
public class SpOrder implements Serializable {

	// ---------- 模块常量 ----------
	/**
	 * 序列化版本id 
	 */
	private static final long serialVersionUID = 1L;	
	/**
	 * 此模块对应的表名 
	 */
	public static final String TABLE_NAME = "sp_order";	
	/**
	 * 此模块对应的权限码 
	 */
	public static final String PERMISSION_CODE = "sp-order";	


	// ---------- 表中字段 ----------
	/**
	 * 主键id 
	 */
	public Long id;	

	/**
	 * 订单编号 
	 */
	public String code;	

	/**
	 * 支付方式  1：支付宝  2: 微信  3:其他 
	 */
	public String payType;	

	/**
	 * 支付金额 
	 */
	public Double payAmount;	

	/**
	 * 订单来源 1: 注册  2:分享 
	 */
	public String orderSource;	

	/**
	 * 付款人 
	 */
	public String drawee;	

	/**
	 * 订单支付时间 
	 */
	public String orderPayTime;	

	/**
	 * 订单编号 
	 */
	public String orderCode;	

	/**
	 * 创建时间 
	 */
	public String createTime;	

	/**
	 * 创建人 
	 */
	public String createBy;	

	/**
	 * 更新时间 
	 */
	public String updateTime;	

	/**
	 * 更新人 
	 */
	public String updateBy;	





	


}
