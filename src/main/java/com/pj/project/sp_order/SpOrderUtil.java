package com.pj.project.sp_order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pj.utils.sg.*;

/**
 * 工具类：sp_order -- 订单信息表
 * @author lizhihao 
 *
 */
@Component
public class SpOrderUtil {

	
	/** 底层 Mapper 对象 */
	public static SpOrderMapper spOrderMapper;
	@Autowired
	private void setSpOrderMapper(SpOrderMapper spOrderMapper) {
		SpOrderUtil.spOrderMapper = spOrderMapper;
	}
	
	
	/** 
	 * 将一个 SpOrder 对象进行进行数据完整性校验 (方便add/update等接口数据校验) [G] 
	 */
	static void check(SpOrder s) {
		AjaxError.throwByIsNull(s.id, "[主键id] 不能为空");		// 验证: 主键id 
		AjaxError.throwByIsNull(s.code, "[订单编号] 不能为空");		// 验证: 订单编号 
		AjaxError.throwByIsNull(s.payType, "[支付方式1：支付宝2:微信3:其他] 不能为空");		// 验证: 支付方式  1：支付宝  2: 微信  3:其他 
		AjaxError.throwByIsNull(s.payAmount, "[支付金额] 不能为空");		// 验证: 支付金额 
		AjaxError.throwByIsNull(s.orderSource, "[订单来源1:注册2:分享] 不能为空");		// 验证: 订单来源 1: 注册  2:分享 
		AjaxError.throwByIsNull(s.drawee, "[付款人] 不能为空");		// 验证: 付款人 
		AjaxError.throwByIsNull(s.orderPayTime, "[订单支付时间] 不能为空");		// 验证: 订单支付时间 
		AjaxError.throwByIsNull(s.orderCode, "[订单编号] 不能为空");		// 验证: 订单编号 
		AjaxError.throwByIsNull(s.createTime, "[创建时间] 不能为空");		// 验证: 创建时间 
		AjaxError.throwByIsNull(s.createBy, "[创建人] 不能为空");		// 验证: 创建人 
		AjaxError.throwByIsNull(s.updateTime, "[更新时间] 不能为空");		// 验证: 更新时间 
		AjaxError.throwByIsNull(s.updateBy, "[更新人] 不能为空");		// 验证: 更新人 
	}

	/** 
	 * 获取一个SpOrder (方便复制代码用) [G] 
	 */ 
	static SpOrder getSpOrder() {
		SpOrder s = new SpOrder();	// 声明对象 
		s.id = 0L;		// 主键id 
		s.code = "";		// 订单编号 
		s.payType = "";		// 支付方式  1：支付宝  2: 微信  3:其他 
		s.payAmount = 0.0;		// 支付金额 
		s.orderSource = "";		// 订单来源 1: 注册  2:分享 
		s.drawee = "";		// 付款人 
		s.orderPayTime = "";		// 订单支付时间 
		s.orderCode = "";		// 订单编号 
		s.createTime = "";		// 创建时间 
		s.createBy = "";		// 创建人 
		s.updateTime = "";		// 更新时间 
		s.updateBy = "";		// 更新人 
		return s;
	}
	
	
	
	
	
}
