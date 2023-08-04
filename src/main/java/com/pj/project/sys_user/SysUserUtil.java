package com.pj.project.sys_user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pj.utils.sg.*;

/**
 * 工具类：sys_user -- 
 * @author lizhihao 
 *
 */
@Component
public class SysUserUtil {

	
	/** 底层 Mapper 对象 */
	public static SysUserMapper sysUserMapper;
	@Autowired
	private void setSysUserMapper(SysUserMapper sysUserMapper) {
		SysUserUtil.sysUserMapper = sysUserMapper;
	}
	
	
	/** 
	 * 将一个 SysUser 对象进行进行数据完整性校验 (方便add/update等接口数据校验) [G] 
	 */
	static void check(SysUser s) {
		AjaxError.throwByIsNull(s.id, "[主键id] 不能为空");		// 验证: 主键id 
		AjaxError.throwByIsNull(s.name, "[用户名] 不能为空");		// 验证: 用户名 
		AjaxError.throwByIsNull(s.phone, "[手机号] 不能为空");		// 验证: 手机号 
		AjaxError.throwByIsNull(s.password, "[密码] 不能为空");		// 验证: 密码 
	}

	/** 
	 * 获取一个SysUser (方便复制代码用) [G] 
	 */ 
	static SysUser getSysUser() {
		SysUser s = new SysUser();	// 声明对象 
		s.id = 0L;		// 主键id 
		s.name = "";		// 用户名 
		s.phone = "";		// 手机号 
		s.password = "";		// 密码 
		return s;
	}
	
	
	
	
	
}
