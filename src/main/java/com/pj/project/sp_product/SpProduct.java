package com.pj.project.sp_product;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Model: sp_product -- 商品信息表
 * @author lizhihao 
 */
@Data
@Accessors(chain = true)
public class SpProduct implements Serializable {

	// ---------- 模块常量 ----------
	/**
	 * 序列化版本id 
	 */
	private static final long serialVersionUID = 1L;	
	/**
	 * 此模块对应的表名 
	 */
	public static final String TABLE_NAME = "sp_product";	
	/**
	 * 此模块对应的权限码 
	 */
	public static final String PERMISSION_CODE = "sp-product";	


	// ---------- 表中字段 ----------
	/**
	 * 主键id 
	 */
	public String id;

	/**
	 * 商品名称 
	 */
	public String name;	

	/**
	 * 商品类别  1:包天  2:包月  3: 三个月  4: 包季度  5:包年 6:永久 
	 */
	public String type;	
	public String typeName;

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
