package com.pj.project.lottery.lottery_forecast;

import java.util.List;

import com.pj.models.so.SoMap;
import com.pj.project.lottery.LOTTERY_SP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.pj.utils.sg.*;


/**
 * Controller: lottery_forecast -- 号码预测
 * @author lizhihao 
 */
@RestController
@RequestMapping("/lotteryForecast/")
public class LotteryForecastController {

	/** 底层 Service 对象 */
	@Autowired
	LotteryForecastService lotteryForecastService;
	@Autowired
	LotteryForecastMapper  lottForecastMapper;

	/** 增 */  
	@RequestMapping("add")
	@Transactional(rollbackFor = Exception.class)
	public AjaxJson add(LotteryForecast l){
		lotteryForecastService.add(l);
		return AjaxJson.getSuccessData(l);
	}

	/** 删 */  
	@RequestMapping("delete")
	public AjaxJson delete(Long id){
		int line = lotteryForecastService.delete(id);
		return AjaxJson.getByLine(line);
	}
	
	/** 删 - 根据id列表 */  
	@RequestMapping("deleteByIds")
	public AjaxJson deleteByIds(){
		List<Long> ids = SoMap.getRequestSoMap().getListByComma("ids", long.class);
		int line = lottForecastMapper.deleteByIds(ids);
		return AjaxJson.getByLine(line);
	}
	
	/** 改 */  
	@RequestMapping("update")
	public AjaxJson update(LotteryForecast l){
		int line = lotteryForecastService.update(l);
		return AjaxJson.getByLine(line);
	}

	/** 查 - 根据id */  
	@RequestMapping("getById")
	public AjaxJson getById(Long id){
		LotteryForecast l = lotteryForecastService.getById(id);
		return AjaxJson.getSuccessData(l);
	}

	/** 查集合 - 根据条件（参数为空时代表忽略指定条件） */  
	@RequestMapping("getList")
	public AjaxJson getList() {
		SoMap so = SoMap.getRequestSoMap();
		List<LotteryForecast> list = lotteryForecastService.getList(so.startPage());
		return AjaxJson.getPageData(so.getDataCount(), list);
	}
	

	
	
	

}
