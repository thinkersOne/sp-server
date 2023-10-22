package com.pj.project.lottery.lottery_calculate_per;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LotteryCalculatePerM {
    private int maxConsecutiveNumbersMax;
    private int maxConsecutiveNumbersMin;
    private int consecutiveNumbersCountMax;
    private int consecutiveNumbersCountMin;
    private int redSumMin;
    private int redSumMax;


}
