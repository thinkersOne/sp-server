package com.pj.project.lottery.unionLotto.utils;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CombinationUtils {

    /**
     * 递归实现排列组合
     * @param candidate  数组--数字集合
     * @param prefix  递归打印使用
     * @param size   当前数组选取位数，例如4个数字里面求3位组合传3即可  传0为当前数组的所有组合输出
     * @param resList 返回内容的数字字符串组合
     */
    public static void recursive(List<String> candidate, String prefix, Integer size, List<String> resList){
        if(prefix.length() == size){
           resList.add(prefix);
           return;
        }
        for(int i=0; i<candidate.size(); i++){
            List<String> temp = new LinkedList<String>(candidate);
            String item = temp.remove(i);  // 取出被删除的元素，这个元素当作一个组合用掉了
            //去重保留一种  例如12和21为同一种  123 132 321位同一种
            for(int k = i; k > 0; k--) {//注释即为全排列
                temp.remove(k-1);
            }
            String result = "";
            if(StringUtils.hasLength(prefix)){
                result = prefix+","+item;
            }else{
                result = item;
            }
            recursive(temp, result, size, resList);
        }
    }

    public static void main(String[] args) {
        String chooseLotteryRed = "01,02,03,04,05,06,07,08,09,10,11,12,13,"+
                "14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33";
        List<String> list = Arrays.asList(chooseLotteryRed.split(","));
        List<String> resList = new ArrayList<String>();
        recursive(list, "", 17,resList);
        System.out.println(resList);
    }

}
