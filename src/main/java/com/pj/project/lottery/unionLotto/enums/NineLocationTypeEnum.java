package com.pj.project.lottery.unionLotto.enums;

public enum NineLocationTypeEnum {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    ;
    private int type;

    NineLocationTypeEnum(int type) {
        this.type = type;
    }

    public int  getType() {
        return type;
    }

}
