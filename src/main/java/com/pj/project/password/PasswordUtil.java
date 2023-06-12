package com.pj.project.password;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pj.utils.sg.*;

/**
 * 工具类：password -- 密码表
 * @author lizhihao 
 *
 */
@Component
public class PasswordUtil {

	
	/** 底层 Mapper 对象 */
	public static PasswordMapper passwordMapper;
	@Autowired
	private void setPasswordMapper(PasswordMapper passwordMapper) {
		PasswordUtil.passwordMapper = passwordMapper;
	}
	
	
	/** 
	 * 将一个 Password 对象进行进行数据完整性校验 (方便add/update等接口数据校验) [G] 
	 */
	static void check(Password p) {
		AjaxError.throwByIsNull(p.id, "[主键id] 不能为空");		// 验证: 主键id 
		AjaxError.throwByIsNull(p.userId, "[所属用户ID] 不能为空");		// 验证: 所属用户ID 
		AjaxError.throwByIsNull(p.categoryId, "[密码分类ID] 不能为空");		// 验证: 密码分类ID 
		AjaxError.throwByIsNull(p.title, "[密码标题] 不能为空");		// 验证: 密码标题 
		AjaxError.throwByIsNull(p.account, "[账号] 不能为空");		// 验证: 账号 
		AjaxError.throwByIsNull(p.password, "[密码] 不能为空");		// 验证: 密码 
		AjaxError.throwByIsNull(p.url, "[登录地址] 不能为空");		// 验证: 登录地址 
		AjaxError.throwByIsNull(p.notes, "[备注] 不能为空");		// 验证: 备注 
		AjaxError.throwByIsNull(p.createBy, "[创建人] 不能为空");		// 验证: 创建人 
		AjaxError.throwByIsNull(p.createTime, "[创建时间] 不能为空");		// 验证: 创建时间 
		AjaxError.throwByIsNull(p.updateBy, "[更新人] 不能为空");		// 验证: 更新人 
		AjaxError.throwByIsNull(p.updateTime, "[更新时间] 不能为空");		// 验证: 更新时间 
	}

	/** 
	 * 获取一个Password (方便复制代码用) [G] 
	 */ 
	static Password getPassword() {
		Password p = new Password();	// 声明对象 
		p.id = 0L;		// 主键id 
		p.userId = 0L;		// 所属用户ID 
		p.categoryId = 0L;		// 密码分类ID 
		p.title = "";		// 密码标题 
		p.account = "";		// 账号 
		p.password = "";		// 密码 
		p.url = "";		// 登录地址 
		p.notes = "";		// 备注 
		p.createBy = "";		// 创建人 
		p.createTime = "";		// 创建时间 
		p.updateBy = "";		// 更新人 
		p.updateTime = "";		// 更新时间 
		return p;
	}
	
	
	
	
	
}
