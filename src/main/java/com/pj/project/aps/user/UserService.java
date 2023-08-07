package com.pj.project.aps.user;

import java.util.List;

import cn.dev33.satoken.stp.StpUtil;
import com.pj.models.so.SoMap;
import com.pj.utils.sg.AjaxJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional(rollbackFor = Exception.class)
	public long register(User u) {
		// 开始添加
		int id = userMapper.add(u);
		// 返回主键
		return id;
	}
	/**
	 * 登录
	 * @param key 账号 (ID / 名称 / 手机号)
	 * @param password 密码
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public AjaxJson doLogin(String key, String password) {
		// 2、获取admin
		User user = userMapper.getByName(key);
		// 3、开始验证
		if(user == null){
			return AjaxJson.getError("无此账号");
		}
		if(!user.getPassword().equals(password)){
			return AjaxJson.getError("密码错误");
		}
		// =========== 至此, 已登录成功 ============
		StpUtil.login(user.getId());
		return AjaxJson.getSuccessData(StpUtil.getTokenInfo());
	}
}