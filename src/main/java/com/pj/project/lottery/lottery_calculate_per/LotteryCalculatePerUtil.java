package com.pj.project.lottery.lottery_calculate_per;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pj.utils.sg.*;

/**
 * 工具类：lottery_calculate_per -- 统计计算每期双色球表
 * @author lizhihao 
 *
 */
@Component
public class LotteryCalculatePerUtil {

	
	/** 底层 Mapper 对象 */
	public static LotteryCalculatePerMapper lotteryCalculatePerMapper;
	@Autowired
	private void setLotteryCalculatePerMapper(LotteryCalculatePerMapper lotteryCalculatePerMapper) {
		LotteryCalculatePerUtil.lotteryCalculatePerMapper = lotteryCalculatePerMapper;
	}
	
	
	/** 
	 * 将一个 LotteryCalculatePer 对象进行进行数据完整性校验 (方便add/update等接口数据校验) [G] 
	 */
	static void check(LotteryCalculatePer l) {
		AjaxError.throwByIsNull(l.id, "[主键id] 不能为空");		// 验证: 主键id 
		AjaxError.throwByIsNull(l.code, "[期号] 不能为空");		// 验证: 期号 
		AjaxError.throwByIsNull(l.red, "[红色球] 不能为空");		// 验证: 红色球 
		AjaxError.throwByIsNull(l.blue, "[篮球] 不能为空");		// 验证: 篮球 
		AjaxError.throwByIsNull(l.redRangeRatio, "[红球分区比例] 不能为空");		// 验证: 红球分区比例
		AjaxError.throwByIsNull(l.redParityRatio, "[红球奇数/偶数比例] 不能为空");		// 验证: 红球奇数/偶数比例
		AjaxError.throwByIsNull(l.redSum, "[红球和] 不能为空");		// 验证: 红球和 
		AjaxError.throwByIsNull(l.blueRange, "[蓝球分区] 不能为空");		// 验证: 蓝球分区
		AjaxError.throwByIsNull(l.blueBigSmall, "[蓝球大/小] 不能为空");		// 验证: 蓝球大/小 
		AjaxError.throwByIsNull(l.blueParity, "[蓝球奇数/偶数] 不能为空");		// 验证: 蓝球奇数/偶数
	}

	/** 
	 * 获取一个LotteryCalculatePer (方便复制代码用) [G] 
	 */ 
	static LotteryCalculatePer getLotteryCalculatePer() {
		LotteryCalculatePer l = new LotteryCalculatePer();	// 声明对象 
		l.id = 0L;		// 主键id 
		l.code = "";		// 期号 
		l.red = "";		// 红色球 
		l.blue = "";		// 篮球 
		l.redRangeRatio = "";		// 红球分区比例
		l.redParityRatio = "";		// 红球奇数/偶数比例
		l.redSum = 0;		// 红球和 
		l.blueRange = 0;		// 蓝球分区
		l.blueBigSmall = "";		// 蓝球大/小 
		l.blueParity = "";		// 蓝球奇数/偶数
		return l;
	}
	
	
	
	
	
}
