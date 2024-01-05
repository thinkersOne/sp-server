package com.pj.project.lottery.lottery_strategy_record;

import java.util.List;

import com.pj.models.so.SoMap;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Mapper: lottery_strategy_record -- 策略记录表
 * @author lizhihao 
 */

@Mapper
@Repository
public interface LotteryStrategyRecordMapper {

	/**
	 * 增  
	 * @param l 实体对象 
	 * @return 受影响行数 
	 */
	int add(LotteryStrategyRecord l);

	/**
	 * 删  
	 * @param id 要删除的数据id  
	 * @return 受影响行数 
	 */
	int delete(Long id);	 

	/** 
	 * 改  
	 * @param l 实体对象 
	 * @return 受影响行数 
	 */
	int update(LotteryStrategyRecord l);

	/** 
	 * 查 - 根据id  
	 * @param id 要查询的数据id 
	 * @return 实体对象 
	 */
	LotteryStrategyRecord getById(Long id);	 

	/**
	 * 查集合 - 根据条件（参数为空时代表忽略指定条件）
	 * @param so 参数集合 
	 * @return 数据列表 
	 */
	List<LotteryStrategyRecord> getList(SoMap so);


}
