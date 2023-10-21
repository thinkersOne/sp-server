package com.pj.project.lottery.unionLotto.enums;

/**
 * 奇偶比
 */
public enum ParityRatioEnum {
    TWO_FOUR("2:4","2:4"),
    FOUR_TWO("4:2","4:2"),
    FIVE_ONE("5:1","5:1"),
    ONE_FIVE("1:5","1:5"),
    THREE_THREE("3:3","3:3"),
    SIX_ZERO("6:0","6:0"),
    ZERO_SIX("0:6","0:6"),
    ;

    private String code;
    private String description;

    ParityRatioEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
