package com.pj.project.lottery;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pj.project.sp_dev.public4mapper.PublicMapper;
import com.pj.project.sp_dev.public4mapper.PublicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 公共Mapper 与 公共Service 
 * @author kong
 *
 */
@Component
public class LOTTERY_SP {
	

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
		LOTTERY_SP.publicMapper = publicMapper;
		LOTTERY_SP.publicService = publicService;
		LOTTERY_SP.objectMapper = objectMapper;
	}
	
	
	
	
}
