package com.pj.project.lottery.lottery_red_proportion;

import java.util.*;

import com.google.common.collect.Lists;
import com.pj.models.so.SoMap;
import com.pj.project.lottery.lottery_calculate_per.LotteryCalculatePer;
import com.pj.project.lottery.lottery_calculate_per.LotteryCalculatePerMapper;
import com.pj.project.lottery.unionLotto.domain.Lottery;
import com.pj.project.lottery.unionLotto.utils.RuleUtils;
import com.pj.utils.BigDecimalUtils;
import com.pj.utils.IntegerUtils;
import com.pj.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * Service: lottery_red_proportion -- 各红球号占比
 * @author lizhihao 
 */
@Service
public class LotteryRedProportionService {

	/** 底层 Mapper 对象 */
	@Autowired
	LotteryRedProportionMapper lotteryRedProportionMapper;
	@Autowired
	LotteryCalculatePerMapper lotteryCalculatePerMapper;

	/** 增 */
	int add(LotteryRedProportion l){
		return lotteryRedProportionMapper.add(l);
	}

	/** 删 */
	int delete(Long id){
		return lotteryRedProportionMapper.delete(id);
	}

	/** 改 */
	int update(LotteryRedProportion l){
		return lotteryRedProportionMapper.update(l);
	}

	/** 查 */
	LotteryRedProportion getById(Long id){
		return lotteryRedProportionMapper.getById(id);
	}

	/** 查集合 - 根据条件（参数为空时代表忽略指定条件） */  
	List<LotteryRedProportion> getList(SoMap so) {
		return lotteryRedProportionMapper.getList(so);	
	}

	public void lotteryRedProportion(){
		//删除数据
		lotteryRedProportionMapper.deleteAll();

		//全量同步
		List<LotteryCalculatePer> list = lotteryCalculatePerMapper.getList(new SoMap());
		if(CollectionUtils.isEmpty(list)){
			throw new RuntimeException("未查到数据!");
		}
		Collections.reverse(list);
		List<LotteryRedProportion> lotteryRedProportions = new ArrayList<>(10000);
		Map<String, Integer> redMap = new HashMap<>(100);
		String currentCode = "";
		int total = 0;
		for (int i = 0; i < list.size() + 1; i++) {
			String red = "";
			if(i == list.size()){
				currentCode = String.valueOf(Long.valueOf(list.get(i - 1).getCode())+ 1);
			}
			total = i * 6;
			if(i > 0){
				red = list.get(i - 1).getRed();
			}
			String[] redArrays = red.split(",");
			if(redArrays != null && redArrays.length == 6){
				redMap.put(redArrays[0], IntegerUtils.convertToInt(redMap.get(redArrays[0])) +1);
				redMap.put(redArrays[1], IntegerUtils.convertToInt(redMap.get(redArrays[1])) +1);
				redMap.put(redArrays[2], IntegerUtils.convertToInt(redMap.get(redArrays[2])) +1);
				redMap.put(redArrays[3], IntegerUtils.convertToInt(redMap.get(redArrays[3])) +1);
				redMap.put(redArrays[4], IntegerUtils.convertToInt(redMap.get(redArrays[4])) +1);
				redMap.put(redArrays[5], IntegerUtils.convertToInt(redMap.get(redArrays[5])) +1);
			}
			LotteryRedProportion l = new LotteryRedProportion();
			if(i == list.size()){
				l.setCode(currentCode);
			}else {
				l.setCode(list.get(i).getCode());
			}
			l.setRed1(BigDecimalUtils.divide(redMap.get(RuleUtils.PARTITION_ONE.get(0)),total));
			l.setRed2(BigDecimalUtils.divide(redMap.get(RuleUtils.PARTITION_ONE.get(1)),total));
			l.setRed3(BigDecimalUtils.divide(redMap.get(RuleUtils.PARTITION_ONE.get(2)),total));
			l.setRed4(BigDecimalUtils.divide(redMap.get(RuleUtils.PARTITION_ONE.get(3)),total));
			l.setRed5(BigDecimalUtils.divide(redMap.get(RuleUtils.PARTITION_ONE.get(4)),total));
			l.setRed6(BigDecimalUtils.divide(redMap.get(RuleUtils.PARTITION_ONE.get(5)),total));
			l.setRed7(BigDecimalUtils.divide(redMap.get(RuleUtils.PARTITION_ONE.get(6)),total));
			l.setRed8(BigDecimalUtils.divide(redMap.get(RuleUtils.PARTITION_ONE.get(7)),total));
			l.setRed9(BigDecimalUtils.divide(redMap.get(RuleUtils.PARTITION_ONE.get(8)),total));
			l.setRed10(BigDecimalUtils.divide(redMap.get(RuleUtils.PARTITION_ONE.get(9)),total));
			l.setRed11(BigDecimalUtils.divide(redMap.get(RuleUtils.PARTITION_ONE.get(10)),total));
			l.setRed12(BigDecimalUtils.divide(redMap.get(RuleUtils.PARTITION_TWO.get(0)),total));
			l.setRed13(BigDecimalUtils.divide(redMap.get(RuleUtils.PARTITION_TWO.get(1)),total));
			l.setRed14(BigDecimalUtils.divide(redMap.get(RuleUtils.PARTITION_TWO.get(2)),total));
			l.setRed15(BigDecimalUtils.divide(redMap.get(RuleUtils.PARTITION_TWO.get(3)),total));
			l.setRed16(BigDecimalUtils.divide(redMap.get(RuleUtils.PARTITION_TWO.get(4)),total));
			l.setRed17(BigDecimalUtils.divide(redMap.get(RuleUtils.PARTITION_TWO.get(5)),total));
			l.setRed18(BigDecimalUtils.divide(redMap.get(RuleUtils.PARTITION_TWO.get(6)),total));
			l.setRed19(BigDecimalUtils.divide(redMap.get(RuleUtils.PARTITION_TWO.get(7)),total));
			l.setRed20(BigDecimalUtils.divide(redMap.get(RuleUtils.PARTITION_TWO.get(8)),total));
			l.setRed21(BigDecimalUtils.divide(redMap.get(RuleUtils.PARTITION_TWO.get(9)),total));
			l.setRed22(BigDecimalUtils.divide(redMap.get(RuleUtils.PARTITION_TWO.get(10)),total));
			l.setRed23(BigDecimalUtils.divide(redMap.get(RuleUtils.PARTITION_THREE.get(0)),total));
			l.setRed24(BigDecimalUtils.divide(redMap.get(RuleUtils.PARTITION_THREE.get(1)),total));
			l.setRed25(BigDecimalUtils.divide(redMap.get(RuleUtils.PARTITION_THREE.get(2)),total));
			l.setRed26(BigDecimalUtils.divide(redMap.get(RuleUtils.PARTITION_THREE.get(3)),total));
			l.setRed27(BigDecimalUtils.divide(redMap.get(RuleUtils.PARTITION_THREE.get(4)),total));
			l.setRed28(BigDecimalUtils.divide(redMap.get(RuleUtils.PARTITION_THREE.get(5)),total));
			l.setRed29(BigDecimalUtils.divide(redMap.get(RuleUtils.PARTITION_THREE.get(6)),total));
			l.setRed30(BigDecimalUtils.divide(redMap.get(RuleUtils.PARTITION_THREE.get(7)),total));
			l.setRed31(BigDecimalUtils.divide(redMap.get(RuleUtils.PARTITION_THREE.get(8)),total));
			l.setRed32(BigDecimalUtils.divide(redMap.get(RuleUtils.PARTITION_THREE.get(9)),total));
			l.setRed33(BigDecimalUtils.divide(redMap.get(RuleUtils.PARTITION_THREE.get(10)),total));
			if(i < list.size()){
				l.setRed(list.get(i).getRed());
			}
			lotteryRedProportions.add(l);
		}
		Collections.reverse(lotteryRedProportions);
		List<List<LotteryRedProportion>> listList = Lists.partition(lotteryRedProportions, 1000);
		listList.stream().forEach(v->{
			lotteryRedProportionMapper.batchInsertLotteryRedProportion(v);
		});
	}
}
