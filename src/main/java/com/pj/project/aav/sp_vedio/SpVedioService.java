package com.pj.project.aav.sp_vedio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.hutool.core.util.ObjectUtil;
import com.pj.current.global.SnowflakeIdGenerator;
import com.pj.current.global.VedioStatusEnum;
import com.pj.current.global.VedioTypeEnum;
import com.pj.models.so.SoMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Service: sp_vedio -- 视频表
 * @author lizhihao
 */
@Service
public class SpVedioService {

	/** 底层 Mapper 对象 */
	@Autowired
	SpVedioMapper spVedioMapper;
	@Autowired
	private SnowflakeIdGenerator snowflakeIdGenerator;
	@Autowired
	private RestTemplate restTemplate;


	/** 增 */
	int add(SpVedio s){
		s.setId(snowflakeIdGenerator.generateId()+"");
		return spVedioMapper.add(s);
	}

	/** 删 */
	int delete(Long id){
		return spVedioMapper.delete(id);
	}

	/** 改 */
	int update(SpVedio s){
		return spVedioMapper.update(s);
	}

	int updateStatus(SpVedio s){
		return spVedioMapper.updateStatus(s);
	}

	/** 查 */
	SpVedio getById(Long id){
		return spVedioMapper.getById(id);
	}

	//请求获取视频数据
	public void getVedioData(){
		Map<String,String> params = new HashMap<>();
		params.put("url",VedioConstent.GIRL_VEDIO_PATH+"url");
		params.put("json",VedioConstent.GIRL_VEDIO_PATH+"json");
		ResponseEntity<VedioDataResponse> forEntity = restTemplate.getForEntity(VedioConstent.GIRL_VEDIO_PATH,
				VedioDataResponse.class, params);
		System.out.println(forEntity);
	}
}