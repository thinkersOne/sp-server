package com.pj.project.aps.category;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Model: category -- 密码分类表
 * @author lizhihao
 */
@Data
@Accessors(chain = true)
public class Category implements Serializable {

	// ---------- 模块常量 ----------
	/**
	 * 序列化版本id
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 此模块对应的表名
	 */
	public static final String TABLE_NAME = "category";
	/**
	 * 此模块对应的权限码
	 */
	public static final String PERMISSION_CODE = "category";


	// ---------- 表中字段 ----------
	/**
	 * 主键id
	 */
	public Long id;
	/**
	 * 所属用户ID
	 */
	public Long userId;

	/**
	 * 分类名称
	 */
	@JsonProperty("Name")
	public String name;

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