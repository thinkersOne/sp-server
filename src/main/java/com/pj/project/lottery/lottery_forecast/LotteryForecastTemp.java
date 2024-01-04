package com.pj.project.lottery.lottery_forecast;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LotteryForecastTemp {
    private String strategy;

    public void appendStrategy(String strategy) {
        // 追加策略
        if(this.strategy == null){
            this.strategy = strategy;
        }else{
            this.strategy += ";" + strategy;
        }
    }


}
