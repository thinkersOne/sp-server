package com.pj.utils;

import java.text.DecimalFormat;

public class BigDecimalUtils {
    private static final String FORMAT_2 = "00";
    /**
     * 格式化
     * @param str
     * @param format
     * @return
     */
    public static String format(String str,String format){
        DecimalFormat df=new DecimalFormat(format);
        return df.format(Integer.parseInt(str));
    }

    public static String format(String str){
        return format( str, FORMAT_2);
    }

    public static String format(int i){
        return format( IntegerUtils.convertToString(i), FORMAT_2);
    }

}
