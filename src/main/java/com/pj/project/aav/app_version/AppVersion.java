package com.pj.project.aav.app_version;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Model: app_version -- app版本管理表
 * @author lizhihao 
 */
@Data
@Accessors(chain = true)
public class AppVersion implements Serializable {

	// ---------- 模块常量 ----------
	/**
	 * 序列化版本id 
	 */
	private static final long serialVersionUID = 1L;	
	/**
	 * 此模块对应的表名 
	 */
	public static final String TABLE_NAME = "app_version";	
	/**
	 * 此模块对应的权限码 
	 */
	public static final String PERMISSION_CODE = "app-version";	


	// ---------- 表中字段 ----------
	/**
	 * 主键id 
	 */
	public Long id;	

	/**
	 * 安卓下载地址 
	 */
	public String androidUrl;	

	/**
	 * ios下载地址 
	 */
	public String iosUrl;	

	/**
	 * 是否强制更新   1：是  2：否 
	 */
	public String isForceUpdate;	

	/**
	 * 版本号 
	 */
	public String version;	

	/**
	 * 版本code 
	 */
	public String versionCode;	





	


}
