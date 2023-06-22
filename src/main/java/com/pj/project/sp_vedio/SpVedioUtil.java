package com.pj.project.sp_vedio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pj.utils.sg.*;

/**
 * 工具类：sp_vedio -- 视频表
 * @author lizhihao 
 *
 */
@Component
public class SpVedioUtil {

	
	/** 底层 Mapper 对象 */
	public static SpVedioMapper spVedioMapper;
	@Autowired
	private void setSpVedioMapper(SpVedioMapper spVedioMapper) {
		SpVedioUtil.spVedioMapper = spVedioMapper;
	}
	
	
	/** 
	 * 将一个 SpVedio 对象进行进行数据完整性校验 (方便add/update等接口数据校验) [G] 
	 */
	static void check(SpVedio s) {
		AjaxError.throwByIsNull(s.id, "[] 不能为空");		// 验证:  
		AjaxError.throwByIsNull(s.url, "[] 不能为空");		// 验证:  
		AjaxError.throwByIsNull(s.type, "[] 不能为空");		// 验证:  
		AjaxError.throwByIsNull(s.status, "[] 不能为空");		// 验证:  
		AjaxError.throwByIsNull(s.createBy, "[] 不能为空");		// 验证:  
		AjaxError.throwByIsNull(s.createTime, "[] 不能为空");		// 验证:  
		AjaxError.throwByIsNull(s.updateBy, "[] 不能为空");		// 验证:  
		AjaxError.throwByIsNull(s.updateTime, "[] 不能为空");		// 验证:  
	}

	/** 
	 * 获取一个SpVedio (方便复制代码用) [G] 
	 */ 
	static SpVedio getSpVedio() {
		SpVedio s = new SpVedio();	// 声明对象 
		s.id = "";		//
		s.url = "";		//  
		s.type = "";		//  
		s.status = "";		//  
		s.createBy = "";		//  
		s.createTime = "";		//  
		s.updateBy = "";		//  
		s.updateTime = "";		//  
		return s;
	}
	
	
	
	
	
}
