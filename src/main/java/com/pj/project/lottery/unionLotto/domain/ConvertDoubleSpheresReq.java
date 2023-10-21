package com.pj.project.lottery.unionLotto.domain;

import lombok.Data;

import java.util.List;

/**
 * 计算下期 号码 入参
 */
@Data
public class ConvertDoubleSpheresReq {
    //由APP上预计的红球组合
    private List<String> lotteryReds;
    //预计 必定会出现的红球，最后计算的红球组合中必定会包含该红球
    private String filterRed;
    //当前期号的红球
    private String currentLotteryRed;
    //上一期 同一位置 的红球
    private String commonAddrRed;
    /**
     * 统计上期的红球出现情况
     */
    //从未出现的红球
    private String noneRed="";
    //出现1次的红球
    private String oneRed="";
    //出现2次的红球
    private String twoRed="";
    //出现3次的红球
    private String threeRed="";
    //出现4次的红球
    private String fourRed="";

}
