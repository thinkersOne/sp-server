package com.pj.project.lottery.lottery_config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pj.utils.sg.*;

/**
 * 工具类：lottery_config -- 配置表
 * @author lizhihao 
 *
 */
@Component
public class LotteryConfigUtil {

	
	/** 底层 Mapper 对象 */
	public static LotteryConfigMapper lotteryConfigMapper;
	@Autowired
	private void setLotteryConfigMapper(LotteryConfigMapper lotteryConfigMapper) {
		LotteryConfigUtil.lotteryConfigMapper = lotteryConfigMapper;
	}
	
	
	/** 
	 * 将一个 LotteryConfig 对象进行进行数据完整性校验 (方便add/update等接口数据校验) [G] 
	 */
	static void check(LotteryConfig l) {
		AjaxError.throwByIsNull(l.id, "[主键id] 不能为空");		// 验证: 主键id 
		AjaxError.throwByIsNull(l.redRate, "[红球比例] 不能为空");		// 验证: 红球比例 
		AjaxError.throwByIsNull(l.redParityRate, "[奇偶比例] 不能为空");		// 验证: 奇偶比例 
		AjaxError.throwByIsNull(l.redRangeRate, "[区间比例] 不能为空");		// 验证: 区间比例 
		AjaxError.throwByIsNull(l.redSumRate, "[和值比例] 不能为空");		// 验证: 和值比例 
		AjaxError.throwByIsNull(l.consecutiveNumbersCountRate, "[连号个数比例] 不能为空");		// 验证: 连号个数比例 
		AjaxError.throwByIsNull(l.maxConsecutiveNumbersRate, "[最大连号数比例] 不能为空");		// 验证: 最大连号数比例 
		AjaxError.throwByIsNull(l.type, "[1:年2:月3:周4:类型] 不能为空");		// 验证: 1:年 2:月 3:周  4:类型 
	}

	/** 
	 * 获取一个LotteryConfig (方便复制代码用) [G] 
	 */ 
	static LotteryConfig getLotteryConfig() {
		LotteryConfig l = new LotteryConfig();	// 声明对象 
		l.id = 0L;		// 主键id 
		l.redRate = 0.0;		// 红球比例 
		l.redParityRate = 0.0;		// 奇偶比例 
		l.redRangeRate = 0.0;		// 区间比例 
		l.redSumRate = 0.0;		// 和值比例 
		l.consecutiveNumbersCountRate = 0.0;		// 连号个数比例 
		l.maxConsecutiveNumbersRate = 0.0;		// 最大连号数比例 
		l.type = 0;		// 1:年 2:月 3:周  4:类型 
		return l;
	}
	
	
	
	
	
}
