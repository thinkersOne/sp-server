package com.pj.project.aav.sp_vedio;

import java.util.List;

import com.pj.models.so.SoMap;
import com.pj.project.sp_dev.SP_DEV_SP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.pj.current.satoken.AuthConst;
import com.pj.utils.sg.*;
import com.pj.current.satoken.StpUserUtil;
import cn.dev33.satoken.annotation.SaCheckPermission;


/**
 * Controller: sp_vedio -- 视频表
 * @author lizhihao
 */
@RestController
@RequestMapping("/spVedio/")
public class SpVedioController {

	/** 底层 Service 对象 */
	@Autowired
	SpVedioService spVedioService;

	/** 增 */
	@RequestMapping("add")
	@SaCheckPermission(AuthConst.SP_VEDIO_ADD)
	@Transactional(rollbackFor = Exception.class)
	public AjaxJson add(SpVedio s){
		spVedioService.add(s);
		s = spVedioService.getById(SP_DEV_SP.publicMapper.getPrimarykey());
		return AjaxJson.getSuccessData(s);
	}

	/** 删 */
	@RequestMapping("delete")
	@SaCheckPermission(AuthConst.SP_VEDIO_DELETE)
	public AjaxJson delete(Long id){
		int line = spVedioService.delete(id);
		return AjaxJson.getByLine(line);
	}

	/** 删 - 根据id列表 */
	@RequestMapping("deleteByIds")
	@SaCheckPermission(AuthConst.SP_VEDIO_DELETE_BY_IDS)
	public AjaxJson deleteByIds(){
		List<Long> ids = SoMap.getRequestSoMap().getListByComma("ids", long.class);
		int line = SP_DEV_SP.publicMapper.deleteByIds(SpVedio.TABLE_NAME, ids);
		return AjaxJson.getByLine(line);
	}

	/** 改 */
	@RequestMapping("update")
	@SaCheckPermission(AuthConst.SP_VEDIO_UPDATE)
	public AjaxJson update(SpVedio s){
		spVedioService.update(s);
		return AjaxJson.getByLine();
	}

	/** 改 */
	@RequestMapping("updateStatus")
	@SaCheckPermission(AuthConst.SP_VEDIO_UPDATE)
	public AjaxJson updateStatus(SpVedio s){
		spVedioService.updateStatus(s);
		return AjaxJson.getByLine();
	}

	/** 查 - 根据id */
	@RequestMapping("getById")
	@SaCheckPermission(AuthConst.SP_VEDIO_GETBY＿ID)
	public AjaxJson getById(Long id){
		SpVedio s = spVedioService.getById(id);
		return AjaxJson.getSuccessData(s);
	}

	/** 查集合 - 根据条件（参数为空时代表忽略指定条件） */
	@RequestMapping("getList")
	@SaCheckPermission(AuthConst.SP_VEDIO_GETLIST)
	public AjaxJson getList() {
		SoMap so = SoMap.getRequestSoMap();
		List<SpVedio> list = spVedioService.getList(so.startPage());
		return AjaxJson.getPageData(so.getDataCount(), list);
	}




	// ------------------------- 前端接口 -------------------------
	/** 改 - 不传不改 [G] */
	@RequestMapping("updateByNotNull")
	public AjaxJson updateByNotNull(Long id){
		AjaxError.throwBy(true, "如需正常调用此接口，请删除此行代码");
		// 鉴别身份，是否为数据创建者
		long userId = SP_DEV_SP.publicMapper.getColumnByIdToLong(SpVedio.TABLE_NAME, "user_id", id);
		AjaxError.throwBy(userId != StpUserUtil.getLoginIdAsLong(), "此数据您无权限修改");
		// 开始修改 (请只保留需要修改的字段)
		SoMap so = SoMap.getRequestSoMap();
		so.clearNotIn("id", "url", "type", "status", "createBy", "createTime", "updateBy", "updateTime").clearNull().humpToLineCase();
		int line = SP_DEV_SP.publicMapper.updateBySoMapById(SpVedio.TABLE_NAME, so, id);
		return AjaxJson.getByLine(line);
	}

	@GetMapping("getGirlVedios")
	public void getGirlVedios(){
		spVedioService.getVedioData();
	}




}
