package com.pj.project.aps.password;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Model: password -- 密码表
 * @author lizhihao
 */
@Data
@Accessors(chain = true)
public class Password implements Serializable {

	// ---------- 模块常量 ----------
	/**
	 * 序列化版本id
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 此模块对应的表名
	 */
	public static final String TABLE_NAME = "password";
	/**
	 * 此模块对应的权限码
	 */
	public static final String PERMISSION_CODE = "password";


	// ---------- 表中字段 ----------
	/**
	 * 主键id
	 */
	@JsonProperty("Id")
	public Long id;

	/**
	 * 所属用户ID
	 */
	public Long userId;

	/**
	 * 密码分类ID
	 */
	@JsonProperty("CategoryId")
	public Long categoryId;

	@JsonProperty("CategoryName")
	public String categoryName;

	/**
	 * 密码标题
	 */
	@JsonProperty("Title")
	public String title;

	/**
	 * 账号
	 */
	@JsonProperty("Account")
	public String account;

	/**
	 * 密码
	 */
	@JsonProperty("Password")
	public String password;

	/**
	 * 登录地址
	 */
	@JsonProperty("Url")
	public String url;

	/**
	 * 备注
	 */
	@JsonProperty("Notes")
	public String notes;

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