package com.pj.project.lottery.lottery_select;

import java.util.List;

import com.pj.models.so.SoMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.pj.utils.sg.*;


/**
 * Controller: lottery_select -- 经过初步筛选后的全量双色球表
 * @author lizhihao 
 */
@RestController
@RequestMapping("/lotterySelect/")
public class LotterySelectController {

	/** 底层 Service 对象 */
	@Autowired
	LotterySelectService lotterySelectService;

	/** 增 */  
	@RequestMapping("add")
	@Transactional(rollbackFor = Exception.class)
	public AjaxJson add(LotterySelect l){
		lotterySelectService.add(l);
		return AjaxJson.getSuccessData(l);
	}

	/** 删 */  
	@RequestMapping("delete")
	public AjaxJson delete(Long id){
		int line = lotterySelectService.delete(id);
		return AjaxJson.getByLine(line);
	}
	
	/** 删 - 根据id列表 */  
//	@RequestMapping("deleteByIds")
//	public AjaxJson deleteByIds(){
//		List<Long> ids = SoMap.getRequestSoMap().getListByComma("ids", long.class);
//		int line = SP.publicMapper.deleteByIds(LotterySelect.TABLE_NAME, ids);
//		return AjaxJson.getByLine(line);
//	}
	
	/** 改 */  
	@RequestMapping("update")
	public AjaxJson update(LotterySelect l){
		int line = lotterySelectService.update(l);
		return AjaxJson.getByLine(line);
	}

	/** 查 - 根据id */  
	@RequestMapping("getById")
	public AjaxJson getById(Long id){
		LotterySelect l = lotterySelectService.getById(id);
		return AjaxJson.getSuccessData(l);
	}

	/** 查集合 - 根据条件（参数为空时代表忽略指定条件） */  
	@RequestMapping("getList")
	public AjaxJson getList() {
		SoMap so = SoMap.getRequestSoMap();
		List<LotterySelect> list = lotterySelectService.getList(so.startPage());
		return AjaxJson.getPageData(so.getDataCount(), list);
	}

	/**
	 * 从lottery_all表计算后同步数据-》LotterySelect
	 * @return
	 */
	@GetMapping("/sync/lotterySelect")
	public AjaxJson lotterySelect() {
		lotterySelectService.lotterySelect();
		return AjaxJson.getSuccess();
	}
	
	
	

}
