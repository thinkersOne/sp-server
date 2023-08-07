package com.pj.project.aps;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 公共Mapper 与 公共Service
 * @author kong
 *
 */
@Component
public class APS_SP {

    /**
     * json序列化对象
     */
    public static ObjectMapper objectMapper;

    // 注入
    @Autowired
    public void setBean(
            ObjectMapper objectMapper
    ) {
        APS_SP.objectMapper = objectMapper;
    }




}