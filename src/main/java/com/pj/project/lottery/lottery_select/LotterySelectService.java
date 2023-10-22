package com.pj.project.lottery.lottery_select;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pj.current.global.RuleConstants;
import com.pj.models.so.SoMap;
import com.pj.project.lottery.lottery_all.LotteryAll;
import com.pj.project.lottery.lottery_all.LotteryAllMapper;
import com.pj.project.lottery.lottery_calculate_nine.LotteryCalculateNineM;
import com.pj.project.lottery.lottery_calculate_nine.LotteryCalculateNineMapper;
import com.pj.project.lottery.lottery_calculate_per.LotteryCalculatePer;
import com.pj.project.lottery.lottery_calculate_per.LotteryCalculatePerM;
import com.pj.project.lottery.lottery_calculate_per.LotteryCalculatePerMapper;
import com.pj.project.lottery.unionLotto.enums.NineTypeEnum;
import com.pj.project.lottery.unionLotto.utils.PersonalLawUtils;
import com.pj.project.lottery.unionLotto.utils.RuleUtils;
import com.pj.utils.IntegerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * Service: lottery_select -- 经过初步筛选后的全量双色球表
 * @author lizhihao 
 */
@Service
public class LotterySelectService {

	/** 底层 Mapper 对象 */
	@Autowired
	LotterySelectMapper lotterySelectMapper;
	@Autowired
	LotteryAllMapper lotteryAllMapper;
	@Autowired
	LotteryCalculatePerMapper lotteryCalculatePerMapper;
	@Autowired
	LotteryCalculateNineMapper lotteryCalculateNineMapper;

	/** 增 */
	int add(LotterySelect l){
		return lotterySelectMapper.add(l);
	}

	/** 删 */
	int delete(Long id){
		return lotterySelectMapper.delete(id);
	}

	/** 改 */
	int update(LotterySelect l){
		return lotterySelectMapper.update(l);
	}

	/** 查 */
	LotterySelect getById(Long id){
		return lotterySelectMapper.getById(id);
	}

	/** 查集合 - 根据条件（参数为空时代表忽略指定条件） */  
	List<LotterySelect> getList(SoMap so) {
		return lotterySelectMapper.getList(so);	
	}

	void lotterySelect(){
		//删除数据
		lotterySelectMapper.deleteAll();

		List<LotterySelect> resultList = new ArrayList<LotterySelect>(1110000);
		//查询全量表并进行过滤
		List<LotteryAll> lotteryAllList = lotteryAllMapper.getList(new SoMap());
		//查询历史中奖号码
		List<LotteryCalculatePer> lotteryCalculatePerMapperList = lotteryCalculatePerMapper.getList(new SoMap());
		Map<String,LotteryCalculatePer> map = new HashMap<String,LotteryCalculatePer>(2000);
		lotteryCalculatePerMapperList.stream().forEach(v->{
			map.put(v.getRed(),v);
		});
		//查询历史和值最小值、最大值,连号个数：0~2、最大连号数：1~5
		LotteryCalculatePerM lotteryCalculatePerM = lotteryCalculatePerMapper.getMinAndMax();
		List<LotteryCalculateNineM> lotteryCalculateNineMs = lotteryCalculateNineMapper.getMinAndMax();
		LotteryCalculateNineM lotteryCalculateNineM09 = lotteryCalculateNineMs.stream().filter(v ->
						v.getNineTurnType() == NineTypeEnum.NINE09.getType()).findFirst().get();
		LotteryCalculateNineM lotteryCalculateNineM17 = lotteryCalculateNineMs.stream().filter(v ->
				v.getNineTurnType() == NineTypeEnum.NINE17.getType()).findFirst().get();
		LotteryCalculateNineM lotteryCalculateNineM33 = lotteryCalculateNineMs.stream().filter(v ->
				v.getNineTurnType() == NineTypeEnum.NINE33.getType()).findFirst().get();
		//开始进行过滤
		lotteryAllList.stream().forEach(v->{
			int redSum = RuleUtils.calRedSum(v.getRed());
			int consecutiveNumbersCount = RuleUtils.countConsecutiveSets(v.getRed());
			int maxConsecutiveNumbers = RuleUtils.getConsecutiveInfo(v.getRed());
			int[] nineTurnArray09 = PersonalLawUtils.calNineTurnArray(v.getRed(), RuleConstants.NINE_TURN_SERIAL_DIAGRAM_09);
			int[] nineTurnArray17 = PersonalLawUtils.calNineTurnArray(v.getRed(), RuleConstants.NINE_TURN_SERIAL_DIAGRAM_17);
			int[] nineTurnArray33 = PersonalLawUtils.calNineTurnArray(v.getRed(), RuleConstants.NINE_TURN_SERIAL_DIAGRAM_33);

			if(map.get(v.getRed()) == null && redSum >= lotteryCalculatePerM.getRedSumMin()
					&& redSum <= lotteryCalculatePerM.getRedSumMax() && consecutiveNumbersCount >=
					lotteryCalculatePerM.getConsecutiveNumbersCountMin() && consecutiveNumbersCount <=
					lotteryCalculatePerM.getConsecutiveNumbersCountMax()
					&& maxConsecutiveNumbers >= lotteryCalculatePerM.getMaxConsecutiveNumbersMin() &&
					maxConsecutiveNumbers <= lotteryCalculatePerM.getMaxConsecutiveNumbersMax() &&
					nineTurnArray09[0] <= lotteryCalculateNineM09.getNineTurnMax()
					&& nineTurnArray09[1] >= lotteryCalculateNineM09.getNineTurnMin() &&
					nineTurnArray17[0] <= lotteryCalculateNineM17.getNineTurnMax()
					&& nineTurnArray17[1] >= lotteryCalculateNineM17.getNineTurnMin() &&
					nineTurnArray33[0] <= lotteryCalculateNineM33.getNineTurnMax()
					&& nineTurnArray33[1] >= lotteryCalculateNineM33.getNineTurnMin()
					){
				LotterySelect lotterySelect = new LotterySelect();
				lotterySelect.setRed(v.getRed());
				resultList.add(lotterySelect);
			}
		});

		if(!CollectionUtils.isEmpty(resultList)){
			lotterySelectMapper.batchInsertLotterySelect(resultList);
		}

	}
	

}
