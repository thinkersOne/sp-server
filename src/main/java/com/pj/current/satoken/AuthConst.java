package com.pj.current.satoken;

/**
 * 权限码常量  
 * @author kong
 *
 */
public final class AuthConst {

	/**
	 *  私有构造方法 
	 */
	private AuthConst() {
	}
	
	
	// --------------- 代表身份的权限 --------------- 
	
	public static final String BAS = "bas"; 			 // 身份相关 
	public static final String DEV = "dev"; 			 // 开发者权限 	--- 系统最高权限
	public static final String IN_SYSTEM = "in-system";		  // 进入后台权限，没有此权限无法进入后台管理
	
	
	// --------------- 所有权限码 --------------- 

	public static final String AUTH = "auth";		   // 权限管理
	public static final String ROLE_LIST = "role-list";		    // 权限管理 - 角色管理
	public static final String MENU_LIST = "menu-list";		   // 权限管理 - 菜单列表
	public static final String ADMIN_LIST = "admin-list";		   // 权限管理 - 管理员列表
	public static final String ADMIN_ADD = "admin-add";		   // 权限管理 - 管理员添加

	public static final String CONSOLE = "console";		   // 监控中心
	public static final String SQL_CONSOLE = "sql-console";		      // 监控中心 - SQL监控
	public static final String REDIS_CONSOLE = "redis-console";		   // 监控中心 - Redis 控制台
	public static final String APILOG_LIST = "apilog-list";		   // 监控中心 - API 请求日志

	public static final String SP_CFG = "sp-cfg";		  	 // 系统配置
	public static final String SP_CFG_APP = "sp-cfg-app";		  	 // 系统配置 - 系统对公配置
	public static final String SP_CFG_SERVER = "sp-cfg-server";		   // 系统配置 - 服务器私有配置

	public static final String USER_ADD = "user-add";
	public static final String USER_DELETE = "user-delete";
	public static final String USER_DELETE_BY_IDS = "user-deleteByIds";
	public static final String USER_UPDATE = "user-update";
	public static final String USER_GETBY＿ID = "user-getById";
	public static final String USER_GETLIST = "user-list";

	public static final String CATEGORY_ADD = "category-add";
	public static final String CATEGORY_DELETE = "category-delete";
	public static final String CATEGORY_DELETE_BY_IDS = "category-deleteByIds";
	public static final String CATEGORY_UPDATE = "category-update";
	public static final String CATEGORY_GETBY＿ID = "category-getById";
	public static final String CATEGORY_GETLIST = "category-list";
	public static final String CATEGORY_GETINFO = "category-info";

	public static final String PASSWORD_ADD = "password-add";
	public static final String PASSWORD_DELETE = "password-delete";
	public static final String PASSWORD_DELETE_BY_IDS = "password-deleteByIds";
	public static final String PASSWORD_UPDATE = "password-update";
	public static final String PASSWORD_GETBY＿ID = "password-getById";
	public static final String PASSWORD_GETLIST = "password-list";
	public static final String PASSWORD_INFO = "password-info";
	// --------------- 其它常量 --------------- 
	
	/** 在 SaSession 上存储 角色id 使用的key */
	public static final String ROLE_ID_KEY = "ROLE_ID";

	public static final String SP_VEDIO_ADD = "spVedio-add";
	public static final String SP_VEDIO_DELETE = "spVedio-delete";
	public static final String SP_VEDIO_DELETE_BY_IDS = "spVedio-deleteByIds";
	public static final String SP_VEDIO_UPDATE = "spVedio-update";
	public static final String SP_VEDIO_GETBY＿ID = "spVedio-getById";
	public static final String SP_VEDIO_GETLIST = "spVedio-list";
	public static final String SP_VEDIO_INFO = "spVedio-info";
}
