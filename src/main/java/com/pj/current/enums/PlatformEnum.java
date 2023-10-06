package com.pj.current.enums;

public enum PlatformEnum {
    WEN_XIN_YI_YAN(1,"文心一言"),
    TONG_YI_QIAN_WEN(2,"通义千问"),
    ;
    private int code;
    private String msg;

    PlatformEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }


}
