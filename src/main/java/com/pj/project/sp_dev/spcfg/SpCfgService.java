package com.pj.project.sp_dev.spcfg;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.pj.project.sp_dev.SP_DEV_SP;

/**
 * 配置类：sp_cfg
 * @author kong
 *
 */
@Service
public class SpCfgService {


	/** 获得cfg_value 根据cfgName */
	@Cacheable(value="sp_cfg_", key="#cfgName")
	public String getCfgValue(String cfgName){
		return SP_DEV_SP.publicMapper.getColumnByWhere("sp_cfg", "cfg_value", "cfg_name", cfgName);
	}


	/** 修改cfg_value 根据cfgName */
	@CacheEvict(value="sp_cfg_", key="#cfgName")
	public int updateCfgValue(String cfgName, String cfgValue){
		return SP_DEV_SP.publicMapper.updateColumnBy("sp_cfg", "cfg_value", cfgValue, "cfg_name", cfgName);
	}

}