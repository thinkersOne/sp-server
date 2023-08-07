package com.pj.project.aps.user;

import java.util.List;

import com.pj.current.satoken.AuthConst;
import com.pj.models.dto.LoginDTO;
import com.pj.models.so.SoMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.pj.utils.sg.*;
import cn.dev33.satoken.annotation.SaCheckPermission;


/**
 * Controller: user -- 用户表
 * @author lizhihao
 */
@RestController
@RequestMapping("/user/")
public class UserController {

	/** 底层 Service 对象 */
	@Autowired
	UserService userService;
	@Autowired
	UserMapper userMapper;

	/** 删 */
	@RequestMapping("delete")
	@SaCheckPermission(AuthConst.USER_DELETE)
	public AjaxJson delete(Long id){
		int line = userService.delete(id);
		return AjaxJson.getByLine(line);
	}

	/** 改 */
	@RequestMapping("update")
	@SaCheckPermission(AuthConst.USER_UPDATE)
	public AjaxJson update(User u){
		int line = userService.update(u);
		return AjaxJson.getByLine(line);
	}

	/** 查 - 根据id */
	@RequestMapping("getById")
	@SaCheckPermission(AuthConst.USER_GETBY＿ID)
	public AjaxJson getById(Long id){
		User u = userService.getById(id);
		return AjaxJson.getSuccessData(u);
	}

	/** 查集合 - 根据条件（参数为空时代表忽略指定条件） */
	@RequestMapping("getList")
	@SaCheckPermission(AuthConst.USER_GETLIST)
	public AjaxJson getList() {
		SoMap so = SoMap.getRequestSoMap();
		List<User> list = userService.getList(so.startPage());
		return AjaxJson.getPageData(so.getDataCount(), list);
	}

	@PostMapping("register")
	AjaxJson register(@RequestBody User u){
		//校验名称及手机号是否重复
		if(userMapper.existsAccount(u) > 0){
			return AjaxJson.getError("账号已存在，请换个账号试试！");
		}
		long id = userService.register(u);
		return AjaxJson.getSuccessData(id);
	}

	@PostMapping("doLogin")
	AjaxJson doLogin(@RequestBody LoginDTO loginDTO) {
		String key = loginDTO.getKey();
		String password = loginDTO.getPassword();
		IpCheckUtil.checkResToNow("admin-login", 1);
		// 1、验证参数
		if(NbUtil.hasNull(key, password)) {
			return AjaxJson.getError("请提供key与password参数");
		}
		return userService.doLogin(key, password);
	}

}