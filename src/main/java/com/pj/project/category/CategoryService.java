package com.pj.project.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pj.utils.so.*;

/**
 * Service: category -- 密码分类表
 * @author lizhihao 
 */
@Service
public class CategoryService {

	/** 底层 Mapper 对象 */
	@Autowired
	CategoryMapper categoryMapper;

	/** 增 */
	int add(Category c){
		return categoryMapper.add(c);
	}

	/** 删 */
	int delete(Long id){
		return categoryMapper.delete(id);
	}

	/** 改 */
	int update(Category c){
		return categoryMapper.update(c);
	}

	/** 查 */
	Category getById(Long id){
		return categoryMapper.getById(id);
	}

	int getCountByIds(List<Long> ids){
		return categoryMapper.getCountByIds(ids);
	}

	/** 查集合 - 根据条件（参数为空时代表忽略指定条件） */  
	List<Category> getList(SoMap so) { 
		return categoryMapper.getList(so);	
	}
	

}
