package com.pj.project.lottery.unionLotto.enums;

public enum CalTypeEnum {
    YEAR(1,"按年统计"),
    MONTH(2,"按月统计"),
    WEEK(3,"按周统计"),
    CODE(4,"按码统计"),
    ;
    private int calType;
    private String description;

    CalTypeEnum(int calType, String description) {
        this.calType = calType;
        this.description = description;
    }

    public int getCalType() {
        return calType;
    }

    public String getDescription() {
        return description;
    }

    public static String getDescription(int calType) {
        for (CalTypeEnum e : CalTypeEnum.values()) {
            if (e.getCalType() == calType) {
                return e.getDescription();
            }
        }
        return null;
    }



}
