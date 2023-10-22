package com.pj.project.lottery.unionLotto.utils;

import com.pj.current.global.RuleConstants;
import com.pj.project.lottery.unionLotto.domain.ConvertDoubleSpheresReq;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 由个人经过 数据分析 得出的规律
 */
public class PersonalLawUtils {
    //未出现次数
    private static final int NONE_MIN_COUNT = 2;
    private static final int NONE_MAX_COUNT = 3;
    //出现1次
    private static final int ONE_MIN_COUNT = 1;
    private static final int ONE_MAX_COUNT = 1;
    //出现2次
    private static final int TWO_MIN_COUNT = 1;
    private static final int TWO_MAX_COUNT = 2;
    //出现3次
    private static final int THREE_MIN_COUNT = 0;
    private static final int THREE_MAX_COUNT = 1;
    //出现4次
    private static final int FOUR_MIN_COUNT = 0;
    private static final int FOUR_MAX_COUNT = 0;

    //当前出现的红球数 与上一期相同
    private static final int CURRENT_MIN_COUNT = 0;
    private static final int CURRENT_MAX_COUNT = 2;

    //当前出现的红球数 与同一位置的上期相同
    private static final int COMMON_MIN_COUNT = 0;
    private static final int COMMON_MAX_COUNT = 1;

    public static List<String> filterByPersonalLaw(List<String> list, ConvertDoubleSpheresReq req){
        if(CollectionUtils.isEmpty(list)){
            return new ArrayList<>(1);
        }
        return list.stream().filter(v->
                enableRed(req.getNoneRed(),v,NONE_MIN_COUNT,NONE_MAX_COUNT) &&
                enableRed(req.getOneRed(),v,ONE_MIN_COUNT,ONE_MAX_COUNT) &&
                enableRed(req.getTwoRed(),v,TWO_MIN_COUNT,TWO_MAX_COUNT) &&
                enableRed(req.getThreeRed(),v,THREE_MIN_COUNT,THREE_MAX_COUNT) &&
                enableRed(req.getFourRed(),v,FOUR_MIN_COUNT,FOUR_MAX_COUNT) &&

                        //下一期的 不能出现超过当前期 指定个数的 红球数
                enableRed(req.getCurrentLotteryRed(),v,CURRENT_MIN_COUNT,CURRENT_MAX_COUNT) &&
                enableRed(req.getCommonAddrRed(),v,COMMON_MIN_COUNT,COMMON_MAX_COUNT)
        ).collect(Collectors.toList());
    }

    public static int[] calNineTurnArray(String red,String[][] nineTurnArray){
        int[]  result = new int[2];
        String s = PersonalLawUtils.calNineTurn(red, nineTurnArray);
        if(StringUtils.isEmpty(s)){
            throw new RuntimeException("calNineTurn error");
        }
        String nineTurn = s.split("\\|")[0];
        result[0] = RuleUtils.getNineTurnMax(nineTurn);
        result[1] =  RuleUtils.getNineTurnMin(nineTurn);
        return result;
    }

    public static String calNineTurn(String red,String[][] nineTurnArray){
        if(nineTurnArray == null || nineTurnArray.length == 0){
            throw new RuntimeException("nineTurnArray is null");
        }
        if(nineTurnArray.length != 4){
            throw  new RuntimeException("nineTurnArray length is not 4");
        }
        String[] oneArray = nineTurnArray[0];
        if(oneArray == null || oneArray.length == 0){
            throw new RuntimeException("nineTurnArray[0] is null");
        }
        if(oneArray.length < 9){
            throw new RuntimeException("nineTurnArray[0] length is less than 9");
        }
        String middle = oneArray[4];
        List<String> redList = Arrays.stream(red.split(",")).collect(Collectors.toList());
        StringBuilder sb = new StringBuilder();
        sb.append(countNineTurn(redList,Arrays.asList(nineTurnArray[0]))).append("_")
                .append(countNineTurn(redList,Arrays.asList(nineTurnArray[1]))).append("_")
                .append(countNineTurn(redList,Arrays.asList(nineTurnArray[2]))).append("_")
                .append(countNineTurn(redList,Arrays.asList(nineTurnArray[3]))).append("|")
                .append(redList.contains(middle));
        return sb.toString();
    }

    private static int countNineTurn(List<String> redList,List<String> nineTurnList){
        if(CollectionUtils.isEmpty(redList) || CollectionUtils.isEmpty(nineTurnList)){
            throw new RuntimeException("redList or nineTurnList is null");
        }
        int count = 0;
        for (String red : redList) {
            if(nineTurnList.contains(red)){
                count++;
            }
        }
        return count;
    }


    public static boolean enableRed(String checkRed,String str,int minCount,int maxCount){
        if(!StringUtils.hasLength(str)){
            return false;
        }
        if(!StringUtils.hasLength(checkRed)){//为空则直接跳过该规则
            return true;
        }
        int currentCount = 0;
        List<String> list = Arrays.asList(checkRed.split(","));
        List<String> list1 = Arrays.asList(str.split(","));
        for (String st: list1){
            if(list.contains(st)){
                currentCount++;
            }
        }
        if(currentCount >= minCount && currentCount <= maxCount){
            return true;
        }
        return false;
    }

}
