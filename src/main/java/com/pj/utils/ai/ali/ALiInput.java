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
public class ALiInput {
    private String prompt;
    private List<ALiHistory> history;
    private List<ALiMessage> messages;
}
