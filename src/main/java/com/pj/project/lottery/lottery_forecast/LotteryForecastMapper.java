package com.pj.project.lottery.lottery_forecast;

import java.util.List;

import com.pj.models.so.SoMap;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Mapper: lottery_forecast -- 号码预测
 * @author lizhihao 
 */

@Mapper
@Repository
public interface LotteryForecastMapper {

	/**
	 * 增  
	 * @param l 实体对象 
	 * @return 受影响行数 
	 */
	int add(LotteryForecast l);

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
	int update(LotteryForecast l);

	/** 
	 * 查 - 根据id  
	 * @param id 要查询的数据id 
	 * @return 实体对象 
	 */
	LotteryForecast getById(Long id);	 

	/**
	 * 查集合 - 根据条件（参数为空时代表忽略指定条件）
	 * @param so 参数集合 
	 * @return 数据列表 
	 */
	List<LotteryForecast> getList(SoMap so);

	int deleteByIds(@Param("ids")List<?> ids);
}
