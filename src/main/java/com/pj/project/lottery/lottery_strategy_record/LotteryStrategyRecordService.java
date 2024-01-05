package com.pj.project.lottery.lottery_strategy_record;

import java.util.List;

import com.pj.models.so.SoMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service: lottery_strategy_record -- 策略记录表
 * @author lizhihao 
 */
@Service
public class LotteryStrategyRecordService {

	/** 底层 Mapper 对象 */
	@Autowired
	LotteryStrategyRecordMapper lotteryStrategyRecordMapper;

	/** 增 */
	int add(LotteryStrategyRecord l){
		return lotteryStrategyRecordMapper.add(l);
	}

	/** 删 */
	int delete(Long id){
		return lotteryStrategyRecordMapper.delete(id);
	}

	/** 改 */
	int update(LotteryStrategyRecord l){
		return lotteryStrategyRecordMapper.update(l);
	}

	/** 查 */
	LotteryStrategyRecord getById(Long id){
		return lotteryStrategyRecordMapper.getById(id);
	}

	/** 查集合 - 根据条件（参数为空时代表忽略指定条件） */  
	List<LotteryStrategyRecord> getList(SoMap so) {
		return lotteryStrategyRecordMapper.getList(so);	
	}
	

}
