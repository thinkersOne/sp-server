package com.pj.project.lottery.unionLotto.enums;

/**
 * 蓝球区域
 */
public enum BlueAreaEnum {
    ONE(1,"一区"),
    TWO(2,"二区"),
    THREE(3,"三区"),
    FOUR(4,"四区"),
    ;

    private int code;
    private String description;

    BlueAreaEnum(int code, String description) {
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
