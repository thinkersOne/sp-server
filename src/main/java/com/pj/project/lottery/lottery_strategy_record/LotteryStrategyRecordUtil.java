package com.pj.project.lottery.lottery_strategy_record;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pj.utils.sg.*;

/**
 * 工具类：lottery_strategy_record -- 策略记录表
 * @author lizhihao 
 *
 */
@Component
public class LotteryStrategyRecordUtil {

	
	/** 底层 Mapper 对象 */
	public static LotteryStrategyRecordMapper lotteryStrategyRecordMapper;
	@Autowired
	private void setLotteryStrategyRecordMapper(LotteryStrategyRecordMapper lotteryStrategyRecordMapper) {
		LotteryStrategyRecordUtil.lotteryStrategyRecordMapper = lotteryStrategyRecordMapper;
	}
	
	
	/** 
	 * 将一个 LotteryStrategyRecord 对象进行进行数据完整性校验 (方便add/update等接口数据校验) [G] 
	 */
	static void check(LotteryStrategyRecord l) {
		AjaxError.throwByIsNull(l.id, "[主键id] 不能为空");		// 验证: 主键id 
		AjaxError.throwByIsNull(l.strategyNo, "[策略编号-雪花] 不能为空");		// 验证: 策略编号-雪花 
		AjaxError.throwByIsNull(l.total, "[统计数据量] 不能为空");		// 验证: 统计数据量 
		AjaxError.throwByIsNull(l.enableContain, "[是否命中] 不能为空");		// 验证: 是否命中 
		AjaxError.throwByIsNull(l.strategy, "[策略] 不能为空");		// 验证: 策略 
	}

	/** 
	 * 获取一个LotteryStrategyRecord (方便复制代码用) [G] 
	 */ 
	static LotteryStrategyRecord getLotteryStrategyRecord() {
		LotteryStrategyRecord l = new LotteryStrategyRecord();	// 声明对象 
		l.id = 0L;		// 主键id 
		l.strategyNo = "";		// 策略编号-雪花 
		l.total = 0;		// 统计数据量 
		l.enableContain = 0;		// 是否命中 
		l.strategy = "";		// 策略 
		return l;
	}
	
	
	
	
	
}
