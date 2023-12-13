package com.pj.project.lottery.lottery_red_proportion;

import java.util.*;

import com.google.common.collect.Lists;
import com.pj.models.so.SoMap;
import com.pj.project.lottery.lottery_calculate_per.LotteryCalculatePer;
import com.pj.project.lottery.lottery_calculate_per.LotteryCalculatePerMapper;
import com.pj.project.lottery.unionLotto.domain.Lottery;
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
		List<LotteryCalculatePer> lotteryCalculatePers = new ArrayList<>(10000);
		Map<String, Map<String,String>> map = new HashMap<>(100);
		for (int i = 0; i < list.size(); i++) {

		}
		Collections.reverse(lotteryCalculatePers);
		List<List<LotteryCalculatePer>> listList = Lists.partition(lotteryCalculatePers, 1000);
		listList.stream().forEach(v->{
			lotteryCalculatePerMapper.batchInsertLotteryCalculatePer(v);
		});
	}
}
