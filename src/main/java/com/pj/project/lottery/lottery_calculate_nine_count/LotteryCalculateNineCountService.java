package com.pj.project.lottery.lottery_calculate_nine_count;

import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.pj.models.so.SoMap;
import com.pj.project.lottery.lottery_calculate_per.LotteryCalculatePer;
import com.pj.project.lottery.lottery_calculate_per.LotteryCalculatePerMapper;
import com.pj.project.lottery.unionLotto.domain.Lottery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * Service: lottery_calculate_nine_count -- 统计九转中四行数据每行上出现个数的统计表
 * @author lizhihao 
 */
@Service
public class LotteryCalculateNineCountService {

	/** 底层 Mapper 对象 */
	@Autowired
	LotteryCalculateNineCountMapper lotteryCalculateNineCountMapper;
	@Autowired
	LotteryCalculatePerMapper lotteryCalculatePerMapper;

	/** 增 */
	int add(LotteryCalculateNineCount l){
		return lotteryCalculateNineCountMapper.add(l);
	}

	/** 删 */
	int delete(Long id){
		return lotteryCalculateNineCountMapper.delete(id);
	}

	/** 改 */
	int update(LotteryCalculateNineCount l){
		return lotteryCalculateNineCountMapper.update(l);
	}

	/** 查 */
	LotteryCalculateNineCount getById(Long id){
		return lotteryCalculateNineCountMapper.getById(id);
	}

	/** 查集合 - 根据条件（参数为空时代表忽略指定条件） */  
	List<LotteryCalculateNineCount> getList(SoMap so) {
		return lotteryCalculateNineCountMapper.getList(so);	
	}

	public void lotteryCalculateNineCount(){
		//删除数据
		lotteryCalculateNineCountMapper.deleteAll();

		//全量同步
		List<LotteryCalculateNineCount> nineCountList = lotteryCalculatePerMapper.getNineCountList();
		if(CollectionUtils.isEmpty(nineCountList)){
			throw new RuntimeException("未查到数据!");
		}

		List<List<LotteryCalculateNineCount>> listList = Lists.partition(nineCountList, 1000);
		listList.stream().forEach(v->{
			lotteryCalculateNineCountMapper.batchInsertLotteryCalculateNineCount(v);
		});
	}
}
