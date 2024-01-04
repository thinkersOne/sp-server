package com.pj.utils;

import com.github.yitter.contract.IdGeneratorOptions;
import com.github.yitter.idgen.YitIdHelper;

public class IdGeneratorUtils {

    static{
        IdGeneratorOptions options = new IdGeneratorOptions((short)1);
        YitIdHelper.setIdGenerator(options);
    }

    public static synchronized long generateId(){
        return YitIdHelper.nextId();
    }


    public static void main(String[] args) {
        System.out.println(generateId());
        System.out.println(generateId());
    }


}
