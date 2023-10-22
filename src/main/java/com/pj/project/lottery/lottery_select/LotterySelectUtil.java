package com.pj.project.lottery.lottery_select;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pj.utils.sg.*;

/**
 * 工具类：lottery_select -- 经过初步筛选后的全量双色球表
 * @author lizhihao 
 *
 */
@Component
public class LotterySelectUtil {

	
	/** 底层 Mapper 对象 */
	public static LotterySelectMapper lotterySelectMapper;
	@Autowired
	private void setLotterySelectMapper(LotterySelectMapper lotterySelectMapper) {
		LotterySelectUtil.lotterySelectMapper = lotterySelectMapper;
	}
	
	
	/** 
	 * 将一个 LotterySelect 对象进行进行数据完整性校验 (方便add/update等接口数据校验) [G] 
	 */
	static void check(LotterySelect l) {
		AjaxError.throwByIsNull(l.id, "[主键id] 不能为空");		// 验证: 主键id 
		AjaxError.throwByIsNull(l.red, "[红球] 不能为空");		// 验证: 红球 
	}

	/** 
	 * 获取一个LotterySelect (方便复制代码用) [G] 
	 */ 
	static LotterySelect getLotterySelect() {
		LotterySelect l = new LotterySelect();	// 声明对象 
		l.id = 0L;		// 主键id 
		l.red = "";		// 红球 
		return l;
	}
	
	
	
	
	
}
