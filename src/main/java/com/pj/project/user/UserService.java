package com.pj.project.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pj.utils.so.*;

/**
 * Service: user -- 用户表
 * @author lizhihao 
 */
@Service
public class UserService {

	/** 底层 Mapper 对象 */
	@Autowired
	UserMapper userMapper;

	/** 增 */
	int add(User u){
		return userMapper.add(u);
	}

	/** 删 */
	int delete(Long id){
		return userMapper.delete(id);
	}

	/** 改 */
	int update(User u){
		return userMapper.update(u);
	}

	/** 查 */
	User getById(Long id){
		return userMapper.getById(id);
	}

	/** 查集合 - 根据条件（参数为空时代表忽略指定条件） */  
	List<User> getList(SoMap so) { 
		return userMapper.getList(so);	
	}
	

}
