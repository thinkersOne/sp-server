package com.pj.project.lottery.lottery_calculate_count;

import java.util.List;

import com.pj.models.so.SoMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.pj.utils.sg.*;


/**
 * Controller: lottery_calculate_count -- 按照不同时间维度统计每个红蓝球情况
 * @author lizhihao 
 */
@RestController
@RequestMapping("/lotteryCalculateCount/")
public class LotteryCalculateCountController {

	/** 底层 Service 对象 */
	@Autowired
	LotteryCalculateCountService lotteryCalculateCountService;

	/** 增 */  
	@RequestMapping("add")
	@Transactional(rollbackFor = Exception.class)
	public AjaxJson add(LotteryCalculateCount l){
		lotteryCalculateCountService.add(l);
		return AjaxJson.getSuccessData(l);
	}

	/** 删 */  
	@RequestMapping("delete")
	public AjaxJson delete(Long id){
		int line = lotteryCalculateCountService.delete(id);
		return AjaxJson.getByLine(line);
	}
	
	/** 删 - 根据id列表 */  
//	@RequestMapping("deleteByIds")
//	public AjaxJson deleteByIds(){
//		List<Long> ids = SoMap.getRequestSoMap().getListByComma("ids", long.class);
//		int line = SP.publicMapper.deleteByIds(LotteryCalculateCount.TABLE_NAME, ids);
//		return AjaxJson.getByLine(line);
//	}
	
	/** 改 */  
	@RequestMapping("update")
	public AjaxJson update(LotteryCalculateCount l){
		int line = lotteryCalculateCountService.update(l);
		return AjaxJson.getByLine(line);
	}

	/** 查 - 根据id */  
	@RequestMapping("getById")
	public AjaxJson getById(Long id){
		LotteryCalculateCount l = lotteryCalculateCountService.getById(id);
		return AjaxJson.getSuccessData(l);
	}

	/** 查集合 - 根据条件（参数为空时代表忽略指定条件） */  
	@RequestMapping("getList")
	public AjaxJson getList() {
		SoMap so = SoMap.getRequestSoMap();
		List<LotteryCalculateCount> list = lotteryCalculateCountService.getList(so.startPage());
		return AjaxJson.getPageData(so.getDataCount(), list);
	}

	/**
	 * 从lottery_calculate_per表计算后同步数据-》LotteryCalculateCount
	 * @return
	 */
	@GetMapping("/sync/lotteryCalculateCount")
	public AjaxJson lotteryCalculateCount() {
		lotteryCalculateCountService.lotteryCalculateCount();
		return AjaxJson.getSuccess();
	}
	

}
