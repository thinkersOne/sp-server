package com.pj.project.sp_dev.sp_product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pj.utils.sg.*;

/**
 * 工具类：sp_product -- 商品信息表
 * @author lizhihao 
 *
 */
@Component
public class SpProductUtil {

	
	/** 底层 Mapper 对象 */
	public static SpProductMapper spProductMapper;
	@Autowired
	private void setSpProductMapper(SpProductMapper spProductMapper) {
		SpProductUtil.spProductMapper = spProductMapper;
	}
	
	
	/** 
	 * 将一个 SpProduct 对象进行进行数据完整性校验 (方便add/update等接口数据校验) [G] 
	 */
	static void check(SpProduct s) {
		AjaxError.throwByIsNull(s.id, "[主键id] 不能为空");		// 验证: 主键id 
		AjaxError.throwByIsNull(s.name, "[商品名称] 不能为空");		// 验证: 商品名称 
		AjaxError.throwByIsNull(s.type, "[商品类别1:包天2:包月3:三个月4:包季度5:包年6:永久] 不能为空");		// 验证: 商品类别  1:包天  2:包月  3: 三个月  4: 包季度  5:包年 6:永久 
		AjaxError.throwByIsNull(s.unitPrice, "[单价] 不能为空");		// 验证: 单价 
		AjaxError.throwByIsNull(s.imgUrl, "[商品图片] 不能为空");		// 验证: 商品图片 
		AjaxError.throwByIsNull(s.status, "[1：上架2:下架] 不能为空");		// 验证: 1：上架  2:下架 
		AjaxError.throwByIsNull(s.createTime, "[创建时间] 不能为空");		// 验证: 创建时间 
		AjaxError.throwByIsNull(s.createBy, "[创建人] 不能为空");		// 验证: 创建人 
		AjaxError.throwByIsNull(s.updateTime, "[更新时间] 不能为空");		// 验证: 更新时间 
		AjaxError.throwByIsNull(s.updateBy, "[更新人] 不能为空");		// 验证: 更新人 
	}

	/** 
	 * 获取一个SpProduct (方便复制代码用) [G] 
	 */ 
	static SpProduct getSpProduct() {
		SpProduct s = new SpProduct();	// 声明对象 
		s.id = 0L;		// 主键id 
		s.name = "";		// 商品名称 
		s.type = "";		// 商品类别  1:包天  2:包月  3: 三个月  4: 包季度  5:包年 6:永久 
		s.unitPrice = 0.0;		// 单价 
		s.imgUrl = "";		// 商品图片 
		s.status = "";		// 1：上架  2:下架 
		s.createTime = "";		// 创建时间 
		s.createBy = "";		// 创建人 
		s.updateTime = "";		// 更新时间 
		s.updateBy = "";		// 更新人 
		return s;
	}
	
	
	
	
	
}
