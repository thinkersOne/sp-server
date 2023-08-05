package com.pj.project.admin4acc;


import java.time.LocalDateTime;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pj.current.config.SystemObject;
import com.pj.project.admin.SpAdmin;
import com.pj.project.admin.SpAdminMapper;
import com.pj.project.admin4login.SpAdminLogin;
import com.pj.project.admin4login.SpAdminLoginMapper;
import com.pj.project.role4permission.SpRolePermissionService;
import com.pj.project.spcfg.SpCfgUtil;
import com.pj.utils.sg.AjaxJson;
import com.pj.utils.sg.IpUtil;
import com.pj.utils.sg.NbUtil;
import com.pj.utils.so.SoMap;

import cn.dev33.satoken.spring.SpringMVCUtil;
import cn.dev33.satoken.stp.StpUtil;
import eu.bitwalker.useragentutils.UserAgent;

/**
 * service：admin账号相关
 * @author kong
 *
 */
@Service
public class SpAccAdminService {

	@Autowired
	SpAdminMapper spAdminMapper;
	
	@Autowired
	SpRolePermissionService spRolePermissionService;

	@Autowired
	SpAdminLoginMapper sysLoginLogMapper;
	
	/**
	  * 登录 
	 * @param key 账号 (ID / 名称 / 手机号)
	 * @param password 密码 
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public AjaxJson doLogin(String key, String password) {

		// 0、判断 way (1=ID, 2=昵称，3=手机号  )
		int way = 2;
		// 2、获取admin
		SpAdmin admin = null;
		// 优化 key 默认 既可作昵称登录也可当做手机号登录
		if(way == 2) {
			admin = spAdminMapper.getByName(key);
		}
		if(ObjectUtil.isEmpty(admin)) {
			admin = spAdminMapper.getByPhone(key);
		}


		// 3、开始验证
		if(admin == null){
			return AjaxJson.getError("无此账号");
		}
		if(NbUtil.isNull(admin.getPassword2())) {
			return AjaxJson.getError("此账号尚未设置密码，无法登陆");
		}
		String md5Password = SystemObject.getPasswordMd5(admin.getId(), password);
		if(admin.getPassword2().equals(md5Password) == false){
			return AjaxJson.getError("密码错误");
		}

		// 4、是否禁用
		if(admin.getStatus() == 2) {
			return AjaxJson.getError("此账号已被禁用，如有疑问，请联系管理员");
		}

		// =========== 至此, 已登录成功 ============
		StpUtil.login(admin.getId());
		String tokenValue = StpUtil.getTokenValue();
		successLogin(admin, tokenValue);
		return AjaxJson.getSuccessData(StpUtil.getTokenInfo());
	}
	
	/**
	 * 指定id的账号成功登录一次 （修改最后登录时间等数据 ）
	 * @param s
	 * @return
	 */
	void successLogin(SpAdmin s, String tokenValue){
		HttpServletRequest request = SpringMVCUtil.getRequest();
		UserAgent ua = UserAgent.parseUserAgentString(request.getHeader("user-agent"));
		String loginIp = IpUtil.getIP(request);
		
		// 1、修改 admin表 最后登录日志 
		int line = spAdminMapper.updateLoginLog(s.getId(), loginIp);
		if(line > 0) {
	        s.setLoginIp(loginIp);
	        s.setLoginTime(LocalDateTime.now().toString());
	        s.setLoginCount(s.getLoginCount() + 1);
		}
		
		// 2、在管理员登录日志表增加记录 
		SpAdminLogin al = new SpAdminLogin();	// 声明对象 
		al.setAccId(s.getId());		// 管理员id 
		al.setAccToken(tokenValue);	// 本次登录Token 
		al.setLoginIp(loginIp);		// 登陆IP 
		al.setAddress(IpUtil.getAddres(loginIp));		// 客户端所在地址 
		al.setDevice(ua.getBrowser().getName());	// 客户端标识 
		al.setSystem(ua.getOperatingSystem().getName()); 	// 客户端系统 
		sysLoginLogMapper.add(al);
	}
	
}
