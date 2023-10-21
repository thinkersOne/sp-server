package com.pj.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringUtils {

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



}
