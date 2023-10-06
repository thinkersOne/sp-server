package com.pj.utils.ai.ali;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class ALiChoice {
    private String finish_reason;
    private List<ALiMessage> messages;
}
