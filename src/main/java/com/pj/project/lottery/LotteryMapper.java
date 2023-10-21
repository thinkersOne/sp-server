package com.pj.project.lottery;

import java.util.List;

import com.pj.models.so.SoMap;
import com.pj.project.lottery.unionLotto.domain.Lottery;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Mapper: lottery -- 
 * @author lizhihao 
 */

@Mapper
@Repository
public interface LotteryMapper {

	/**
	 * 增  
	 * @param l 实体对象 
	 * @return 受影响行数 
	 */
	int add(Lottery l);

	void batchInsertLottery(List<Lottery> list);
	/**
	 * 删
	 * @return 受影响行数 
	 */
	int delete(String code);	 

	/** 
	 * 改  
	 * @param l 实体对象 
	 * @return 受影响行数 
	 */
	int update(Lottery l);

	/** 
	 * 查 - 根据id
	 * @return 实体对象 
	 */
	Lottery getById(String code);	 

	/**
	 * 查集合 - 根据条件（参数为空时代表忽略指定条件）
	 * @return 数据列表 
	 */
	List<Lottery> getList(SoMap so);

	void deleteAll();

	Lottery getCurrentLottery();

	List<Lottery> getLotteryByRed(String red);
}
