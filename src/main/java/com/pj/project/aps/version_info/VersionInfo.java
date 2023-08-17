package com.pj.project.aps.version_info;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Model: version_info -- 版本更新表
 * @author lizhihao
 */
@Data
@Accessors(chain = true)
public class VersionInfo implements Serializable {

    // ---------- 模块常量 ----------
    /**
     * 序列化版本id
     */
    private static final long serialVersionUID = 1L;
    /**
     * 此模块对应的表名
     */
    public static final String TABLE_NAME = "version_info";
    /**
     * 此模块对应的权限码
     */
    public static final String PERMISSION_CODE = "version-info";


    // ---------- 表中字段 ----------
    /**
     * 主键id
     */
    public Long id;

    /**
     * 当前版本号
     */
    public String version;

    /**
     * 安装程序下载地址
     */
    public String url;

    /**
     * 版本更新内容说明
     */
    public String description;

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