package com.pj.project.lottery.lottery_forecast;

import java.util.*;
import java.util.stream.Collectors;

import cn.hutool.json.JSONUtil;
import com.google.common.collect.Lists;
import com.pj.current.enums.LotteryForestConfigEnum;
import com.pj.current.enums.LotteryTypeEnum;
import com.pj.current.global.LotteryConstant;
import com.pj.models.so.SoMap;
import com.pj.project.lottery.LotteryMapper;
import com.pj.project.lottery.LotteryParameter;
import com.pj.project.lottery.LotteryService;
import com.pj.project.lottery.lottery_all.LotteryAllService;
import com.pj.project.lottery.lottery_calculate_count.LotteryCalculateCount;
import com.pj.project.lottery.lottery_calculate_count.LotteryCalculateCountMapper;
import com.pj.project.lottery.lottery_calculate_count.LotteryCalculateCountService;
import com.pj.project.lottery.lottery_calculate_nine.LotteryCalculateNine;
import com.pj.project.lottery.lottery_calculate_nine.LotteryCalculateNineM;
import com.pj.project.lottery.lottery_calculate_nine.LotteryCalculateNineMapper;
import com.pj.project.lottery.lottery_calculate_nine.LotteryCalculateNineService;
import com.pj.project.lottery.lottery_calculate_nine_count.LotteryCalculateNineCountMapper;
import com.pj.project.lottery.lottery_calculate_nine_count.LotteryCalculateNineCountService;
import com.pj.project.lottery.lottery_calculate_per.LotteryCalculatePer;
import com.pj.project.lottery.lottery_calculate_per.LotteryCalculatePerMapper;
import com.pj.project.lottery.lottery_calculate_per.LotteryCalculatePerService;
import com.pj.project.lottery.lottery_config.LotteryConfig;
import com.pj.project.lottery.lottery_config.LotteryConfigMapper;
import com.pj.project.lottery.lottery_red_proportion.LotteryRedProportionService;
import com.pj.project.lottery.lottery_select.LotterySelectCodesDTO;
import com.pj.project.lottery.lottery_select.LotterySelectService;
import com.pj.project.lottery.lottery_strategy_record.LotteryStrategyRecord;
import com.pj.project.lottery.lottery_strategy_record.LotteryStrategyRecordMapper;
import com.pj.project.lottery.unionLotto.domain.Lottery;
import com.pj.project.lottery.unionLotto.enums.CalTypeEnum;
import com.pj.project.lottery.unionLotto.enums.NineTypeEnum;
import com.pj.project.lottery.unionLotto.enums.ParityRatioEnum;
import com.pj.project.lottery.unionLotto.enums.RangeEnum;
import com.pj.project.lottery.unionLotto.utils.RuleUtils;
import com.pj.utils.FileGenerator;
import com.pj.utils.IdGeneratorUtils;
import com.pj.utils.StringUtils;
import com.pj.utils.cache.RedisUtil;
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
	LotteryCalculateNineMapper lotteryCalculateNineMapper;
	@Autowired
	LotteryStrategyRecordMapper lotteryStrategyRecordMapper;
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
		try {
			if (org.springframework.util.StringUtils.hasText(lotteryForestVo.getCode())) {
				this.syncData(lotteryForestVo.getCode());
			}
			Map<String, List<?>> map = new HashMap<>();
			LotteryForecastTemp lotteryForecastTemp = null;
			if (lotteryForestVo.getType() == 0) {
				for (int i = 1; i < 4; i++) {
					lotteryForecastTemp = setList(i, i + 1, map);
				}
			} else {
				lotteryForecastTemp = setList(lotteryForestVo.getType(), lotteryForestVo.getOrderBy(), map);
			}
			String groupId = IdGeneratorUtils.generateId() + "";
			if (org.springframework.util.StringUtils.hasText(RedisUtil.get(lotteryForecastTemp.getStrategy()))) {
				groupId = RedisUtil.get(lotteryForecastTemp.getStrategy());
			} else {
				RedisUtil.setByForever(lotteryForecastTemp.getStrategy(), groupId);
			}
			List<String> redList = (List<String>) map.get(LotteryForestConfigEnum.RED_LIST.getName());
			List<String> redParityRatioList = (List<String>) map.get(LotteryForestConfigEnum.RED_PARITY_RATIO_LIST.getName());
			List<String> redRangeList = (List<String>) map.get(LotteryForestConfigEnum.RED_RANGE_LIST.getName());
			List<String> redSumList = (List<String>) map.get(LotteryForestConfigEnum.RED_SUM_LIST.getName());
			List<Integer> consecutiveNumbersCountList = (List<Integer>) map.get(LotteryForestConfigEnum.CONSECUTIVE_NUMBERS_COUNT_LIST.getName());
			List<Integer> maxConsecutiveNumbersCountList = (List<Integer>) map.get(LotteryForestConfigEnum.MAX_CONSECUTIVE_NUMBERS_COUNT_LIST.getName());
			List<String> nineTurn09List = (List<String>) map.get(LotteryForestConfigEnum.NINE_TURN_09_LIST.getName());
			List<String> nineTurn17List = (List<String>) map.get(LotteryForestConfigEnum.NINE_TURN_17_LIST.getName());
			List<String> nineTurn33List = (List<String>) map.get(LotteryForestConfigEnum.NINE_TURN_33_LIST.getName());
			LotterySelectCodesDTO selectCodesDTO = LotterySelectCodesDTO.builder().redList(redList).redParityRatioList(redParityRatioList)
					.redRangeRatioList(redRangeList).redSumList(redSumList)
					.consecutiveNumbersCountList(consecutiveNumbersCountList)
					.maxConsecutiveNumbersCountList(maxConsecutiveNumbersCountList)
					.nineTurn09List(nineTurn09List).nineTurn17List(nineTurn17List).nineTurn33List(nineTurn33List)
					.build();
			List<String> resultList = lotterySelectService.lotterySelectCodes(selectCodesDTO);
			log.info("总数据量:" + resultList.size());
			// 保存文件
			LotteryCalculatePer beforeInfo = lotteryCalculatePerMapper.getBeforeInfo();
			String code = StringUtils.nextCode(beforeInfo.getCode());
			//查看是否预测准确
			Lottery lottery = lotteryMapper.getByCode(code);
			boolean enableCorrect = false;
			if (lottery != null) {
				enableCorrect = resultList.contains(lottery.getRed());
			}
			//保存数据
			LotteryStrategyRecord lotteryStrategyRecord = LotteryStrategyRecord.builder().strategyNo(groupId).code(code)
					.enableContain(enableCorrect).total(resultList.size()).strategy(lotteryForecastTemp.getStrategy()).build();
			lotteryStrategyRecordMapper.add(lotteryStrategyRecord);
			FileGenerator.generateFile(
					LotteryConstant.ROOT_PATH + groupId + "/" + code + "/",
					enableCorrect + "-" + resultList.size() + "-" + code + ".txt",
					JSONUtil.toJsonStr(resultList));
		}catch (Exception e){
			//记录错误code
			log.error("预测期间出现异常:{},code:{}",e.getMessage(),lotteryForestVo.getCode());
			RedisUtil.forListAdd(LotteryConstant.LOTTERY_FORECAST_ERROR_KEY,lotteryForestVo.getCode());
		}
	}

	public LotteryForecastTemp setList(int type,int orderBy,Map<String,List<?>> map){
		//年
		LotteryCalculateCount maxLotteryCalculateCount =
				lotteryCalculateCountMapper.getMaxLotteryCalculateCount(type);
		SoMap soMap = new SoMap();
		soMap.set("type",type);
		//查询配置
		List<LotteryConfig> list = lotteryConfigMapper.getList(soMap);
		if(CollectionUtils.isEmpty(list)){
			log.warn("没有配置年份计算规则!");
			return null;
		}
		LotteryConfig lotteryConfig = list.get(0);
		//当前 年
		soMap.set("calType",type);
		soMap.set("sortType",orderBy);
		List<LotteryCalculateCount> lotteryCalculateCountList = lotteryCalculateCountMapper.getList(soMap);
		LotteryCalculateCount lotteryCalculateCount = lotteryCalculateCountList.get(0);
		//筛选红球
		List<String> redList = getRedList(maxLotteryCalculateCount, lotteryConfig, lotteryCalculateCount);
		if(map.get(LotteryForestConfigEnum.RED_LIST.getName()) != null){
			redList.addAll((Collection<? extends String>) map.get(LotteryForestConfigEnum.RED_LIST.getName()));
		}
		map.put(LotteryForestConfigEnum.RED_LIST.getName(),redList);
		//筛选 红球奇偶比
		List<String> redParityRatioList = getRedParityRatioList(maxLotteryCalculateCount, lotteryConfig, lotteryCalculateCount);
		if(map.get(LotteryForestConfigEnum.RED_PARITY_RATIO_LIST.getName()) != null){
			redParityRatioList.addAll((Collection<? extends String>) map.get(LotteryForestConfigEnum.RED_PARITY_RATIO_LIST.getName()));
		}
		map.put(LotteryForestConfigEnum.RED_PARITY_RATIO_LIST.getName(),redParityRatioList);
		//筛选 红球区间比
		List<String> redRangeList = getRedRangeList(maxLotteryCalculateCount, lotteryConfig, lotteryCalculateCount);
		if(map.get(LotteryForestConfigEnum.RED_RANGE_LIST.getName()) != null){
			redRangeList.addAll((Collection<? extends String>) map.get(LotteryForestConfigEnum.RED_RANGE_LIST.getName()));
		}
		map.put(LotteryForestConfigEnum.RED_RANGE_LIST.getName(),redRangeList);
		//筛选 红球和值比
		List<String> redSumList = getRedSumList(maxLotteryCalculateCount, lotteryConfig, lotteryCalculateCount);
		if(map.get(LotteryForestConfigEnum.RED_SUM_LIST.getName()) != null){
			redSumList.addAll((Collection<? extends String>) map.get(LotteryForestConfigEnum.RED_SUM_LIST.getName()));
		}
		map.put(LotteryForestConfigEnum.RED_SUM_LIST.getName(),redSumList);
		//筛选 连号个数
		List<Integer> consecutiveNumbersCountList = getConsecutiveNumbersCountList(maxLotteryCalculateCount, lotteryConfig, lotteryCalculateCount);
		if(map.get(LotteryForestConfigEnum.CONSECUTIVE_NUMBERS_COUNT_LIST.getName()) != null){
			consecutiveNumbersCountList.addAll((Collection<? extends Integer>) map.get(LotteryForestConfigEnum.CONSECUTIVE_NUMBERS_COUNT_LIST.getName()));
		}
		map.put(LotteryForestConfigEnum.CONSECUTIVE_NUMBERS_COUNT_LIST.getName(),consecutiveNumbersCountList);
		//筛选 最大连号数
		List<Integer> maxConsecutiveNumbersCountList = getMaxConsecutiveNumbersCountList(maxLotteryCalculateCount, lotteryConfig, lotteryCalculateCount);
		if(map.get(LotteryForestConfigEnum.MAX_CONSECUTIVE_NUMBERS_COUNT_LIST.getName()) != null){
			maxConsecutiveNumbersCountList.addAll((Collection<? extends Integer>) map.get(LotteryForestConfigEnum.MAX_CONSECUTIVE_NUMBERS_COUNT_LIST.getName()));
		}
		map.put(LotteryForestConfigEnum.MAX_CONSECUTIVE_NUMBERS_COUNT_LIST.getName(),maxConsecutiveNumbersCountList);
		//筛选 九转连环09
		List<LotteryCalculateNine> maxNineTurnCountList = lotteryCalculateNineMapper.getMaxNineTurnCount(type);
		List<LotteryCalculateNine> currentList = getLotteryCalculateNines(type, lotteryCalculateCount);
		List<String> nineTurn09List = getNineTurn09List(maxNineTurnCountList, currentList, lotteryConfig);
		if(map.get(LotteryForestConfigEnum.NINE_TURN_09_LIST.getName()) != null){
			nineTurn09List.addAll((Collection<? extends String>) map.get(LotteryForestConfigEnum.NINE_TURN_09_LIST.getName()));
		}
		map.put(LotteryForestConfigEnum.NINE_TURN_09_LIST.getName(),nineTurn09List);
		List<String> nineTurn17List =getNineTurn17List(maxNineTurnCountList, currentList, lotteryConfig);
		if(map.get(LotteryForestConfigEnum.NINE_TURN_17_LIST.getName()) != null){
			nineTurn17List.addAll((Collection<? extends String>) map.get(LotteryForestConfigEnum.NINE_TURN_17_LIST.getName()));
		}
		map.put(LotteryForestConfigEnum.NINE_TURN_17_LIST.getName(),nineTurn17List);
		List<String> nineTurn33List = getNineTurn33List(maxNineTurnCountList, currentList, lotteryConfig);
		if(map.get(LotteryForestConfigEnum.NINE_TURN_33_LIST.getName()) != null){
			nineTurn33List.addAll((Collection<? extends String>) map.get(LotteryForestConfigEnum.NINE_TURN_33_LIST.getName()));
		}
		map.put(LotteryForestConfigEnum.NINE_TURN_33_LIST.getName(),nineTurn33List);

		LotteryForecastTemp forecastTemp = LotteryForecastTemp.builder().build();
		forecastTemp.appendStrategy(lotteryConfig.getRedRate()+","+lotteryConfig.getRedParityRate()
				+","+lotteryConfig.getRedRangeRate()+","+lotteryConfig.getRedSumRate()+
				","+lotteryConfig.getConsecutiveNumbersCountRate()+","+lotteryConfig.getMaxConsecutiveNumbersRate());
		return forecastTemp;
	}

	private List<String> getNineTurn33List(List<LotteryCalculateNine> maxNineTurnCountList
			,List<LotteryCalculateNine> currentList,LotteryConfig lotteryConfig){
		List<String> nineTurn33List = new ArrayList<>(200);
		List<LotteryCalculateNine> lotteryCalculateNines33 = maxNineTurnCountList.stream()
				.filter(v -> v.nineTurnType == NineTypeEnum.NINE33.getType())
				.collect(Collectors.toList());
		lotteryCalculateNines33.stream().forEach(v->{
			if(!CollectionUtils.isEmpty(currentList)){
				boolean correctCondition = currentList.stream().anyMatch(w -> w.getNineTurnType() == v.getNineTurnType()
								&& w.getNineTurnCount() >= v.getNineTurnCount() * lotteryConfig.getRedNineTurn09Rate());
				if(correctCondition && !nineTurn33List.contains(v.getNineTurn())){
					nineTurn33List.add(v.getNineTurn());
				}
			}
		});
		return nineTurn33List;
	}

	private List<String> getNineTurn17List(List<LotteryCalculateNine> maxNineTurnCountList
			,List<LotteryCalculateNine> currentList,LotteryConfig lotteryConfig){
		List<String> nineTurn17List = new ArrayList<>(200);
		List<LotteryCalculateNine> lotteryCalculateNines17 = maxNineTurnCountList.stream()
				.filter(v -> v.nineTurnType == NineTypeEnum.NINE17.getType())
				.collect(Collectors.toList());
		lotteryCalculateNines17.stream().forEach(v->{
			if(!CollectionUtils.isEmpty(currentList)){
				boolean correctCondition = currentList.stream().anyMatch(w -> w.getNineTurnType() == v.getNineTurnType()
								&& w.getNineTurnCount() >= v.getNineTurnCount() * lotteryConfig.getRedNineTurn09Rate());
				if(correctCondition && !nineTurn17List.contains(v.getNineTurn())){
					nineTurn17List.add(v.getNineTurn());
				}
			}
		});
		return nineTurn17List;
	}

	private List<String> getNineTurn09List(List<LotteryCalculateNine> maxNineTurnCountList
			,List<LotteryCalculateNine> currentList,LotteryConfig lotteryConfig){
		List<String> nineTurn09List = new ArrayList<>(200);
		List<LotteryCalculateNine> lotteryCalculateNines09 = maxNineTurnCountList.stream()
				.filter(v -> v.nineTurnType == NineTypeEnum.NINE09.getType())
				.collect(Collectors.toList());
		lotteryCalculateNines09.stream().forEach(v->{
			if(!CollectionUtils.isEmpty(currentList)){
				boolean correctCondition = currentList.stream().anyMatch(w -> w.getNineTurnType() == v.getNineTurnType()
								&& w.getNineTurnCount() <= v.getNineTurnCount() * lotteryConfig.getRedNineTurn09Rate());
				if(correctCondition && !nineTurn09List.contains(v.getNineTurn())){
					nineTurn09List.add(v.getNineTurn());
				}
			}
		});
		return nineTurn09List;
	}

	private List<LotteryCalculateNine> getLotteryCalculateNines(int type, LotteryCalculateCount lotteryCalculateCount) {
		SoMap currentMap = new SoMap();
		currentMap.set("calType", type);
		if(CalTypeEnum.YEAR.getCalType() == type){
			currentMap.set("year", lotteryCalculateCount.getYear());
		}else if (CalTypeEnum.MONTH.getCalType() == type){
			currentMap.set("year", lotteryCalculateCount.getYear());
			currentMap.set("month", lotteryCalculateCount.getMonth());
		}else if (CalTypeEnum.WEEK.getCalType() == type){
			currentMap.set("year", lotteryCalculateCount.getYear());
			currentMap.set("week", lotteryCalculateCount.getWeek());
		}else if (CalTypeEnum.CODE.getCalType() == type){
			currentMap.set("codes", lotteryCalculateCount.getCodes());
		}
		List<LotteryCalculateNine> currentList = lotteryCalculateNineMapper.getCurrentList(currentMap);
		return currentList;
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
		lotteryAllService.syncData();
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
