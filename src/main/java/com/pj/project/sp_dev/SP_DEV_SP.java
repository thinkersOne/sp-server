package com.pj.project.sp_dev;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pj.project.sp_dev.public4mapper.PublicMapper;
import com.pj.project.sp_dev.public4mapper.PublicService;

/**
 * 公共Mapper 与 公共Service 
 * @author kong
 *
 */
@Component
public class SP_DEV_SP {
	

	/**
	 * 公共Mapper
	 */
	public static PublicMapper publicMapper;	
	/**
	 * 公共Service
	 */
	public static PublicService publicService;				
	
	/**
	 * json序列化对象 
	 */
	public static ObjectMapper objectMapper;
	
	// 注入 
	@Autowired
	public void setBean(
			PublicMapper publicMapper,
			PublicService publicService,
			ObjectMapper objectMapper
			) {
		SP_DEV_SP.publicMapper = publicMapper;
		SP_DEV_SP.publicService = publicService;
		SP_DEV_SP.objectMapper = objectMapper;
	}
	
	
	
	
}
