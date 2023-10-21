package com.pj.project.lottery.unionLotto.domain;

import com.pj.project.lottery.unionLotto.utils.UnionLottoResult;
import lombok.Data;

import java.util.List;

@Data
public class UnionLottoResponse {
    private int state;
    private String message;
    private int total;
    private int pageNum;
    private int pageNo;
    private int pageSize;
    private int Tflag;
    private List<UnionLottoResult> result;

}
