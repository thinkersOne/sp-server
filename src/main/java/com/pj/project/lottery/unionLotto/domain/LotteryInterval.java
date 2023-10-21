package com.pj.project.lottery.unionLotto.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author : zhang sq 
 * @date : 2021/09/04 18:56:19
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LotteryInterval implements Serializable {
    private Long id;

    private Integer lotteryCount;

    private Double theoreticalFrequency;

    private Integer occurrences;

    private Double deviation;

    private String interval;

    private String code;

    private Integer intervalMin;

    private Integer intervalMax;

    private static final long serialVersionUID = 1L;
}