package com.pj.project.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pj.utils.sg.*;

/**
 * 工具类：user -- 用户表
 * @author lizhihao 
 *
 */
@Component
public class UserUtil {

	
	/** 底层 Mapper 对象 */
	public static UserMapper userMapper;
	@Autowired
	private void setUserMapper(UserMapper userMapper) {
		UserUtil.userMapper = userMapper;
	}
	
	
	/** 
	 * 将一个 User 对象进行进行数据完整性校验 (方便add/update等接口数据校验) [G] 
	 */
	static void check(User u) {
		AjaxError.throwByIsNull(u.id, "[主键id] 不能为空");		// 验证: 主键id 
		AjaxError.throwByIsNull(u.username, "[用户名] 不能为空");		// 验证: 用户名 
		AjaxError.throwByIsNull(u.password, "[密码] 不能为空");		// 验证: 密码 
		AjaxError.throwByIsNull(u.createBy, "[创建人] 不能为空");		// 验证: 创建人 
		AjaxError.throwByIsNull(u.createTime, "[创建时间] 不能为空");		// 验证: 创建时间 
		AjaxError.throwByIsNull(u.updateBy, "[更新人] 不能为空");		// 验证: 更新人 
		AjaxError.throwByIsNull(u.updateTime, "[更新时间] 不能为空");		// 验证: 更新时间 
	}

	/** 
	 * 获取一个User (方便复制代码用) [G] 
	 */ 
	static User getUser() {
		User u = new User();	// 声明对象 
		u.id = 0L;		// 主键id 
		u.username = "";		// 用户名 
		u.password = "";		// 密码 
		u.createBy = "";		// 创建人 
		u.createTime = "";		// 创建时间 
		u.updateBy = "";		// 更新人 
		u.updateTime = "";		// 更新时间 
		return u;
	}
	
	
	
	
	
}
