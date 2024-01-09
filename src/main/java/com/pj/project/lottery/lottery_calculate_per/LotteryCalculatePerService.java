package com.pj.project.lottery.lottery_calculate_per;

import java.util.*;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.pj.current.global.RuleConstants;
import com.pj.models.so.SoMap;
import com.pj.project.lottery.LotteryMapper;
import com.pj.project.lottery.lottery_all.LotteryAll;
import com.pj.project.lottery.unionLotto.domain.Lottery;
import com.pj.project.lottery.unionLotto.enums.UnionLottoWeekEnum;
import com.pj.project.lottery.unionLotto.utils.PersonalLawUtils;
import com.pj.project.lottery.unionLotto.utils.RuleUtils;
import com.pj.utils.StringUtils;
import com.pj.utils.Ttime;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * Service: lottery_calculate_per -- 统计计算每期双色球表
 * @author lizhihao 
 */
@Service
public class LotteryCalculatePerService {

	/** 底层 Mapper 对象 */
	@Autowired
	LotteryCalculatePerMapper lotteryCalculatePerMapper;
	@Autowired
	LotteryMapper lotteryMapper;

	/** 增 */
	int add(LotteryCalculatePer l){
		return lotteryCalculatePerMapper.add(l);
	}

	/** 删 */
	int delete(Long id){
		return lotteryCalculatePerMapper.delete(id);
	}

	/** 改 */
	int update(LotteryCalculatePer l){
		return lotteryCalculatePerMapper.update(l);
	}

	/** 查 */
	LotteryCalculatePer getById(Long id){
		return lotteryCalculatePerMapper.getById(id);
	}

	/** 查集合 - 根据条件（参数为空时代表忽略指定条件） */  
	List<LotteryCalculatePer> getList(SoMap so) {
		return lotteryCalculatePerMapper.getList(so);	
	}

	public void lotteryCalculatePer(){
		//删除数据
		lotteryCalculatePerMapper.deleteAll();

		//全量同步
		List<Lottery> list = lotteryMapper.getList(new SoMap());
		if(CollectionUtils.isEmpty(list)){
			throw new RuntimeException("未查到数据!");
		}
		Collections.reverse(list);
		List<LotteryCalculatePer> lotteryCalculatePers = new ArrayList<>(10000);
		Map<String, Map<String,String>> map = new HashMap<>(100);
		for (int i = 0; i < list.size(); i++) {
			lotteryCalculatePers.add(getLotteryCalculatePer(list, map, i));
		}
		Collections.reverse(lotteryCalculatePers);
		List<List<LotteryCalculatePer>> listList = Lists.partition(lotteryCalculatePers, 1000);
		listList.stream().forEach(v->{
			lotteryCalculatePerMapper.batchInsertLotteryCalculatePer(v);
		});
	}

	@NotNull
	public LotteryCalculatePer getLotteryCalculatePer(List<Lottery> list, Map<String, Map<String, String>> map, int i) {
		LotteryCalculatePer lotteryCalculatePer = getLotteryCalculatePer(list.get(i));
		if(i >0){
			Lottery lottery = list.get(i - 1);
			int beforeCodeCount = StringUtils.arraysContainerStr(list.get(i).getRed().split(","), lottery.getRed());
			lotteryCalculatePer.setBeforeCodeCount(beforeCodeCount);
		}
		//按照年-期号分组
		String year = lotteryCalculatePer.getCode().substring(0, 4);
		String number = lotteryCalculatePer.getCode().substring(4, 7);
		Map<String,String> currentMap = map.get(year);
		if(currentMap == null){
			currentMap = new HashMap<>(200);
		}
		if(Integer.valueOf(number) > 10){
			String red = currentMap.get(StringUtils.intToStr(Integer.valueOf(number) - 10));
			int beforeCommonCodeCount = StringUtils.arraysContainerStr(list.get(i).getRed().split(","), red);
			lotteryCalculatePer.setBeforeCommonCodeCount(beforeCommonCodeCount);
		}
		currentMap.put(number,lotteryCalculatePer.getRed());
		map.put(year, currentMap);
		return lotteryCalculatePer;
	}

	@NotNull
	private LotteryCalculatePer getLotteryCalculatePer(Lottery v) {
		LotteryCalculatePer l = new LotteryCalculatePer();
		l.setCode(v.getCode());
		l.setBlue(v.getBlue());
		l.setRed(v.getRed());
		l.setRedRangeRatio(RuleUtils.calRange(v.getRed()));
		l.setRedParityRatio(RuleUtils.calParityRatio(v.getRed()));
		l.setRedSum(RuleUtils.calRedSum(v.getRed()));
		l.setBlueRange(RuleUtils.getBlueArea(v.getBlue()));
		l.setBlueBigSmall(RuleUtils.getBlueBigSmall(v.getBlue()));
		l.setBlueParity(RuleUtils.getParityRatio(v.getBlue()));
		//解析年月周
		String date = v.getDate();
		l.setYear(Ttime.getYear(date));
		l.setMonth(Ttime.getMonth(date));
		l.setWeek(Ttime.getWeekOfYear(Ttime.getYearMonthDay(date)));
        //九转连环图
		l.setNineTurn09(PersonalLawUtils.calNineTurn(v.getRed(), RuleConstants.NINE_TURN_SERIAL_DIAGRAM_09));
		l.setNineTurn17(PersonalLawUtils.calNineTurn(v.getRed(), RuleConstants.NINE_TURN_SERIAL_DIAGRAM_17));
		l.setNineTurn33(PersonalLawUtils.calNineTurn(v.getRed(), RuleConstants.NINE_TURN_SERIAL_DIAGRAM_33));
        //连号情况
		l.setConsecutiveNumbersCount(RuleUtils.countConsecutiveSets(v.getRed()));
		l.setMaxConsecutiveNumbers(RuleUtils.getConsecutiveInfo(v.getRed()));
		return l;
	}

	public void add(Lottery v){
		lotteryCalculatePerMapper.add(getLotteryCalculatePer( v));
	}

}
