package com.pj.project.lottery.lottery_forecast;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.json.JSONUtil;
import com.google.common.collect.Lists;
import com.pj.current.enums.LotteryTypeEnum;
import com.pj.models.so.SoMap;
import com.pj.project.lottery.LotteryMapper;
import com.pj.project.lottery.LotteryParameter;
import com.pj.project.lottery.LotteryService;
import com.pj.project.lottery.lottery_all.LotteryAllService;
import com.pj.project.lottery.lottery_calculate_count.LotteryCalculateCount;
import com.pj.project.lottery.lottery_calculate_count.LotteryCalculateCountMapper;
import com.pj.project.lottery.lottery_calculate_count.LotteryCalculateCountService;
import com.pj.project.lottery.lottery_calculate_nine.LotteryCalculateNineService;
import com.pj.project.lottery.lottery_calculate_nine_count.LotteryCalculateNineCountService;
import com.pj.project.lottery.lottery_calculate_per.LotteryCalculatePer;
import com.pj.project.lottery.lottery_calculate_per.LotteryCalculatePerMapper;
import com.pj.project.lottery.lottery_calculate_per.LotteryCalculatePerService;
import com.pj.project.lottery.lottery_config.LotteryConfig;
import com.pj.project.lottery.lottery_config.LotteryConfigMapper;
import com.pj.project.lottery.lottery_red_proportion.LotteryRedProportionService;
import com.pj.project.lottery.lottery_select.LotterySelectCodesDTO;
import com.pj.project.lottery.lottery_select.LotterySelectService;
import com.pj.project.lottery.unionLotto.enums.ParityRatioEnum;
import com.pj.project.lottery.unionLotto.enums.RangeEnum;
import com.pj.project.lottery.unionLotto.utils.RuleUtils;
import com.pj.utils.FileGenerator;
import com.pj.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * Service: lottery_forecast -- 号码预测
 * @author lizhihao 
 */
@Service
@Slf4j
public class LotteryForecastService {

	/** 底层 Mapper 对象 */
	@Autowired
	LotteryForecastMapper lotteryForecastMapper;
	@Autowired
	LotteryCalculateCountMapper lotteryCalculateCountMapper;
	@Autowired
	LotteryConfigMapper lotteryConfigMapper;
	@Autowired
	LotteryCalculatePerMapper lotteryCalculatePerMapper;
	@Autowired
	LotterySelectService lotterySelectService;
	@Autowired
	LotteryService lotteryService;
	@Autowired
	LotteryMapper lotteryMapper;
	@Autowired
	LotteryCalculatePerService lotteryCalculatePerService;
	@Autowired
	LotteryCalculateCountService lotteryCalculateCountService;
	@Autowired
	LotteryCalculateNineService lotteryCalculateNineService;
	@Autowired
	LotteryCalculateNineCountService lotteryCalculateNineCountService;
	@Autowired
	LotteryRedProportionService lotteryRedProportionService;
	@Autowired
	LotteryAllService lotteryAllService;

	/** 增 */
	int add(LotteryForecast l){
		LotteryCalculatePer beforeInfo = lotteryCalculatePerMapper.getBeforeInfo();
		l.setCode(beforeInfo.getCode());

		LotterySelectCodesDTO selectCodesDTO = LotterySelectCodesDTO.builder().redList(Lists.newArrayList(l.getRed()))
				.redParityRatioList(Lists.newArrayList(l.getRedParityRatio()))
				.redRangeRatioList(Lists.newArrayList(l.getRedRangeRatio()))
				.redSumList(Lists.newArrayList(l.getRedSum()))
				.consecutiveNumbersCountList(Lists.newArrayList(l.getConsecutiveNumbersCount()))
				.maxConsecutiveNumbersCountList(Lists.newArrayList(l.getMaxConsecutiveNumbersCount()))
				.nineTurn17List(Lists.newArrayList(l.getNineTurn17()))
				.nineTurn33List(Lists.newArrayList(l.getNineTurn33()))
				.nineTurn09List(Lists.newArrayList(l.getNineTurn09()))
				.build();
		List<String> forcastRedList = lotterySelectService.lotterySelectCodes(selectCodesDTO);
		if(CollectionUtils.isEmpty(forcastRedList)){
			log.info("该组合未出号！");
			return 0;
		}
		String collect = forcastRedList.stream().collect(Collectors.joining(";"));
		l.setForecastRed(collect);
		return lotteryForecastMapper.add(l);
	}

	public void lotteryConfig(LotteryForestVo lotteryForestVo){
		if(org.springframework.util.StringUtils.hasText(lotteryForestVo.getCode())){
			this.syncData(lotteryForestVo.getCode());
		}
		//年
		LotteryCalculateCount maxLotteryCalculateCount =
				lotteryCalculateCountMapper.getMaxLotteryCalculateCount(lotteryForestVo.getType());
		SoMap soMap = new SoMap();
		soMap.set("type",lotteryForestVo.getType());
		//查询配置
		List<LotteryConfig> list = lotteryConfigMapper.getList(soMap);
		if(CollectionUtils.isEmpty(list)){
			log.warn("没有配置年份计算规则!");
			return;
		}
		LotteryConfig lotteryConfig = list.get(0);
		//当前 年
		soMap.set("calType",lotteryForestVo.getType());
		soMap.set("sortType",lotteryForestVo.getOrderBy());
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
		log.info("总数据量:"+ resultList.size());
		// 保存文件
		LotteryCalculatePer beforeInfo = lotteryCalculatePerMapper.getBeforeInfo();
		FileGenerator.generateFile(StringUtils.nextCode(beforeInfo.getCode())+"/",
				lotteryForestVo.getType()+"-"+lotteryConfig.getRedRate()+"-"+lotteryConfig.getRedParityRate()
						+"-"+lotteryConfig.getRedRangeRate()+"-"+lotteryConfig.getRedSumRate()
						+"-"+lotteryConfig.getMaxConsecutiveNumbersRate()+"-"+lotteryConfig.getConsecutiveNumbersCountRate()
						+".txt", JSONUtil.toJsonStr(resultList));
	}



	public void syncData(String code){
		//全量lottery同步
		LotteryParameter lotteryParameter = LotteryParameter.builder()
				.type(LotteryTypeEnum.UNION_LOTTO.getType()).pageNo(1).pageSize(10000).build();
		lotteryService.syncLotterydata(lotteryParameter);
		//删除code之后的数据
		if(org.springframework.util.StringUtils.hasText(code)){
			lotteryMapper.deleteAfterCode(code);
		}
		//同步其他数据
		lotteryCalculatePerService.lotteryCalculatePer();
		lotteryCalculateCountService.lotteryCalculateCount();
		lotteryCalculateNineService.lotteryCalculateNine();
		lotteryCalculateNineCountService.lotteryCalculateNineCount();
		lotteryAllService.batchAdd();
		lotterySelectService.lotterySelect();
		lotteryRedProportionService.lotteryRedProportion();
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

	/** 删 */
	int delete(Long id){
		return lotteryForecastMapper.delete(id);
	}

	/** 改 */
	int update(LotteryForecast l){
		return lotteryForecastMapper.update(l);
	}

	/** 查 */
	LotteryForecast getById(Long id){
		return lotteryForecastMapper.getById(id);
	}

	/** 查集合 - 根据条件（参数为空时代表忽略指定条件） */  
	List<LotteryForecast> getList(SoMap so) {
		return lotteryForecastMapper.getList(so);	
	}
	

}
