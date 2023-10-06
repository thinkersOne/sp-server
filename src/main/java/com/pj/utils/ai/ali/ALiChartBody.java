package com.pj.utils.ai.ali;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class ALiChartBody {
    private String model;
    private ALiInput input;
    private ALiParameter parameters;

}
