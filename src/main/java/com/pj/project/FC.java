package com.pj.project;

import com.pj.project.category.CategoryMapper;
import com.pj.project.category.CategoryService;
import com.pj.project.password.PasswordMapper;
import com.pj.project.password.PasswordService;
import com.pj.project.user.UserMapper;
import com.pj.project.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pj.project.public4mapper.PublicMapper;
import com.pj.project.public4mapper.PublicService;

/**
 * SpringBean依赖清单，项目中所有Bean在此定义
 */
@Component
public class FC {

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
			FC.userMapper = userMapper;
			FC.categoryMapper = categoryMapper;
			FC.passwordMapper = passwordMapper;
			FC.publicMapper = publicMapper;
			
			FC.userService = userService;
			FC.categoryService = categoryService;
			FC.passwordService = passwordService;
			FC.publicService = publicService;
	}


}