package com.pj.project.user;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pj.utils.so.*;
import org.springframework.stereotype.Repository;

/**
 * Mapper: user -- 用户表
 * @author lizhihao 
 */

@Mapper
@Repository
public interface UserMapper {

	/**
	 * 增  
	 * @param u 实体对象 
	 * @return 受影响行数 
	 */
	int add(User u);

	/**
	 * 删  
	 * @param id 要删除的数据id  
	 * @return 受影响行数 
	 */
	int delete(Long id);	 

	/** 
	 * 改  
	 * @param u 实体对象 
	 * @return 受影响行数 
	 */
	int update(User u);

	/** 
	 * 查 - 根据id  
	 * @param id 要查询的数据id 
	 * @return 实体对象 
	 */
	User getById(Long id);	 

	/**
	 * 查集合 - 根据条件（参数为空时代表忽略指定条件）
	 * @param so 参数集合 
	 * @return 数据列表 
	 */
	List<User> getList(SoMap so);


}
