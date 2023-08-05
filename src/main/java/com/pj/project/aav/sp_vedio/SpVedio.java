package com.pj.project.aav.sp_vedio;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Model: sp_vedio -- 视频表
 * @author lizhihao 
 */
@Data
@Accessors(chain = true)
public class SpVedio implements Serializable {

	// ---------- 模块常量 ----------
	/**
	 * 序列化版本id 
	 */
	private static final long serialVersionUID = 1L;	
	/**
	 * 此模块对应的表名 
	 */
	public static final String TABLE_NAME = "sp_vedio";	
	/**
	 * 此模块对应的权限码 
	 */
	public static final String PERMISSION_CODE = "sp-vedio";	


	// ---------- 表中字段 ----------
	/**
	 *  
	 */
	public String id;

	/**
	 *  
	 */
	public String url;	

	/**
	 *  
	 */
	public String type;	
	public String typeName;

	/**
	 *  
	 */
	public String status;	
	public String statusName;

	/**
	 *  
	 */
	public String createBy;	

	/**
	 *  
	 */
	public String createTime;	

	/**
	 *  
	 */
	public String updateBy;	

	/**
	 *  
	 */
	public String updateTime;	





	


}
