package com.pj.utils.ai.ali;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class ALiUsage {
    private Integer output_tokens;
    private Integer input_tokens;

}
