package com.pj.project.aav.sys_user;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Model: sys_user -- 
 * @author lizhihao 
 */
@Data
@Accessors(chain = true)
public class SysUser implements Serializable {

	// ---------- 模块常量 ----------
	/**
	 * 序列化版本id 
	 */
	private static final long serialVersionUID = 1L;	
	/**
	 * 此模块对应的表名 
	 */
	public static final String TABLE_NAME = "sys_user";	
	/**
	 * 此模块对应的权限码 
	 */
	public static final String PERMISSION_CODE = "sys-user";	


	// ---------- 表中字段 ----------
	/**
	 * 主键id 
	 */
	public Long id;	

	/**
	 * 用户名 
	 */
	public String name;	
	public String key;

	/**
	 * 手机号 
	 */
	public String phone;	

	/**
	 * 密码 
	 */
	public String password;	





	


}
