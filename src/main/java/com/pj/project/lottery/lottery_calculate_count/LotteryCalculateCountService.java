package com.pj.project.lottery.lottery_calculate_count;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.collect.Lists;
import com.pj.models.so.SoMap;
import com.pj.project.lottery.lottery_all.LotteryAll;
import com.pj.project.lottery.lottery_calculate_per.LotteryCalculatePer;
import com.pj.project.lottery.lottery_calculate_per.LotteryCalculatePerMapper;
import com.pj.project.lottery.unionLotto.enums.*;
import com.pj.project.lottery.unionLotto.utils.RuleUtils;
import com.pj.utils.IntegerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * Service: lottery_calculate_count -- 按照不同时间维度统计每个红蓝球情况
 * @author lizhihao 
 */
@Service
public class LotteryCalculateCountService {

	/** 底层 Mapper 对象 */
	@Autowired
	LotteryCalculateCountMapper lotteryCalculateCountMapper;
	@Autowired
	LotteryCalculatePerMapper lotteryCalculatePerMapper;

	/** 增 */
	int add(LotteryCalculateCount l){
		return lotteryCalculateCountMapper.add(l);
	}

	/** 删 */
	int delete(Long id){
		return lotteryCalculateCountMapper.delete(id);
	}

	/** 改 */
	int update(LotteryCalculateCount l){
		return lotteryCalculateCountMapper.update(l);
	}

	/** 查 */
	LotteryCalculateCount getById(Long id){
		return lotteryCalculateCountMapper.getById(id);
	}

	/** 查集合 - 根据条件（参数为空时代表忽略指定条件） */  
	List<LotteryCalculateCount> getList(SoMap so) {
		String calType = (String)so.get("calType");
		String sortType = "";
		if("1".equals(calType)){
			sortType = "2";
		}else if("2".equals(calType)){
			sortType = "3";
		} else if ("3".equals(calType)) {
			sortType = "4";
		} else if ("4".equals(calType)) {
			sortType = "5";
		}
		so.set("sortType", sortType);
		return lotteryCalculateCountMapper.getList(so);	
	}

	List<LotteryCalculateCountAvgVo> getAvgList(SoMap so) {
		String calType = (String)so.get("calType");
		if("1".equals(calType)){
			return lotteryCalculateCountMapper.getAvgListYear(so);
		}else if("2".equals(calType)){
			return lotteryCalculateCountMapper.getAvgListMonth(so);
		}
		return new ArrayList<>(1);
	}
	
	public void lotteryCalculateCount(){
		//删除数据
		lotteryCalculateCountMapper.deleteAll();

		//查询数据
		List<LotteryCalculatePer> lotteryCalculatePerMapperList = lotteryCalculatePerMapper.getList(new SoMap());
		if(CollectionUtils.isEmpty(lotteryCalculatePerMapperList)){
			throw new RuntimeException("没有数据!");
		}
		//按照code XXX0-XXX9分组
		Map<String,List<LotteryCalculatePer>> codeMap = new HashMap<>(100);
		List<LotteryCalculatePer> list = new ArrayList<>(10);
		StringBuffer sb = new StringBuffer();
		Map<String,LotteryCalculatePer> beforeMap = new HashMap<>(100);
		lotteryCalculatePerMapperList.sort((a,b)-> a.getCode().compareTo(b.getCode()));
		Stream.iterate(0, n -> n + 1).limit(lotteryCalculatePerMapperList.size()).forEach(i ->{
			LotteryCalculatePer lotteryCalculatePer = lotteryCalculatePerMapperList.get(i);
			String code = lotteryCalculatePer.getCode();
			String year = code.substring(0, 4);
			if(lotteryCalculatePer.getCode().endsWith("1")){
				if(!beforeMap.isEmpty() && !beforeMap.containsKey(year)){
					LotteryCalculatePer beforLcp = lotteryCalculatePerMapperList.get(i - 1);
					if(!beforLcp.getCode().endsWith("0")){
						sb.append("-").append(beforLcp.getCode());
						List<LotteryCalculatePer> perList = new ArrayList<>(list);
						codeMap.put(sb.toString(), perList);
						sb.delete(0, sb.length());
						list.clear();
					}
				}
				list.add(lotteryCalculatePer);
				sb.append(lotteryCalculatePer.getCode());
			}else if(lotteryCalculatePer.getCode().endsWith("0")){
				list.add(lotteryCalculatePer);
				sb.append("-").append(lotteryCalculatePer.getCode());
				List<LotteryCalculatePer> perList = new ArrayList<>(list);
				codeMap.put(sb.toString(), perList);
				sb.delete(0, sb.length());
				list.clear();
			}else{
				list.add(lotteryCalculatePer);
			}
			beforeMap.put(year,lotteryCalculatePer);
		});
		List<LotteryCalculateCount> codesLotteryCalculateCountList = getList(codeMap,CalTypeEnum.CODE.getCalType());
		lotteryCalculateCountMapper.batchInsertLotteryCalculateCount(codesLotteryCalculateCountList);
		//按照年份分组
		Map<Integer, List<LotteryCalculatePer>> yearList = lotteryCalculatePerMapperList.stream()
				.collect(Collectors.groupingBy(LotteryCalculatePer::getYear));
		List<LotteryCalculateCount> yearLotteryCalculateCountList = getList(yearList,CalTypeEnum.YEAR.getCalType());
		lotteryCalculateCountMapper.batchInsertLotteryCalculateCount(yearLotteryCalculateCountList);
		//按照年+月份分组
		Map<String, List<LotteryCalculatePer>> yearMonthList =
				lotteryCalculatePerMapperList.stream().collect(Collectors.groupingBy(v-> v.getYear()+"_"+v.getMonth()));
		List<LotteryCalculateCount> monthLotteryCalculateCountList = getList(yearMonthList,CalTypeEnum.MONTH.getCalType());
		lotteryCalculateCountMapper.batchInsertLotteryCalculateCount(monthLotteryCalculateCountList);
		//统计每周
		Map<String, List<LotteryCalculatePer>> weekList = lotteryCalculatePerMapperList.stream()
				.collect(Collectors.groupingBy(v-> v.getWeek()));
		List<LotteryCalculateCount> weekLotteryCalculateCountList = getList(weekList,CalTypeEnum.WEEK.getCalType());
		List<List<LotteryCalculateCount>> listList = Lists.partition(weekLotteryCalculateCountList, 1000);
		listList.stream().forEach(v->{
			lotteryCalculateCountMapper.batchInsertLotteryCalculateCount(v);
		});
		System.out.println("----------------");
	}

	public <T> List<LotteryCalculateCount> getList(Map<T, List<LotteryCalculatePer>> map,int calType){
		List<LotteryCalculateCount> list = new ArrayList<>(10);
		map.forEach((key,value) ->{
			LotteryCalculateCount l = new LotteryCalculateCount();
			Map<String,Integer> redMap = new HashMap<>(100);
			Map<String,Integer> blueMap = new HashMap<>(100);
			Map<String,Integer> redParityRatioMap = new HashMap<>(100);
			Map<String,Integer> redRangeMap = new HashMap<>(100);
			Map<String,Integer> blueBigSmallMap = new HashMap<>(100);
			Map<String,Integer> blueAreaMap = new HashMap<>(100);
			Map<String,Integer> blueParityRatioMap = new HashMap<>(100);
			Map<String,Integer> redSumMap = new HashMap<>(100);
			Map<Integer,Integer> maxConsecutiveNumbersMap = new HashMap<>(100);
			Map<Integer,Integer> consecutiveNumbersCountMap = new HashMap<>(100);
			value.stream().forEach(v->{
				String[] redArrays = v.getRed().split(",");
				redMap.put(redArrays[0], IntegerUtils.convertToInt(redMap.get(redArrays[0])) +1);
				redMap.put(redArrays[1], IntegerUtils.convertToInt(redMap.get(redArrays[1])) +1);
				redMap.put(redArrays[2], IntegerUtils.convertToInt(redMap.get(redArrays[2])) +1);
				redMap.put(redArrays[3], IntegerUtils.convertToInt(redMap.get(redArrays[3])) +1);
				redMap.put(redArrays[4], IntegerUtils.convertToInt(redMap.get(redArrays[4])) +1);
				redMap.put(redArrays[5], IntegerUtils.convertToInt(redMap.get(redArrays[5])) +1);
				blueMap.put(v.getBlue(), IntegerUtils.convertToInt(blueMap.get(v.getBlue())) +1);
				maxConsecutiveNumbersMap.put(v.getMaxConsecutiveNumbers(),
						IntegerUtils.convertToInt(maxConsecutiveNumbersMap.get(v.getMaxConsecutiveNumbers()))+1);
				consecutiveNumbersCountMap.put(v.getConsecutiveNumbersCount(),
						IntegerUtils.convertToInt(consecutiveNumbersCountMap.get(v.getConsecutiveNumbersCount()))+1);
				redParityRatioMap.put(v.getRedParityRatio(), IntegerUtils.convertToInt(redParityRatioMap.get(v.getRedParityRatio())) +1);
				redRangeMap.put(v.getRedRangeRatio(), IntegerUtils.convertToInt(redRangeMap.get(v.getRedRangeRatio())) +1);
				blueBigSmallMap.put(v.getBlueBigSmall(), IntegerUtils.convertToInt(blueBigSmallMap.get(v.getBlueBigSmall())) +1);
				blueAreaMap.put(v.getBlueRange()+"", IntegerUtils.convertToInt(blueAreaMap.get(v.getBlueRange()+"")) +1);
				blueParityRatioMap.put(v.getBlueParity(), IntegerUtils.convertToInt(blueParityRatioMap.get(v.getBlueParity())) +1);
				int redSum = IntegerUtils.convertToInt(redArrays[0]) + IntegerUtils.convertToInt(redArrays[1])
						+ IntegerUtils.convertToInt(redArrays[2]) + IntegerUtils.convertToInt(redArrays[3])
						+ IntegerUtils.convertToInt(redArrays[4]) + IntegerUtils.convertToInt(redArrays[5]);
				RuleUtils.RED_SUM_LIST.stream().forEach(r ->{
					String[] redSumArrays = r.split("_");
					if(redSum >= Integer.valueOf(redSumArrays[0]) && redSum <= Integer.valueOf(redSumArrays[1])){
						redSumMap.put(r, IntegerUtils.convertToInt(redSumMap.get(r)) +1);
					}
				});
			});
			if(CalTypeEnum.YEAR.getCalType() == calType){
				l.setYear((Integer) key);
			} else if (CalTypeEnum.MONTH.getCalType() == calType) {
				String[] split = ((String) key).split("_");
				l.setYear(Integer.valueOf(split[0]));
				l.setMonth(Integer.valueOf(split[1]));
			} else if (CalTypeEnum.WEEK.getCalType() == calType) {
				String[] split = ((String) key).split("-");
				l.setYear(Integer.valueOf(split[0]));
				l.setWeek(Integer.valueOf(split[1]));
			}else if (CalTypeEnum.CODE.getCalType() == calType) {
				l.setCodes((String) key);
			}
			l.setBlue1(blueMap.get(RuleUtils.AREA_ONE.get(0)));
			l.setBlue2(blueMap.get(RuleUtils.AREA_ONE.get(1)));
			l.setBlue3(blueMap.get(RuleUtils.AREA_ONE.get(2)));
			l.setBlue4(blueMap.get(RuleUtils.AREA_ONE.get(3)));
			l.setBlue5(blueMap.get(RuleUtils.AREA_TWO.get(0)));
			l.setBlue6(blueMap.get(RuleUtils.AREA_TWO.get(1)));
			l.setBlue7(blueMap.get(RuleUtils.AREA_TWO.get(2)));
			l.setBlue8(blueMap.get(RuleUtils.AREA_TWO.get(3)));
			l.setBlue9(blueMap.get(RuleUtils.AREA_THREE.get(0)));
			l.setBlue10(blueMap.get(RuleUtils.AREA_THREE.get(1)));
			l.setBlue11(blueMap.get(RuleUtils.AREA_THREE.get(2)));
			l.setBlue12(blueMap.get(RuleUtils.AREA_THREE.get(3)));
			l.setBlue13(blueMap.get(RuleUtils.AREA_FOUR.get(0)));
			l.setBlue14(blueMap.get(RuleUtils.AREA_FOUR.get(1)));
			l.setBlue15(blueMap.get(RuleUtils.AREA_FOUR.get(2)));
			l.setBlue16(blueMap.get(RuleUtils.AREA_FOUR.get(3)));

			l.setRed1(redMap.get(RuleUtils.PARTITION_ONE.get(0)));
			l.setRed2(redMap.get(RuleUtils.PARTITION_ONE.get(1)));
			l.setRed3(redMap.get(RuleUtils.PARTITION_ONE.get(2)));
			l.setRed4(redMap.get(RuleUtils.PARTITION_ONE.get(3)));
			l.setRed5(redMap.get(RuleUtils.PARTITION_ONE.get(4)));
			l.setRed6(redMap.get(RuleUtils.PARTITION_ONE.get(5)));
			l.setRed7(redMap.get(RuleUtils.PARTITION_ONE.get(6)));
			l.setRed8(redMap.get(RuleUtils.PARTITION_ONE.get(7)));
			l.setRed9(redMap.get(RuleUtils.PARTITION_ONE.get(8)));
			l.setRed10(redMap.get(RuleUtils.PARTITION_ONE.get(9)));
			l.setRed11(redMap.get(RuleUtils.PARTITION_ONE.get(10)));
			l.setRed12(redMap.get(RuleUtils.PARTITION_TWO.get(0)));
			l.setRed13(redMap.get(RuleUtils.PARTITION_TWO.get(1)));
			l.setRed14(redMap.get(RuleUtils.PARTITION_TWO.get(2)));
			l.setRed15(redMap.get(RuleUtils.PARTITION_TWO.get(3)));
			l.setRed16(redMap.get(RuleUtils.PARTITION_TWO.get(4)));
			l.setRed17(redMap.get(RuleUtils.PARTITION_TWO.get(5)));
			l.setRed18(redMap.get(RuleUtils.PARTITION_TWO.get(6)));
			l.setRed19(redMap.get(RuleUtils.PARTITION_TWO.get(7)));
			l.setRed20(redMap.get(RuleUtils.PARTITION_TWO.get(8)));
			l.setRed21(redMap.get(RuleUtils.PARTITION_TWO.get(9)));
			l.setRed22(redMap.get(RuleUtils.PARTITION_TWO.get(10)));
			l.setRed23(redMap.get(RuleUtils.PARTITION_THREE.get(0)));
			l.setRed24(redMap.get(RuleUtils.PARTITION_THREE.get(1)));
			l.setRed25(redMap.get(RuleUtils.PARTITION_THREE.get(2)));
			l.setRed26(redMap.get(RuleUtils.PARTITION_THREE.get(3)));
			l.setRed27(redMap.get(RuleUtils.PARTITION_THREE.get(4)));
			l.setRed28(redMap.get(RuleUtils.PARTITION_THREE.get(5)));
			l.setRed29(redMap.get(RuleUtils.PARTITION_THREE.get(6)));
			l.setRed30(redMap.get(RuleUtils.PARTITION_THREE.get(7)));
			l.setRed31(redMap.get(RuleUtils.PARTITION_THREE.get(8)));
			l.setRed32(redMap.get(RuleUtils.PARTITION_THREE.get(9)));
			l.setRed33(redMap.get(RuleUtils.PARTITION_THREE.get(10)));

			l.setCalType(calType);
			l.setRedParityRatio06(redParityRatioMap.get(ParityRatioEnum.ZERO_SIX.getCode()));
			l.setRedParityRatio60(redParityRatioMap.get(ParityRatioEnum.SIX_ZERO.getCode()));
			l.setRedParityRatio24(redParityRatioMap.get(ParityRatioEnum.TWO_FOUR.getCode()));
			l.setRedParityRatio42(redParityRatioMap.get(ParityRatioEnum.FOUR_TWO.getCode()));
			l.setRedParityRatio15(redParityRatioMap.get(ParityRatioEnum.ONE_FIVE.getCode()));
			l.setRedParityRatio51(redParityRatioMap.get(ParityRatioEnum.FIVE_ONE.getCode()));
			l.setRedParityRatio33(redParityRatioMap.get(ParityRatioEnum.THREE_THREE.getCode()));

			l.setRedRange114(redRangeMap.get(RangeEnum.ONE_ONE_FOUR.getCode()));
			l.setRedRange411(redRangeMap.get(RangeEnum.FOUR_ONE_ONE.getCode()));
			l.setRedRange141(redRangeMap.get(RangeEnum.ONE_FOUR_ONE.getCode()));
			l.setRedRange231(redRangeMap.get(RangeEnum.TWO_THREE_ONE.getCode()));
			l.setRedRange222(redRangeMap.get(RangeEnum.TWO_TWO_TWO.getCode()));
			l.setRedRange321(redRangeMap.get(RangeEnum.THREE_TWO_ONE.getCode()));
			l.setRedRange123(redRangeMap.get(RangeEnum.ONE_TWO_THREE.getCode()));
			l.setRedRange204(redRangeMap.get(RangeEnum.TWO_ZERO_FOUR.getCode()));
			l.setRedRange240(redRangeMap.get(RangeEnum.TWO_FOUR_ZERO.getCode()));
			l.setRedRange402(redRangeMap.get(RangeEnum.FOUR_ZERO_TWO.getCode()));
			l.setRedRange420(redRangeMap.get(RangeEnum.FOUR_TWO_ZERO.getCode()));
			l.setRedRange312(redRangeMap.get(RangeEnum.THREE_ONE_TWO.getCode()));
			l.setRedRange213(redRangeMap.get(RangeEnum.TWO_ONE_THREE.getCode()));

			l.setBlueSmall(blueBigSmallMap.get(BlueBigSmallEnum.SMALL.getCode()));
			l.setBlueBig(blueBigSmallMap.get(BlueBigSmallEnum.BIG.getCode()));
			l.setBlueAreaOne(blueAreaMap.get(BlueAreaEnum.ONE.getCode()+""));
			l.setBlueAreaTwo(blueAreaMap.get(BlueAreaEnum.TWO.getCode()+""));
			l.setBlueAreaThree(blueAreaMap.get(BlueAreaEnum.THREE.getCode()+""));
			l.setBlueAreaFour(blueAreaMap.get(BlueAreaEnum.FOUR.getCode()+""));
			l.setBlueParity(blueParityRatioMap.get(BlueParityRatioEnum.PARITY.getCode()));
			l.setBlueRatio(blueParityRatioMap.get(BlueParityRatioEnum.RATIO.getCode()));

			l.setRed2160(redSumMap.get(RuleUtils.RED_SUM_LIST.get(0)));
			l.setRed7378(redSumMap.get(RuleUtils.RED_SUM_LIST.get(1)));
			l.setRed6166(redSumMap.get(RuleUtils.RED_SUM_LIST.get(2)));
			l.setRed103108(redSumMap.get(RuleUtils.RED_SUM_LIST.get(3)));
			l.setRed9196(redSumMap.get(RuleUtils.RED_SUM_LIST.get(4)));
			l.setRed7984(redSumMap.get(RuleUtils.RED_SUM_LIST.get(5)));
			l.setRed6772(redSumMap.get(RuleUtils.RED_SUM_LIST.get(6)));
			l.setRed109114(redSumMap.get(RuleUtils.RED_SUM_LIST.get(7)));
			l.setRed115120(redSumMap.get(RuleUtils.RED_SUM_LIST.get(8)));
			l.setRed133138(redSumMap.get(RuleUtils.RED_SUM_LIST.get(9)));
			l.setRed97102(redSumMap.get(RuleUtils.RED_SUM_LIST.get(10)));
			l.setRed139144(redSumMap.get(RuleUtils.RED_SUM_LIST.get(11)));
			l.setRed127132(redSumMap.get(RuleUtils.RED_SUM_LIST.get(12)));
			l.setRed121126(redSumMap.get(RuleUtils.RED_SUM_LIST.get(13)));
			l.setRed145183(redSumMap.get(RuleUtils.RED_SUM_LIST.get(14)));

			l.setMaxConsecutiveNumbers1(maxConsecutiveNumbersMap.get(1));
			l.setMaxConsecutiveNumbers2(maxConsecutiveNumbersMap.get(2));
			l.setMaxConsecutiveNumbers3(maxConsecutiveNumbersMap.get(3));
			l.setMaxConsecutiveNumbers4(maxConsecutiveNumbersMap.get(4));
			l.setMaxConsecutiveNumbers5(maxConsecutiveNumbersMap.get(5));
			l.setConsecutiveNumbersCount0(consecutiveNumbersCountMap.get(0));
			l.setConsecutiveNumbersCount1(consecutiveNumbersCountMap.get(1));
			l.setConsecutiveNumbersCount2(consecutiveNumbersCountMap.get(2));
			list.add(l);
		});
		return list;
	}

	public LotteryCalculateCount getMaxLotteryCalculateCount(int calType){
		return lotteryCalculateCountMapper.getMaxLotteryCalculateCount(calType);
	}



}
