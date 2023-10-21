package com.pj.project.lottery.unionLotto.enums;

/**
 * 区间比
 */
public enum RangeEnum {
    TWO_THREE_ONE("2:3:1","2:3:1"),
    ONE_THREE_TWO("1:3:2","1:3:2"),
    TWO_TWO_TWO("2:2:2","2:2:2"),
    THREE_TWO_ONE("3:2:1","3:2:1"),
    ONE_TWO_THREE("1:2:3","1:2:3"),
    TWO_ZERO_FOUR("2:0:4","2:0:4"),
    TWO_FOUR_ZERO("2:4:0","2:4:0"),
    FOUR_ZERO_TWO("4:0:2","4:0:2"),
    FOUR_TWO_ZERO("4:2:0","4:2:0"),
    THREE_ONE_TWO("3:1:2","3:1:2"),
    TWO_ONE_THREE("2:1:3","2:1:3"),
    FOUR_ONE_ONE("4:1:1","4:1:1"),
    ONE_ONE_FOUR("1:1:4","1:1:4"),
    ONE_FOUR_ONE("1:4:1","1:4:1"),
    ZERO_FIVE_ONE("0:5:1","0:5:1"),
    ;

    private String code;
    private String description;

    RangeEnum(String code, String description) {
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
