package com.pj.project.aav.app_version;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pj.utils.sg.*;

/**
 * 工具类：app_version -- app版本管理表
 * @author lizhihao 
 *
 */
@Component
public class AppVersionUtil {

	
	/** 底层 Mapper 对象 */
	public static AppVersionMapper appVersionMapper;
	@Autowired
	private void setAppVersionMapper(AppVersionMapper appVersionMapper) {
		AppVersionUtil.appVersionMapper = appVersionMapper;
	}
	
	
	/** 
	 * 将一个 AppVersion 对象进行进行数据完整性校验 (方便add/update等接口数据校验) [G] 
	 */
	static void check(AppVersion a) {
		AjaxError.throwByIsNull(a.id, "[主键id] 不能为空");		// 验证: 主键id 
		AjaxError.throwByIsNull(a.androidUrl, "[安卓下载地址] 不能为空");		// 验证: 安卓下载地址 
		AjaxError.throwByIsNull(a.iosUrl, "[ios下载地址] 不能为空");		// 验证: ios下载地址 
		AjaxError.throwByIsNull(a.isForceUpdate, "[是否强制更新1：是2：否] 不能为空");		// 验证: 是否强制更新   1：是  2：否 
		AjaxError.throwByIsNull(a.version, "[版本号] 不能为空");		// 验证: 版本号 
		AjaxError.throwByIsNull(a.versionCode, "[版本code] 不能为空");		// 验证: 版本code 
	}

	/** 
	 * 获取一个AppVersion (方便复制代码用) [G] 
	 */ 
	static AppVersion getAppVersion() {
		AppVersion a = new AppVersion();	// 声明对象 
		a.id = 0L;		// 主键id 
		a.androidUrl = "";		// 安卓下载地址 
		a.iosUrl = "";		// ios下载地址 
		a.isForceUpdate = "";		// 是否强制更新   1：是  2：否 
		a.version = "";		// 版本号 
		a.versionCode = "";		// 版本code 
		return a;
	}
	
	
	
	
	
}
