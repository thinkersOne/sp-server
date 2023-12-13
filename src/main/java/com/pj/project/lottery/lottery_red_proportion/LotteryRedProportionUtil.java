package com.pj.project.lottery.lottery_red_proportion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pj.utils.sg.*;

/**
 * 工具类：lottery_red_proportion -- 各红球号占比
 * @author lizhihao 
 *
 */
@Component
public class LotteryRedProportionUtil {

	
	/** 底层 Mapper 对象 */
	public static LotteryRedProportionMapper lotteryRedProportionMapper;
	@Autowired
	private void setLotteryRedProportionMapper(LotteryRedProportionMapper lotteryRedProportionMapper) {
		LotteryRedProportionUtil.lotteryRedProportionMapper = lotteryRedProportionMapper;
	}
	
	
	/** 
	 * 将一个 LotteryRedProportion 对象进行进行数据完整性校验 (方便add/update等接口数据校验) [G] 
	 */
	static void check(LotteryRedProportion l) {
		AjaxError.throwByIsNull(l.id, "[主键id] 不能为空");		// 验证: 主键id 
		AjaxError.throwByIsNull(l.code, "[期号] 不能为空");		// 验证: 期号 
		AjaxError.throwByIsNull(l.red1, "[红球1占比] 不能为空");		// 验证: 红球1占比 
		AjaxError.throwByIsNull(l.red2, "[红球2占比] 不能为空");		// 验证: 红球2占比 
		AjaxError.throwByIsNull(l.red3, "[红球3占比] 不能为空");		// 验证: 红球3占比 
		AjaxError.throwByIsNull(l.red4, "[红球4占比] 不能为空");		// 验证: 红球4占比 
		AjaxError.throwByIsNull(l.red5, "[红球5占比] 不能为空");		// 验证: 红球5占比 
		AjaxError.throwByIsNull(l.red6, "[红球6占比] 不能为空");		// 验证: 红球6占比 
		AjaxError.throwByIsNull(l.red7, "[红球7占比] 不能为空");		// 验证: 红球7占比 
		AjaxError.throwByIsNull(l.red8, "[红球8占比] 不能为空");		// 验证: 红球8占比 
		AjaxError.throwByIsNull(l.red9, "[红球9占比] 不能为空");		// 验证: 红球9占比 
		AjaxError.throwByIsNull(l.red10, "[红球10占比] 不能为空");		// 验证: 红球10占比 
		AjaxError.throwByIsNull(l.red11, "[红球11占比] 不能为空");		// 验证: 红球11占比 
		AjaxError.throwByIsNull(l.red12, "[红球12占比] 不能为空");		// 验证: 红球12占比 
		AjaxError.throwByIsNull(l.red13, "[红球13占比] 不能为空");		// 验证: 红球13占比 
		AjaxError.throwByIsNull(l.red14, "[红球14占比] 不能为空");		// 验证: 红球14占比 
		AjaxError.throwByIsNull(l.red15, "[红球15占比] 不能为空");		// 验证: 红球15占比 
		AjaxError.throwByIsNull(l.red16, "[红球16占比] 不能为空");		// 验证: 红球16占比 
		AjaxError.throwByIsNull(l.red17, "[红球17占比] 不能为空");		// 验证: 红球17占比 
		AjaxError.throwByIsNull(l.red18, "[红球18占比] 不能为空");		// 验证: 红球18占比 
		AjaxError.throwByIsNull(l.red19, "[红球19占比] 不能为空");		// 验证: 红球19占比 
		AjaxError.throwByIsNull(l.red20, "[红球20占比] 不能为空");		// 验证: 红球20占比 
		AjaxError.throwByIsNull(l.red21, "[红球21占比] 不能为空");		// 验证: 红球21占比 
		AjaxError.throwByIsNull(l.red22, "[红球22占比] 不能为空");		// 验证: 红球22占比 
		AjaxError.throwByIsNull(l.red23, "[红球23占比] 不能为空");		// 验证: 红球23占比 
		AjaxError.throwByIsNull(l.red24, "[红球24占比] 不能为空");		// 验证: 红球24占比 
		AjaxError.throwByIsNull(l.red25, "[红球25占比] 不能为空");		// 验证: 红球25占比 
		AjaxError.throwByIsNull(l.red26, "[红球26占比] 不能为空");		// 验证: 红球26占比 
		AjaxError.throwByIsNull(l.red27, "[红球27占比] 不能为空");		// 验证: 红球27占比 
		AjaxError.throwByIsNull(l.red28, "[红球28占比] 不能为空");		// 验证: 红球28占比 
		AjaxError.throwByIsNull(l.red29, "[红球29占比] 不能为空");		// 验证: 红球29占比 
		AjaxError.throwByIsNull(l.red30, "[红球30占比] 不能为空");		// 验证: 红球30占比 
		AjaxError.throwByIsNull(l.red31, "[红球31占比] 不能为空");		// 验证: 红球31占比 
		AjaxError.throwByIsNull(l.red32, "[红球32占比] 不能为空");		// 验证: 红球32占比 
		AjaxError.throwByIsNull(l.red33, "[红球33占比] 不能为空");		// 验证: 红球33占比 
		AjaxError.throwByIsNull(l.red, "[红球号] 不能为空");		// 验证: 红球号 
	}

	/** 
	 * 获取一个LotteryRedProportion (方便复制代码用) [G] 
	 */ 
	static LotteryRedProportion getLotteryRedProportion() {
		LotteryRedProportion l = new LotteryRedProportion();	// 声明对象 
		l.id = 0L;		// 主键id 
		l.code = "";		// 期号 
		l.red1 = 0.0;		// 红球1占比 
		l.red2 = 0.0;		// 红球2占比 
		l.red3 = 0.0;		// 红球3占比 
		l.red4 = 0.0;		// 红球4占比 
		l.red5 = 0.0;		// 红球5占比 
		l.red6 = 0.0;		// 红球6占比 
		l.red7 = 0.0;		// 红球7占比 
		l.red8 = 0.0;		// 红球8占比 
		l.red9 = 0.0;		// 红球9占比 
		l.red10 = 0.0;		// 红球10占比 
		l.red11 = 0.0;		// 红球11占比 
		l.red12 = 0.0;		// 红球12占比 
		l.red13 = 0.0;		// 红球13占比 
		l.red14 = 0.0;		// 红球14占比 
		l.red15 = 0.0;		// 红球15占比 
		l.red16 = 0.0;		// 红球16占比 
		l.red17 = 0.0;		// 红球17占比 
		l.red18 = 0.0;		// 红球18占比 
		l.red19 = 0.0;		// 红球19占比 
		l.red20 = 0.0;		// 红球20占比 
		l.red21 = 0.0;		// 红球21占比 
		l.red22 = 0.0;		// 红球22占比 
		l.red23 = 0.0;		// 红球23占比 
		l.red24 = 0.0;		// 红球24占比 
		l.red25 = 0.0;		// 红球25占比 
		l.red26 = 0.0;		// 红球26占比 
		l.red27 = 0.0;		// 红球27占比 
		l.red28 = 0.0;		// 红球28占比 
		l.red29 = 0.0;		// 红球29占比 
		l.red30 = 0.0;		// 红球30占比 
		l.red31 = 0.0;		// 红球31占比 
		l.red32 = 0.0;		// 红球32占比 
		l.red33 = 0.0;		// 红球33占比 
		l.red = "";		// 红球号 
		return l;
	}
	
	
	
	
	
}
