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
		lotteryCalculatePerMapperList.sort((a,b)-> a.getCode().compareTo(b.getCode()));

		batchInsertLotteryCalculateCount(lotteryCalculatePerMapperList);
		System.out.println("----------------");
	}

	public void batchInsertLotteryCalculateCount(List<LotteryCalculatePer> lotteryCalculatePerMapperList) {
		//按照code XXX0-XXX9分组
		Map<String,List<LotteryCalculatePer>> codeMap = new HashMap<>(100);
		List<LotteryCalculatePer> list = new ArrayList<>(10);
		StringBuffer sb = new StringBuffer();
		Map<String,LotteryCalculatePer> beforeMap = new HashMap<>(100);
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
			l.setBlue1(IntegerUtils.convertToInt(blueMap.get(RuleUtils.AREA_ONE.get(0))));
			l.setBlue2(IntegerUtils.convertToInt(blueMap.get(RuleUtils.AREA_ONE.get(1))));
			l.setBlue3(IntegerUtils.convertToInt(blueMap.get(RuleUtils.AREA_ONE.get(2))));
			l.setBlue4(IntegerUtils.convertToInt(blueMap.get(RuleUtils.AREA_ONE.get(3))));
			l.setBlue5(IntegerUtils.convertToInt(blueMap.get(RuleUtils.AREA_TWO.get(0))));
			l.setBlue6(IntegerUtils.convertToInt(blueMap.get(RuleUtils.AREA_TWO.get(1))));
			l.setBlue7(IntegerUtils.convertToInt(blueMap.get(RuleUtils.AREA_TWO.get(2))));
			l.setBlue8(IntegerUtils.convertToInt(blueMap.get(RuleUtils.AREA_TWO.get(3))));
			l.setBlue9(IntegerUtils.convertToInt(blueMap.get(RuleUtils.AREA_THREE.get(0))));
			l.setBlue10(IntegerUtils.convertToInt(blueMap.get(RuleUtils.AREA_THREE.get(1))));
			l.setBlue11(IntegerUtils.convertToInt(blueMap.get(RuleUtils.AREA_THREE.get(2))));
			l.setBlue12(IntegerUtils.convertToInt(blueMap.get(RuleUtils.AREA_THREE.get(3))));
			l.setBlue13(IntegerUtils.convertToInt(blueMap.get(RuleUtils.AREA_FOUR.get(0))));
			l.setBlue14(IntegerUtils.convertToInt(blueMap.get(RuleUtils.AREA_FOUR.get(1))));
			l.setBlue15(IntegerUtils.convertToInt(blueMap.get(RuleUtils.AREA_FOUR.get(2))));
			l.setBlue16(IntegerUtils.convertToInt(blueMap.get(RuleUtils.AREA_FOUR.get(3))));

			l.setRed1(IntegerUtils.convertToInt(redMap.get(RuleUtils.PARTITION_ONE.get(0))));
			l.setRed2(IntegerUtils.convertToInt(redMap.get(RuleUtils.PARTITION_ONE.get(1))));
			l.setRed3(IntegerUtils.convertToInt(redMap.get(RuleUtils.PARTITION_ONE.get(2))));
			l.setRed4(IntegerUtils.convertToInt(redMap.get(RuleUtils.PARTITION_ONE.get(3))));
			l.setRed5(IntegerUtils.convertToInt(redMap.get(RuleUtils.PARTITION_ONE.get(4))));
			l.setRed6(IntegerUtils.convertToInt(redMap.get(RuleUtils.PARTITION_ONE.get(5))));
			l.setRed7(IntegerUtils.convertToInt(redMap.get(RuleUtils.PARTITION_ONE.get(6))));
			l.setRed8(IntegerUtils.convertToInt(redMap.get(RuleUtils.PARTITION_ONE.get(7))));
			l.setRed9(IntegerUtils.convertToInt(redMap.get(RuleUtils.PARTITION_ONE.get(8))));
			l.setRed10(IntegerUtils.convertToInt(redMap.get(RuleUtils.PARTITION_ONE.get(9))));
			l.setRed11(IntegerUtils.convertToInt(redMap.get(RuleUtils.PARTITION_ONE.get(10))));
			l.setRed12(IntegerUtils.convertToInt(redMap.get(RuleUtils.PARTITION_TWO.get(0))));
			l.setRed13(IntegerUtils.convertToInt(redMap.get(RuleUtils.PARTITION_TWO.get(1))));
			l.setRed14(IntegerUtils.convertToInt(redMap.get(RuleUtils.PARTITION_TWO.get(2))));
			l.setRed15(IntegerUtils.convertToInt(redMap.get(RuleUtils.PARTITION_TWO.get(3))));
			l.setRed16(IntegerUtils.convertToInt(redMap.get(RuleUtils.PARTITION_TWO.get(4))));
			l.setRed17(IntegerUtils.convertToInt(redMap.get(RuleUtils.PARTITION_TWO.get(5))));
			l.setRed18(IntegerUtils.convertToInt(redMap.get(RuleUtils.PARTITION_TWO.get(6))));
			l.setRed19(IntegerUtils.convertToInt(redMap.get(RuleUtils.PARTITION_TWO.get(7))));
			l.setRed20(IntegerUtils.convertToInt(redMap.get(RuleUtils.PARTITION_TWO.get(8))));
			l.setRed21(IntegerUtils.convertToInt(redMap.get(RuleUtils.PARTITION_TWO.get(9))));
			l.setRed22(IntegerUtils.convertToInt(redMap.get(RuleUtils.PARTITION_TWO.get(10))));
			l.setRed23(IntegerUtils.convertToInt(redMap.get(RuleUtils.PARTITION_THREE.get(0))));
			l.setRed24(IntegerUtils.convertToInt(redMap.get(RuleUtils.PARTITION_THREE.get(1))));
			l.setRed25(IntegerUtils.convertToInt(redMap.get(RuleUtils.PARTITION_THREE.get(2))));
			l.setRed26(IntegerUtils.convertToInt(redMap.get(RuleUtils.PARTITION_THREE.get(3))));
			l.setRed27(IntegerUtils.convertToInt(redMap.get(RuleUtils.PARTITION_THREE.get(4))));
			l.setRed28(IntegerUtils.convertToInt(redMap.get(RuleUtils.PARTITION_THREE.get(5))));
			l.setRed29(IntegerUtils.convertToInt(redMap.get(RuleUtils.PARTITION_THREE.get(6))));
			l.setRed30(IntegerUtils.convertToInt(redMap.get(RuleUtils.PARTITION_THREE.get(7))));
			l.setRed31(IntegerUtils.convertToInt(redMap.get(RuleUtils.PARTITION_THREE.get(8))));
			l.setRed32(IntegerUtils.convertToInt(redMap.get(RuleUtils.PARTITION_THREE.get(9))));
			l.setRed33(IntegerUtils.convertToInt(redMap.get(RuleUtils.PARTITION_THREE.get(10))));

			l.setCalType(calType);
			l.setRedParityRatio06(IntegerUtils.convertToInt(redParityRatioMap.get(ParityRatioEnum.ZERO_SIX.getCode())));
			l.setRedParityRatio60(IntegerUtils.convertToInt(redParityRatioMap.get(ParityRatioEnum.SIX_ZERO.getCode())));
			l.setRedParityRatio24(IntegerUtils.convertToInt(redParityRatioMap.get(ParityRatioEnum.TWO_FOUR.getCode())));
			l.setRedParityRatio42(IntegerUtils.convertToInt(redParityRatioMap.get(ParityRatioEnum.FOUR_TWO.getCode())));
			l.setRedParityRatio15(IntegerUtils.convertToInt(redParityRatioMap.get(ParityRatioEnum.ONE_FIVE.getCode())));
			l.setRedParityRatio51(IntegerUtils.convertToInt(redParityRatioMap.get(ParityRatioEnum.FIVE_ONE.getCode())));
			l.setRedParityRatio33(IntegerUtils.convertToInt(redParityRatioMap.get(ParityRatioEnum.THREE_THREE.getCode())));

			l.setRedRange114(IntegerUtils.convertToInt(redRangeMap.get(RangeEnum.ONE_ONE_FOUR.getCode())));
			l.setRedRange411(IntegerUtils.convertToInt(redRangeMap.get(RangeEnum.FOUR_ONE_ONE.getCode())));
			l.setRedRange141(IntegerUtils.convertToInt(redRangeMap.get(RangeEnum.ONE_FOUR_ONE.getCode())));
			l.setRedRange231(IntegerUtils.convertToInt(redRangeMap.get(RangeEnum.TWO_THREE_ONE.getCode())));
			l.setRedRange222(IntegerUtils.convertToInt(redRangeMap.get(RangeEnum.TWO_TWO_TWO.getCode())));
			l.setRedRange321(IntegerUtils.convertToInt(redRangeMap.get(RangeEnum.THREE_TWO_ONE.getCode())));
			l.setRedRange123(IntegerUtils.convertToInt(redRangeMap.get(RangeEnum.ONE_TWO_THREE.getCode())));
			l.setRedRange204(IntegerUtils.convertToInt(redRangeMap.get(RangeEnum.TWO_ZERO_FOUR.getCode())));
			l.setRedRange240(IntegerUtils.convertToInt(redRangeMap.get(RangeEnum.TWO_FOUR_ZERO.getCode())));
			l.setRedRange402(IntegerUtils.convertToInt(redRangeMap.get(RangeEnum.FOUR_ZERO_TWO.getCode())));
			l.setRedRange420(IntegerUtils.convertToInt(redRangeMap.get(RangeEnum.FOUR_TWO_ZERO.getCode())));
			l.setRedRange312(IntegerUtils.convertToInt(redRangeMap.get(RangeEnum.THREE_ONE_TWO.getCode())));
			l.setRedRange213(IntegerUtils.convertToInt(redRangeMap.get(RangeEnum.TWO_ONE_THREE.getCode())));

			l.setBlueSmall(IntegerUtils.convertToInt(blueBigSmallMap.get(BlueBigSmallEnum.SMALL.getCode())));
			l.setBlueBig(IntegerUtils.convertToInt(blueBigSmallMap.get(BlueBigSmallEnum.BIG.getCode())));
			l.setBlueAreaOne(IntegerUtils.convertToInt(blueAreaMap.get(BlueAreaEnum.ONE.getCode()+"")));
			l.setBlueAreaTwo(IntegerUtils.convertToInt(blueAreaMap.get(BlueAreaEnum.TWO.getCode()+"")));
			l.setBlueAreaThree(IntegerUtils.convertToInt(blueAreaMap.get(BlueAreaEnum.THREE.getCode()+"")));
			l.setBlueAreaFour(IntegerUtils.convertToInt(blueAreaMap.get(BlueAreaEnum.FOUR.getCode()+"")));
			l.setBlueParity(IntegerUtils.convertToInt(blueParityRatioMap.get(BlueParityRatioEnum.PARITY.getCode())));
			l.setBlueRatio(IntegerUtils.convertToInt(blueParityRatioMap.get(BlueParityRatioEnum.RATIO.getCode())));

			l.setRed2160(IntegerUtils.convertToInt(redSumMap.get(RuleUtils.RED_SUM_LIST.get(0))));
			l.setRed7378(IntegerUtils.convertToInt(redSumMap.get(RuleUtils.RED_SUM_LIST.get(1))));
			l.setRed6166(IntegerUtils.convertToInt(redSumMap.get(RuleUtils.RED_SUM_LIST.get(2))));
			l.setRed103108(IntegerUtils.convertToInt(redSumMap.get(RuleUtils.RED_SUM_LIST.get(3))));
			l.setRed9196(IntegerUtils.convertToInt(redSumMap.get(RuleUtils.RED_SUM_LIST.get(4))));
			l.setRed7984(IntegerUtils.convertToInt(redSumMap.get(RuleUtils.RED_SUM_LIST.get(5))));
			l.setRed6772(IntegerUtils.convertToInt(redSumMap.get(RuleUtils.RED_SUM_LIST.get(6))));
			l.setRed109114(IntegerUtils.convertToInt(redSumMap.get(RuleUtils.RED_SUM_LIST.get(7))));
			l.setRed115120(IntegerUtils.convertToInt(redSumMap.get(RuleUtils.RED_SUM_LIST.get(8))));
			l.setRed133138(IntegerUtils.convertToInt(redSumMap.get(RuleUtils.RED_SUM_LIST.get(9))));
			l.setRed97102(IntegerUtils.convertToInt(redSumMap.get(RuleUtils.RED_SUM_LIST.get(10))));
			l.setRed139144(IntegerUtils.convertToInt(redSumMap.get(RuleUtils.RED_SUM_LIST.get(11))));
			l.setRed127132(IntegerUtils.convertToInt(redSumMap.get(RuleUtils.RED_SUM_LIST.get(12))));
			l.setRed121126(IntegerUtils.convertToInt(redSumMap.get(RuleUtils.RED_SUM_LIST.get(13))));
			l.setRed145183(IntegerUtils.convertToInt(redSumMap.get(RuleUtils.RED_SUM_LIST.get(14))));

			l.setMaxConsecutiveNumbers1(IntegerUtils.convertToInt(maxConsecutiveNumbersMap.get(1)));
			l.setMaxConsecutiveNumbers2(IntegerUtils.convertToInt(maxConsecutiveNumbersMap.get(2)));
			l.setMaxConsecutiveNumbers3(IntegerUtils.convertToInt(maxConsecutiveNumbersMap.get(3)));
			l.setMaxConsecutiveNumbers4(IntegerUtils.convertToInt(maxConsecutiveNumbersMap.get(4)));
			l.setMaxConsecutiveNumbers5(IntegerUtils.convertToInt(maxConsecutiveNumbersMap.get(5)));
			l.setConsecutiveNumbersCount0(IntegerUtils.convertToInt(consecutiveNumbersCountMap.get(0)));
			l.setConsecutiveNumbersCount1(IntegerUtils.convertToInt(consecutiveNumbersCountMap.get(1)));
			l.setConsecutiveNumbersCount2(IntegerUtils.convertToInt(consecutiveNumbersCountMap.get(2)));
			list.add(l);
		});
		return list;
	}

	public LotteryCalculateCount getMaxLotteryCalculateCount(int calType){
		return lotteryCalculateCountMapper.getMaxLotteryCalculateCount(calType);
	}



}
