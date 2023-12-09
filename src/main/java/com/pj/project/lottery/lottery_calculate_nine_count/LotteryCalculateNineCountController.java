package com.pj.project.lottery.lottery_calculate_nine_count;

import java.util.List;

import com.pj.models.so.SoMap;
import com.pj.project.lottery.LOTTERY_FC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.pj.utils.sg.*;


/**
 * Controller: lottery_calculate_nine_count -- 统计九转中四行数据每行上出现个数的统计表
 * @author lizhihao 
 */
@RestController
@RequestMapping("/lotteryCalculateNineCount/")
public class LotteryCalculateNineCountController {

	/** 底层 Service 对象 */
	@Autowired
	LotteryCalculateNineCountService lotteryCalculateNineCountService;

	/** 增 */  
	@RequestMapping("add")
	@Transactional(rollbackFor = Exception.class)
	public AjaxJson add(LotteryCalculateNineCount l){
		lotteryCalculateNineCountService.add(l);
		return AjaxJson.getSuccessData(l);
	}

	/** 删 */  
	@RequestMapping("delete")
	public AjaxJson delete(Long id){
		int line = lotteryCalculateNineCountService.delete(id);
		return AjaxJson.getByLine(line);
	}
	
	/** 删 - 根据id列表 */  
	@RequestMapping("deleteByIds")
	public AjaxJson deleteByIds(){
		List<Long> ids = SoMap.getRequestSoMap().getListByComma("ids", long.class);
		int line = LOTTERY_FC.publicMapper.deleteByIds(LotteryCalculateNineCount.TABLE_NAME, ids);
		return AjaxJson.getByLine(line);
	}
	
	/** 改 */  
	@RequestMapping("update")
	public AjaxJson update(LotteryCalculateNineCount l){
		int line = lotteryCalculateNineCountService.update(l);
		return AjaxJson.getByLine(line);
	}

	/** 查 - 根据id */  
	@RequestMapping("getById")
	public AjaxJson getById(Long id){
		LotteryCalculateNineCount l = lotteryCalculateNineCountService.getById(id);
		return AjaxJson.getSuccessData(l);
	}

	/** 查集合 - 根据条件（参数为空时代表忽略指定条件） */  
	@RequestMapping("getList")
	public AjaxJson getList() {
		SoMap so = SoMap.getRequestSoMap();
		List<LotteryCalculateNineCount> list = lotteryCalculateNineCountService.getList(so.startPage());
		return AjaxJson.getPageData(so.getDataCount(), list);
	}

	@GetMapping("/sync/lotteryCalculateNineCount")
	public AjaxJson lotteryCalculateNineCount() {
		lotteryCalculateNineCountService.lotteryCalculateNineCount();
		return AjaxJson.getSuccess();
	}
	

}
