package com.pj.utils.ai.ali;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class ALiParameter {
    private String result_format;
    private Float top_p;
    private Integer top_k;
    private Integer seed;

}
