package com.pj.project.lottery;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class LotteryParameter {
    private int type;
    private int pageNo;
    private int pageSize;
    private String name;

}
