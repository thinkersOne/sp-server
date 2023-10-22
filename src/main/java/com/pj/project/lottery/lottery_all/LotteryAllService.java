package com.pj.project.lottery.lottery_all;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.pj.models.so.SoMap;
import com.pj.project.lottery.unionLotto.domain.ConvertDoubleSpheresReq;
import com.pj.project.lottery.unionLotto.utils.CombinationUtils;
import com.pj.project.lottery.unionLotto.utils.RuleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service: lottery_all -- 所有可能得红球组合
 * @author lizhihao 
 */
@Service
public class LotteryAllService {

	/** 底层 Mapper 对象 */
	@Autowired
	LotteryAllMapper lotteryAllMapper;

	/** 增 */
	int add(LotteryAll l){
		return lotteryAllMapper.add(l);
	}

	void batchAdd(){
		String chooseLotteryRed = "01,02,03,04,05,06,07,08,09,10,11,12,13,"+
				"14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33";
		List<String> list = Arrays.asList(chooseLotteryRed.split(","));
		List<String> resList = new ArrayList<String>();
		CombinationUtils.recursive(list, "", 17,resList);
		List<LotteryAll> lotteryAlls = resList.stream().map(v -> {
			LotteryAll l = new LotteryAll();
			l.setRed(v);
			return l;
		}).collect(Collectors.toList());
		lotteryAllMapper.batchInsertLotteryAll(lotteryAlls);
	}

	/** 删 */
	int delete(Long id){
		return lotteryAllMapper.delete(id);
	}

	/** 改 */
	int update(LotteryAll l){
		return lotteryAllMapper.update(l);
	}

	/** 查 */
	LotteryAll getById(Long id){
		return lotteryAllMapper.getById(id);
	}

	/** 查集合 - 根据条件（参数为空时代表忽略指定条件） */  
	List<LotteryAll> getList(SoMap so) {
		return lotteryAllMapper.getList(so);	
	}
	

}
