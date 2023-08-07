package com.pj.project.aps;

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
public class APS_FC {

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
        APS_FC.userMapper = userMapper;
        APS_FC.categoryMapper = categoryMapper;
        APS_FC.passwordMapper = passwordMapper;
        APS_FC.publicMapper = publicMapper;

        APS_FC.userService = userService;
        APS_FC.categoryService = categoryService;
        APS_FC.passwordService = passwordService;
        APS_FC.publicService = publicService;
    }


}