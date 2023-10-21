package com.pj.project.lottery.unionLotto.utils;

import com.pj.project.lottery.unionLotto.domain.Prizegrad;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UnionLottoResult {
    private String name;
    private String code;
    private String detailsLink;
    private String videoLink;
    private String date;
    private String week;
    private String red;
    private String blue;
    private String blue2;
    private String sales;
    private String poolmoney;
    private String content;
    private String addmoney;
    private String addmoney2;
    private String msg;
    private String z2add;
    private String m2add;
    private List<Prizegrad> prizegrades;

}
