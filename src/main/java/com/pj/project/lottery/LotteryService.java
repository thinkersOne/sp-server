package com.pj.project.lottery;

import java.util.*;
import java.util.stream.Collectors;

import cn.hutool.json.JSONUtil;
import com.pj.current.enums.LotteryTypeEnum;
import com.pj.models.so.SoMap;
import com.pj.project.lottery.lottery_calculate_count.LotteryCalculateCount;
import com.pj.project.lottery.lottery_calculate_count.LotteryCalculateCountMapper;
import com.pj.project.lottery.lottery_calculate_per.LotteryCalculatePer;
import com.pj.project.lottery.lottery_calculate_per.LotteryCalculatePerMapper;
import com.pj.project.lottery.lottery_config.LotteryConfig;
import com.pj.project.lottery.lottery_config.LotteryConfigMapper;
import com.pj.project.lottery.lottery_select.LotterySelectCodesDTO;
import com.pj.project.lottery.lottery_select.LotterySelectService;
import com.pj.project.lottery.unionLotto.*;
import com.pj.project.lottery.unionLotto.domain.ConvertDoubleSpheresReq;
import com.pj.project.lottery.unionLotto.domain.Lottery;
import com.pj.project.lottery.unionLotto.enums.CalTypeEnum;
import com.pj.project.lottery.unionLotto.enums.ParityRatioEnum;
import com.pj.project.lottery.unionLotto.enums.RangeEnum;
import com.pj.project.lottery.unionLotto.utils.PersonalLawUtils;
import com.pj.project.lottery.unionLotto.utils.RuleUtils;
import com.pj.utils.FileGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * Service: lottery -- 
 * @author lizhihao 
 */
@Service
@Slf4j
public class LotteryService {

	/** 底层 Mapper 对象 */
	@Autowired
	LotteryMapper lotteryMapper;
	@Autowired
	LotteryCalculatePerMapper lotteryCalculatePerMapper;
	@Autowired
	LotteryCalculateCountMapper lotteryCalculateCountMapper;
	@Autowired
	LotterySelectService lotterySelectService;
	@Autowired
	LotteryConfigMapper lotteryConfigMapper;
	@Autowired
	UnionLotto unionLotto;

	/** 增 */
	int add(Lottery l){
		return lotteryMapper.add(l);
	}

	/** 删 */
	int delete(String code){
		return lotteryMapper.delete(code);
	}

	/** 改 */
	int update(Lottery l){
		return lotteryMapper.update(l);
	}

	/** 查 */
	Lottery getById(String code){
		return lotteryMapper.getById(code);
	}

	/** 查集合 - 根据条件（参数为空时代表忽略指定条件） */  
	List<Lottery> getList(SoMap so) {
		return lotteryMapper.getList(so);
	}

	public void syncLotterydata(LotteryParameter lotteryParameter){
		lotteryMapper.deleteAll();

		if(LotteryTypeEnum.UNION_LOTTO.getType() == lotteryParameter.getType()){
			lotteryParameter.setName(LotteryTypeEnum.UNION_LOTTO.getCode());
			//查询数据
			List<Lottery> lotterys = unionLotto.getLotterys(lotteryParameter);
			log.info(JSONUtil.toJsonStr(lotterys));
			lotteryMapper.batchInsertLottery(lotterys);
		}
	}

	/**
	 * 获取最终结果
	 * @param req
	 * @return
	 */
	public List<String> convertDoubleSpheres(ConvertDoubleSpheresReq req) {
		List<String> list = RuleUtils.getAllGenerateCompose(Arrays.asList(
				RuleUtils.getChooseLotteryRed(req).split(",")));
		//按照app上所选的进行过滤，不超过5个相同
		if(!CollectionUtils.isEmpty(req.getLotteryReds())){
			list = RuleUtils.filterLotterysAmount(list, req.getLotteryReds(), 3);
		}
		//根据指定规则获取红球
		List<String> filterLotterys = filterLotterys(list,req.getFilterRed());
		//对结果 按照一定规律 进行筛选
		//查询是否存在当前期号数据
		Lottery currentLottery = lotteryMapper.getCurrentLottery();
		if(currentLottery != null){
			req.setCurrentLotteryRed(currentLottery.getRed());
		}
		List<String> result = PersonalLawUtils.filterByPersonalLaw(filterLotterys, req);
		List<String> finalFilterList = new ArrayList<>(result);
//        List<String> finalFilterList = RuleUtils.filterLotterysAmount(result, 3);
		//蓝球
		List<String> blueResult =
				RuleUtils.getParity(RuleUtils.getBlueAreaThree(RuleUtils.getBlueBig())
				);
		List<String> collect = finalFilterList.stream().map(v ->
				v + "," + blueResult.toString()
		).collect(Collectors.toList());
		return collect;
	}

	/**
	 * 从已选的红球中过滤出不满足条件的红球组合
	 * @param chooseLotterys
	 * @return
	 */
	public List<String> filterLotterys(List<String> chooseLotterys,String filterRed){
		if(CollectionUtils.isEmpty(chooseLotterys)){
			return new ArrayList<>(1);
		}
		//策略
		List<String> result = getStrings(chooseLotterys);
		//筛选出 1连号
		result = RuleUtils.filterEvenNoCount(result,0);
		return result;
	}


	private List<String> getStrings(List<String> filterLotterys) {
		//2:3:1 或 1:2:3 或 3:2:1   连号   4:2 或 3:3
		List<String> filterEvenNoYes = RuleUtils.filterEvenNoNo(filterLotterys);
		Set<String> set = new HashSet<>();
//        List<String> list1 = RuleUtils.filterParityRatio(filterEvenNoYes, ParityRatioEnum.FOUR_TWO.getCode());
//        List<String> list2 = RuleUtils.filterParityRatio(filterEvenNoYes, ParityRatioEnum.THREE_THREE.getCode());
		List<String> list3 = RuleUtils.filterParityRatio(filterEvenNoYes, ParityRatioEnum.TWO_FOUR.getCode());
//        List<String> list4 = RuleUtils.filterParityRatio(filterEvenNoYes, ParityRatioEnum.FIVE_ONE.getCode());
//        set.addAll(list1);
//        set.addAll(list2);
		set.addAll(list3);
//        set.addAll(list4);
		Set<String> set1 = new HashSet<>();
		List<String> convertList = new ArrayList<>(set);
//        List<String> list11 = RuleUtils.filterRange(convertList, RangeEnum.ONE_THREE_TWO.getCode());
//        List<String> list12 = RuleUtils.filterRange(convertList, RangeEnum.TWO_TWO_TWO.getCode());
//        List<String> list13 = RuleUtils.filterRange(convertList, RangeEnum.TWO_THREE_ONE.getCode());
//        List<String> list14 = RuleUtils.filterRange(convertList, RangeEnum.ZERO_FIVE_ONE.getCode());
//        List<String> list15 = RuleUtils.filterRange(convertList, RangeEnum.THREE_TWO_ONE.getCode());
		List<String> list16 = RuleUtils.filterRange(convertList, RangeEnum.ONE_TWO_THREE.getCode());
//        List<String> list17 = RuleUtils.filterRange(convertList, RangeEnum.TWO_ONE_THREE.getCode());
//        set1.addAll(list11);
//        set1.addAll(list12);
//        set1.addAll(list13);
//        set1.addAll(list14);
//        set1.addAll(list15);
		set1.addAll(list16);
//        set1.addAll(list17);
		List<String> result = new ArrayList<>(5000);
		result.addAll(set1);
		return result;

//        return filterLotterys;
	}

	/**
	 * 计算红球 按照 consecutiveNumber个连续红球出现的情况统计
	 * @param consecutiveNumber
	 * @return
	 */
	public List<Lottery> getLotteryByRed(int consecutiveNumber){
		List<Lottery> result = new ArrayList<>(30);
		for (int i = 0; i < RuleUtils.PARTITION_ONE.size() - consecutiveNumber; i++) {
			result.addAll(lotteryMapper.getLotteryByRed(getRed(consecutiveNumber, i)));
		}
		for (int i = 0; i < RuleUtils.PARTITION_TWO.size() - consecutiveNumber; i++) {
			result.addAll(lotteryMapper.getLotteryByRed(getRed(consecutiveNumber, i)));
		}
		for (int i = 0; i < RuleUtils.PARTITION_THREE.size() - consecutiveNumber; i++) {
			result.addAll(lotteryMapper.getLotteryByRed(getRed(consecutiveNumber, i)));
		}
		log.info("共有:"+ result.size() +"条连续"+consecutiveNumber+"个号码的数据");
		String codes = result.stream().map(v -> v.code).collect(Collectors.joining(","));
		log.info("连续"+consecutiveNumber+"个号码的数据期号:"+ codes);
		return result;
	}

	public List<String> lotteryConfig(LotteryParameter lotteryParameter){
		//年
		LotteryCalculateCount maxLotteryCalculateCount =
				lotteryCalculateCountMapper.getMaxLotteryCalculateCount(lotteryParameter.getType());
		SoMap soMap = new SoMap();
		soMap.set("type",lotteryParameter.getType());
		//查询配置
		List<LotteryConfig> list = lotteryConfigMapper.getList(soMap);
		if(CollectionUtils.isEmpty(list)){
			log.warn("没有配置年份计算规则!");
			return new ArrayList<>();
		}
		LotteryConfig lotteryConfig = list.get(0);
		//当前 年
		soMap.set("calType",lotteryParameter.getType());
		soMap.set("sortType",lotteryParameter.getOrderBy());
		List<LotteryCalculateCount> lotteryCalculateCountList = lotteryCalculateCountMapper.getList(soMap);
		LotteryCalculateCount lotteryCalculateCount = lotteryCalculateCountList.get(0);
		//筛选红球
		List<String> redList = getRedList(maxLotteryCalculateCount, lotteryConfig, lotteryCalculateCount);
		//筛选 红球奇偶比
		List<String> redParityRatioList = getRedParityRatioList(maxLotteryCalculateCount, lotteryConfig, lotteryCalculateCount);
		//筛选 红球区间比
		List<String> redRangeList = getRedRangeList(maxLotteryCalculateCount, lotteryConfig, lotteryCalculateCount);
		//筛选 红球和值比
		List<String> redSumList = getRedSumList(maxLotteryCalculateCount, lotteryConfig, lotteryCalculateCount);
		//筛选 连号个数
		List<Integer> consecutiveNumbersCountList = getConsecutiveNumbersCountList(maxLotteryCalculateCount, lotteryConfig, lotteryCalculateCount);
		//筛选 最大连号数
		List<Integer> maxConsecutiveNumbersCountList = getMaxConsecutiveNumbersCountList(maxLotteryCalculateCount, lotteryConfig, lotteryCalculateCount);

		LotterySelectCodesDTO selectCodesDTO = LotterySelectCodesDTO.builder().redList(redList).redParityRatioList(redParityRatioList)
				.redRangeRatioList(redRangeList).redSumList(redSumList)
				.consecutiveNumbersCountList(consecutiveNumbersCountList)
				.maxConsecutiveNumbersCountList(maxConsecutiveNumbersCountList).build();
		List<String> resultList = lotterySelectService.lotterySelectCodes(selectCodesDTO);
		// 保存文件
		LotteryCalculatePer beforeInfo = lotteryCalculatePerMapper.getBeforeInfo();
		FileGenerator.generateFile(beforeInfo.getCode()+"/",
				lotteryParameter.getType()+".txt",JSONUtil.toJsonStr(resultList));
		return resultList;
	}

	private static List<Integer> getMaxConsecutiveNumbersCountList(LotteryCalculateCount maxLotteryCalculateCount,
		LotteryConfig lotteryConfig, LotteryCalculateCount lotteryCalculateCount) {
		List<Integer> maxConsecutiveNumbersCountList = new ArrayList<>(4);
		if(lotteryCalculateCount.getMaxConsecutiveNumbers1() <= maxLotteryCalculateCount.getMaxConsecutiveNumbers1() * lotteryConfig.getMaxConsecutiveNumbersRate()){
			maxConsecutiveNumbersCountList.add(RuleUtils.MAXCONSECUTIVE_NUMBERS_LIST.get(0));
		}
		if(lotteryCalculateCount.getMaxConsecutiveNumbers2() <= maxLotteryCalculateCount.getMaxConsecutiveNumbers2() * lotteryConfig.getMaxConsecutiveNumbersRate()){
			maxConsecutiveNumbersCountList.add(RuleUtils.MAXCONSECUTIVE_NUMBERS_LIST.get(1));
		}
		if(lotteryCalculateCount.getMaxConsecutiveNumbers3() <= maxLotteryCalculateCount.getMaxConsecutiveNumbers3() * lotteryConfig.getMaxConsecutiveNumbersRate()){
			maxConsecutiveNumbersCountList.add(RuleUtils.MAXCONSECUTIVE_NUMBERS_LIST.get(2));
		}
		if(lotteryCalculateCount.getMaxConsecutiveNumbers4() <= maxLotteryCalculateCount.getMaxConsecutiveNumbers4() * lotteryConfig.getMaxConsecutiveNumbersRate()){
			maxConsecutiveNumbersCountList.add(RuleUtils.MAXCONSECUTIVE_NUMBERS_LIST.get(3));
		}
		if(lotteryCalculateCount.getMaxConsecutiveNumbers5() <= maxLotteryCalculateCount.getMaxConsecutiveNumbers5() * lotteryConfig.getMaxConsecutiveNumbersRate()){
			maxConsecutiveNumbersCountList.add(RuleUtils.MAXCONSECUTIVE_NUMBERS_LIST.get(4));
		}
		return maxConsecutiveNumbersCountList;
	}

	private static List<Integer> getConsecutiveNumbersCountList(LotteryCalculateCount maxLotteryCalculateCount,
		LotteryConfig lotteryConfig, LotteryCalculateCount lotteryCalculateCount) {
		List<Integer> consecutiveNumbersCountList = new ArrayList<>(3);
		if(lotteryCalculateCount.getConsecutiveNumbersCount0() <=
				maxLotteryCalculateCount.getConsecutiveNumbersCount0() * lotteryConfig.getConsecutiveNumbersCountRate()){
			consecutiveNumbersCountList.add(RuleUtils.CONSECUTIVE_NUMBERS_COUNT_LIST.get(0));
		}
		if(lotteryCalculateCount.getConsecutiveNumbersCount1() <=
				maxLotteryCalculateCount.getConsecutiveNumbersCount1() * lotteryConfig.getConsecutiveNumbersCountRate()){
			consecutiveNumbersCountList.add(RuleUtils.CONSECUTIVE_NUMBERS_COUNT_LIST.get(1));
		}
		if(lotteryCalculateCount.getConsecutiveNumbersCount2() <=
				maxLotteryCalculateCount.getConsecutiveNumbersCount2() * lotteryConfig.getConsecutiveNumbersCountRate()){
			consecutiveNumbersCountList.add(RuleUtils.CONSECUTIVE_NUMBERS_COUNT_LIST.get(2));
		}
		return consecutiveNumbersCountList;
	}

	private static List<String> getRedSumList(LotteryCalculateCount maxLotteryCalculateCount,
		LotteryConfig lotteryConfig, LotteryCalculateCount lotteryCalculateCount) {
		List<String> redSumList = new ArrayList<>(14);
		if(lotteryCalculateCount.getRed2160() <= maxLotteryCalculateCount.getRed2160() *  lotteryConfig.getRedSumRate()){
			redSumList.add(RuleUtils.RED_SUM_LIST.get(0));
		}
		if(lotteryCalculateCount.getRed7378() <= maxLotteryCalculateCount.getRed7378() *  lotteryConfig.getRedSumRate()){
			redSumList.add(RuleUtils.RED_SUM_LIST.get(1));
		}
		if(lotteryCalculateCount.getRed6166() <= maxLotteryCalculateCount.getRed6166() *  lotteryConfig.getRedSumRate()){
			redSumList.add(RuleUtils.RED_SUM_LIST.get(2));
		}
		if(lotteryCalculateCount.getRed103108() <= maxLotteryCalculateCount.getRed103108() *  lotteryConfig.getRedSumRate()){
			redSumList.add(RuleUtils.RED_SUM_LIST.get(3));
		}
		if(lotteryCalculateCount.getRed9196() <= maxLotteryCalculateCount.getRed9196() *  lotteryConfig.getRedSumRate()){
			redSumList.add(RuleUtils.RED_SUM_LIST.get(4));
		}
		if(lotteryCalculateCount.getRed7984() <= maxLotteryCalculateCount.getRed7984() *  lotteryConfig.getRedSumRate()){
			redSumList.add(RuleUtils.RED_SUM_LIST.get(5));
		}
		if(lotteryCalculateCount.getRed6772() <= maxLotteryCalculateCount.getRed6772() *  lotteryConfig.getRedSumRate()){
			redSumList.add(RuleUtils.RED_SUM_LIST.get(6));
		}
		if(lotteryCalculateCount.getRed109114() <= maxLotteryCalculateCount.getRed109114() *  lotteryConfig.getRedSumRate()){
			redSumList.add(RuleUtils.RED_SUM_LIST.get(7));
		}
		if(lotteryCalculateCount.getRed115120() <= maxLotteryCalculateCount.getRed115120() *  lotteryConfig.getRedSumRate()){
			redSumList.add(RuleUtils.RED_SUM_LIST.get(8));
		}
		if(lotteryCalculateCount.getRed133138() <= maxLotteryCalculateCount.getRed133138() *  lotteryConfig.getRedSumRate()){
			redSumList.add(RuleUtils.RED_SUM_LIST.get(9));
		}
		if(lotteryCalculateCount.getRed97102() <= maxLotteryCalculateCount.getRed97102() *  lotteryConfig.getRedSumRate()){
			redSumList.add(RuleUtils.RED_SUM_LIST.get(10));
		}
		if(lotteryCalculateCount.getRed139144() <= maxLotteryCalculateCount.getRed139144() *  lotteryConfig.getRedSumRate()){
			redSumList.add(RuleUtils.RED_SUM_LIST.get(11));
		}
		if(lotteryCalculateCount.getRed127132() <= maxLotteryCalculateCount.getRed127132() *  lotteryConfig.getRedSumRate()){
			redSumList.add(RuleUtils.RED_SUM_LIST.get(12));
		}
		if(lotteryCalculateCount.getRed121126() <= maxLotteryCalculateCount.getRed121126() *  lotteryConfig.getRedSumRate()){
			redSumList.add(RuleUtils.RED_SUM_LIST.get(13));
		}
		if(lotteryCalculateCount.getRed145183() <= maxLotteryCalculateCount.getRed145183() *  lotteryConfig.getRedSumRate()){
			redSumList.add(RuleUtils.RED_SUM_LIST.get(14));
		}
		return redSumList;
	}

	private static List<String> getRedRangeList(LotteryCalculateCount maxLotteryCalculateCount,
		LotteryConfig lotteryConfig, LotteryCalculateCount lotteryCalculateCount) {
		List<String> redRangeRatioList = new ArrayList<>(13);
		if(lotteryCalculateCount.getRedRange114() <=  maxLotteryCalculateCount.getRedRange114()
			*  lotteryConfig.getRedRangeRate()){
			redRangeRatioList.add(RangeEnum.ONE_ONE_FOUR.getCode());
		}
		if(lotteryCalculateCount.getRedRange141() <=  maxLotteryCalculateCount.getRedRange141()
				*  lotteryConfig.getRedRangeRate()){
			redRangeRatioList.add(RangeEnum.ONE_FOUR_ONE.getCode());
		}
		if(lotteryCalculateCount.getRedRange411() <=  maxLotteryCalculateCount.getRedRange411()
				*  lotteryConfig.getRedRangeRate()){
			redRangeRatioList.add(RangeEnum.FOUR_ONE_ONE.getCode());
		}
		if(lotteryCalculateCount.getRedRange213() <=  maxLotteryCalculateCount.getRedRange213()
				*  lotteryConfig.getRedRangeRate()){
			redRangeRatioList.add(RangeEnum.TWO_ONE_THREE.getCode());
		}
		if(lotteryCalculateCount.getRedRange123() <=  maxLotteryCalculateCount.getRedRange123()
				*  lotteryConfig.getRedRangeRate()){
			redRangeRatioList.add(RangeEnum.ONE_TWO_THREE.getCode());
		}
		if(lotteryCalculateCount.getRedRange231() <=  maxLotteryCalculateCount.getRedRange231()
				*  lotteryConfig.getRedRangeRate()){
			redRangeRatioList.add(RangeEnum.TWO_THREE_ONE.getCode());
		}
		if(lotteryCalculateCount.getRedRange312() <=  maxLotteryCalculateCount.getRedRange312()
				*  lotteryConfig.getRedRangeRate()){
			redRangeRatioList.add(RangeEnum.THREE_ONE_TWO.getCode());
		}
		if(lotteryCalculateCount.getRedRange321() <=  maxLotteryCalculateCount.getRedRange321()
				*  lotteryConfig.getRedRangeRate()){
			redRangeRatioList.add(RangeEnum.THREE_TWO_ONE.getCode());
		}
		if(lotteryCalculateCount.getRedRange204() <=  maxLotteryCalculateCount.getRedRange204()
				*  lotteryConfig.getRedRangeRate()){
			redRangeRatioList.add(RangeEnum.TWO_ZERO_FOUR.getCode());
		}
		if(lotteryCalculateCount.getRedRange240() <=  maxLotteryCalculateCount.getRedRange240()
				*  lotteryConfig.getRedRangeRate()){
			redRangeRatioList.add(RangeEnum.TWO_FOUR_ZERO.getCode());
		}
		if(lotteryCalculateCount.getRedRange402() <=  maxLotteryCalculateCount.getRedRange402()
				*  lotteryConfig.getRedRangeRate()){
			redRangeRatioList.add(RangeEnum.FOUR_TWO_ZERO.getCode());
		}
		if(lotteryCalculateCount.getRedRange420() <=  maxLotteryCalculateCount.getRedRange420()
				*  lotteryConfig.getRedRangeRate()){
			redRangeRatioList.add(RangeEnum.FOUR_TWO_ZERO.getCode());
		}
		if(lotteryCalculateCount.getRedRange222() <=  maxLotteryCalculateCount.getRedRange222()
				*  lotteryConfig.getRedRangeRate()){
			redRangeRatioList.add(RangeEnum.TWO_TWO_TWO.getCode());
		}
		return redRangeRatioList;
	}

	private static List<String> getRedParityRatioList(LotteryCalculateCount maxLotteryCalculateCount,
		LotteryConfig lotteryConfig, LotteryCalculateCount lotteryCalculateCount) {
		List<String> redParityRatioList = new ArrayList<>(7);
		if(lotteryCalculateCount.getRedParityRatio06() <=  maxLotteryCalculateCount.getRedParityRatio06() *
				lotteryConfig.getRedParityRate()){
			redParityRatioList.add(ParityRatioEnum.ZERO_SIX.getCode());
		}
		if(lotteryCalculateCount.getRedParityRatio60() <=  maxLotteryCalculateCount.getRedParityRatio60() *
				lotteryConfig.getRedParityRate()){
			redParityRatioList.add(ParityRatioEnum.SIX_ZERO.getCode());
		}
		if(lotteryCalculateCount.getRedParityRatio15() <=  maxLotteryCalculateCount.getRedParityRatio15() *
				lotteryConfig.getRedParityRate()){
			redParityRatioList.add(ParityRatioEnum.ONE_FIVE.getCode());
		}
		if(lotteryCalculateCount.getRedParityRatio51() <=  maxLotteryCalculateCount.getRedParityRatio51() *
				lotteryConfig.getRedParityRate()){
			redParityRatioList.add(ParityRatioEnum.FIVE_ONE.getCode());
		}
		if(lotteryCalculateCount.getRedParityRatio33() <=  maxLotteryCalculateCount.getRedParityRatio33() *
				lotteryConfig.getRedParityRate()){
			redParityRatioList.add(ParityRatioEnum.THREE_THREE.getCode());
		}
		if(lotteryCalculateCount.getRedParityRatio24() <=  maxLotteryCalculateCount.getRedParityRatio24() *
				lotteryConfig.getRedParityRate()){
			redParityRatioList.add(ParityRatioEnum.TWO_FOUR.getCode());
		}
		if(lotteryCalculateCount.getRedParityRatio42() <=  maxLotteryCalculateCount.getRedParityRatio42() *
				lotteryConfig.getRedParityRate()){
			redParityRatioList.add(ParityRatioEnum.FOUR_TWO.getCode());
		}
		return redParityRatioList;
	}

	private static List<String> getRedList(LotteryCalculateCount maxLotteryCalculateCount,
		LotteryConfig lotteryConfig, LotteryCalculateCount lotteryCalculateCount) {
		List<String> redList = new ArrayList<>(33);
		if(lotteryCalculateCount.getRed1() <= maxLotteryCalculateCount.getRed1() * lotteryConfig.getRedRate()){
			redList.add(RuleUtils.PARTITION_ONE.get(0));
		}
		if(lotteryCalculateCount.getRed2() <= maxLotteryCalculateCount.getRed2() * lotteryConfig.getRedRate()){
			redList.add(RuleUtils.PARTITION_ONE.get(1));
		}
		if(lotteryCalculateCount.getRed3() <= maxLotteryCalculateCount.getRed3() * lotteryConfig.getRedRate()){
			redList.add(RuleUtils.PARTITION_ONE.get(2));
		}
		if(lotteryCalculateCount.getRed4() <= maxLotteryCalculateCount.getRed4() * lotteryConfig.getRedRate()){
			redList.add(RuleUtils.PARTITION_ONE.get(3));
		}
		if(lotteryCalculateCount.getRed5() <= maxLotteryCalculateCount.getRed5() * lotteryConfig.getRedRate()){
			redList.add(RuleUtils.PARTITION_ONE.get(4));
		}
		if(lotteryCalculateCount.getRed6() <= maxLotteryCalculateCount.getRed6() * lotteryConfig.getRedRate()){
			redList.add(RuleUtils.PARTITION_ONE.get(5));
		}
		if(lotteryCalculateCount.getRed7() <= maxLotteryCalculateCount.getRed7() * lotteryConfig.getRedRate()){
			redList.add(RuleUtils.PARTITION_ONE.get(6));
		}
		if(lotteryCalculateCount.getRed8() <= maxLotteryCalculateCount.getRed8() * lotteryConfig.getRedRate()){
			redList.add(RuleUtils.PARTITION_ONE.get(7));
		}
		if(lotteryCalculateCount.getRed9() <= maxLotteryCalculateCount.getRed9() * lotteryConfig.getRedRate()){
			redList.add(RuleUtils.PARTITION_ONE.get(8));
		}
		if(lotteryCalculateCount.getRed10() <= maxLotteryCalculateCount.getRed10() * lotteryConfig.getRedRate()){
			redList.add(RuleUtils.PARTITION_ONE.get(9));
		}
		if(lotteryCalculateCount.getRed11() <= maxLotteryCalculateCount.getRed11() * lotteryConfig.getRedRate()){
			redList.add(RuleUtils.PARTITION_ONE.get(10));
		}
		if(lotteryCalculateCount.getRed12() <= maxLotteryCalculateCount.getRed12() * lotteryConfig.getRedRate()){
			redList.add(RuleUtils.PARTITION_TWO.get(0));
		}
		if(lotteryCalculateCount.getRed13() <= maxLotteryCalculateCount.getRed13() * lotteryConfig.getRedRate()){
			redList.add(RuleUtils.PARTITION_TWO.get(1));
		}
		if(lotteryCalculateCount.getRed14() <= maxLotteryCalculateCount.getRed14() * lotteryConfig.getRedRate()){
			redList.add(RuleUtils.PARTITION_TWO.get(2));
		}
		if(lotteryCalculateCount.getRed15() <= maxLotteryCalculateCount.getRed15() * lotteryConfig.getRedRate()){
			redList.add(RuleUtils.PARTITION_TWO.get(3));
		}
		if(lotteryCalculateCount.getRed16() <= maxLotteryCalculateCount.getRed16() * lotteryConfig.getRedRate()){
			redList.add(RuleUtils.PARTITION_TWO.get(4));
		}
		if(lotteryCalculateCount.getRed17() <= maxLotteryCalculateCount.getRed17() * lotteryConfig.getRedRate()){
			redList.add(RuleUtils.PARTITION_TWO.get(5));
		}
		if(lotteryCalculateCount.getRed18() <= maxLotteryCalculateCount.getRed18() * lotteryConfig.getRedRate()){
			redList.add(RuleUtils.PARTITION_TWO.get(6));
		}
		if(lotteryCalculateCount.getRed19() <= maxLotteryCalculateCount.getRed19() * lotteryConfig.getRedRate()){
			redList.add(RuleUtils.PARTITION_TWO.get(7));
		}
		if(lotteryCalculateCount.getRed20() <= maxLotteryCalculateCount.getRed20() * lotteryConfig.getRedRate()){
			redList.add(RuleUtils.PARTITION_TWO.get(8));
		}
		if(lotteryCalculateCount.getRed21() <= maxLotteryCalculateCount.getRed21() * lotteryConfig.getRedRate()){
			redList.add(RuleUtils.PARTITION_TWO.get(9));
		}
		if(lotteryCalculateCount.getRed22() <= maxLotteryCalculateCount.getRed22() * lotteryConfig.getRedRate()){
			redList.add(RuleUtils.PARTITION_TWO.get(10));
		}
		if(lotteryCalculateCount.getRed23() <= maxLotteryCalculateCount.getRed23() * lotteryConfig.getRedRate()){
			redList.add(RuleUtils.PARTITION_THREE.get(0));
		}
		if(lotteryCalculateCount.getRed24() <= maxLotteryCalculateCount.getRed24() * lotteryConfig.getRedRate()){
			redList.add(RuleUtils.PARTITION_THREE.get(1));
		}
		if(lotteryCalculateCount.getRed25() <= maxLotteryCalculateCount.getRed25() * lotteryConfig.getRedRate()){
			redList.add(RuleUtils.PARTITION_THREE.get(2));
		}
		if(lotteryCalculateCount.getRed26() <= maxLotteryCalculateCount.getRed26() * lotteryConfig.getRedRate()){
			redList.add(RuleUtils.PARTITION_THREE.get(3));
		}
		if(lotteryCalculateCount.getRed27() <= maxLotteryCalculateCount.getRed27() * lotteryConfig.getRedRate()){
			redList.add(RuleUtils.PARTITION_THREE.get(4));
		}
		if(lotteryCalculateCount.getRed28() <= maxLotteryCalculateCount.getRed28() * lotteryConfig.getRedRate()){
			redList.add(RuleUtils.PARTITION_THREE.get(5));
		}
		if(lotteryCalculateCount.getRed29() <= maxLotteryCalculateCount.getRed29() * lotteryConfig.getRedRate()){
			redList.add(RuleUtils.PARTITION_THREE.get(6));
		}
		if(lotteryCalculateCount.getRed30() <= maxLotteryCalculateCount.getRed30() * lotteryConfig.getRedRate()){
			redList.add(RuleUtils.PARTITION_THREE.get(7));
		}
		if(lotteryCalculateCount.getRed31() <= maxLotteryCalculateCount.getRed31() * lotteryConfig.getRedRate()){
			redList.add(RuleUtils.PARTITION_THREE.get(8));
		}
		if(lotteryCalculateCount.getRed32() <= maxLotteryCalculateCount.getRed32() * lotteryConfig.getRedRate()){
			redList.add(RuleUtils.PARTITION_THREE.get(9));
		}
		if(lotteryCalculateCount.getRed33() <= maxLotteryCalculateCount.getRed33() * lotteryConfig.getRedRate()){
			redList.add(RuleUtils.PARTITION_THREE.get(10));
		}
		return redList;
	}

	private static String getRed(int consecutiveNumber, int i) {
		StringBuffer sb = new StringBuffer();
		for (int j = 0; j < consecutiveNumber; j++) {
			if(j == consecutiveNumber - 1){
				sb.append(RuleUtils.PARTITION_ONE.get(i +j));
			}else{
				sb.append(RuleUtils.PARTITION_ONE.get(i +j)).append(",");
			}
		}
		return sb.toString();
	}

}
