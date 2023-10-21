package com.pj.current.enums;

public enum LotteryTypeEnum {
    UNION_LOTTO(1,"双色球","ssq"),
    K8(2,"快乐8","kl8"),
    FC_3D(3,"福彩3D","3d"),
    QLC(4,"七乐彩","qlc"),

    ;
    private int type;
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private String code;

    LotteryTypeEnum(int type, String name,String code) {
        this.type = type;
        this.name = name;
        this.code = code;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
