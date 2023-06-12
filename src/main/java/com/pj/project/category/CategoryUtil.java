package com.pj.project.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pj.utils.sg.*;

/**
 * 工具类：category -- 密码分类表
 * @author lizhihao 
 *
 */
@Component
public class CategoryUtil {

	
	/** 底层 Mapper 对象 */
	public static CategoryMapper categoryMapper;
	@Autowired
	private void setCategoryMapper(CategoryMapper categoryMapper) {
		CategoryUtil.categoryMapper = categoryMapper;
	}
	
	
	/** 
	 * 将一个 Category 对象进行进行数据完整性校验 (方便add/update等接口数据校验) [G] 
	 */
	static void check(Category c) {
		AjaxError.throwByIsNull(c.id, "[主键id] 不能为空");		// 验证: 主键id 
		AjaxError.throwByIsNull(c.name, "[分类名称] 不能为空");		// 验证: 分类名称 
		AjaxError.throwByIsNull(c.createBy, "[创建人] 不能为空");		// 验证: 创建人 
		AjaxError.throwByIsNull(c.createTime, "[创建时间] 不能为空");		// 验证: 创建时间 
		AjaxError.throwByIsNull(c.updateBy, "[更新人] 不能为空");		// 验证: 更新人 
		AjaxError.throwByIsNull(c.updateTime, "[更新时间] 不能为空");		// 验证: 更新时间 
	}

	/** 
	 * 获取一个Category (方便复制代码用) [G] 
	 */ 
	static Category getCategory() {
		Category c = new Category();	// 声明对象 
		c.id = 0L;		// 主键id 
		c.name = "";		// 分类名称 
		c.createBy = "";		// 创建人 
		c.createTime = "";		// 创建时间 
		c.updateBy = "";		// 更新人 
		c.updateTime = "";		// 更新时间 
		return c;
	}
	
	
	
	
	
}
