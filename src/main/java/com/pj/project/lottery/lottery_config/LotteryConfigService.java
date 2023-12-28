package com.pj.project.lottery.lottery_config;

import java.util.List;

import com.pj.models.so.SoMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service: lottery_config -- 配置表
 * @author lizhihao 
 */
@Service
public class LotteryConfigService {

	/** 底层 Mapper 对象 */
	@Autowired
	LotteryConfigMapper lotteryConfigMapper;

	/** 增 */
	int add(LotteryConfig l){
		return lotteryConfigMapper.add(l);
	}

	/** 删 */
	int delete(Long id){
		return lotteryConfigMapper.delete(id);
	}

	/** 改 */
	int update(LotteryConfig l){
		return lotteryConfigMapper.update(l);
	}

	/** 查 */
	LotteryConfig getById(Long id){
		return lotteryConfigMapper.getById(id);
	}

	/** 查集合 - 根据条件（参数为空时代表忽略指定条件） */  
	List<LotteryConfig> getList(SoMap so) {
		return lotteryConfigMapper.getList(so);	
	}
	

}
