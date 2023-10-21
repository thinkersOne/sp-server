package com.pj.project.lottery.unionLotto.enums;

/**
 * 蓝球012路
 */
public enum BlueBroadEnum {
    ZERO(0,"零区"),
    ONE(1,"一区"),
    TWO(2,"二区"),
    ;

    private int code;
    private String description;

    BlueBroadEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
