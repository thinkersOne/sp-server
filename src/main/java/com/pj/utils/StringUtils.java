package com.pj.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringUtils {

    public static String nextCode(String code){
        if(org.springframework.util.StringUtils.isEmpty(code)){
            return "1";
        }
        return (convertToInteger(code) + 1) +"";
    }
    public static Integer convertToInteger(String name){
        if(org.springframework.util.StringUtils.hasLength(name)){
            return Integer.valueOf(name);
        }
        return 0;
    }

    public static List<String> convertToList(String str){
        if(!org.springframework.util.StringUtils.hasLength(str)){
            return new ArrayList<>(1);
        }
        return Arrays.asList(str.split(","));
    }

    public static int arraysContainerStr(String[] strings, String str){
        if(strings == null || strings.length == 0 || org.springframework.util.StringUtils.isEmpty(str)){
            return 0;
        }
        int count = 0;
        String[] split = str.split(",");
        for(String s : split){
            for(String s1 : strings) {
                if (s.equals(s1)) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }

    public static String intToStr(int number){
        if(number < 10){
            return "00" + number;
        }else if(number < 100){
            return "0" + number;
        }
        return number+"";
    }

    public static void main(String[] args) {
        String code = "03,10,11,20,28,32";
        String[] strings = new String[]{"02", "05", "11", "20", "28", "32"};
        int i = arraysContainerStr(strings, code);
        System.out.println(i);

        String s = code.substring(0, 4);
        System.out.println(s);

    }

}
