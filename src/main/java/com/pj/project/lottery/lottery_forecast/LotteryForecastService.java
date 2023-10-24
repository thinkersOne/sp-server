package com.pj.project.lottery.lottery_forecast;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.pj.models.so.SoMap;
import com.pj.project.lottery.lottery_calculate_per.LotteryCalculatePer;
import com.pj.project.lottery.lottery_calculate_per.LotteryCalculatePerMapper;
import com.pj.project.lottery.lottery_select.LotterySelectCodesDTO;
import com.pj.project.lottery.lottery_select.LotterySelectService;
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
	LotteryCalculatePerMapper lotteryCalculatePerMapper;
	@Autowired
	LotterySelectService lotterySelectService;

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
