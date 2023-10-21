package com.pj.project.lottery.lottery_calculate_nine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pj.utils.sg.*;

/**
 * 工具类：lottery_calculate_nine -- 九转连环图统计表
 * @author lizhihao 
 *
 */
@Component
public class LotteryCalculateNineUtil {

	
	/** 底层 Mapper 对象 */
	public static LotteryCalculateNineMapper lotteryCalculateNineMapper;
	@Autowired
	private void setLotteryCalculateNineMapper(LotteryCalculateNineMapper lotteryCalculateNineMapper) {
		LotteryCalculateNineUtil.lotteryCalculateNineMapper = lotteryCalculateNineMapper;
	}
	
	
	/** 
	 * 将一个 LotteryCalculateNine 对象进行进行数据完整性校验 (方便add/update等接口数据校验) [G] 
	 */
	static void check(LotteryCalculateNine l) {
		AjaxError.throwByIsNull(l.id, "[主键id] 不能为空");		// 验证: 主键id 
		AjaxError.throwByIsNull(l.year, "[年份] 不能为空");		// 验证: 年份 
		AjaxError.throwByIsNull(l.month, "[月份] 不能为空");		// 验证: 月份 
		AjaxError.throwByIsNull(l.calType, "[类型1：年份2：月份] 不能为空");		// 验证: 类型  1：年份  2：月份
		AjaxError.throwByIsNull(l.codes, "[区号区间] 不能为空");		// 验证: 区号区间 
		AjaxError.throwByIsNull(l.nineTurn, "[九转连环分布比] 不能为空");		// 验证: 九转连环分布比 
		AjaxError.throwByIsNull(l.nineTurnCount, "[统计个数] 不能为空");		// 验证: 统计个数 
		AjaxError.throwByIsNull(l.nineTurnType, "[09,17,33] 不能为空");		// 验证: 09,17,33 
	}

	/** 
	 * 获取一个LotteryCalculateNine (方便复制代码用) [G] 
	 */ 
	static LotteryCalculateNine getLotteryCalculateNine() {
		LotteryCalculateNine l = new LotteryCalculateNine();	// 声明对象 
		l.id = 0L;		// 主键id 
		l.year = 0;		// 年份 
		l.month = 0;		// 月份 
		l.calType = 0;		// 类型  1：年份  2：月份
		l.codes = "";		// 区号区间 
		l.nineTurn = "";		// 九转连环分布比 
		l.nineTurnCount = 0;		// 统计个数 
		l.nineTurnType = 0;		// 09,17,33 
		return l;
	}
	
	
	
	
	
}
