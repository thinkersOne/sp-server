package com.pj.project.aav.sp_vedio;

import java.util.List;
import java.util.stream.Collectors;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.pj.models.so.SoMap;
import com.pj.project.sp_dev.SP_DEV_SP;
import com.pj.project.sp_dev.uploadfile.UploadUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import com.pj.current.satoken.AuthConst;
import com.pj.utils.sg.*;
import com.pj.current.satoken.StpUserUtil;
import cn.dev33.satoken.annotation.SaCheckPermission;


/**
 * Controller: sp_vedio -- 视频表
 * @author lizhihao
 */
@RestController
@RequestMapping("/spVedio/")
public class SpVedioController {

	/** 底层 Service 对象 */
	@Autowired
	SpVedioService spVedioService;
	@Autowired
	SpVedioMapper spVedioMapper;

	/** 增 */
	@PostMapping("add")
	@SaCheckLogin
	@Transactional(rollbackFor = Exception.class)
	public AjaxJson add(@RequestBody SpVedio s){
		Long id = Long.valueOf(spVedioService.add(s));
		s = spVedioService.getById(id);
		return AjaxJson.getSuccessData(s);
	}

	/** 删 */
	@RequestMapping("delete")
	@SaCheckPermission(AuthConst.SP_VEDIO_DELETE)
	public AjaxJson delete(Long id){
		int line = spVedioService.delete(id);
		return AjaxJson.getByLine(line);
	}

	/** 删 - 根据id列表 */
	@RequestMapping("deleteByIds")
	@SaCheckPermission(AuthConst.SP_VEDIO_DELETE_BY_IDS)
	public AjaxJson deleteByIds(){
		List<Long> ids = SoMap.getRequestSoMap().getListByComma("ids", long.class);
		int line = SP_DEV_SP.publicMapper.deleteByIds(SpVedio.TABLE_NAME, ids);
		return AjaxJson.getByLine(line);
	}

	/** 改 */
	@RequestMapping("update")
	@SaCheckPermission(AuthConst.SP_VEDIO_UPDATE)
	public AjaxJson update(SpVedio s){
		spVedioService.update(s);
		return AjaxJson.getByLine();
	}

	/** 改 */
	@RequestMapping("updateStatus")
	@SaCheckPermission(AuthConst.SP_VEDIO_UPDATE)
	public AjaxJson updateStatus(SpVedio s){
		spVedioService.updateStatus(s);
		return AjaxJson.getByLine();
	}

	/** 查 - 根据id */
	@RequestMapping("getById")
	@SaCheckPermission(AuthConst.SP_VEDIO_GETBY＿ID)
	public AjaxJson getById(Long id){
		SpVedio s = spVedioService.getById(id);
		return AjaxJson.getSuccessData(s);
	}

	@GetMapping("getList")
	@SaCheckLogin
	public AjaxJson getList(@Param("type") int type) {
		List<SpVedio> spVedios = spVedioMapper.getList(type);
		if(!CollectionUtils.isEmpty(spVedios)){
			spVedios.forEach(v->{
				v.setUrl(UploadUtil.getDoMain() + v.getUrl());
			});
		}
		return AjaxJson.getSuccessData(spVedios);
	}


}