package com.pj.project.aps.memo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Model: memo -- 备忘录表
 * @author lizhihao
 */
@Data
@Accessors(chain = true)
public class Memo implements Serializable {

    // ---------- 模块常量 ----------
    /**
     * 序列化版本id
     */
    private static final long serialVersionUID = 1L;
    /**
     * 此模块对应的表名
     */
    public static final String TABLE_NAME = "memo";
    /**
     * 此模块对应的权限码
     */
    public static final String PERMISSION_CODE = "memo";


    // ---------- 表中字段 ----------
    /**
     * 主键id
     */
    public Long id;

    /**
     * 每项任务/备忘录
     */
    @JsonProperty("Content")
    public String content;

    /**
     * 用户id
     */
    public Long userId;

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