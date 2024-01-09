package com.pj.project.lottery.lottery_calculate_count;

import java.util.List;

import com.pj.models.so.SoMap;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Mapper: lottery_calculate_count -- 按照不同时间维度统计每个红蓝球情况
 * @author lizhihao 
 */

@Mapper
@Repository
public interface LotteryCalculateCountMapper {

	/**
	 * 增  
	 * @param l 实体对象 
	 * @return 受影响行数 
	 */
	int add(LotteryCalculateCount l);

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
	int update(LotteryCalculateCount l);

	/** 
	 * 查 - 根据id  
	 * @param id 要查询的数据id 
	 * @return 实体对象 
	 */
	LotteryCalculateCount getById(Long id);	 

	/**
	 * 查集合 - 根据条件（参数为空时代表忽略指定条件）
	 * @param so 参数集合 
	 * @return 数据列表 
	 */
	List<LotteryCalculateCount> getList(SoMap so);

	List<LotteryCalculateCountAvgVo> getAvgListYear(SoMap so);
	List<LotteryCalculateCountAvgVo> getAvgListMonth(SoMap so);

	void batchInsertLotteryCalculateCount(List<LotteryCalculateCount> list);

	void deleteAll();

	LotteryCalculateCount getMaxLotteryCalculateCount(int calType);
	List<LotteryCalculateCount> getMaxLotteryCalculateCounts();

}
