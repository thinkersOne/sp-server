package com.pj.project.sp_dev.admin4acc;

import com.pj.models.dto.LoginDTO;
import com.pj.project.sp_dev.admin.SpAdmin;
import com.pj.project.sp_dev.admin.SpAdminUtil;
import com.pj.project.sp_dev.role4permission.SpRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pj.project.sp_dev.spcfg.SpCfgUtil;
import com.pj.utils.sg.AjaxJson;
import com.pj.utils.sg.IpCheckUtil;
import com.pj.utils.sg.NbUtil;
import com.pj.models.so.SoMap;

import cn.dev33.satoken.stp.StpUtil;

/**
 * Admin账号相关的接口
 *
 * @author kong
 *
 */
@RestController
@RequestMapping("/AccAdmin/")
public class SpAccAdminController {

	@Autowired
	SpAccAdminService spAccAdminService;

	@Autowired
	SpRolePermissionService spRolePermissionService;

	/** 账号、密码登录  */
	@PostMapping("doLogin")
	AjaxJson doLogin(LoginDTO loginDTO) {
		String key = loginDTO.getKey();
		String password = loginDTO.getPassword();
		IpCheckUtil.checkResToNow("admin-login", 1);
		// 1、验证参数
		if(NbUtil.hasNull(key, password)) {
			return AjaxJson.getError("请提供key与password参数");
		}
		return spAccAdminService.doLogin(key, password);
	}

	/** 退出登录  */
	@RequestMapping("doExit")
	AjaxJson doExit() {
		StpUtil.logout();
		return AjaxJson.getSuccess();
	}

	/** 获取会话信息
	 * @throws InterruptedException */
	@RequestMapping("getLoginInfo")
	AjaxJson getLoginInfo() {
		// 当前admin
		SpAdmin admin = SpAdminUtil.getCurrAdmin();
		// 组织参数 (admin信息，权限信息，配置信息)
		SoMap map = new SoMap();
		map.set("admin", admin);
		map.set("perList", spRolePermissionService.getPcodeByRid(admin.getRoleId()));
		map.set("appCfg", SpCfgUtil.getAppCfg());
		return AjaxJson.getSuccessData(map);
	}

}