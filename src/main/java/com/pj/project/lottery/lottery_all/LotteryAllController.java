package com.pj.project.lottery.lottery_all;

import java.util.List;

import com.pj.models.so.SoMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.pj.utils.sg.*;


/**
 * Controller: lottery_all -- 所有可能得红球组合
 * @author lizhihao 
 */
@RestController
@RequestMapping("/lotteryAll/")
public class LotteryAllController {

	/** 底层 Service 对象 */
	@Autowired
	LotteryAllService lotteryAllService;

	/** 增 */  
	@RequestMapping("add")
	@Transactional(rollbackFor = Exception.class)
	public AjaxJson add(LotteryAll l){
		lotteryAllService.add(l);
		return AjaxJson.getSuccessData(l);
	}
	@GetMapping("batchAdd")
	@Transactional(rollbackFor = Exception.class)
	public AjaxJson batchAdd(){
		lotteryAllService.syncData();
		return AjaxJson.getSuccess();
	}

	/** 删 */  
	@RequestMapping("delete")
	public AjaxJson delete(Long id){
		int line = lotteryAllService.delete(id);
		return AjaxJson.getByLine(line);
	}
	
	/** 删 - 根据id列表 */  
//	@RequestMapping("deleteByIds")
//	public AjaxJson deleteByIds(){
//		List<Long> ids = SoMap.getRequestSoMap().getListByComma("ids", long.class);
//		int line = SP.publicMapper.deleteByIds(LotteryAll.TABLE_NAME, ids);
//		return AjaxJson.getByLine(line);
//	}
	
	/** 改 */  
	@RequestMapping("update")
	public AjaxJson update(LotteryAll l){
		int line = lotteryAllService.update(l);
		return AjaxJson.getByLine(line);
	}

	/** 查 - 根据id */  
	@RequestMapping("getById")
	public AjaxJson getById(Long id){
		LotteryAll l = lotteryAllService.getById(id);
		return AjaxJson.getSuccessData(l);
	}

	/** 查集合 - 根据条件（参数为空时代表忽略指定条件） */  
	@RequestMapping("getList")
	public AjaxJson getList() {
		SoMap so = SoMap.getRequestSoMap();
		List<LotteryAll> list = lotteryAllService.getList(so.startPage());
		return AjaxJson.getPageData(so.getDataCount(), list);
	}
	

	
	

}
