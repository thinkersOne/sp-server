package com.pj.project.lottery.lottery_forecast;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LotteryForestVo {
    private int type;
    private int orderBy;
    private String code;
}
