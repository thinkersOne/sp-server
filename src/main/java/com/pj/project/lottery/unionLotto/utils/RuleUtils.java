package com.pj.project.lottery.unionLotto.utils;

import com.pj.current.global.RuleConstants;
import com.pj.project.lottery.lottery_calculate_nine_count.LotteryCalculateNineCount;
import com.pj.project.lottery.lottery_calculate_nine_count.NineMinAndMaxDTO;
import com.pj.project.lottery.unionLotto.domain.ConvertDoubleSpheresReq;
import com.pj.project.lottery.unionLotto.domain.Lottery;
import com.pj.project.lottery.unionLotto.domain.LotteryCalVo;
import com.pj.project.lottery.unionLotto.domain.LotteryInterval;
import com.pj.project.lottery.unionLotto.enums.*;
import com.pj.utils.BigDecimalUtils;
import com.pj.utils.IntegerUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 规则类
 */
public class RuleUtils {
    private static final String KEY = "NINE_TURN_SERIAL";
    private static final int DOUBLE_SPHERE_AMOUNT = 6;
    private static final int LOTTERY_AMOUNT = 5;
    private static final int COUNT_FOUR = 4;
    /**
     * 分区
     */
    public static final List<String> PARTITION_ONE = Arrays.asList(
            "01","02","03","04","05","06","07","08","09","10","11"
    );
    public static final List<String> PARTITION_TWO = Arrays.asList(
            "12","13","14","15","16","17","18","19","20","21","22"
    );
    public static final List<String> PARTITION_THREE = Arrays.asList(
            "23","24","25","26","27","28","29","30","31","32","33"
    );

    /**
     * 蓝球区域四个区
     */
    public static final List<String> AREA_ONE = Arrays.asList(
            "01","02","03","04"
    );
    public static final List<String> AREA_TWO = Arrays.asList(
            "05","06","07","08"
    );
    public static final List<String> AREA_THREE = Arrays.asList(
            "09","10","11","12"
    );
    public static final List<String> AREA_FOUR = Arrays.asList(
            "13","14","15","16"
    );

    public static final List<String> RED_SUM_LIST = Arrays.asList(
           "21_60","73_78","61_66","103_108","91_96","79_84","85_90","67_72","109_114","115_120","133_138","97_102",
            "139_144","127_132","121_126","145_183"
    );

    public static final List<Integer> CONSECUTIVE_NUMBERS_COUNT_LIST = Arrays.asList(
            0,1,2
    );

    public static final List<Integer> MAXCONSECUTIVE_NUMBERS_LIST = Arrays.asList(
            1,2,3,4,5
    );

    /**
     * 是否包含当前期号的红球
     * @param list
     * @param currentLotteryRed
     * @return
     */
    public static boolean isContainers(List<Lottery> list, String currentLotteryRed){
        if(CollectionUtils.isEmpty(list) || !StringUtils.hasLength(currentLotteryRed)){
            return false;
        }
        return list.stream().map(v-> v.getRed()).collect(Collectors.toList())
                .contains(currentLotteryRed);
    }

    /**
     * 判断当前选择的红球是否满足 九转连环图
     * @param currentLotteryRed
     * @return
     */
    public static boolean isMeetRuleConstants(String currentLotteryRed,String[][] sourceNineTurns){
        if(!StringUtils.hasLength(currentLotteryRed)){
            return false;
        }
        String[] currentReds = currentLotteryRed.split(",");
        Map<String,Boolean> map = new HashMap<>(4);
        for (int i = 0; i < currentReds.length; i++) {
            for (int j = 0; j < sourceNineTurns.length; j++) {
                List<String> list = Arrays.asList(sourceNineTurns[j]);
                //排除掉中心的红球，比较除中心以外的红球
                if(!currentReds[i].equals(sourceNineTurns[j][4])
                        && list.contains(currentReds[i]) && map.get(KEY+j) == null){
                    map.put(KEY+j,true);
                }
            }
        }
        int count = 0;
        for (Map.Entry<String,Boolean> entry: map.entrySet()){
            if(entry.getValue()){
                count++;
            }
        }
        return count >= 2;//3-4个才算合格
    }
    //33
    public static boolean isMeetRuleConstants33(String currentLotteryRed){
        return isMeetRuleConstants( currentLotteryRed, RuleConstants.NINE_TURN_SERIAL_DIAGRAM_33);
    }

    //17
    public static boolean isMeetRuleConstants17(String currentLotteryRed){
        return isMeetRuleConstants( currentLotteryRed,RuleConstants.NINE_TURN_SERIAL_DIAGRAM_17);
    }

    //09
    public static boolean isMeetRuleConstants09(String currentLotteryRed){
        return isMeetRuleConstants( currentLotteryRed,RuleConstants.NINE_TURN_SERIAL_DIAGRAM_09);
    }

    public static boolean enableContainNine(String code,int min,int max,String[] strings){
        int count = com.pj.utils.StringUtils.arraysContainerStr(strings,code);
        return count >= min && count <= max;
    }

    public static boolean enableContainNine(String code,List<NineMinAndMaxDTO> nineMinAndMaxDTOS){
        List<NineMinAndMaxDTO> nineMinAndMaxDTO9List = nineMinAndMaxDTOS.stream()
                .filter(v -> v.getType() == NineTypeEnum.NINE09.getType())
                .collect(Collectors.toList());
        boolean a = enableNinePerCount(nineMinAndMaxDTO9List, code, RuleConstants.NINE_TURN_SERIAL_DIAGRAM_09);
        if(!a){
            return false;
        }
        List<NineMinAndMaxDTO> nineMinAndMaxDTO17List = nineMinAndMaxDTOS.stream()
                .filter(v -> v.getType() == NineTypeEnum.NINE17.getType())
                .collect(Collectors.toList());
        boolean b = enableNinePerCount(nineMinAndMaxDTO17List, code, RuleConstants.NINE_TURN_SERIAL_DIAGRAM_17);
        if(!b){
            return false;
        }
        List<NineMinAndMaxDTO> nineMinAndMaxDTO33List = nineMinAndMaxDTOS.stream()
                .filter(v -> v.getType() == NineTypeEnum.NINE33.getType())
                .collect(Collectors.toList());
        boolean c = enableNinePerCount(nineMinAndMaxDTO33List, code, RuleConstants.NINE_TURN_SERIAL_DIAGRAM_33);
        if(!c){
            return false;
        }
        return true;
    }

    private static boolean enableNinePerCount(List<NineMinAndMaxDTO> nineMinAndMaxDTO9List,String code,String[][] strings){
        for (int i = 0; i < 4; i++) {
            int finalI = i;
            NineMinAndMaxDTO nineMinAndMaxDTO1 = nineMinAndMaxDTO9List.stream()
                    .filter(v -> v.getLocationType() == finalI +1).findFirst().get();
            String[] strings1 = strings[i];
            boolean a = enableContainNine(code, nineMinAndMaxDTO1.getMin(), nineMinAndMaxDTO1.getMax(), strings1);
            if(!a){
                return false;
            }
        }
        return true;
    }


    /**
     * 是否同时满足三种九转连环图情况
     * @param currentLotteryRed
     * @return
     */
    public static boolean isMeetRuleConstants(String currentLotteryRed){
        return isMeetRuleConstants33( currentLotteryRed) && isMeetRuleConstants17( currentLotteryRed)
                && isMeetRuleConstants09( currentLotteryRed);
    }

    /**
     * 将选择的一堆红色球转换成组合之后的红色球组合集合
     * @param chooseLotteryRed
     * @param amount
     * @return
     */
    public static List<String> convertLotterys(String chooseLotteryRed,int amount){
        if(!StringUtils.hasLength(chooseLotteryRed) || chooseLotteryRed.length() < amount){
            return new ArrayList<>(1);
        }
        List<String> list = new ArrayList<>(100);
        String[] allLotteryReds = chooseLotteryRed.split(",");
        for (int i = 0; i < allLotteryReds.length; i++) {
            if((i+amount) <= allLotteryReds.length) {
                for (int n = i + 1; n < allLotteryReds.length; n++) {
                    StringBuffer sb = new StringBuffer();
                    sb.append(allLotteryReds[i] + ",");
                    int count = 0;
                    for (int j = n; j < (amount + n - 1) && j < allLotteryReds.length; j++) {
                        if (count == amount - 2) {
                            sb.append(allLotteryReds[j]);
                        } else {
                            sb.append(allLotteryReds[j] + ",");
                        }
                        count++;
                    }
                    if(StringUtils.hasLength(sb.toString()) && count == (amount - 1)){
                        list.add(sb.toString());
                    }
                }
            }
        }
        return list;
    }

    /**
     * 过滤chooseLotteryReds，将生成的按照指定个数进行模糊过滤
     * @param chooseLotteryReds
     * @param generateLotteryRed
     * @param count
     * @return
     */
    public static List<String> filterLotterysAmount(List<String> chooseLotteryReds,String generateLotteryRed,int count){
        if(CollectionUtils.isEmpty(chooseLotteryReds) || !StringUtils.hasLength(generateLotteryRed)){
            return new ArrayList<>(1);
        }
        List<String> list = new ArrayList<>(100);
        for (String currentLotteryRed: chooseLotteryReds){
            if(!isMoreMatch( currentLotteryRed, generateLotteryRed,count)){
                list.add(currentLotteryRed);
            }
        }
        return list;
    }

    public static boolean redSumContainer(List<String> redSumList,int redSum){
        for (String redSumItem: redSumList){
            String[] split = redSumItem.split("_");
            int min = Integer.parseInt(split[0]);
            int max = Integer.parseInt(split[1]);
            if(redSum >= min && redSum <= max){
                return true;
            }
        }
        return false;
    }

    public static List<String> filterLotterysAmount(List<String> chooseLotteryReds,List<String> generateLotteryReds,int count){
        if(CollectionUtils.isEmpty(chooseLotteryReds) || CollectionUtils.isEmpty(generateLotteryReds)){
            return new ArrayList<>(1);
        }
        List<String> list = chooseLotteryReds;
        for (String str: generateLotteryReds){
            list = filterLotterysAmount(list, str, count);
        }
        return list;
    }

    public static List<String> filterLotterysAmount(List<String> chooseLotteryReds,int count){
        if(CollectionUtils.isEmpty(chooseLotteryReds)){
            return new ArrayList<>(1);
        }
        List<String> list = new ArrayList<>(10000);
        for (int i = 0; i < chooseLotteryReds.size(); i++) {
            if(list.size() == 0){
                list.add(chooseLotteryReds.get(i));
                continue;
            }
            boolean a = false;
            for (int j = 0; j < list.size() & !a; j++) {
                if(!isMoreMatch( chooseLotteryReds.get(i), list.get(j),count)){
                    list.add(chooseLotteryReds.get(i));
                }else{
                    a = true;
                }
            }
        }
        return list;
    }

    /**
     * 比较两个字符串，先转换成集合对象，
     * 比较几何元素相同个数是否超过count
     * false：满足条件
     * true：不满足条件
     * 如：不超过4个相同的才算满足条件，true则不满足
     * @param currentLotteryRed
     * @param generateLotteryRed
     * @param count
     * @return
     */
    public static boolean isMoreMatch(String currentLotteryRed,String generateLotteryRed,int count){
        if(!StringUtils.hasLength(currentLotteryRed) || !StringUtils.hasLength(generateLotteryRed)){
            return false;
        }
        String[] currentLotterys = currentLotteryRed.split(",");
        String[] generateLotterys = generateLotteryRed.split(",");
        List<String> generateLotteryList = Arrays.asList(generateLotterys);
        int amount = 0;
        for (String str: currentLotterys) {
            if(generateLotteryList.contains(str)){
                amount++;
            }
        }
        return amount >= count;
    }

    /**
     * 是否满足分区
     * 至少在两个分区及以上分支才算满足条件
     * 都在一个分区进行过滤
     * @param currentRed
     * @return
     */
    public static Boolean enableMeetPartition(String currentRed){
        if(!StringUtils.hasLength(currentRed)){
            return false;
        }
        String[] list = currentRed.split(",");
        boolean onePartition = false,twoPartition = false,threePartition = false;
        for (String str:list){
            if(PARTITION_ONE.contains(str)){
                onePartition = true;
            }
            if(PARTITION_TWO.contains(str)){
                twoPartition = true;
            }
            if(PARTITION_THREE.contains(str)){
                threePartition = true;
            }
        }
        return (onePartition && twoPartition) || (onePartition && threePartition) || (threePartition && twoPartition);
    }

    /**
     * 是否满足在分区的个数
     * @param currentRed
     * @return
     */
    public static Boolean enableMeetPartitionCount(String currentRed){
        if(!StringUtils.hasLength(currentRed)){
            return false;
        }
        String[] list = currentRed.split(",");
        int countOne = 0,countTwo = 0,countThree = 0;
        for (String str:list){
            if(PARTITION_ONE.contains(str)){
                countOne++;
            }
            if(PARTITION_TWO.contains(str)){
                countTwo++;
            }
            if(PARTITION_THREE.contains(str)){
                countThree++;
            }
        }
        return (countOne <= COUNT_FOUR && countTwo <= COUNT_FOUR && countThree <= COUNT_FOUR);
    }

    /**
     * 不超过3个连号
     * @param currentLotteryRed
     * @return
     */
    public static final boolean enableMeetEnvNo(String currentLotteryRed){
        return enableMeetEnvNo( currentLotteryRed,3);
    }

    /**
     * 根据选择的所有数字进行随机组合成 6个数据
     * @param list
     * @return
     */
    public static List<String> getAllGenerateCompose(List<String> list){
        List<String> resList = new ArrayList<String>();
        CombinationUtils.recursive(list, "", 17,resList);
        return resList;
    }

    /**
     * 获取最大连号数，如 2,3,4,6,7,9  最大连号数为3.最小为1
     * @param list
     * @return
     */
    public static int  getConsecutiveInfo(List<String> list){
        if(CollectionUtils.isEmpty(list)){
            throw new IllegalArgumentException("list is empty");
        }
        if(list.size() < 6){
            throw new IllegalArgumentException("list size is less than 6");
        }
        String firstRed = list.get(0);
        int consecutiveNumberCount = 1;
        int nextVal = Integer.valueOf(firstRed) + 1;
        consecutiveNumberCount = calConsecutiveNumberCount(list, nextVal, 1, consecutiveNumberCount, 1, false);
        return consecutiveNumberCount;
    }

    public static int  getConsecutiveInfo(String red){
        List<String> list = Arrays.asList(red.split(","));
        return getConsecutiveInfo(list);
    }

    private static int calConsecutiveNumberCount(List<String> list, int nextVal,int i,
        int hisMaxConsecutiveNumberCount,int maxConsecutiveNumberCount,boolean enableConsive){
        if(i > 5){
            return hisMaxConsecutiveNumberCount;
        }
        if(list.contains((nextVal)+"")){
            maxConsecutiveNumberCount++;
            if(hisMaxConsecutiveNumberCount < maxConsecutiveNumberCount){
                hisMaxConsecutiveNumberCount = maxConsecutiveNumberCount;
            }
            nextVal++;
            i++;
        }else{
            nextVal = Integer.valueOf(list.get(i)) + 1;
            enableConsive = false;
            maxConsecutiveNumberCount = 1;
            i++;
        }
        return calConsecutiveNumberCount(list,nextVal,i,hisMaxConsecutiveNumberCount, maxConsecutiveNumberCount, enableConsive);
    }


    /**
     * 统计 连号个数
     * @param numbers
     * @param count
     * @param index
     * @param enableConsecutive
     * @return
     */
    public static int countConsecutiveSets(List<String> numbers,int count,int index,boolean enableConsecutive) {
        Set<String> set = new HashSet<>(numbers);
        for (int i = index; i < numbers.size(); i++) {
            Integer currentRed = Integer.valueOf(numbers.get(i));
            if(set.contains((currentRed + 1)+"")){
                if(!enableConsecutive){
                    count ++;
                    index++;
                    enableConsecutive = true;
                    countConsecutiveSets(numbers,count,index,enableConsecutive);
                }
            }else{
                enableConsecutive = false;
            }
        }
        return count;
    }

    public static int countConsecutiveSets(String red){
        List<String> list = Arrays.asList(red.split(","));
        return countConsecutiveSets(list);
    }

    public static int getNineTurnMax(String k){
        String[] split = k.split("_");
        int maxNineTurn = 0;
        for (int i = 0; i < split.length; i++) {
            if(IntegerUtils.compareTo(split[i],maxNineTurn)){
                maxNineTurn = Integer.valueOf(split[i]);
            }
        }
        return maxNineTurn;
    }

    public static int getNineTurnMin(String k){
        String[] split = k.split("_");
        int minNineTurn = 0;
        for (int i = 0; i < split.length; i++) {
            if(IntegerUtils.compareTo(minNineTurn,split[i])){
                minNineTurn = Integer.valueOf(split[i]);
            }
        }
        return minNineTurn;
    }

    public static int countConsecutiveSets(List<String> numbers){
        return countConsecutiveSets(numbers,0,0,false);
    }

    /**
     * 获取 组合
     * @param req
     * @return
     */
    public static String getChooseLotteryRed(ConvertDoubleSpheresReq req){
        StringBuffer sb = new StringBuffer();
        append( sb,req.getNoneRed());
        append( sb,req.getOneRed());
        append( sb,req.getTwoRed());
        append( sb,req.getThreeRed());
        append( sb,req.getFourRed());
        return sb.toString();
    }

    public static String append(StringBuffer sb,String str){
        if(org.springframework.util.StringUtils.hasLength(str)){
            if(org.springframework.util.StringUtils.hasLength(sb.toString())){
                sb.append(",").append(str);
            }else{
                sb.append(str);
            }
        }
        return str;
    }

    /**
     * 连号-过滤指定要求的红球
     * @param list
     * @return
     */
    public static List<String> filterEvenNo(List<String> list){
        if(CollectionUtils.isEmpty(list)){
            return list;
        }
        return list.stream().filter(v-> enableEvenNo(v)).collect(Collectors.toList());
    }

    /**
     * 只要连号的数据
     * @param list
     * @return
     */
    public static List<String> filterEvenNoYes(List<String> list){
        return filterEvenNo(list);
    }

    /**
     * 只要非连号的数据
     * @param list
     * @return
     */
    public static List<String> filterEvenNoNo(List<String> list){
        if(CollectionUtils.isEmpty(list)){
            return list;
        }
        return list.stream().filter(v-> !enableEvenNo(v)).collect(Collectors.toList());
    }

    /**
     * 奇偶-晒出满足条件的红球
     * @param list
     * @return
     */
    public static List<String> filterParityRatio(List<String> list,String parityRatio){
        if(CollectionUtils.isEmpty(list)){
            return list;
        }
        return list.stream().filter(v-> calParityRatio(v).equals(parityRatio)).collect(Collectors.toList());
    }

    /**
     * 当前期号有多少个连号
     * @param currentLottery
     * @return
     */
    public static int calEvenNoCount(String currentLottery){
        if(!StringUtils.hasLength(currentLottery)){
            return 0;
        }
        List<String> list = Arrays.asList(currentLottery.split(","));
        Collections.sort(list); // 升序排列
        int resultCount = 0;
        int currentLot = 0;
        boolean currentEnableEvenNo = false;
        for (String str: list){
            if(currentLot != 0 && (currentLot + 1) == Integer.valueOf(str)
                && !currentEnableEvenNo){
                currentEnableEvenNo = true;
                resultCount++;
            }else{
                currentEnableEvenNo = false;
            }
            currentLot = Integer.valueOf(str);
        }
        return resultCount;
    }

    /**
     * 区间比-晒出满足条件的红球
     * @param list
     * @return
     */
    public static List<String> filterRange(List<String> list,String range){
        if(CollectionUtils.isEmpty(list)){
            return list;
        }
        return list.stream().filter(v-> calRange(v).equals(range)).collect(Collectors.toList());
    }

    /**
     * 筛选出指定连号个数的集合
     * @param list
     * @param evenNoCount
     * @return
     */
    public static List<String> filterEvenNoCount(List<String> list,int evenNoCount){
        if(CollectionUtils.isEmpty(list)){
            return list;
        }
        return list.stream().filter(v-> calEvenNoCount(v) == evenNoCount).collect(Collectors.toList());
    }

    /**
     * 从篮球字符串中随机抽选一个
     * @param currentBlue
     * @return
     */
    public static String getRandomBlue(String currentBlue){
        if(!StringUtils.hasLength(currentBlue)){
            return null;
        }
        List<String> list = Arrays.asList(currentBlue.split(","));
        return list.get(RandomUtils.randomIndex(list.size()));
    }

    /**
     * 计算红球和值
     * @param currentLotteryRed
     * @return
     */
    public static int calRedSum(String currentLotteryRed){
        if(!StringUtils.hasLength(currentLotteryRed)){
            return 0;
        }
        String[] currentLotteryReds = currentLotteryRed.split(",");
        int sum = 0;
        for (String str: currentLotteryReds){
            sum += com.pj.utils.StringUtils.convertToInteger(str);
        }
        return sum;
    }

    /**
     * 计算出某个号码是在哪个和值区间
     * @param currentLotteryRed
     * @return
     */
    public static String calRedSumRange(String currentLotteryRed){
        int redSum = calRedSum(currentLotteryRed);
        for (int i = 0; i < RED_SUM_LIST.size(); i++) {
            String v = RED_SUM_LIST.get(i);
            String[] split = v.split("_");
            if(Integer.valueOf(split[0]) <= redSum && redSum <= Integer.valueOf(split[1])){
                return v;
            }
        }
        return "";
    }

    /**
     * 计算区间比
     * @param currentLotteryRed
     * @return
     */
    public static String calRange(String currentLotteryRed){
        if(!StringUtils.hasLength(currentLotteryRed)){
            return null;
        }
        String[] currentLotteryReds = currentLotteryRed.split(",");
        int rangeOne = 0,rangeTwo= 0,rangeThree = 0;
        for (String str: currentLotteryReds){
            if(PARTITION_ONE.contains(str)){
                rangeOne++;
            }else if(PARTITION_TWO.contains(str)){
                rangeTwo++;
            }else if(PARTITION_THREE.contains(str)){
                rangeThree++;
            }
        }
        return rangeOne+":"+rangeTwo+":"+rangeThree;
    }

    /**
     * 奇偶比
     * @param currentLotteryRed
     * @return
     */
    public static String calParityRatio(String currentLotteryRed){
        if(!StringUtils.hasLength(currentLotteryRed)){
            return null;
        }
        String[] currentLotteryReds = currentLotteryRed.split(",");
        int parity = 0,ratio= 0;
        for (String str: currentLotteryReds){
            if((Integer.valueOf(str) & 1) == 1){//奇数
                parity++;
            }else{//偶数
                ratio++;
            }
        }
        return parity+":"+ratio;
    }


    /**
     * 奇
     * @return
     */
    public static List<String> getParity(List<String> blues){
        if(CollectionUtils.isEmpty(blues)){
            return new ArrayList<>(1);
        }
        return blues.stream().filter(v-> (Integer.valueOf(v) & 1) == 1).collect(Collectors.toList());
    }
    /**
     * 偶
     * @return
     */
    public static List<String> getRatio(List<String> blues){
        if(CollectionUtils.isEmpty(blues)){
            return new ArrayList<>(1);
        }
        return blues.stream().filter(v-> (Integer.valueOf(v) & 1) != 1).collect(Collectors.toList());
    }

    /**
     * 根据篮球获取区域
     * @param blue
     * @return
     */
    public static int getBlueArea(String blue){
        if(!StringUtils.hasLength(blue)){
            return 0;
        }
        if(AREA_ONE.contains(blue)){
            return BlueAreaEnum.ONE.getCode();
        }
        if(AREA_TWO.contains(blue)){
            return BlueAreaEnum.TWO.getCode();
        }
        if(AREA_THREE.contains(blue)){
            return BlueAreaEnum.THREE.getCode();
        }
        if(AREA_FOUR.contains(blue)){
            return BlueAreaEnum.FOUR.getCode();
        }
        return 0;
    }
    //筛选1区
    public static List<String> getBlueAreaOne(List<String> list){
        if(CollectionUtils.isEmpty(list)){
            return new ArrayList<>(1);
        }
        return list.stream().filter(v-> AREA_ONE.contains(v)).collect(Collectors.toList());
    }
    //筛选2区
    public static List<String> getBlueAreaTwo(List<String> list){
        if(CollectionUtils.isEmpty(list)){
            return new ArrayList<>(1);
        }
        return list.stream().filter(v-> AREA_TWO.contains(v)).collect(Collectors.toList());
    }
    //筛选3区
    public static List<String> getBlueAreaThree(List<String> list){
        if(CollectionUtils.isEmpty(list)){
            return new ArrayList<>(1);
        }
        return list.stream().filter(v-> AREA_THREE.contains(v)).collect(Collectors.toList());
    }
    //筛选4区
    public static List<String> getBlueAreaFour(List<String> list){
        if(CollectionUtils.isEmpty(list)){
            return new ArrayList<>(1);
        }
        return list.stream().filter(v-> AREA_FOUR.contains(v)).collect(Collectors.toList());
    }
    /**
     * 获取大小
     * @param blue
     * @return
     */
    public static String getBlueBigSmall(String blue){
        if(!StringUtils.hasLength(blue)){
            return null;
        }
        if(AREA_ONE.contains(blue) || AREA_TWO.contains(blue)){
            return BlueBigSmallEnum.SMALL.getCode();
        }
        if(AREA_THREE.contains(blue) || AREA_FOUR.contains(blue)){
            return BlueBigSmallEnum.BIG.getCode();
        }
        return null;
    }
    //取大
    public static List<String> getBlueBig(){
        List<String> list = new ArrayList<>(8);
        list.addAll(AREA_THREE);
        list.addAll(AREA_FOUR);
        return list;
    }
    //取小
    public static List<String> getBlueSmall(){
        List<String> list = new ArrayList<>(8);
        list.addAll(AREA_ONE);
        list.addAll(AREA_TWO);
        return list;
    }

    /**
     * 获取蓝球奇偶
     * @param blue
     * @return
     */
    public static String getParityRatio(String blue){
        if(!StringUtils.hasLength(blue)){
            return null;
        }
        if((Integer.valueOf(blue) & 1) == 1){//奇数
            return BlueParityRatioEnum.PARITY.getCode();
        }else{//偶数
            return BlueParityRatioEnum.RATIO.getCode();
        }
    }

    /**
     * 获取蓝球 012路
     * @param blue
     * @return
     */
    public static int getAreaBroad(String blue){
        if(!StringUtils.hasLength(blue)){
            return 0;
        }
        if(Integer.valueOf(blue) % 3 == 0){
            return BlueBroadEnum.ZERO.getCode();
        }
        if(Integer.valueOf(blue) % 3 == 1){
            return BlueBroadEnum.ONE.getCode();
        }
        if(Integer.valueOf(blue) % 3 == 2){
            return BlueBroadEnum.TWO.getCode();
        }
        return 0;
    }
    //0路
    public static List<String> getAreaBroadZero(List<String> list){
        if(CollectionUtils.isEmpty(list)){
            return new ArrayList<>(1);
        }
        return list.stream().filter(v-> Integer.valueOf(v) % 3 == 0).collect(Collectors.toList());
    }
    //1路
    public static List<String> getAreaBroadOne(List<String> list){
        if(CollectionUtils.isEmpty(list)){
            return new ArrayList<>(1);
        }
        return list.stream().filter(v-> Integer.valueOf(v) % 3 == 1).collect(Collectors.toList());
    }
    //2路
    public static List<String> getAreaBroadTwo(List<String> list){
        if(CollectionUtils.isEmpty(list)){
            return new ArrayList<>(1);
        }
        return list.stream().filter(v-> Integer.valueOf(v) % 3 == 2).collect(Collectors.toList());
    }
    /**
     * 跨度
     * @param currentLotteryRed
     * @return
     */
    public static int calSpan(String currentLotteryRed){
        if(!StringUtils.hasLength(currentLotteryRed)){
            return 0;
        }
        String[] currentLotteryReds = currentLotteryRed.split(",");
        int lotteryMin = Integer.valueOf(currentLotteryReds[0]),lotteryMax= Integer.valueOf(currentLotteryReds[0]);
        for (String str: currentLotteryReds){
            if(lotteryMin >= Integer.valueOf(str)){
                lotteryMin = Integer.valueOf(str);
            }
            if(lotteryMax <= Integer.valueOf(str)){
                lotteryMax = Integer.valueOf(str);
            }
        }
        return (lotteryMax - lotteryMin);
    }

    /**
     * 是否连号
     * @param currentLotteryRed
     * @return
     */
    public static boolean enableEvenNo(String currentLotteryRed){
        if(!StringUtils.hasLength(currentLotteryRed)){
            return false;
        }
        List<String> list = Arrays.asList(currentLotteryRed.split(","));
        Collections.sort(list);
        int currentLottery = 0;
        for (String str: list){
            if(currentLottery != Integer.valueOf(str)){
                currentLottery = Integer.valueOf(str);
            }else{
                return true;
            }
            currentLottery++;
        }
        return false;
    }

    /**
     * 是否满足连号要求
     * 不能超过3个连号
     * true：满足   false：不满足
     * @param currentLotteryRed
     * @param count
     * @return
     */
    public static final boolean enableMeetEnvNo(String currentLotteryRed,int count){
        if(!StringUtils.hasLength(currentLotteryRed)){
            return false;
        }
        List<String> list = Arrays.asList(currentLotteryRed.split(","));
        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i);
            Integer strInt = com.pj.utils.StringUtils.convertToInteger(str);
            int amount = 1;
            for (int j = 1; j < list.size(); j++) {
                if(list.contains(BigDecimalUtils.format(strInt+j))){
                    amount++;
                }else{
                    break;
                }
            }
            if(amount > count){
                return false;
            }
        }
        return true;
    }

    /**
     * 是否满足在 和值范围内
     * @param currentLotteryRed
     * @param lotteryCalVo
     * @return
     */
    public static boolean enableMeetSum(String currentLotteryRed, LotteryCalVo lotteryCalVo){
        int calRedSum = RuleUtils.calRedSum(currentLotteryRed);
        return calRedSum >= lotteryCalVo.getMin() && calRedSum <= lotteryCalVo.getMax();
    }

    /**
     * 当前 期号 与上一期号比较 重复数不得超过 3个
     * @param currentLotteryRed
     * @param lottery
     * @param limitCount
     * @return
     */
    public static boolean enableMeetRepeatCount(String currentLotteryRed,Lottery lottery,int limitCount){
        if(!StringUtils.hasLength(currentLotteryRed) || lottery == null){
            return false;
        }
        String[] split = currentLotteryRed.split(",");
        List<String> list = Arrays.asList(lottery.getRed().split(","));
        int count = 0;
        for (int i = 0; i < split.length; i++) {
            if(list.contains(split[i])){
                count++;
            }
        }
        return count <= limitCount;
    }

    /**
     * 三期 中至少一个相同，最多不超过7个相同
     * @param currentLotteryRed
     * @param lotteryList
     * @return
     */
    public static boolean enableMeetTwo(String currentLotteryRed,List<Lottery> lotteryList){
        List<String> list = new ArrayList<>(3);
        list.addAll(com.pj.utils.StringUtils.convertToList(lotteryList.get(lotteryList.size()-2).getRed()));
        list.addAll(com.pj.utils.StringUtils.convertToList(lotteryList.get(lotteryList.size()-1).getRed()));
        list.addAll(com.pj.utils.StringUtils.convertToList(currentLotteryRed));
        Set<String> set = new HashSet<>(list);
        int i = list.size() - set.size();
        return i >= 1 && i <= 3;
    }

    /**
     * 是否满足和值情况
     * 查询历史所有和值理论与实际比例，
     * 不能超过理论值
     * @param list
     * @param currentLotteryRed
     * @return
     */
    public static boolean enableMeetSum(List<LotteryInterval> list, String currentLotteryRed){
        if(CollectionUtils.isEmpty(list)){
            return true;
        }
        int calRedSum = RuleUtils.calRedSum(currentLotteryRed);
        return !list.stream().anyMatch(v-> v.getIntervalMin() <= calRedSum && v.getIntervalMax() >= calRedSum);
    }

    public static void main(String[] args) {
        List<String> numbers = Arrays.asList("1", "3", "5", "7", "9","18");
        int consecutiveSets = countConsecutiveSets(numbers);
        System.out.println("连号数个数: " + consecutiveSets);
        int consecutiveInfo = getConsecutiveInfo(numbers);
        System.out.println(consecutiveInfo);
    }


}
