package com.pj.utils;

public class IntegerUtils {

    public static String convertToString(Integer code){
        if(code == null){
            return null;
        }
        return String.valueOf(code);
    }

    public static int convertToInt(String str){
        if(str == null){
            return 0;
        }
        return Integer.parseInt(str);
    }

    public static int convertToInt(Integer i){
        if(i == null){
            return 0;
        }
        return i;
    }

}
