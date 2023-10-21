package com.pj.project.lottery.lottery_calculate_per;

import java.util.List;

import com.pj.models.so.SoMap;
import com.pj.project.lottery.unionLotto.domain.Lottery;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Mapper: lottery_calculate_per -- 统计计算每期双色球表
 * @author lizhihao 
 */

@Mapper
@Repository
public interface LotteryCalculatePerMapper {

	/**
	 * 增  
	 * @param l 实体对象 
	 * @return 受影响行数 
	 */
	int add(LotteryCalculatePer l);

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
	int update(LotteryCalculatePer l);

	/** 
	 * 查 - 根据id  
	 * @param id 要查询的数据id 
	 * @return 实体对象 
	 */
	LotteryCalculatePer getById(Long id);	 

	/**
	 * 查集合 - 根据条件（参数为空时代表忽略指定条件）
	 * @param so 参数集合 
	 * @return 数据列表 
	 */
	List<LotteryCalculatePer> getList(SoMap so);
	void batchInsertLotteryCalculatePer(List<LotteryCalculatePer> list);
	void deleteAll();
}
