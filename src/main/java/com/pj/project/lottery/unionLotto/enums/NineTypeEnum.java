package com.pj.project.lottery.unionLotto.enums;

public enum NineTypeEnum {
    NINE09(9),
    NINE17(17),
    NINE33(33),
    ;
    private int type;

    NineTypeEnum(int type) {
        this.type = type;
    }

    public int  getType() {
        return type;
    }

}
