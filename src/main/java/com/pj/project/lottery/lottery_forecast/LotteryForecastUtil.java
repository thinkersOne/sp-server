package com.pj.project.lottery.lottery_forecast;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pj.utils.sg.*;

/**
 * 工具类：lottery_forecast -- 号码预测
 * @author lizhihao 
 *
 */
@Component
public class LotteryForecastUtil {

	
	/** 底层 Mapper 对象 */
	public static LotteryForecastMapper lotteryForecastMapper;
	@Autowired
	private void setLotteryForecastMapper(LotteryForecastMapper lotteryForecastMapper) {
		LotteryForecastUtil.lotteryForecastMapper = lotteryForecastMapper;
	}
	
	
	/** 
	 * 将一个 LotteryForecast 对象进行进行数据完整性校验 (方便add/update等接口数据校验) [G] 
	 */
	static void check(LotteryForecast l) {
		AjaxError.throwByIsNull(l.id, "[主键id] 不能为空");		// 验证: 主键id 
		AjaxError.throwByIsNull(l.red, "[预测红球] 不能为空");		// 验证: 预测红球 
		AjaxError.throwByIsNull(l.redParityRatio, "[奇偶比] 不能为空");		// 验证: 奇偶比 
		AjaxError.throwByIsNull(l.redRangeRatio, "[分区比] 不能为空");		// 验证: 分区比 
		AjaxError.throwByIsNull(l.redSum, "[红球和值] 不能为空");		// 验证: 红球和值 
		AjaxError.throwByIsNull(l.consecutiveNumbersCount, "[连号个数] 不能为空");		// 验证: 连号个数 
		AjaxError.throwByIsNull(l.maxConsecutiveNumbersCount, "[最大连号数] 不能为空");		// 验证: 最大连号数 
		AjaxError.throwByIsNull(l.nineTurn09, "[九转连环-09] 不能为空");		// 验证: 九转连环-09 
		AjaxError.throwByIsNull(l.nineTurn17, "[九转连环-17] 不能为空");		// 验证: 九转连环-17 
		AjaxError.throwByIsNull(l.nineTurn33, "[九转连环-33] 不能为空");		// 验证: 九转连环-33 
		AjaxError.throwByIsNull(l.code, "[预测期号] 不能为空");		// 验证: 预测期号 
		AjaxError.throwByIsNull(l.forecastRed, "[预测中奖号码] 不能为空");		// 验证: 预测中奖号码 
		AjaxError.throwByIsNull(l.blue, "[预测蓝球] 不能为空");		// 验证: 预测蓝球 
	}

	/** 
	 * 获取一个LotteryForecast (方便复制代码用) [G] 
	 */ 
	static LotteryForecast getLotteryForecast() {
		LotteryForecast l = new LotteryForecast();	// 声明对象 
		l.id = 0L;		// 主键id 
		l.red = "";		// 预测红球 
		l.redParityRatio = "";		// 奇偶比 
		l.redRangeRatio = "";		// 分区比 
		l.redSum = "";		// 红球和值 
		l.consecutiveNumbersCount = 0;		// 连号个数
		l.maxConsecutiveNumbersCount = 0;		// 最大连号数
		l.nineTurn09 = "";		// 九转连环-09 
		l.nineTurn17 = "";		// 九转连环-17 
		l.nineTurn33 = "";		// 九转连环-33 
		l.code = "";		// 预测期号 
		l.forecastRed = "";		// 预测中奖号码 
		l.blue = "";		// 预测蓝球 
		return l;
	}
	
	
	
	
	
}
