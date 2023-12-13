package com.pj.project.lottery.lottery_red_proportion;

import java.util.List;

import com.pj.models.so.SoMap;
import com.pj.project.lottery.LOTTERY_SP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.pj.utils.sg.*;


/**
 * Controller: lottery_red_proportion -- 各红球号占比
 * @author lizhihao 
 */
@RestController
@RequestMapping("/lotteryRedProportion/")
public class LotteryRedProportionController {

	/** 底层 Service 对象 */
	@Autowired
	LotteryRedProportionService lotteryRedProportionService;

	/** 增 */  
	@RequestMapping("add")
	@Transactional(rollbackFor = Exception.class)
	public AjaxJson add(LotteryRedProportion l){
		lotteryRedProportionService.add(l);
		l = lotteryRedProportionService.getById(LOTTERY_SP.publicMapper.getPrimarykey());
		return AjaxJson.getSuccessData(l);
	}

	/** 删 */  
	@RequestMapping("delete")
	public AjaxJson delete(Long id){
		int line = lotteryRedProportionService.delete(id);
		return AjaxJson.getByLine(line);
	}
	
	/** 删 - 根据id列表 */  
	@RequestMapping("deleteByIds")
	public AjaxJson deleteByIds(){
		List<Long> ids = SoMap.getRequestSoMap().getListByComma("ids", long.class);
		int line = LOTTERY_SP.publicMapper.deleteByIds(LotteryRedProportion.TABLE_NAME, ids);
		return AjaxJson.getByLine(line);
	}
	
	/** 改 */  
	@RequestMapping("update")
	public AjaxJson update(LotteryRedProportion l){
		int line = lotteryRedProportionService.update(l);
		return AjaxJson.getByLine(line);
	}

	/** 查 - 根据id */  
	@RequestMapping("getById")
	public AjaxJson getById(Long id){
		LotteryRedProportion l = lotteryRedProportionService.getById(id);
		return AjaxJson.getSuccessData(l);
	}

	/** 查集合 - 根据条件（参数为空时代表忽略指定条件） */  
	@RequestMapping("getList")
	public AjaxJson getList() {
		SoMap so = SoMap.getRequestSoMap();
		List<LotteryRedProportion> list = lotteryRedProportionService.getList(so.startPage());
		return AjaxJson.getPageData(so.getDataCount(), list);
	}

	@GetMapping("/sync/lotteryRedProportion")
	public AjaxJson lotteryRedProportion() {
		lotteryRedProportionService.lotteryRedProportion();
		return AjaxJson.getSuccess();
	}
	
	
	

}
