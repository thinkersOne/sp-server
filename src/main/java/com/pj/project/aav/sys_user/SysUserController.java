package com.pj.project.aav.sys_user;

import java.util.List;

import cn.dev33.satoken.stp.StpUtil;
import com.pj.models.so.SoMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.pj.utils.sg.*;
import com.pj.project.sp_dev.SP_DEV_SP;

import com.pj.current.satoken.StpUserUtil;


/**
 * Controller: sys_user --
 * @author lizhihao
 */
@RestController
@RequestMapping("/sysUser/")
public class SysUserController {

	/** 底层 Service 对象 */
	@Autowired
	SysUserService sysUserService;
	@Autowired
	SysUserMapper sysUserMapper;

	@PostMapping("register")
	public AjaxJson register(@RequestBody SysUser s){
		//校验名称及手机号是否重复
		if(sysUserMapper.existsAccount(s) > 0){
			return AjaxJson.getError("账号已存在，请换个账号试试！");
		}
		sysUserService.add(s);
		s = sysUserService.getById(SP_DEV_SP.publicMapper.getPrimarykey());
		return AjaxJson.getSuccessData(s);
	}

	@PostMapping("doLogin")
	public AjaxJson doLogin(@RequestBody SysUser s){
		String key = s.getKey();
		String password = s.getPassword();
		// 1、验证参数
		if(NbUtil.hasNull(key, password)) {
			return AjaxJson.getError("请提供key与password参数");
		}
		IpCheckUtil.checkResToNow("admin-login", 1);
		// 1、验证参数
		if(NbUtil.hasNull(key, password)) {
			return AjaxJson.getError("请提供key与password参数");
		}
		return sysUserService.doLogin(key, password);
	}

	/** 增 */
	@RequestMapping("add")
	@Transactional(rollbackFor = Exception.class)
	public AjaxJson add(SysUser s){
		sysUserService.add(s);
		s = sysUserService.getById(SP_DEV_SP.publicMapper.getPrimarykey());
		return AjaxJson.getSuccessData(s);
	}

	/** 删 */
	@RequestMapping("delete")
	public AjaxJson delete(Long id){
		int line = sysUserService.delete(id);
		return AjaxJson.getByLine(line);
	}

	/** 删 - 根据id列表 */
	@RequestMapping("deleteByIds")
	public AjaxJson deleteByIds(){
		List<Long> ids = SoMap.getRequestSoMap().getListByComma("ids", long.class);
		int line = SP_DEV_SP.publicMapper.deleteByIds(SysUser.TABLE_NAME, ids);
		return AjaxJson.getByLine(line);
	}

	/** 改 */
	@RequestMapping("update")
	public AjaxJson update(SysUser s){
		int line = sysUserService.update(s);
		return AjaxJson.getByLine(line);
	}

	/** 查 - 根据id */
	@RequestMapping("getById")
	public AjaxJson getById(Long id){
		SysUser s = sysUserService.getById(id);
		return AjaxJson.getSuccessData(s);
	}

	/** 查集合 - 根据条件（参数为空时代表忽略指定条件） */
	@RequestMapping("getList")
	public AjaxJson getList() {
		SoMap so = SoMap.getRequestSoMap();
		List<SysUser> list = sysUserService.getList(so.startPage());
		return AjaxJson.getPageData(so.getDataCount(), list);
	}

	/** 退出登录  */
	@GetMapping("logOut")
	AjaxJson logOut() {
		StpUtil.logout();
		return AjaxJson.getSuccess();
	}

}