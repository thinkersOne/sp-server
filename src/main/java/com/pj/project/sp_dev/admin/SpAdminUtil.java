package com.pj.project.sp_dev.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pj.utils.sg.AjaxError;
import com.pj.utils.sg.NbUtil;

import cn.dev33.satoken.stp.StpUtil;

/**
 * admin工具类
 * @author kong
 *
 */
@Component
public class SpAdminUtil {

	
	/** 底层 Mapper 对象 */
	public static SpAdminMapper spAdminMapper;
	@Autowired
	private void setSpAdminMapper(SpAdminMapper spAdminMapper) {
		SpAdminUtil.spAdminMapper = spAdminMapper;
	}
	
	/**
	 * 当前admin
	 * @return
	 */
	public static SpAdmin getCurrAdmin() {
		long adminId = StpUtil.getLoginIdAsLong();
		return spAdminMapper.getById(adminId);
	}

	/**
	 * 检查指定姓名是否合法 ,如果不合法，则抛出异常
	 * @param adminId
	 * @param name
	 * @return
	 */
	public static boolean checkName(long adminId, String name) {
		if(NbUtil.isNull(name)) {
			throw AjaxError.get("账号名称不能为空");
		}
//		if(NbUtil.isNumber(name)) {
//			throw AjaxError.get("账号名称不能为纯数字");
//		}
//		if(name.startsWith("a")) {
//			throw AjaxException.get("账号名称不能以字母a开头");
//		}
		// 如果能查出来数据，而且不是本人，则代表与已有数据重复
		SpAdmin a2 = spAdminMapper.getByName(name);
		if(a2 != null && a2.getId() != adminId) {
			throw AjaxError.get("账号名称已有账号使用，请更换");
		}
		return true;
	}

	/**
	 * 检查整个admin是否合格
	 * @param a
	 * @return
	 */
	public static boolean checkAdmin(SpAdmin a) {
		// 检查姓名
		checkName(a.getId(), a.getName());
		// 检查密码
//		if(a.getPassword2().length() < 4) {
//			throw new AjaxError("密码不得低于4位");
//		}
		return true;
	}



	/**
	 * 指定的name是否可用
	 * @param name
	 * @return
	 */
	public static boolean nameIsOk(String name) {
		SpAdmin a2 = spAdminMapper.getByName(name);
		if(a2 == null) {
			return true;
		}
		return false;
	}




	/**
	 * 获取一个SpAdmin (方便复制代码用) [G]
	 */
	static SpAdmin getSpAdmin() {
		SpAdmin s = new SpAdmin();	// 声明对象
		s.id = 0L;		// id，--主键、自增
		s.name = "";		// admin名称
		s.avatar = "";		// 头像地址
		s.password = "";		// 密码
		s.pw = "";		// 明文密码
		s.phone = "";		// 手机号
		s.roleId = 0L;		// 所属角色id
		s.status = 0;		// 账号状态(1=正常, 2=禁用)
		s.createByAid = 0L;		// 创建自哪个管理员
		s.createTime = "";		// 创建时间
		s.loginTime = "";		// 上次登陆时间
		s.loginIp = "";		// 上次登陆IP
		s.loginCount = 0;		// 登陆次数
		s.userType = "";		// 会员类型  0:普通  1:会员
		return s;
	}



	
	
}
