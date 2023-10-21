package com.pj.project.lottery.unionLotto.enums;

public enum UnionLottoWeekEnum {
    TWO("二"),
    FOUR("四"),
    SERVER("日"),
    ;
    private String week;

    UnionLottoWeekEnum(String week) {
        this.week = week;
    }

    public String getWeek() {
        return week;
    }

    public static boolean containerWeek(String week) {
        for (UnionLottoWeekEnum e : UnionLottoWeekEnum.values()) {
            if (e.getWeek().equals(week)) {
                return true;
            }
        }
        return false;
    }



}
