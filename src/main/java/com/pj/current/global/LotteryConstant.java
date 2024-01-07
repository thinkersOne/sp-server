package com.pj.current.global;

import com.pj.project.lottery.lottery_forecast.LotteryForestStrategyCodeVo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LotteryConstant {
    //////////////////////////////////////////////////////// lottery unionLotto
    public static final String UNION_LOTTO_URL = "http://www.cwl.gov.cn/cwl_admin/front/cwlkj/search/kjxx/findDrawNotice";
    public static final int PAGE_SIZE = 1650;//动态变化

    public static final String LOTTERY_RED = "01,02,03,04,05,06,07,08,09,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33";

    public static final String ROOT_PATH = "D:\\documents\\files\\lottery\\";

    public static final String LOTTERY_FORECAST_ERROR_KEY = "ERROR_CODE";


    public static List<LotteryForestStrategyCodeVo> lotteryForestStrategyCodeVoList;

    static {
        Map<String, Integer[]> map = getMap();
        lotteryForestStrategyCodeVoList = new ArrayList<>(500);
        map.forEach((k,v)->{
            LotteryForestStrategyCodeVo lotteryForestStrategyCodeVo = new LotteryForestStrategyCodeVo();
            lotteryForestStrategyCodeVo.setBeginCode(v[0]);
            lotteryForestStrategyCodeVo.setEndCode(v[1]);
            lotteryForestStrategyCodeVoList.add(lotteryForestStrategyCodeVo);
        });
    }

    private static Map<String,Integer[]> getMap() {
        Map<String,Integer[]> map = new HashMap<>(100);
        map.put("2013",new Integer[]{2013001,2013154});
        map.put("2014",new Integer[]{2014001,2014152});
        map.put("2015",new Integer[]{2015001,2015154});
        map.put("2016",new Integer[]{2016001,2016153});
        map.put("2017",new Integer[]{2017001,2017154});
        map.put("2018",new Integer[]{2018001,2018153});
        map.put("2019",new Integer[]{2019001,2019151});
        map.put("2020",new Integer[]{2020001,2020134});
        map.put("2021",new Integer[]{2021001,2021150});
        map.put("2022",new Integer[]{2022001,2022150});
        map.put("2023",new Integer[]{2023001,2023151});
//        map.put("2024",new Integer[]{2013001,2013154});
        return map;
    }
}
