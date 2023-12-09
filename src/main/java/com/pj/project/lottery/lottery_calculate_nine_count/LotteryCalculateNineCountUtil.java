package com.pj.project.lottery.lottery_calculate_nine_count;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pj.utils.sg.*;

/**
 * 工具类：lottery_calculate_nine_count -- 统计九转中四行数据每行上出现个数的统计表
 * @author lizhihao 
 *
 */
@Component
public class LotteryCalculateNineCountUtil {

	
	/** 底层 Mapper 对象 */
	public static LotteryCalculateNineCountMapper lotteryCalculateNineCountMapper;
	@Autowired
	private void setLotteryCalculateNineCountMapper(LotteryCalculateNineCountMapper lotteryCalculateNineCountMapper) {
		LotteryCalculateNineCountUtil.lotteryCalculateNineCountMapper = lotteryCalculateNineCountMapper;
	}
	
	
	/** 
	 * 将一个 LotteryCalculateNineCount 对象进行进行数据完整性校验 (方便add/update等接口数据校验) [G] 
	 */
	static void check(LotteryCalculateNineCount l) {
		AjaxError.throwByIsNull(l.id, "[主键id] 不能为空");		// 验证: 主键id 
		AjaxError.throwByIsNull(l.count, "[统计出现的个数] 不能为空");		// 验证: 统计出现的个数 
		AjaxError.throwByIsNull(l.nine, "[分组统计个数] 不能为空");		// 验证: 分组统计个数 
		AjaxError.throwByIsNull(l.type, "[类型：9、17、33] 不能为空");		// 验证: 类型：9、17、33 
		AjaxError.throwByIsNull(l.locationType, "[方位类型1、2、3、4] 不能为空");		// 验证: 方位类型 1、2、3、4 
	}

	/** 
	 * 获取一个LotteryCalculateNineCount (方便复制代码用) [G] 
	 */ 
	static LotteryCalculateNineCount getLotteryCalculateNineCount() {
		LotteryCalculateNineCount l = new LotteryCalculateNineCount();	// 声明对象 
		l.id = 0L;		// 主键id 
		l.count = 0;		// 统计出现的个数 
		l.nine = 0;		// 分组统计个数 
		l.type = 0;		// 类型：9、17、33 
		l.locationType = 0;		// 方位类型 1、2、3、4 
		return l;
	}
	
	
	
	
	
}
