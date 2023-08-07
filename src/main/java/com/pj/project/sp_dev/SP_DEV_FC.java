package com.pj.project.sp_dev;

import com.pj.project.aps.category.CategoryMapper;
import com.pj.project.aps.category.CategoryService;
import com.pj.project.aps.password.PasswordMapper;
import com.pj.project.aps.password.PasswordService;
import com.pj.project.aps.user.UserMapper;
import com.pj.project.aps.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pj.project.sp_dev.public4mapper.PublicMapper;
import com.pj.project.sp_dev.public4mapper.PublicService;

/**
 * SpringBean依赖清单，项目中所有Bean在此定义
 */
@Component
public class SP_DEV_FC {

	// ======================================== 所有Mapper ============================================== 

	public static UserMapper userMapper;		// Mapper：用户表
	public static CategoryMapper categoryMapper;		// Mapper：密码分类表
	public static PasswordMapper passwordMapper;		// Mapper：密码表
	public static PublicMapper publicMapper;					// Mapper: 公共Mapper 



	// ======================================== 所有Service ============================================== 

	public static UserService userService;		// Service：用户表
	public static CategoryService categoryService;		// Service：密码分类表
	public static PasswordService passwordService;		// Service：密码表
	public static PublicService publicService;						// Service：公共service



	// ======================================== 所有注入所有Bean ============================================== 
	
	// 注入 
	@Autowired
	public void setBean(
			UserMapper userMapper,
			CategoryMapper categoryMapper,
			PasswordMapper passwordMapper,
			PublicMapper publicMapper,
			
			UserService userService,
			CategoryService categoryService,
			PasswordService passwordService,
			PublicService publicService
			) {
			SP_DEV_FC.userMapper = userMapper;
			SP_DEV_FC.categoryMapper = categoryMapper;
			SP_DEV_FC.passwordMapper = passwordMapper;
			SP_DEV_FC.publicMapper = publicMapper;
			
			SP_DEV_FC.userService = userService;
			SP_DEV_FC.categoryService = categoryService;
			SP_DEV_FC.passwordService = passwordService;
			SP_DEV_FC.publicService = publicService;
	}


}