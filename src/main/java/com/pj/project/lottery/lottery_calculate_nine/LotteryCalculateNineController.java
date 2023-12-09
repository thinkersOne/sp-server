package com.pj.project.lottery.lottery_calculate_nine;

import java.util.List;

import com.pj.models.so.SoMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.pj.utils.sg.*;


/**
 * Controller: lottery_calculate_nine -- 九转连环图统计表
 * @author lizhihao 
 */
@RestController
@RequestMapping("/lotteryCalculateNine/")
public class LotteryCalculateNineController {

	/** 底层 Service 对象 */
	@Autowired
	LotteryCalculateNineService lotteryCalculateNineService;

	/** 增 */  
	@RequestMapping("add")
	@Transactional(rollbackFor = Exception.class)
	public AjaxJson add(LotteryCalculateNine l){
		lotteryCalculateNineService.add(l);
		return AjaxJson.getSuccessData(l);
	}

	/** 删 */  
	@RequestMapping("delete")
	public AjaxJson delete(Long id){
		int line = lotteryCalculateNineService.delete(id);
		return AjaxJson.getByLine(line);
	}
	
	/** 删 - 根据id列表 */  
//	@RequestMapping("deleteByIds")
//	public AjaxJson deleteByIds(){
//		List<Long> ids = SoMap.getRequestSoMap().getListByComma("ids", long.class);
//		int line = SP.publicMapper.deleteByIds(LotteryCalculateNine.TABLE_NAME, ids);
//		return AjaxJson.getByLine(line);
//	}
	
	/** 改 */  
	@RequestMapping("update")
	public AjaxJson update(LotteryCalculateNine l){
		int line = lotteryCalculateNineService.update(l);
		return AjaxJson.getByLine(line);
	}

	/** 查 - 根据id */  
	@RequestMapping("getById")
	public AjaxJson getById(Long id){
		LotteryCalculateNine l = lotteryCalculateNineService.getById(id);
		return AjaxJson.getSuccessData(l);
	}

	/** 查集合 - 根据条件（参数为空时代表忽略指定条件） */  
	@RequestMapping("getList")
	public AjaxJson getList() {
		SoMap so = SoMap.getRequestSoMap();
		List<LotteryCalculateNine> list = lotteryCalculateNineService.getList(so.startPage());
		return AjaxJson.getPageData(so.getDataCount(), list);
	}

	/**
	 * 从lottery_calculate_per表计算后同步数据-》LotteryCalculateNine
	 * @return
	 */
	@GetMapping("/sync/lotteryCalculateNine")
	public AjaxJson LotteryCalculateNine() {
		lotteryCalculateNineService.lotteryCalculateNine();
		return AjaxJson.getSuccess();
	}
	

}
