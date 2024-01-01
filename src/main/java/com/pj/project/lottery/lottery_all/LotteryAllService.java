package com.pj.project.lottery.lottery_all;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.pj.current.global.LotteryConstant;
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

	public void syncData(){
		//删除数据
		lotteryAllMapper.deleteAll();
		String chooseLotteryRed = LotteryConstant.LOTTERY_RED;
		List<String> list = Arrays.asList(chooseLotteryRed.split(","));
		List<String> resList = new ArrayList<String>();
		CombinationUtils.recursive(list, "", 17,resList);
		List<LotteryAll> lotteryAlls = resList.stream().map(v -> {
			LotteryAll l = new LotteryAll();
			l.setRed(v);
			return l;
		}).collect(Collectors.toList());
		//分片：1000
		List<List<LotteryAll>> listList = Lists.partition(lotteryAlls, 1000);
		listList.stream().forEach(v->{
			lotteryAllMapper.batchInsertLotteryAll(v);
		});
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
