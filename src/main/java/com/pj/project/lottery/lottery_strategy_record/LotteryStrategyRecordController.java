package com.pj.project.lottery.lottery_strategy_record;

import java.util.List;

import com.pj.models.so.SoMap;
import com.pj.project.lottery.LOTTERY_SP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.pj.current.satoken.AuthConst;
import com.pj.utils.sg.*;
import com.pj.current.satoken.StpUserUtil;
import cn.dev33.satoken.annotation.SaCheckPermission;


/**
 * Controller: lottery_strategy_record -- 策略记录表
 * @author lizhihao 
 */
@RestController
@RequestMapping("/lotteryStrategyRecord/")
public class LotteryStrategyRecordController {

	/** 底层 Service 对象 */
	@Autowired
	LotteryStrategyRecordService lotteryStrategyRecordService;

	/** 增 */  
	@RequestMapping("add")
	@Transactional(rollbackFor = Exception.class)
	public AjaxJson add(LotteryStrategyRecord l){
		lotteryStrategyRecordService.add(l);
		l = lotteryStrategyRecordService.getById(LOTTERY_SP.publicMapper.getPrimarykey());
		return AjaxJson.getSuccessData(l);
	}

	/** 删 */  
	@RequestMapping("delete")
	public AjaxJson delete(Long id){
		int line = lotteryStrategyRecordService.delete(id);
		return AjaxJson.getByLine(line);
	}
	
	/** 删 - 根据id列表 */  
	@RequestMapping("deleteByIds")
	public AjaxJson deleteByIds(){
		List<Long> ids = SoMap.getRequestSoMap().getListByComma("ids", long.class);
		int line = LOTTERY_SP.publicMapper.deleteByIds(LotteryStrategyRecord.TABLE_NAME, ids);
		return AjaxJson.getByLine(line);
	}
	
	/** 改 */  
	@RequestMapping("update")
	public AjaxJson update(LotteryStrategyRecord l){
		int line = lotteryStrategyRecordService.update(l);
		return AjaxJson.getByLine(line);
	}

	/** 查 - 根据id */  
	@RequestMapping("getById")
	public AjaxJson getById(Long id){
		LotteryStrategyRecord l = lotteryStrategyRecordService.getById(id);
		return AjaxJson.getSuccessData(l);
	}

	/** 查集合 - 根据条件（参数为空时代表忽略指定条件） */  
	@RequestMapping("getList")
	public AjaxJson getList() {
		SoMap so = SoMap.getRequestSoMap();
		List<LotteryStrategyRecord> list = lotteryStrategyRecordService.getList(so.startPage());
		return AjaxJson.getPageData(so.getDataCount(), list);
	}
	

	
	
	
	

}
