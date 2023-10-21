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
	public Integer year;	

	/**
	 * 月份 
	 */
	public Integer month;	

	/**
	 * 周 
	 */
	public Integer week;	

	/**
	 * 期号XX1-XX0 
	 */
	public String codes;	

	/**
	 * 红球1个数 
	 */
	public Integer red1;	

	/**
	 * 红球2个数 
	 */
	public Integer red2;	

	/**
	 * 红球3个数 
	 */
	public Integer red3;	

	/**
	 * 红球4个数 
	 */
	public Integer red4;	

	/**
	 * 红球5个数 
	 */
	public Integer red5;	

	/**
	 * 红球6个数 
	 */
	public Integer red6;	

	/**
	 * 红球7个数 
	 */
	public Integer red7;	

	/**
	 * 红球8个数 
	 */
	public Integer red8;	

	/**
	 * 红球9个数 
	 */
	public Integer red9;	

	/**
	 * 红球10个数 
	 */
	public Integer red10;	

	/**
	 * 红球11个数 
	 */
	public Integer red11;	

	/**
	 * 红球12个数 
	 */
	public Integer red12;	

	/**
	 * 红球13个数 
	 */
	public Integer red13;	

	/**
	 * 红球14个数 
	 */
	public Integer red14;	

	/**
	 * 红球15个数 
	 */
	public Integer red15;	

	/**
	 * 红球16个数 
	 */
	public Integer red16;	

	/**
	 * 红球17个数 
	 */
	public Integer red17;	

	/**
	 * 红球18个数 
	 */
	public Integer red18;	

	/**
	 * 红球19个数 
	 */
	public Integer red19;	

	/**
	 * 红球20个数 
	 */
	public Integer red20;	

	/**
	 * 红球21个数 
	 */
	public Integer red21;	

	/**
	 * 红球22个数 
	 */
	public Integer red22;	

	/**
	 * 红球23个数 
	 */
	public Integer red23;	

	/**
	 * 红球24个数 
	 */
	public Integer red24;	

	/**
	 * 红球25个数 
	 */
	public Integer red25;	

	/**
	 * 红球26个数 
	 */
	public Integer red26;	

	/**
	 * 红球27个数 
	 */
	public Integer red27;	

	/**
	 * 红球28个数 
	 */
	public Integer red28;	

	/**
	 * 红球29个数 
	 */
	public Integer red29;	

	/**
	 * 红球30个数 
	 */
	public Integer red30;	

	/**
	 * 红球31个数 
	 */
	public Integer red31;	

	/**
	 * 红球32个数 
	 */
	public Integer red32;	

	/**
	 * 红球33个数 
	 */
	public Integer red33;	

	/**
	 * 蓝球1个数 
	 */
	public Integer blue1;	

	/**
	 * 蓝球2个数 
	 */
	public Integer blue2;	

	/**
	 * 蓝球3个数 
	 */
	public Integer blue3;	

	/**
	 * 蓝球4个数 
	 */
	public Integer blue4;	

	/**
	 * 蓝球5个数 
	 */
	public Integer blue5;	

	/**
	 * 蓝球6个数 
	 */
	public Integer blue6;	

	/**
	 * 蓝球7个数 
	 */
	public Integer blue7;	

	/**
	 * 蓝球8个数 
	 */
	public Integer blue8;	

	/**
	 * 蓝球9个数 
	 */
	public Integer blue9;	

	/**
	 * 蓝球10个数 
	 */
	public Integer blue10;	

	/**
	 * 蓝球11个数 
	 */
	public Integer blue11;	

	/**
	 * 蓝球12个数 
	 */
	public Integer blue12;	

	/**
	 * 蓝球13个数 
	 */
	public Integer blue13;	

	/**
	 * 蓝球14个数 
	 */
	public Integer blue14;	

	/**
	 * 蓝球15个数 
	 */
	public Integer blue15;	

	/**
	 * 蓝球16个数 
	 */
	public Integer blue16;	

	/**
	 * 1：按月统计  2：按年统计  3：按周统计 
	 */
	public Integer calType;	

	/**
	 * 红球奇偶2:4 
	 */
	public Integer redParityRatio24;	

	/**
	 * 红球奇偶4:2 
	 */
	public Integer redParityRatio42;	

	/**
	 * 红球奇偶5:1 
	 */
	public Integer redParityRatio51;	

	/**
	 * 红球奇偶1:5 
	 */
	public Integer redParityRatio15;	

	/**
	 * 红球奇偶3:3 
	 */
	public Integer redParityRatio33;	

	/**
	 * 红球奇偶6:0 
	 */
	public Integer redParityRatio60;	

	/**
	 * 红球奇偶0:6 
	 */
	public Integer redParityRatio06;	

	/**
	 * 红球区间比2_3_1 
	 */
	public Integer redRange231;	

	/**
	 * 红球区间比2_2_2 
	 */
	public Integer redRange222;	

	/**
	 * 红球区间比3_2_1 
	 */
	public Integer redRange321;	

	/**
	 * 红球区间比1_2_3 
	 */
	public Integer redRange123;	

	/**
	 * 红球区间比2_0_4 
	 */
	public Integer redRange204;	

	/**
	 * 红球区间比2_4_0 
	 */
	public Integer redRange240;	

	/**
	 * 红球区间比4_0_2 
	 */
	public Integer redRange402;	

	/**
	 * 红球区间比4_2_0 
	 */
	public Integer redRange420;	

	/**
	 * 红球区间比3_1_2 
	 */
	public Integer redRange312;	

	/**
	 * 红球区间比2_1_3 
	 */
	public Integer redRange213;	

	/**
	 * 红球区间比4_1_1 
	 */
	public Integer redRange411;	

	/**
	 * 红球区间比1_1_4 
	 */
	public Integer redRange114;	

	/**
	 * 红球区间比1_4_1 
	 */
	public Integer redRange141;	

	/**
	 * 蓝球个数_小 
	 */
	public Integer blueSmall;	

	/**
	 * 蓝球个数_大 
	 */
	public Integer blueBig;	

	/**
	 * 蓝球区域_1 
	 */
	public Integer blueAreaOne;	

	/**
	 * 蓝球区域_2 
	 */
	public Integer blueAreaTwo;	

	/**
	 * 蓝球区域_3 
	 */
	public Integer blueAreaThree;	

	/**
	 * 蓝球区域_4 
	 */
	public Integer blueAreaFour;	

	/**
	 * 蓝球奇数个数 
	 */
	public Integer blueParity;	

	/**
	 * 蓝球偶数个数 
	 */
	public Integer blueRatio;	

	/**
	 * 红球和21_60 
	 */
	public Integer red2160;	

	/**
	 * 红球和73_78 
	 */
	public Integer red7378;	

	/**
	 * 红球和61_66 
	 */
	public Integer red6166;	

	/**
	 * 红球和103_108 
	 */
	public Integer red103108;	

	/**
	 * 红球和91_96 
	 */
	public Integer red9196;	

	/**
	 * 红球和79_84 
	 */
	public Integer red7984;	

	/**
	 * 红球和67_72 
	 */
	public Integer red6772;	

	/**
	 * 红球和109_114 
	 */
	public Integer red109114;	

	/**
	 * 红球和115_120 
	 */
	public Integer red115120;	

	/**
	 * 红球和133_138 
	 */
	public Integer red133138;	

	/**
	 * 红球和97_102 
	 */
	public Integer red97102;	

	/**
	 * 红球和139_144 
	 */
	public Integer red139144;	

	/**
	 * 红球和127_132 
	 */
	public Integer red127132;	

	/**
	 * 红球和121_126 
	 */
	public Integer red121126;	

	/**
	 * 红球和145_183 
	 */
	public Integer red145183;	





	


}
