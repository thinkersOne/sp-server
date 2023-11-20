package com.pj.project.sp_dev.sp_product;

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
 * Controller: sp_product -- 商品信息表
 * @author lizhihao 
 */
@RestController
@RequestMapping("/spProduct/")
public class SpProductController {

	/** 底层 Service 对象 */
	@Autowired
	SpProductService spProductService;

	/** 增 */  
	@RequestMapping("add")
	@SaCheckPermission(AuthConst.SP_PRODUCT_ADD)
	@Transactional(rollbackFor = Exception.class)
	public AjaxJson add(SpProduct s){
		spProductService.add(s);
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
		int line = SP_DEV_SP.publicMapper.deleteByIds(SpProduct.TABLE_NAME, ids);
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

}
