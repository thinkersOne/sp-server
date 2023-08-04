package com.pj.project.sys_user;

import java.util.List;

import com.pj.project.admin.SpAdmin;
import org.apache.ibatis.annotations.Mapper;

import com.pj.utils.so.*;
import org.springframework.stereotype.Repository;

/**
 * Mapper: sys_user -- 
 * @author lizhihao 
 */

@Mapper
@Repository
public interface SysUserMapper {

	int existsAccount(SysUser sysUser);
	/**
	 * 增  
	 * @param s 实体对象 
	 * @return 受影响行数 
	 */
	int add(SysUser s);

	/**
	 * 删  
	 * @param id 要删除的数据id  
	 * @return 受影响行数 
	 */
	int delete(Long id);	 

	/** 
	 * 改  
	 * @param s 实体对象 
	 * @return 受影响行数 
	 */
	int update(SysUser s);
	/**
	 * 查询，根据name
	 * @param name
	 * @return
	 */
	SysUser getByName(String name);

	/**
	 * 查询，根据 phone
	 * @param phone
	 * @return
	 */
	SysUser getByPhone(String phone);
	/** 
	 * 查 - 根据id  
	 * @param id 要查询的数据id 
	 * @return 实体对象 
	 */
	SysUser getById(Long id);	 

	/**
	 * 查集合 - 根据条件（参数为空时代表忽略指定条件）
	 * @param so 参数集合 
	 * @return 数据列表 
	 */
	List<SysUser> getList(SoMap so);


}
