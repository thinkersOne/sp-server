package com.pj.utils;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * 获取九转连环可能得组合
     * @return
     */
    public static List<String> getNineCompose(){
        List<String> list = new ArrayList<>(500);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    for (int l = 0; l < 5; l++) {
                        if(i+j+k+l != 6){
                            continue;
                        }
                        list.add(i+"_"+j+"_"+k+"_"+l);
                    }
                }
            }
        }
        return list;
    }

    public static void main(String[] args) {
        System.out.println(getNineCompose());
        System.out.println(getNineCompose().size());
    }

}
