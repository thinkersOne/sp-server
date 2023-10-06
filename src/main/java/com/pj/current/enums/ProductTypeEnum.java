package com.pj.current.enums;

public enum ProductTypeEnum {
    ONE("1","包天"),
    TWO("2","包月"),
    THREE("3","三个月"),
    FOUR("4","包季度"),
    FIVE("5","包年"),
    SIX("6","永久"),
    ;

    private String type;
    private String typeName;

    private ProductTypeEnum(String type, String name){
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
        for(ProductTypeEnum vt : ProductTypeEnum.values()) {
            if (type.equals(vt.getType())) {
                return vt.getTypeName();
            }
        }
        return null;
    }

}
