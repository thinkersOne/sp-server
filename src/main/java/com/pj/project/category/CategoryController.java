package com.pj.project.category;

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
 * Controller: category -- 密码分类表
 * @author lizhihao 
 */
@RestController
@RequestMapping("/category/")
public class CategoryController {

	/** 底层 Service 对象 */
	@Autowired
	CategoryService categoryService;

	/** 增 */  
	@RequestMapping("add")
	@SaCheckPermission(AuthConst.CATEGORY_ADD)
	@Transactional(rollbackFor = Exception.class)
	public AjaxJson add(Category c){
		c.setUserId(StpUtil.getLoginIdAsLong());
		categoryService.add(c);
		c = categoryService.getById(SP.publicMapper.getPrimarykey());
		return AjaxJson.getSuccessData(c);
	}

	/** 删 */  
	@RequestMapping("delete")
	@SaCheckPermission(AuthConst.CATEGORY_DELETE)
	public AjaxJson delete(Long id){
		int line = categoryService.delete(id);
		return AjaxJson.getByLine(line);
	}
	
	/** 删 - 根据id列表 */  
	@RequestMapping("deleteByIds")
	@SaCheckPermission(AuthConst.CATEGORY_DELETE_BY_IDS)
	public AjaxJson deleteByIds(){
		List<Long> ids = SoMap.getRequestSoMap().getListByComma("ids", long.class);
		int count = categoryService.getCountByIds(ids);
		if(count > 0){
			return AjaxJson.getError("该分类已使用，不能进行删除！");
		}
		int line = SP.publicMapper.deleteByIds(Category.TABLE_NAME, ids);
		return AjaxJson.getByLine(line);
	}
	
	/** 改 */  
	@RequestMapping("update")
	@SaCheckPermission(AuthConst.CATEGORY_UPDATE)
	public AjaxJson update(Category c){
		Category category = categoryService.getById(c.getId());
		category.setName(c.getName());
		category.setUserId(StpUtil.getLoginIdAsLong());
		int line = categoryService.update(category);
		return AjaxJson.getByLine(line);
	}

	/** 查 - 根据id */  
	@RequestMapping("getById")
	@SaCheckPermission(AuthConst.CATEGORY_GETBY＿ID)
	public AjaxJson getById(Long id){
		Category c = categoryService.getById(id);
		return AjaxJson.getSuccessData(c);
	}

	/** 查集合 - 根据条件（参数为空时代表忽略指定条件） */  
	@RequestMapping("getList")
	@SaCheckPermission(AuthConst.CATEGORY_GETLIST)
	public AjaxJson getList() {
		SoMap so = SoMap.getRequestSoMap();
		so.set("userId",StpUtil.getLoginIdAsLong());
		List<Category> list = categoryService.getList(so.startPage());
		return AjaxJson.getPageData(so.getDataCount(), list);
	}
	
	
	
	
	// ------------------------- 前端接口 -------------------------
	
	
	/** 改 - 不传不改 [G] */
	@RequestMapping("updateByNotNull")
	public AjaxJson updateByNotNull(Long id){
		AjaxError.throwBy(true, "如需正常调用此接口，请删除此行代码");
		// 鉴别身份，是否为数据创建者 
		long userId = SP.publicMapper.getColumnByIdToLong(Category.TABLE_NAME, "user_id", id);
		AjaxError.throwBy(userId != StpUserUtil.getLoginIdAsLong(), "此数据您无权限修改");
		// 开始修改 (请只保留需要修改的字段)
		SoMap so = SoMap.getRequestSoMap();
		so.clearNotIn("id", "name", "createBy", "createTime", "updateBy", "updateTime").clearNull().humpToLineCase();	
		int line = SP.publicMapper.updateBySoMapById(Category.TABLE_NAME, so, id);
		return AjaxJson.getByLine(line);
	}
	
	
	
	
	
	

}
