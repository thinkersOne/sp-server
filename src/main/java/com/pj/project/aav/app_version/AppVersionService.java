package com.pj.project.aav.app_version;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service: app_version -- app版本管理表
 * @author lizhihao 
 */
@Service
public class AppVersionService {

	/** 底层 Mapper 对象 */
	@Autowired
	AppVersionMapper appVersionMapper;

	/** 增 */
	int add(AppVersion a){
		return appVersionMapper.add(a);
	}

	/** 删 */
	int delete(Long id){
		return appVersionMapper.delete(id);
	}

	/** 改 */
	int update(AppVersion a){
		return appVersionMapper.update(a);
	}

	/** 查 */
	AppVersion getById(Long id){
		return appVersionMapper.getById(id);
	}

	

}
