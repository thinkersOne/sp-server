package com.pj.project.sp_product;

import java.util.List;

import com.pj.current.global.SnowflakeIdGenerator;
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
 * Controller: sp_product -- 商品信息表
 * @author lizhihao 
 */
@RestController
@RequestMapping("/spProduct/")
public class SpProductController {

	/** 底层 Service 对象 */
	@Autowired
	SpProductService spProductService;
	@Autowired
	private SnowflakeIdGenerator snowflakeIdGenerator;

	/** 增 */  
	@RequestMapping("add")
	@SaCheckPermission(AuthConst.SP_PRODUCT_ADD)
	@Transactional(rollbackFor = Exception.class)
	public AjaxJson add(SpProduct s){
		s.setId(snowflakeIdGenerator.generateId()+"");
		spProductService.add(s);
		s = spProductService.getById(SP.publicMapper.getPrimarykey());
		return AjaxJson.getSuccessData(s);
	}

	/** 删 */  
	@RequestMapping("delete")
	@SaCheckPermission(AuthConst.SP_PRODUCT_DELETE)
	public AjaxJson delete(Long id){
		int line = spProductService.delete(id);
		return AjaxJson.getByLine(line);
	}
	
	/** 删 - 根据id列表 */  
	@RequestMapping("deleteByIds")
	@SaCheckPermission(AuthConst.SP_PRODUCT_DELETE_BY_IDS)
	public AjaxJson deleteByIds(){
		List<Long> ids = SoMap.getRequestSoMap().getListByComma("ids", long.class); 
		int line = SP.publicMapper.deleteByIds(SpProduct.TABLE_NAME, ids);
		return AjaxJson.getByLine(line);
	}
	
	/** 改 */  
	@RequestMapping("update")
	@SaCheckPermission(AuthConst.SP_PRODUCT_UPDATE)
	public AjaxJson update(SpProduct s){
		int line = spProductService.update(s);
		return AjaxJson.getByLine(line);
	}

	/** 查 - 根据id */  
	@RequestMapping("getById")
	@SaCheckPermission(AuthConst.SP_PRODUCT_GETBY＿ID)
	public AjaxJson getById(Long id){
		SpProduct s = spProductService.getById(id);
		return AjaxJson.getSuccessData(s);
	}

	/** 查集合 - 根据条件（参数为空时代表忽略指定条件） */  
	@RequestMapping("getList")
	@SaCheckPermission(AuthConst.SP_PRODUCT_GETLIST)
	public AjaxJson getList() {
		SoMap so = SoMap.getRequestSoMap();
		List<SpProduct> list = spProductService.getList(so.startPage());
		return AjaxJson.getPageData(so.getDataCount(), list);
	}
	
	
	
	
	// ------------------------- 前端接口 -------------------------
	
	
	/** 改 - 不传不改 [G] */
	@RequestMapping("updateByNotNull")
	public AjaxJson updateByNotNull(Long id){
		AjaxError.throwBy(true, "如需正常调用此接口，请删除此行代码");
		// 鉴别身份，是否为数据创建者 
		long userId = SP.publicMapper.getColumnByIdToLong(SpProduct.TABLE_NAME, "user_id", id);
		AjaxError.throwBy(userId != StpUserUtil.getLoginIdAsLong(), "此数据您无权限修改");
		// 开始修改 (请只保留需要修改的字段)
		SoMap so = SoMap.getRequestSoMap();
		so.clearNotIn("id", "name", "type", "createTime", "createBy", "updateTime", "updateBy").clearNull().humpToLineCase();	
		int line = SP.publicMapper.updateBySoMapById(SpProduct.TABLE_NAME, so, id);
		return AjaxJson.getByLine(line);
	}
	
	
	
	
	
	

}
