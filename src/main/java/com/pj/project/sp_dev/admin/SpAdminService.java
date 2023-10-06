package com.pj.project.sp_dev.admin;

import com.pj.models.so.SoMap;
import com.pj.project.aps.user.User;
import com.pj.project.aps.user.UserMapper;
import com.pj.project.aps.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pj.project.sp_dev.SP_DEV_SP;
import com.pj.project.sp_dev.admin4password.SpAdminPasswordService;

import cn.dev33.satoken.stp.StpUtil;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service: admin管理员
 * @author kong
 *
 */
@Service
public class SpAdminService {


	@Autowired
	SpAdminMapper spAdminMapper;
	@Autowired
	UserMapper userMapper;

	@Autowired
	SpAdminPasswordService spAdminPasswordService;
	@Autowired
	UserService userService;

	@Transactional(rollbackFor = Exception.class)
	public long register(SpAdmin admin) {
		// 检查姓名是否合法
//		SpAdminUtil.checkAdmin(admin);
		// 自己创建
		admin.setCreateByAid(0L);
		// 开始添加
		spAdminMapper.add(admin);
		// 获取主键
		long id = SP_DEV_SP.publicMapper.getPrimarykey();
		// 更改密码（md5与明文）
		spAdminPasswordService.updatePassword(id, admin.getPassword2());

		//順便把user也給註冊一下
		User user = User.builder().createTime(admin.getCreateTime()).updateTime(admin.getCreateTime())
				.createBy(admin.getCreateByAid() + "").updateBy(admin.getCreateByAid() + "")
				.username(admin.getName()).password(admin.getPassword2()).id(admin.getId()).build();
		userService.register(user);
		// 返回主键
		return id;
	}

	/**
	 * 管理员添加一个管理员
	 * @param admin
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public long add(SpAdmin admin) {
		// 检查姓名是否合法
		SpAdminUtil.checkAdmin(admin);
		// 创建人，为当前账号
		admin.setCreateByAid(StpUtil.getLoginIdAsLong());
		// 开始添加
		spAdminMapper.add(admin);
		// 获取主键
		long id = SP_DEV_SP.publicMapper.getPrimarykey();
		// 更改密码（md5与明文）
		spAdminPasswordService.updatePassword(id, admin.getPassword2());

		// 返回主键
		return id;
	}

	public void synchronousData(){
		List<SpAdmin> spAdminList = spAdminMapper.getList(SoMap.getRequestSoMap());
		List<User> userList = userMapper.getList(SoMap.getRequestSoMap());
		if(CollectionUtils.isEmpty(spAdminList)){
			return;
		}
		List<SpAdmin> result = new ArrayList<>(20);
		Map<Long,User> map = new HashMap<>(20);
		if(CollectionUtils.isEmpty(userList)){
			result.addAll(spAdminList);
		}else{
			userList.stream().forEach(v->{
				map.put(v.getId(), v);
			});
			spAdminList.stream().forEach(w->{
				if(!map.containsKey(w.getId())){
					result.add(w);
				}
			});
		}

		if(CollectionUtils.isEmpty(result)){
			return;
		}

		result.stream().forEach(v->{
			User user = User.builder().createTime(v.getCreateTime()).updateTime(v.getCreateTime())
					.createBy(v.getCreateByAid() + "").updateBy(v.getCreateByAid() + "")
					.username(v.getName()).password(v.getPw()).id(v.getId()).build();
			userMapper.add(user);
		});
	}

	/**
	 * 当前admin
	 * @return
	 */
	public SpAdmin getCurrAdmin() {
		long adminId = StpUtil.getLoginIdAsLong();
		return spAdminMapper.getById(adminId);
	}
}