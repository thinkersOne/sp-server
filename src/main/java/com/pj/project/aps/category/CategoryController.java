package com.pj.project.aps.category;

import java.util.List;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.pj.models.so.SoMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.pj.current.satoken.AuthConst;
import com.pj.utils.sg.*;
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
	@Autowired
	CategoryMapper categoryMapper;

	/** 增 */
	@PostMapping("add")
	@SaCheckLogin
	@Transactional(rollbackFor = Exception.class)
	public AjaxJson add(@RequestBody Category c){
		if(categoryMapper.existName(c.getName()) > 0){
			return AjaxJson.getError("名称已存在!");
		}
		c.setUserId(StpUtil.getLoginIdAsLong());
		int id = categoryService.add(c);
		c = categoryService.getById(Long.valueOf(id));
		return AjaxJson.getSuccessData(c);
	}
	@GetMapping("searchByName")
	@SaCheckLogin
	public AjaxJson searchByName(@Param("name") String name) {
		List<Category> list = categoryService.searchByName(name);
		return AjaxJson.getPageData(Long.valueOf(list.size()), list);
	}
	/** 删 - 根据id列表 */
	@PostMapping("deleteByIds")
	@SaCheckLogin
	public AjaxJson deleteByIds(@RequestBody List<Long> ids){
		int count = categoryService.getCountByIds(ids);
		if(count > 0){
			return AjaxJson.getError("该分类已使用，不能进行删除！");
		}
		int line = categoryMapper.deleteByIds(Category.TABLE_NAME, ids);
		return AjaxJson.getByLine(line);
	}

	/** 删 */
	@RequestMapping("delete")
	@SaCheckPermission(AuthConst.CATEGORY_DELETE)
	public AjaxJson delete(Long id){
		int line = categoryService.delete(id);
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


}