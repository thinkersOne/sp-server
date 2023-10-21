package com.pj.project.lottery.lottery_calculate_count;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pj.utils.sg.*;

/**
 * 工具类：lottery_calculate_count -- 按照不同时间维度统计每个红蓝球情况
 * @author lizhihao 
 *
 */
@Component
public class LotteryCalculateCountUtil {

	
	/** 底层 Mapper 对象 */
	public static LotteryCalculateCountMapper lotteryCalculateCountMapper;
	@Autowired
	private void setLotteryCalculateCountMapper(LotteryCalculateCountMapper lotteryCalculateCountMapper) {
		LotteryCalculateCountUtil.lotteryCalculateCountMapper = lotteryCalculateCountMapper;
	}
	
	
	/** 
	 * 将一个 LotteryCalculateCount 对象进行进行数据完整性校验 (方便add/update等接口数据校验) [G] 
	 */
	static void check(LotteryCalculateCount l) {
		AjaxError.throwByIsNull(l.id, "[主键id] 不能为空");		// 验证: 主键id 
		AjaxError.throwByIsNull(l.red1, "[红球1个数] 不能为空");		// 验证: 红球1个数 
		AjaxError.throwByIsNull(l.red2, "[红球2个数] 不能为空");		// 验证: 红球2个数 
		AjaxError.throwByIsNull(l.red3, "[红球3个数] 不能为空");		// 验证: 红球3个数 
		AjaxError.throwByIsNull(l.red4, "[红球4个数] 不能为空");		// 验证: 红球4个数 
		AjaxError.throwByIsNull(l.red5, "[红球5个数] 不能为空");		// 验证: 红球5个数 
		AjaxError.throwByIsNull(l.red6, "[红球6个数] 不能为空");		// 验证: 红球6个数 
		AjaxError.throwByIsNull(l.red7, "[红球7个数] 不能为空");		// 验证: 红球7个数 
		AjaxError.throwByIsNull(l.red8, "[红球8个数] 不能为空");		// 验证: 红球8个数 
		AjaxError.throwByIsNull(l.red9, "[红球9个数] 不能为空");		// 验证: 红球9个数 
		AjaxError.throwByIsNull(l.red10, "[红球10个数] 不能为空");		// 验证: 红球10个数 
		AjaxError.throwByIsNull(l.red11, "[红球11个数] 不能为空");		// 验证: 红球11个数 
		AjaxError.throwByIsNull(l.red12, "[红球12个数] 不能为空");		// 验证: 红球12个数 
		AjaxError.throwByIsNull(l.red13, "[红球13个数] 不能为空");		// 验证: 红球13个数 
		AjaxError.throwByIsNull(l.red14, "[红球14个数] 不能为空");		// 验证: 红球14个数 
		AjaxError.throwByIsNull(l.red15, "[红球15个数] 不能为空");		// 验证: 红球15个数 
		AjaxError.throwByIsNull(l.red16, "[红球16个数] 不能为空");		// 验证: 红球16个数 
		AjaxError.throwByIsNull(l.red17, "[红球17个数] 不能为空");		// 验证: 红球17个数 
		AjaxError.throwByIsNull(l.red18, "[红球18个数] 不能为空");		// 验证: 红球18个数 
		AjaxError.throwByIsNull(l.red19, "[红球19个数] 不能为空");		// 验证: 红球19个数 
		AjaxError.throwByIsNull(l.red20, "[红球20个数] 不能为空");		// 验证: 红球20个数 
		AjaxError.throwByIsNull(l.red21, "[红球21个数] 不能为空");		// 验证: 红球21个数 
		AjaxError.throwByIsNull(l.red22, "[红球22个数] 不能为空");		// 验证: 红球22个数 
		AjaxError.throwByIsNull(l.red23, "[红球23个数] 不能为空");		// 验证: 红球23个数 
		AjaxError.throwByIsNull(l.red24, "[红球24个数] 不能为空");		// 验证: 红球24个数 
		AjaxError.throwByIsNull(l.red25, "[红球25个数] 不能为空");		// 验证: 红球25个数 
		AjaxError.throwByIsNull(l.red26, "[红球26个数] 不能为空");		// 验证: 红球26个数 
		AjaxError.throwByIsNull(l.red27, "[红球27个数] 不能为空");		// 验证: 红球27个数 
		AjaxError.throwByIsNull(l.red28, "[红球28个数] 不能为空");		// 验证: 红球28个数 
		AjaxError.throwByIsNull(l.red29, "[红球29个数] 不能为空");		// 验证: 红球29个数 
		AjaxError.throwByIsNull(l.red30, "[红球30个数] 不能为空");		// 验证: 红球30个数 
		AjaxError.throwByIsNull(l.red31, "[红球31个数] 不能为空");		// 验证: 红球31个数 
		AjaxError.throwByIsNull(l.red32, "[红球32个数] 不能为空");		// 验证: 红球32个数 
		AjaxError.throwByIsNull(l.red33, "[红球33个数] 不能为空");		// 验证: 红球33个数 
		AjaxError.throwByIsNull(l.blue1, "[蓝球1个数] 不能为空");		// 验证: 蓝球1个数 
		AjaxError.throwByIsNull(l.blue2, "[蓝球2个数] 不能为空");		// 验证: 蓝球2个数 
		AjaxError.throwByIsNull(l.blue3, "[蓝球3个数] 不能为空");		// 验证: 蓝球3个数 
		AjaxError.throwByIsNull(l.blue4, "[蓝球4个数] 不能为空");		// 验证: 蓝球4个数 
		AjaxError.throwByIsNull(l.blue5, "[蓝球5个数] 不能为空");		// 验证: 蓝球5个数 
		AjaxError.throwByIsNull(l.blue6, "[蓝球6个数] 不能为空");		// 验证: 蓝球6个数 
		AjaxError.throwByIsNull(l.blue7, "[蓝球7个数] 不能为空");		// 验证: 蓝球7个数 
		AjaxError.throwByIsNull(l.blue8, "[蓝球8个数] 不能为空");		// 验证: 蓝球8个数 
		AjaxError.throwByIsNull(l.blue9, "[蓝球9个数] 不能为空");		// 验证: 蓝球9个数 
		AjaxError.throwByIsNull(l.blue10, "[蓝球10个数] 不能为空");		// 验证: 蓝球10个数 
		AjaxError.throwByIsNull(l.blue11, "[蓝球11个数] 不能为空");		// 验证: 蓝球11个数 
		AjaxError.throwByIsNull(l.blue12, "[蓝球12个数] 不能为空");		// 验证: 蓝球12个数 
		AjaxError.throwByIsNull(l.blue13, "[蓝球13个数] 不能为空");		// 验证: 蓝球13个数 
		AjaxError.throwByIsNull(l.blue14, "[蓝球14个数] 不能为空");		// 验证: 蓝球14个数 
		AjaxError.throwByIsNull(l.blue15, "[蓝球15个数] 不能为空");		// 验证: 蓝球15个数 
		AjaxError.throwByIsNull(l.blue16, "[蓝球16个数] 不能为空");		// 验证: 蓝球16个数 
		AjaxError.throwByIsNull(l.calType, "[1：按月统计2：按年统计3：按周统计] 不能为空");		// 验证: 1：按月统计  2：按年统计  3：按周统计 
		AjaxError.throwByIsNull(l.redParityRatio24, "[红球奇偶2:4] 不能为空");		// 验证: 红球奇偶2:4 
		AjaxError.throwByIsNull(l.redParityRatio42, "[红球奇偶4:2] 不能为空");		// 验证: 红球奇偶4:2 
		AjaxError.throwByIsNull(l.redParityRatio51, "[红球奇偶5:1] 不能为空");		// 验证: 红球奇偶5:1 
		AjaxError.throwByIsNull(l.redParityRatio15, "[红球奇偶1:5] 不能为空");		// 验证: 红球奇偶1:5 
		AjaxError.throwByIsNull(l.redParityRatio33, "[红球奇偶3:3] 不能为空");		// 验证: 红球奇偶3:3 
		AjaxError.throwByIsNull(l.redParityRatio60, "[红球奇偶6:0] 不能为空");		// 验证: 红球奇偶6:0 
		AjaxError.throwByIsNull(l.redParityRatio06, "[红球奇偶0:6] 不能为空");		// 验证: 红球奇偶0:6 
		AjaxError.throwByIsNull(l.redRange231, "[红球区间比2_3_1] 不能为空");		// 验证: 红球区间比2_3_1 
		AjaxError.throwByIsNull(l.redRange222, "[红球区间比2_2_2] 不能为空");		// 验证: 红球区间比2_2_2 
		AjaxError.throwByIsNull(l.redRange321, "[红球区间比3_2_1] 不能为空");		// 验证: 红球区间比3_2_1 
		AjaxError.throwByIsNull(l.redRange123, "[红球区间比1_2_3] 不能为空");		// 验证: 红球区间比1_2_3 
		AjaxError.throwByIsNull(l.redRange204, "[红球区间比2_0_4] 不能为空");		// 验证: 红球区间比2_0_4 
		AjaxError.throwByIsNull(l.redRange240, "[红球区间比2_4_0] 不能为空");		// 验证: 红球区间比2_4_0 
		AjaxError.throwByIsNull(l.redRange402, "[红球区间比4_0_2] 不能为空");		// 验证: 红球区间比4_0_2 
		AjaxError.throwByIsNull(l.redRange420, "[红球区间比4_2_0] 不能为空");		// 验证: 红球区间比4_2_0 
		AjaxError.throwByIsNull(l.redRange312, "[红球区间比3_1_2] 不能为空");		// 验证: 红球区间比3_1_2 
		AjaxError.throwByIsNull(l.redRange213, "[红球区间比2_1_3] 不能为空");		// 验证: 红球区间比2_1_3 
		AjaxError.throwByIsNull(l.redRange411, "[红球区间比4_1_1] 不能为空");		// 验证: 红球区间比4_1_1 
		AjaxError.throwByIsNull(l.redRange114, "[红球区间比1_1_4] 不能为空");		// 验证: 红球区间比1_1_4 
		AjaxError.throwByIsNull(l.redRange141, "[红球区间比1_4_1] 不能为空");		// 验证: 红球区间比1_4_1 
		AjaxError.throwByIsNull(l.blueSmall, "[蓝球个数_小] 不能为空");		// 验证: 蓝球个数_小 
		AjaxError.throwByIsNull(l.blueBig, "[蓝球个数_大] 不能为空");		// 验证: 蓝球个数_大 
		AjaxError.throwByIsNull(l.blueAreaOne, "[蓝球区域_1] 不能为空");		// 验证: 蓝球区域_1 
		AjaxError.throwByIsNull(l.blueAreaTwo, "[蓝球区域_2] 不能为空");		// 验证: 蓝球区域_2 
		AjaxError.throwByIsNull(l.blueAreaThree, "[蓝球区域_3] 不能为空");		// 验证: 蓝球区域_3 
		AjaxError.throwByIsNull(l.blueAreaFour, "[蓝球区域_4] 不能为空");		// 验证: 蓝球区域_4 
		AjaxError.throwByIsNull(l.blueParity, "[蓝球奇数个数] 不能为空");		// 验证: 蓝球奇数个数 
		AjaxError.throwByIsNull(l.blueRatio, "[蓝球偶数个数] 不能为空");		// 验证: 蓝球偶数个数 
		AjaxError.throwByIsNull(l.red2160, "[红球和21_60] 不能为空");		// 验证: 红球和21_60 
		AjaxError.throwByIsNull(l.red7378, "[红球和73_78] 不能为空");		// 验证: 红球和73_78 
		AjaxError.throwByIsNull(l.red6166, "[红球和61_66] 不能为空");		// 验证: 红球和61_66 
		AjaxError.throwByIsNull(l.red103108, "[红球和103_108] 不能为空");		// 验证: 红球和103_108 
		AjaxError.throwByIsNull(l.red9196, "[红球和91_96] 不能为空");		// 验证: 红球和91_96 
		AjaxError.throwByIsNull(l.red7984, "[红球和79_84] 不能为空");		// 验证: 红球和79_84 
		AjaxError.throwByIsNull(l.red6772, "[红球和67_72] 不能为空");		// 验证: 红球和67_72 
		AjaxError.throwByIsNull(l.red109114, "[红球和109_114] 不能为空");		// 验证: 红球和109_114 
		AjaxError.throwByIsNull(l.red115120, "[红球和115_120] 不能为空");		// 验证: 红球和115_120 
		AjaxError.throwByIsNull(l.red133138, "[红球和133_138] 不能为空");		// 验证: 红球和133_138 
		AjaxError.throwByIsNull(l.red97102, "[红球和97_102] 不能为空");		// 验证: 红球和97_102 
		AjaxError.throwByIsNull(l.red139144, "[红球和139_144] 不能为空");		// 验证: 红球和139_144 
		AjaxError.throwByIsNull(l.red127132, "[红球和127_132] 不能为空");		// 验证: 红球和127_132 
		AjaxError.throwByIsNull(l.red121126, "[红球和121_126] 不能为空");		// 验证: 红球和121_126 
		AjaxError.throwByIsNull(l.red145183, "[红球和145_183] 不能为空");		// 验证: 红球和145_183 
	}

	/** 
	 * 获取一个LotteryCalculateCount (方便复制代码用) [G] 
	 */ 
	static LotteryCalculateCount getLotteryCalculateCount() {
		LotteryCalculateCount l = new LotteryCalculateCount();	// 声明对象 
		l.id = 0L;		// 主键id 
		l.red1 = 0;		// 红球1个数 
		l.red2 = 0;		// 红球2个数 
		l.red3 = 0;		// 红球3个数 
		l.red4 = 0;		// 红球4个数 
		l.red5 = 0;		// 红球5个数 
		l.red6 = 0;		// 红球6个数 
		l.red7 = 0;		// 红球7个数 
		l.red8 = 0;		// 红球8个数 
		l.red9 = 0;		// 红球9个数 
		l.red10 = 0;		// 红球10个数 
		l.red11 = 0;		// 红球11个数 
		l.red12 = 0;		// 红球12个数 
		l.red13 = 0;		// 红球13个数 
		l.red14 = 0;		// 红球14个数 
		l.red15 = 0;		// 红球15个数 
		l.red16 = 0;		// 红球16个数 
		l.red17 = 0;		// 红球17个数 
		l.red18 = 0;		// 红球18个数 
		l.red19 = 0;		// 红球19个数 
		l.red20 = 0;		// 红球20个数 
		l.red21 = 0;		// 红球21个数 
		l.red22 = 0;		// 红球22个数 
		l.red23 = 0;		// 红球23个数 
		l.red24 = 0;		// 红球24个数 
		l.red25 = 0;		// 红球25个数 
		l.red26 = 0;		// 红球26个数 
		l.red27 = 0;		// 红球27个数 
		l.red28 = 0;		// 红球28个数 
		l.red29 = 0;		// 红球29个数 
		l.red30 = 0;		// 红球30个数 
		l.red31 = 0;		// 红球31个数 
		l.red32 = 0;		// 红球32个数 
		l.red33 = 0;		// 红球33个数 
		l.blue1 = 0;		// 蓝球1个数 
		l.blue2 = 0;		// 蓝球2个数 
		l.blue3 = 0;		// 蓝球3个数 
		l.blue4 = 0;		// 蓝球4个数 
		l.blue5 = 0;		// 蓝球5个数 
		l.blue6 = 0;		// 蓝球6个数 
		l.blue7 = 0;		// 蓝球7个数 
		l.blue8 = 0;		// 蓝球8个数 
		l.blue9 = 0;		// 蓝球9个数 
		l.blue10 = 0;		// 蓝球10个数 
		l.blue11 = 0;		// 蓝球11个数 
		l.blue12 = 0;		// 蓝球12个数 
		l.blue13 = 0;		// 蓝球13个数 
		l.blue14 = 0;		// 蓝球14个数 
		l.blue15 = 0;		// 蓝球15个数 
		l.blue16 = 0;		// 蓝球16个数 
		l.calType = 0;		// 1：按月统计  2：按年统计  3：按周统计 
		l.redParityRatio24 = 0;		// 红球奇偶2:4 
		l.redParityRatio42 = 0;		// 红球奇偶4:2 
		l.redParityRatio51 = 0;		// 红球奇偶5:1 
		l.redParityRatio15 = 0;		// 红球奇偶1:5 
		l.redParityRatio33 = 0;		// 红球奇偶3:3 
		l.redParityRatio60 = 0;		// 红球奇偶6:0 
		l.redParityRatio06 = 0;		// 红球奇偶0:6 
		l.redRange231 = 0;		// 红球区间比2_3_1 
		l.redRange222 = 0;		// 红球区间比2_2_2 
		l.redRange321 = 0;		// 红球区间比3_2_1 
		l.redRange123 = 0;		// 红球区间比1_2_3 
		l.redRange204 = 0;		// 红球区间比2_0_4 
		l.redRange240 = 0;		// 红球区间比2_4_0 
		l.redRange402 = 0;		// 红球区间比4_0_2 
		l.redRange420 = 0;		// 红球区间比4_2_0 
		l.redRange312 = 0;		// 红球区间比3_1_2 
		l.redRange213 = 0;		// 红球区间比2_1_3 
		l.redRange411 = 0;		// 红球区间比4_1_1 
		l.redRange114 = 0;		// 红球区间比1_1_4 
		l.redRange141 = 0;		// 红球区间比1_4_1 
		l.blueSmall = 0;		// 蓝球个数_小 
		l.blueBig = 0;		// 蓝球个数_大 
		l.blueAreaOne = 0;		// 蓝球区域_1 
		l.blueAreaTwo = 0;		// 蓝球区域_2 
		l.blueAreaThree = 0;		// 蓝球区域_3 
		l.blueAreaFour = 0;		// 蓝球区域_4 
		l.blueParity = 0;		// 蓝球奇数个数 
		l.blueRatio = 0;		// 蓝球偶数个数 
		l.red2160 = 0;		// 红球和21_60 
		l.red7378 = 0;		// 红球和73_78 
		l.red6166 = 0;		// 红球和61_66 
		l.red103108 = 0;		// 红球和103_108 
		l.red9196 = 0;		// 红球和91_96 
		l.red7984 = 0;		// 红球和79_84 
		l.red6772 = 0;		// 红球和67_72 
		l.red109114 = 0;		// 红球和109_114 
		l.red115120 = 0;		// 红球和115_120 
		l.red133138 = 0;		// 红球和133_138 
		l.red97102 = 0;		// 红球和97_102 
		l.red139144 = 0;		// 红球和139_144 
		l.red127132 = 0;		// 红球和127_132 
		l.red121126 = 0;		// 红球和121_126 
		l.red145183 = 0;		// 红球和145_183 
		return l;
	}
	
	
	
	
	
}
