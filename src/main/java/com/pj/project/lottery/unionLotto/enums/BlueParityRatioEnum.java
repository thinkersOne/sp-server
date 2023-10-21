package com.pj.project.lottery.unionLotto.enums;

/**
 * 蓝球 奇偶
 */
public enum BlueParityRatioEnum {
    PARITY("奇","奇数"),
    RATIO("偶","偶数"),
    ;

    private String code;
    private String description;

    BlueParityRatioEnum(String code, String description) {
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
