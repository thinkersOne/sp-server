package com.pj.project.lottery.lottery_calculate_nine_count;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NineMinAndMaxDTO {
    private int min;
    private int max;
    private int type;
    private int locationType;


}
