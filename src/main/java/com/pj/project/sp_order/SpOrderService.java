package com.pj.project.sp_order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pj.utils.so.*;

/**
 * Service: sp_order -- 订单信息表
 * @author lizhihao 
 */
@Service
public class SpOrderService {

	/** 底层 Mapper 对象 */
	@Autowired
	SpOrderMapper spOrderMapper;

	/** 增 */
	int add(SpOrder s){
		return spOrderMapper.add(s);
	}

	/** 删 */
	int delete(Long id){
		return spOrderMapper.delete(id);
	}

	/** 改 */
	int update(SpOrder s){
		return spOrderMapper.update(s);
	}

	/** 查 */
	SpOrder getById(Long id){
		return spOrderMapper.getById(id);
	}

	/** 查集合 - 根据条件（参数为空时代表忽略指定条件） */  
	List<SpOrder> getList(SoMap so) { 
		return spOrderMapper.getList(so);	
	}
	

}
