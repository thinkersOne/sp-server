package com.pj.project.sp_dev.admin;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.pj.models.so.SoMap;

/**
 * Mapper: 系统管理员表
 * @author kong
 */
@Mapper
public interface SpAdminMapper {


	/**
	 * 增 #{name}, #{password}, #{roleId}
	 * @param obj
	 * @return
	 */
	int add(SpAdmin obj);

	/**
	 * 删
	 * @param id
	 * @return
	 */
	int delete(long id);

	/**
	 * 改
	 * @param obj
	 * @return
	 */
	int update(SpAdmin obj);

	/**
	 * 查
	 * @param id
	 * @return
	 */
	SpAdmin getById(long id);

	/**
	 * 查
	 * @param so
	 * @return
	 */
	List<SpAdmin> getList(SoMap so);

	/**
	 * 查询，根据name
	 * @param name
	 * @return
	 */
	SpAdmin getByName(String name);

	/**
	 * 查询，根据 phone
	 * @param phone
	 * @return
	 */
	SpAdmin getByPhone(String phone);

	int existsAccount(SpAdmin admin);

	/**
	 * 修改指定账号的 最后登录记录
	 * @param id
	 * @param loginIp
	 * @return
	 */
	public int updateLoginLog(@Param("id")long id, @Param("loginIp")String loginIp);


}