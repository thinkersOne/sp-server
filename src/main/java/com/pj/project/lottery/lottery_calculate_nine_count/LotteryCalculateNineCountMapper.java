package com.pj.project.lottery.lottery_calculate_nine_count;

import java.util.List;

import com.pj.models.so.SoMap;
import com.pj.project.lottery.lottery_calculate_per.LotteryCalculatePer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Mapper: lottery_calculate_nine_count -- 统计九转中四行数据每行上出现个数的统计表
 * @author lizhihao 
 */

@Mapper
@Repository
public interface LotteryCalculateNineCountMapper {

	/**
	 * 增  
	 * @param l 实体对象 
	 * @return 受影响行数 
	 */
	int add(LotteryCalculateNineCount l);

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
	int update(LotteryCalculateNineCount l);

	/** 
	 * 查 - 根据id  
	 * @param id 要查询的数据id 
	 * @return 实体对象 
	 */
	LotteryCalculateNineCount getById(Long id);	 

	/**
	 * 查集合 - 根据条件（参数为空时代表忽略指定条件）
	 * @param so 参数集合 
	 * @return 数据列表 
	 */
	List<LotteryCalculateNineCount> getList(SoMap so);
	void batchInsertLotteryCalculateNineCount(List<LotteryCalculateNineCount> list);
	void deleteAll();

	List<NineMinAndMaxDTO> getNineMinAndMaxs();

}
