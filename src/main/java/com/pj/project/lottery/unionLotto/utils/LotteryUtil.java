package com.pj.project.lottery.unionLotto.utils;

import com.pj.project.lottery.unionLotto.domain.Lottery;
import com.pj.project.lottery.LotteryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pj.utils.sg.*;

/**
 * 工具类：lottery -- 
 * @author lizhihao 
 *
 */
@Component
public class LotteryUtil {

	
	/** 底层 Mapper 对象 */
	public static LotteryMapper lotteryMapper;
	@Autowired
	private void setLotteryMapper(LotteryMapper lotteryMapper) {
		LotteryUtil.lotteryMapper = lotteryMapper;
	}
	
	
	/** 
	 * 将一个 Lottery 对象进行进行数据完整性校验 (方便add/update等接口数据校验) [G] 
	 */
	static void check(Lottery l) {
		AjaxError.throwByIsNull(l.code, "[期号] 不能为空");		// 验证: 期号 
		AjaxError.throwByIsNull(l.date, "[开奖日期] 不能为空");		// 验证: 开奖日期 
		AjaxError.throwByIsNull(l.red, "[中奖号码：红] 不能为空");		// 验证: 中奖号码：红 
		AjaxError.throwByIsNull(l.blue, "[中奖号码：蓝] 不能为空");		// 验证: 中奖号码：蓝 
		AjaxError.throwByIsNull(l.sales, "[总销售额] 不能为空");		// 验证: 总销售额 
		AjaxError.throwByIsNull(l.oneTypeNum, "[一等奖注数] 不能为空");		// 验证: 一等奖注数 
		AjaxError.throwByIsNull(l.oneTypeMoney, "[一等奖中奖金额] 不能为空");		// 验证: 一等奖中奖金额 
		AjaxError.throwByIsNull(l.twoTypeNum, "[二等奖注数] 不能为空");		// 验证: 二等奖注数 
		AjaxError.throwByIsNull(l.twoTypeMoney, "[二等奖中奖金额] 不能为空");		// 验证: 二等奖中奖金额 
		AjaxError.throwByIsNull(l.threeTypeNum, "[三等奖注数] 不能为空");		// 验证: 三等奖注数 
		AjaxError.throwByIsNull(l.threeTypeMoney, "[三等奖中奖金额] 不能为空");		// 验证: 三等奖中奖金额 
		AjaxError.throwByIsNull(l.poolmoney, "[奖金池] 不能为空");		// 验证: 奖金池 
	}

	/** 
	 * 获取一个Lottery (方便复制代码用) [G] 
	 */ 
	static Lottery getLottery() {
		Lottery l = new Lottery();	// 声明对象 
		l.code = "";		// 期号 
		l.date = "";		// 开奖日期 
		l.red = "";		// 中奖号码：红 
		l.blue = "";		// 中奖号码：蓝 
		l.sales = "";		// 总销售额 
		l.oneTypeNum = "";		// 一等奖注数 
		l.oneTypeMoney = "";		// 一等奖中奖金额 
		l.twoTypeNum = "";		// 二等奖注数 
		l.twoTypeMoney = "";		// 二等奖中奖金额 
		l.threeTypeNum = "";		// 三等奖注数 
		l.threeTypeMoney = "";		// 三等奖中奖金额 
		l.poolmoney = "";		// 奖金池 
		return l;
	}
	
	
	
	
	
}
