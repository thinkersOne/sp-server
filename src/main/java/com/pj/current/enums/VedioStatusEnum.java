package com.pj.current.enums;

public enum VedioStatusEnum {
    UP("1","上架"),
    DOWN("2","下架"),
    ;

    private String status;
    private String statusName;

    private VedioStatusEnum(String type, String name){
        this.status = type;
        this.statusName = name;
    }
    public String getStatus() {
        return status;
    }
    public String getStatusName() {
        return statusName;
    }

    public static String getVedioName(String type){
        for(VedioStatusEnum vt : VedioStatusEnum.values()) {
            if (type.equals(vt.getStatus())) {
                return vt.getStatusName();
            }
        }
        return null;
    }

}
