package com.pj.project.lottery.lottery_select;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LotterySelectCodesDTO {
    /**
     * 预选可能的红球
     */
    private List<String> redList;
    /**
     * 红球奇偶比
     */
    private List<String> redParityRatioList;
    /**
     * 红球分区比
     */
    private List<String> redRangeRatioList;
    /**
     * 红球和值
     */
    private List<String> redSumList;
    /**
     * 连号个数
     */
    private List<Integer> consecutiveNumbersCountList;
    /**
     * 最大连号数
     */
    private List<Integer> maxConsecutiveNumbersCountList;
    /**
     * 九转连环-9
     */
    private List<String> nineTurn09List;
    /**
     * 九转连环-17
     */
    private List<String> nineTurn17List;
    /**
     * 九转连环-33
     */
    private List<String> nineTurn33List;

}
