package com.pj.project.aav.sp_image;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Model: sp_image --
 * @author lizhihao
 */
@Data
@Accessors(chain = true)
public class SpImage implements Serializable {

    // ---------- 模块常量 ----------
    /**
     * 序列化版本id
     */
    private static final long serialVersionUID = 1L;
    /**
     * 此模块对应的表名
     */
    public static final String TABLE_NAME = "sp_image";
    /**
     * 此模块对应的权限码
     */
    public static final String PERMISSION_CODE = "sp-image";


    // ---------- 表中字段 ----------
    /**
     * 主键id
     */
    public Long id;

    /**
     * 图片完整路径地址
     */
    public String url;

    /**
     * 1：注册登录  2：轮播图
     */
    public Integer type;

    /**
     * 创建时间
     */
    public String createTime;

    /**
     * 创建人
     */
    public String createBy;

    /**
     * 更新人
     */
    public String updateBy;

    /**
     * 更新时间
     */
    public String updateTime;


    public String description;

    public int status;



}