package com.pj.project.password;

import java.util.List;

import cn.dev33.satoken.stp.StpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.pj.current.satoken.AuthConst;
import com.pj.utils.sg.*;
import com.pj.utils.so.*;
import com.pj.project.SP;

import com.pj.current.satoken.StpUserUtil;
import cn.dev33.satoken.annotation.SaCheckPermission;


/**
 * Controller: password -- 密码表
 * @author lizhihao 
 */
@RestController
@RequestMapping("/password/")
public class PasswordController {

	/** 底层 Service 对象 */
	@Autowired
	PasswordService passwordService;

	/** 增 */  
	@RequestMapping("add")
	@SaCheckPermission(AuthConst.PASSWORD_ADD)
	@Transactional(rollbackFor = Exception.class)
	public AjaxJson add(Password p){
		passwordService.add(p);
		p = passwordService.getById(SP.publicMapper.getPrimarykey());
		return AjaxJson.getSuccessData(p);
	}

	/** 删 */  
	@RequestMapping("delete")
	@SaCheckPermission(AuthConst.PASSWORD_DELETE)
	public AjaxJson delete(Long id){
		int line = passwordService.delete(id);
		return AjaxJson.getByLine(line);
	}
	
	/** 删 - 根据id列表 */  
	@RequestMapping("deleteByIds")
	@SaCheckPermission(AuthConst.PASSWORD_DELETE_BY_IDS)
	public AjaxJson deleteByIds(){
		List<Long> ids = SoMap.getRequestSoMap().getListByComma("ids", long.class); 
		int line = SP.publicMapper.deleteByIds(Password.TABLE_NAME, ids);
		return AjaxJson.getByLine(line);
	}
	
	/** 改 */  
	@RequestMapping("update")
	@SaCheckPermission(AuthConst.PASSWORD_UPDATE)
	public AjaxJson update(Password p){
		p.setUserId(StpUtil.getLoginIdAsLong());
		int line = passwordService.update(p);
		return AjaxJson.getByLine(line);
	}

	/** 查 - 根据id */  
	@RequestMapping("getById")
	@SaCheckPermission(AuthConst.PASSWORD_GETBY＿ID)
	public AjaxJson getById(Long id){
		Password p = passwordService.getById(id);
		return AjaxJson.getSuccessData(p);
	}

	/** 查集合 - 根据条件（参数为空时代表忽略指定条件） */  
	@RequestMapping("getList")
	@SaCheckPermission(AuthConst.PASSWORD_GETLIST)
	public AjaxJson getList() {
		SoMap so = SoMap.getRequestSoMap();
		so.set("userId", StpUtil.getLoginIdAsLong());
		List<Password> list = passwordService.getList(so.startPage());
		return AjaxJson.getPageData(so.getDataCount(), list);
	}
	
	
	
	
	// ------------------------- 前端接口 -------------------------
	
	
	/** 改 - 不传不改 [G] */
	@RequestMapping("updateByNotNull")
	public AjaxJson updateByNotNull(Long id){
		AjaxError.throwBy(true, "如需正常调用此接口，请删除此行代码");
		// 鉴别身份，是否为数据创建者 
		long userId = SP.publicMapper.getColumnByIdToLong(Password.TABLE_NAME, "user_id", id);
		AjaxError.throwBy(userId != StpUserUtil.getLoginIdAsLong(), "此数据您无权限修改");
		// 开始修改 (请只保留需要修改的字段)
		SoMap so = SoMap.getRequestSoMap();
		so.clearNotIn("id", "userId", "categoryId", "title", "account", "password", "url", "notes", "createBy", "createTime", "updateBy", "updateTime").clearNull().humpToLineCase();	
		int line = SP.publicMapper.updateBySoMapById(Password.TABLE_NAME, so, id);
		return AjaxJson.getByLine(line);
	}
	
	
	
	
	
	

}
