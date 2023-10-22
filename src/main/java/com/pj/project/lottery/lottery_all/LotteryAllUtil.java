package com.pj.project.lottery.lottery_all;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pj.utils.sg.*;

/**
 * 工具类：lottery_all -- 所有可能得红球组合
 * @author lizhihao 
 *
 */
@Component
public class LotteryAllUtil {

	
	/** 底层 Mapper 对象 */
	public static LotteryAllMapper lotteryAllMapper;
	@Autowired
	private void setLotteryAllMapper(LotteryAllMapper lotteryAllMapper) {
		LotteryAllUtil.lotteryAllMapper = lotteryAllMapper;
	}
	
	
	/** 
	 * 将一个 LotteryAll 对象进行进行数据完整性校验 (方便add/update等接口数据校验) [G] 
	 */
	static void check(LotteryAll l) {
		AjaxError.throwByIsNull(l.id, "[主键id] 不能为空");		// 验证: 主键id 
		AjaxError.throwByIsNull(l.red, "[红球] 不能为空");		// 验证: 红球 
	}

	/** 
	 * 获取一个LotteryAll (方便复制代码用) [G] 
	 */ 
	static LotteryAll getLotteryAll() {
		LotteryAll l = new LotteryAll();	// 声明对象 
		l.id = 0L;		// 主键id 
		l.red = "";		// 红球 
		return l;
	}
	
	
	
	
	
}
