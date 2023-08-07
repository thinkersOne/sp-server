package com.pj.project.aav.app_version;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.pj.current.satoken.AuthConst;
import com.pj.utils.sg.*;
import com.pj.project.SP;
import com.pj.current.satoken.StpUserUtil;
import cn.dev33.satoken.annotation.SaCheckPermission;


/**
 * Controller: app_version -- app版本管理表
 * @author lizhihao 
 */
@RestController
@RequestMapping("/appVersion/")
public class AppVersionController {

	/** 底层 Service 对象 */
	@Autowired
	AppVersionService appVersionService;

	/** 增 */  
	@RequestMapping("add")
	@Transactional(rollbackFor = Exception.class)
	public AjaxJson add(AppVersion a){
		appVersionService.add(a);
		a = appVersionService.getById(SP.publicMapper.getPrimarykey());
		return AjaxJson.getSuccessData(a);
	}

	/** 删 */  
	@RequestMapping("delete")
	public AjaxJson delete(Long id){
		int line = appVersionService.delete(id);
		return AjaxJson.getByLine(line);
	}
	
	/** 删 - 根据id列表 */  
	@PostMapping("deleteByIds")
	public AjaxJson deleteByIds(@RequestBody List<Long> ids){
		int line = SP.publicMapper.deleteByIds(AppVersion.TABLE_NAME, ids);
		return AjaxJson.getByLine(line);
	}
	
	/** 改 */  
	@RequestMapping("update")
	public AjaxJson update(AppVersion a){
		int line = appVersionService.update(a);
		return AjaxJson.getByLine(line);
	}

	/** 查 - 根据id */  
	@RequestMapping("getById")
	public AjaxJson getById(Long id){
		AppVersion a = appVersionService.getById(id);
		return AjaxJson.getSuccessData(a);
	}

	// ------------------------- 前端接口 -------------------------
	
}
