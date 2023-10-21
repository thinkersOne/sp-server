package com.pj.project.lottery.lottery_calculate_per;

import java.util.List;

import com.pj.models.so.SoMap;
import com.pj.project.lottery.LotteryParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.pj.utils.sg.*;


/**
 * Controller: lottery_calculate_per -- 统计计算每期双色球表
 * @author lizhihao 
 */
@RestController
@RequestMapping("/lotteryCalculatePer/")
public class LotteryCalculatePerController {

	/** 底层 Service 对象 */
	@Autowired
	LotteryCalculatePerService lotteryCalculatePerService;

	/** 增 */  
	@RequestMapping("add")
	@Transactional(rollbackFor = Exception.class)
	public AjaxJson add(LotteryCalculatePer l){
		lotteryCalculatePerService.add(l);
		return AjaxJson.getSuccessData(l);
	}

	/** 删 */  
	@RequestMapping("delete")
	public AjaxJson delete(Long id){
		int line = lotteryCalculatePerService.delete(id);
		return AjaxJson.getByLine(line);
	}
	
	/** 改 */  
	@RequestMapping("update")
	public AjaxJson update(LotteryCalculatePer l){
		int line = lotteryCalculatePerService.update(l);
		return AjaxJson.getByLine(line);
	}

	/** 查 - 根据id */  
	@RequestMapping("getById")
	public AjaxJson getById(Long id){
		LotteryCalculatePer l = lotteryCalculatePerService.getById(id);
		return AjaxJson.getSuccessData(l);
	}

	/** 查集合 - 根据条件（参数为空时代表忽略指定条件） */  
	@RequestMapping("getList")
	public AjaxJson getList() {
		SoMap so = SoMap.getRequestSoMap();
		List<LotteryCalculatePer> list = lotteryCalculatePerService.getList(so.startPage());
		return AjaxJson.getPageData(so.getDataCount(), list);
	}

	/**
	 * 从基础表计算后同步数据-》lottery_calculate_per
	 * @return
	 */
	@GetMapping("/sync/lotteryCalculatePer")
	public AjaxJson lotteryCalculatePer() {
		lotteryCalculatePerService.lotteryCalculatePer();
		return AjaxJson.getSuccess();
	}
	
	
	

}
