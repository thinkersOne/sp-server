package com.pj.project.lottery.lottery_calculate_count;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Model: lottery_calculate_count -- 按照不同时间维度统计每个红蓝球情况
 * @author lizhihao 
 */
@Data
@Accessors(chain = true)
public class LotteryCalculateCount implements Serializable {

	// ---------- 模块常量 ----------
	/**
	 * 序列化版本id 
	 */
	private static final long serialVersionUID = 1L;	
	/**
	 * 此模块对应的表名 
	 */
	public static final String TABLE_NAME = "lottery_calculate_count";	
	/**
	 * 此模块对应的权限码 
	 */
	public static final String PERMISSION_CODE = "lottery-calculate-count";	


	// ---------- 表中字段 ----------
	/**
	 * 主键id 
	 */
	public Long id;	

	/**
	 * 年份 
	 */
	public int year;	

	/**
	 * 月份 
	 */
	public int month;	

	/**
	 * 周 
	 */
	public int week;	

	/**
	 * 期号XX1-XX0 
	 */
	public String codes;	

	/**
	 * 红球1个数 
	 */
	public int red1;	

	/**
	 * 红球2个数 
	 */
	public int red2;	

	/**
	 * 红球3个数 
	 */
	public int red3;	

	/**
	 * 红球4个数 
	 */
	public int red4;	

	/**
	 * 红球5个数 
	 */
	public int red5;	

	/**
	 * 红球6个数 
	 */
	public int red6;	

	/**
	 * 红球7个数 
	 */
	public int red7;	

	/**
	 * 红球8个数 
	 */
	public int red8;	

	/**
	 * 红球9个数 
	 */
	public int red9;	

	/**
	 * 红球10个数 
	 */
	public int red10;	

	/**
	 * 红球11个数 
	 */
	public int red11;	

	/**
	 * 红球12个数 
	 */
	public int red12;	

	/**
	 * 红球13个数 
	 */
	public int red13;	

	/**
	 * 红球14个数 
	 */
	public int red14;	

	/**
	 * 红球15个数 
	 */
	public int red15;	

	/**
	 * 红球16个数 
	 */
	public int red16;	

	/**
	 * 红球17个数 
	 */
	public int red17;	

	/**
	 * 红球18个数 
	 */
	public int red18;	

	/**
	 * 红球19个数 
	 */
	public int red19;	

	/**
	 * 红球20个数 
	 */
	public int red20;	

	/**
	 * 红球21个数 
	 */
	public int red21;	

	/**
	 * 红球22个数 
	 */
	public int red22;	

	/**
	 * 红球23个数 
	 */
	public int red23;	

	/**
	 * 红球24个数 
	 */
	public int red24;	

	/**
	 * 红球25个数 
	 */
	public int red25;	

	/**
	 * 红球26个数 
	 */
	public int red26;	

	/**
	 * 红球27个数 
	 */
	public int red27;	

	/**
	 * 红球28个数 
	 */
	public int red28;	

	/**
	 * 红球29个数 
	 */
	public int red29;	

	/**
	 * 红球30个数 
	 */
	public int red30;	

	/**
	 * 红球31个数 
	 */
	public int red31;	

	/**
	 * 红球32个数 
	 */
	public int red32;	

	/**
	 * 红球33个数 
	 */
	public int red33;	

	/**
	 * 蓝球1个数 
	 */
	public int blue1;	

	/**
	 * 蓝球2个数 
	 */
	public int blue2;	

	/**
	 * 蓝球3个数 
	 */
	public int blue3;	

	/**
	 * 蓝球4个数 
	 */
	public int blue4;	

	/**
	 * 蓝球5个数 
	 */
	public int blue5;	

	/**
	 * 蓝球6个数 
	 */
	public int blue6;	

	/**
	 * 蓝球7个数 
	 */
	public int blue7;	

	/**
	 * 蓝球8个数 
	 */
	public int blue8;	

	/**
	 * 蓝球9个数 
	 */
	public int blue9;	

	/**
	 * 蓝球10个数 
	 */
	public int blue10;	

	/**
	 * 蓝球11个数 
	 */
	public int blue11;	

	/**
	 * 蓝球12个数 
	 */
	public int blue12;	

	/**
	 * 蓝球13个数 
	 */
	public int blue13;	

	/**
	 * 蓝球14个数 
	 */
	public int blue14;	

	/**
	 * 蓝球15个数 
	 */
	public int blue15;	

	/**
	 * 蓝球16个数 
	 */
	public int blue16;	

	/**
	 * 1：按月统计  2：按年统计  3：按周统计 
	 */
	public int calType;	

	/**
	 * 红球奇偶2:4 
	 */
	public int redParityRatio24;	

	/**
	 * 红球奇偶4:2 
	 */
	public int redParityRatio42;	

	/**
	 * 红球奇偶5:1 
	 */
	public int redParityRatio51;	

	/**
	 * 红球奇偶1:5 
	 */
	public int redParityRatio15;	

	/**
	 * 红球奇偶3:3 
	 */
	public int redParityRatio33;	

	/**
	 * 红球奇偶6:0 
	 */
	public int redParityRatio60;	

	/**
	 * 红球奇偶0:6 
	 */
	public int redParityRatio06;	

	/**
	 * 红球区间比2_3_1 
	 */
	public int redRange231;	

	/**
	 * 红球区间比2_2_2 
	 */
	public int redRange222;	

	/**
	 * 红球区间比3_2_1 
	 */
	public int redRange321;	

	/**
	 * 红球区间比1_2_3 
	 */
	public int redRange123;	

	/**
	 * 红球区间比2_0_4 
	 */
	public int redRange204;	

	/**
	 * 红球区间比2_4_0 
	 */
	public int redRange240;	

	/**
	 * 红球区间比4_0_2 
	 */
	public int redRange402;	

	/**
	 * 红球区间比4_2_0 
	 */
	public int redRange420;	

	/**
	 * 红球区间比3_1_2 
	 */
	public int redRange312;	

	/**
	 * 红球区间比2_1_3 
	 */
	public int redRange213;	

	/**
	 * 红球区间比4_1_1 
	 */
	public int redRange411;	

	/**
	 * 红球区间比1_1_4 
	 */
	public int redRange114;	

	/**
	 * 红球区间比1_4_1 
	 */
	public int redRange141;	

	/**
	 * 蓝球个数_小 
	 */
	public int blueSmall;	

	/**
	 * 蓝球个数_大 
	 */
	public int blueBig;	

	/**
	 * 蓝球区域_1 
	 */
	public int blueAreaOne;	

	/**
	 * 蓝球区域_2 
	 */
	public int blueAreaTwo;	

	/**
	 * 蓝球区域_3 
	 */
	public int blueAreaThree;	

	/**
	 * 蓝球区域_4 
	 */
	public int blueAreaFour;	

	/**
	 * 蓝球奇数个数 
	 */
	public int blueParity;	

	/**
	 * 蓝球偶数个数 
	 */
	public int blueRatio;	

	/**
	 * 红球和21_60 
	 */
	public int red2160;	

	/**
	 * 红球和73_78 
	 */
	public int red7378;	

	/**
	 * 红球和61_66 
	 */
	public int red6166;	

	/**
	 * 红球和103_108 
	 */
	public int red103108;	

	/**
	 * 红球和91_96 
	 */
	public int red9196;	

	/**
	 * 红球和79_84 
	 */
	public int red7984;	

	/**
	 * 红球和67_72 
	 */
	public int red6772;	

	/**
	 * 红球和109_114 
	 */
	public int red109114;	

	/**
	 * 红球和115_120 
	 */
	public int red115120;	

	/**
	 * 红球和133_138 
	 */
	public int red133138;	

	/**
	 * 红球和97_102 
	 */
	public int red97102;	

	/**
	 * 红球和139_144 
	 */
	public int red139144;	

	/**
	 * 红球和127_132 
	 */
	public int red127132;	

	/**
	 * 红球和121_126 
	 */
	public int red121126;	

	/**
	 * 红球和145_183 
	 */
	public int red145183;	

	/**
	 * 连号个数 
	 */
	public int consecutiveNumbersCount0;	

	/**
	 * 连号个数 
	 */
	public int consecutiveNumbersCount1;	

	/**
	 * 连号个数 
	 */
	public int consecutiveNumbersCount2;	

	/**
	 * 最大连号数 
	 */
	public int maxConsecutiveNumbers1;	

	/**
	 * 最大连号数 
	 */
	public int maxConsecutiveNumbers2;	

	/**
	 * 最大连号数 
	 */
	public int maxConsecutiveNumbers3;	

	/**
	 * 最大连号数 
	 */
	public int maxConsecutiveNumbers4;	

	/**
	 * 最大连号数 
	 */
	public int maxConsecutiveNumbers5;	





	


}
