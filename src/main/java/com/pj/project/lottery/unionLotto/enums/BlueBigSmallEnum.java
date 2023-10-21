package com.pj.project.lottery.unionLotto.enums;

/**
 * 蓝球 大小
 */
public enum BlueBigSmallEnum {
    BIG("大","一区|二区"),
    SMALL("小","三区|四区"),
    ;

    private String code;
    private String description;

    BlueBigSmallEnum(String code, String description) {
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
