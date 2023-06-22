package com.pj.current.global;

public enum VedioTypeEnum {
    NORMAL("1","普通会员"),
    VIP("2","VIP会员"),
    ;

    private String type;
    private String name;

    private VedioTypeEnum(String type, String name){
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public static String getVedioName(String type){
        for(VedioTypeEnum vt : VedioTypeEnum.values()) {
            if (type.equals(vt.getType())) {
                return vt.getName();
            }
        }
        return null;
    }

}
