package com.pj.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class ListUtils {

    public static List<?> intersectionForList_3(List<?> arr1, List<?> arr2) {
        if(arr1 == null || arr2 == null){
            return new ArrayList<>(1);
        }
        long startTime = System.currentTimeMillis();
        List<Object> resultList = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        arr1.forEach(a1->{
            map.put(a1+"",a1);
        });
        arr2.forEach(a2->{
            Object obj = map.get(a2 + "");
            if (obj!=null){
                resultList.add(obj);
            }
        });
        long endTime = System.currentTimeMillis();
        log.info("intersectionForList_3：" + (endTime-startTime));
        return resultList;
    }


}
