package com.pj.utils;

import org.springframework.util.StringUtils;

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

    public static boolean compareTo(int a,String b){
        if(StringUtils.isEmpty(b)){
            return a > 0;
        }
        return a > Integer.parseInt(b);
    }

    public static boolean compareTo(String a,int b){
        if(StringUtils.isEmpty(a)){
            return 0 > b;
        }
        return Integer.valueOf(a) > b;
    }

}
