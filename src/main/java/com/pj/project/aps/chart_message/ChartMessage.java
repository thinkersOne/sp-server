package com.pj.project.aps.chart_message;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Model: chart_message -- AI聊天对话记录信息表
 * @author lizhihao
 */
@Data
@Accessors(chain = true)
@Getter
public class ChartMessage implements Serializable {

    // ---------- 模块常量 ----------
    /**
     * 序列化版本id
     */
    private static final long serialVersionUID = 1L;
    /**
     * 此模块对应的表名
     */
    public static final String TABLE_NAME = "chart_message";
    /**
     * 此模块对应的权限码
     */
    public static final String PERMISSION_CODE = "chart-message";


    // ---------- 表中字段 ----------
    /**
     * 主键id
     */
//	@JsonProperty("Id")
    public Long id;

    /**
     * 用户id
     */
    @JsonProperty("UserId")
    public Long userId;

    /**
     * 回复信息
     */
    @JsonProperty("Content")
    public String content;

    /**
     * ai回复
     */
    @JsonProperty("Sender")
    public String sender;

    public String role;
    public Integer plantform;
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