package com.pj.project.lottery.unionLotto.domain;

import com.alibaba.druid.util.StringUtils;
import com.pj.current.global.GlobalStatusCode;

import java.io.Serializable;

/**
 *
 * Copyright:   Copyright 2007 - 2017 MPR Tech. Co. Ltd. All Rights Reserved.
 * Date:        2017年5月25日 下午4:25:39
 * Author:      cncbox
 * Version:     1.0.0.0
 * Description: 返回实体类
 * @param <T> T
 */
//@XmlRootElement
public class ResultRes<T> implements Serializable {

    /**
     *
     */
    private static final String RESULT_SUCCESS = "200";
    private static final long serialVersionUID = 1L;

    private String code = RESULT_SUCCESS;

    private String msg = "SUCCESS";

    private T rows;

    public ResultRes() {
    }

    public ResultRes(GlobalStatusCode globalStatusCode) {
        this(globalStatusCode, null);
    }

    public ResultRes(String statusCode, String message) {
        this(statusCode, message, null);
    }

    public ResultRes(GlobalStatusCode globalStatusCode, T data) {
        this(globalStatusCode.getStatusCode(), globalStatusCode.getMessage(), data,null);
    }

    /**
     *
     * @param globalStatusCode
     * @param data
     * @param s message 中可以存在{}占位符，通过此参数按照顺序逐个替换
     */
    public ResultRes(GlobalStatusCode globalStatusCode, T data, String... s) {
        this(globalStatusCode.getStatusCode(), globalStatusCode.getMessage(), data,s);
    }

    public ResultRes(String code, String msg, T data) {
        this.rows = data;
        this.msg = msg;
        this.code = code;
    }

    public ResultRes(String code, String msg, T data, String... s) {
        this.rows = data;
        this.msg = msg;
        this.code = code;
        if(s != null){
            for(String _s :s){
                this.msg = this.msg.replace("{}",_s);
            }
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getRows() {
        return rows;
    }

    public void setRows(T data) {
        this.rows = data;
    }


    public void setStatusCode(GlobalStatusCode statusCode) {
        this.code = statusCode.getStatusCode();
        this.msg = statusCode.getMessage();
    }

    public boolean hasError() {
        return !StringUtils.equalsIgnoreCase(this.getCode(), RESULT_SUCCESS);
    }
}
