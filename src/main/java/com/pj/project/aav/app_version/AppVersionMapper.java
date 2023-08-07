package com.pj.project.aav.app_version;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Mapper: app_version -- app版本管理表
 * @author lizhihao 
 */

@Mapper
@Repository
public interface AppVersionMapper {

	/**
	 * 增  
	 * @param a 实体对象 
	 * @return 受影响行数 
	 */
	int add(AppVersion a);

	/**
	 * 删  
	 * @param id 要删除的数据id  
	 * @return 受影响行数 
	 */
	int delete(Long id);	 

	/** 
	 * 改  
	 * @param a 实体对象 
	 * @return 受影响行数 
	 */
	int update(AppVersion a);

	/** 
	 * 查 - 根据id  
	 * @param id 要查询的数据id 
	 * @return 实体对象 
	 */
	AppVersion getById(Long id);	 



}
