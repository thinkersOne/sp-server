package com.pj.current.enums;

public enum ProductStatusEnum {
    UP("1","上架"),
    DOWN("2","下架"),
    ;

    private String type;
    private String typeName;

    private ProductStatusEnum(String type, String name){
        this.type = type;
        this.typeName = name;
    }
    public String getType() {
        return type;
    }
    public String getTypeName() {
        return typeName;
    }

    public static String getTypeName(String type){
        for(ProductStatusEnum vt : ProductStatusEnum.values()) {
            if (type.equals(vt.getType())) {
                return vt.getTypeName();
            }
        }
        return null;
    }

}
