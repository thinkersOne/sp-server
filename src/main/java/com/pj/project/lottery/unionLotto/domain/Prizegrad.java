package com.pj.project.lottery.unionLotto.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Prizegrad {
    private int type;
    private String typenum;
    private String typemoney;


}
