package com.pj.project.aps.user;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Model: user -- 用户表
 * @author lizhihao
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements Serializable {

	// ---------- 模块常量 ----------
	/**
	 * 序列化版本id
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 此模块对应的表名
	 */
	public static final String TABLE_NAME = "user";
	/**
	 * 此模块对应的权限码
	 */
	public static final String PERMISSION_CODE = "user";


	// ---------- 表中字段 ----------
	/**
	 * 主键id
	 */
	public Long id;

	/**
	 * 用户名
	 */
	@JsonProperty("name")
	public String username;

	/**
	 * 密码
	 */
	public String password;

	/**
	 * 创建人
	 */
	public String createBy;

	/**
	 * 创建时间
	 */
	public String createTime;

	/**
	 * 更新人
	 */
	public String updateBy;

	/**
	 * 更新时间
	 */
	public String updateTime;








}