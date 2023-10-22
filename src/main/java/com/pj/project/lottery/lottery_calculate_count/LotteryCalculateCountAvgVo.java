package com.pj.project.lottery.lottery_calculate_count;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LotteryCalculateCountAvgVo {
    /**
     * 年份
     */
    public Integer year;

    /**
     * 红球奇偶2:4
     */
    public String redParityRatio24;

    /**
     * 红球奇偶4:2
     */
    public String redParityRatio42;

    /**
     * 红球奇偶5:1
     */
    public String redParityRatio51;

    /**
     * 红球奇偶1:5
     */
    public String redParityRatio15;

    /**
     * 红球奇偶3:3
     */
    public String redParityRatio33;

    /**
     * 红球奇偶6:0
     */
    public String redParityRatio60;

    /**
     * 红球奇偶0:6
     */
    public String redParityRatio06;

    /**
     * 红球区间比2_3_1
     */
    public String redRange231;

    /**
     * 红球区间比2_2_2
     */
    public String redRange222;

    /**
     * 红球区间比3_2_1
     */
    public String redRange321;

    /**
     * 红球区间比1_2_3
     */
    public String redRange123;

    /**
     * 红球区间比2_0_4
     */
    public String redRange204;

    /**
     * 红球区间比2_4_0
     */
    public String redRange240;

    /**
     * 红球区间比4_0_2
     */
    public String redRange402;

    /**
     * 红球区间比4_2_0
     */
    public String redRange420;

    /**
     * 红球区间比3_1_2
     */
    public String redRange312;

    /**
     * 红球区间比2_1_3
     */
    public String redRange213;

    /**
     * 红球区间比4_1_1
     */
    public String redRange411;

    /**
     * 红球区间比1_1_4
     */
    public String redRange114;

    /**
     * 红球区间比1_4_1
     */
    public String redRange141;
    /**
     * 红球和21_60
     */
    public String red2160;

    /**
     * 红球和73_78
     */
    public String red7378;

    /**
     * 红球和61_66
     */
    public String red6166;

    /**
     * 红球和103_108
     */
    public String red103108;

    /**
     * 红球和91_96
     */
    public String red9196;

    /**
     * 红球和79_84
     */
    public String red7984;

    /**
     * 红球和67_72
     */
    public String red6772;

    /**
     * 红球和109_114
     */
    public String red109114;

    /**
     * 红球和115_120
     */
    public String red115120;

    /**
     * 红球和133_138
     */
    public String red133138;

    /**
     * 红球和97_102
     */
    public String red97102;

    /**
     * 红球和139_144
     */
    public String red139144;

    /**
     * 红球和127_132
     */
    public String red127132;

    /**
     * 红球和121_126
     */
    public String red121126;

    /**
     * 红球和145_183
     */
    public String red145183;

}
