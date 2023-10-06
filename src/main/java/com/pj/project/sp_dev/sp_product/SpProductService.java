package com.pj.project.sp_dev.sp_product;

import java.util.List;

import cn.hutool.core.util.ObjectUtil;
import com.pj.current.enums.ProductTypeEnum;
import com.pj.models.so.SoMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service: sp_product -- 商品信息表
 * @author lizhihao 
 */
@Service
public class SpProductService {

	/** 底层 Mapper 对象 */
	@Autowired
	SpProductMapper spProductMapper;

	/** 增 */
	int add(SpProduct s){
		return spProductMapper.add(s);
	}

	/** 删 */
	int delete(Long id){
		return spProductMapper.delete(id);
	}

	/** 改 */
	int update(SpProduct s){
		return spProductMapper.update(s);
	}

	/** 查 */
	SpProduct getById(Long id){
		return spProductMapper.getById(id);
	}

	/** 查集合 - 根据条件（参数为空时代表忽略指定条件） */  
	List<SpProduct> getList(SoMap so) {
		List<SpProduct> list = spProductMapper.getList(so);
		if(!ObjectUtil.isEmpty(list)){
			list.stream().forEach(v->{
				v.setTypeName(ProductTypeEnum.getTypeName(v.getType()));
			});
		}
		return list;
	}
	

}
