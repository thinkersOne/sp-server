package com.pj.project.sys_user;

import java.util.List;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import com.pj.current.config.SystemObject;
import com.pj.project.admin.SpAdmin;
import com.pj.project.spcfg.SpCfgUtil;
import com.pj.utils.sg.AjaxJson;
import com.pj.utils.sg.NbUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pj.utils.so.*;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service: sys_user -- 
 * @author lizhihao 
 */
@Service
public class SysUserService {

	/** 底层 Mapper 对象 */
	@Autowired
	SysUserMapper sysUserMapper;

	/** 增 */
	int add(SysUser s){
		return sysUserMapper.add(s);
	}

	/** 删 */
	int delete(Long id){
		return sysUserMapper.delete(id);
	}

	/** 改 */
	int update(SysUser s){
		return sysUserMapper.update(s);
	}

	/** 查 */
	SysUser getById(Long id){
		return sysUserMapper.getById(id);
	}

	@Transactional(rollbackFor = Exception.class)
	public AjaxJson doLogin(String key, String password) {
		SysUser sysUser = sysUserMapper.getByName(key);
		if(ObjectUtil.isEmpty(sysUser)) {
			sysUser = sysUserMapper.getByPhone(key);
		}
		// 3、开始验证
		if(sysUser == null){
			return AjaxJson.getError("无此账号");
		}
		if(!sysUser.getPassword().equals(password)){
			return AjaxJson.getError("密码错误");
		}

		// =========== 至此, 已登录成功 ============
		StpUtil.login(sysUser.getId());
		String tokenValue = StpUtil.getTokenValue();
		return AjaxJson.getSuccessData(StpUtil.getTokenInfo());
	}

	/** 查集合 - 根据条件（参数为空时代表忽略指定条件） */  
	List<SysUser> getList(SoMap so) { 
		return sysUserMapper.getList(so);	
	}
	

}