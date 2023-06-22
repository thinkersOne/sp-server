package com.pj.project.admin;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Model: sp_admin -- 系统管理员表
 * @author lizhihao 
 */
@Data
@Accessors(chain = true)
public class SpAdmin implements Serializable {

	// ---------- 模块常量 ----------
	/**
	 * 序列化版本id 
	 */
	private static final long serialVersionUID = 1L;	
	/**
	 * 此模块对应的表名 
	 */
	public static final String TABLE_NAME = "sp_admin";	
	/**
	 * 此模块对应的权限码 
	 */
	public static final String PERMISSION_CODE = "sp-admin";	


	// ---------- 表中字段 ----------
	/**
	 * id，--主键、自增 
	 */
	public Long id;	

	/**
	 * admin名称 
	 */
	public String name;	

	/**
	 * 头像地址 
	 */
	public String avatar;	

	/**
	 * 密码 
	 */
	public String password;	

	/**
	 * 明文密码 
	 */
	public String pw;	

	/**
	 * 手机号 
	 */
	public String phone;	

	/**
	 * 所属角色id 
	 */
	public Long roleId;

	/**
	 * 账号状态(1=正常, 2=禁用) 
	 */
	public Integer status;	

	/**
	 * 创建自哪个管理员 
	 */
	public Long createByAid;	

	/**
	 * 创建时间 
	 */
	public String createTime;	

	/**
	 * 上次登陆时间 
	 */
	public String loginTime;	

	/**
	 * 上次登陆IP 
	 */
	public String loginIp;	

	/**
	 * 登陆次数 
	 */
	public Integer loginCount;	

	/**
	 * 会员类型  0:普通  1:会员
	 */
	public String userType;

	// -------- 额外字段

	/** 所属角色名称   */
	private String roleName;


	/** 防止密码被传递到前台  */
    public String getPassword(){
    	return "********";
    }
    /** 获取真实密码   */
    @JsonIgnore()
    public String getPassword2(){
    	return this.password;
    }
	


}
