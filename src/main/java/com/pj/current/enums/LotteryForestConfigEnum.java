package com.pj.current.enums;

public enum LotteryForestConfigEnum {
    RED_LIST(1,"redList"),
    RED_PARITY_RATIO_LIST(2,"redParityRatioList"),
    RED_RANGE_LIST(3,"redRangeList"),
    RED_SUM_LIST(4,"redSumList"),
    CONSECUTIVE_NUMBERS_COUNT_LIST(5,"consecutiveNumbersCountList"),
    MAX_CONSECUTIVE_NUMBERS_COUNT_LIST(6,"maxConsecutiveNumbersCountList"),
    ;
    private int code;
    private String name;

    private LotteryForestConfigEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
