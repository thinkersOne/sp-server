package com.pj.project.sp_dev.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pj.project.sp_dev.SP_DEV_SP;
import com.pj.project.sp_dev.admin4password.SpAdminPasswordService;

import cn.dev33.satoken.stp.StpUtil;

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
	SpAdminPasswordService spAdminPasswordService;


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

}