package com.pj.project.lottery;

import com.pj.current.global.StatusCode;
import com.pj.models.so.SoMap;
import com.pj.project.lottery.unionLotto.domain.ConvertDoubleSpheresReq;
import com.pj.project.lottery.unionLotto.domain.Lottery;
import com.pj.project.lottery.unionLotto.domain.ResultRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.pj.utils.sg.*;

import java.util.Arrays;
import java.util.List;

/**
 * Controller: lottery -- 
 * @author lizhihao 
 */
@RestController
@RequestMapping("/lottery/")
public class LotteryController {

	/** 底层 Service 对象 */
	@Autowired
	LotteryService lotteryService;

	/** 增 */  
	@RequestMapping("add")
	@Transactional(rollbackFor = Exception.class)
	public AjaxJson add(Lottery l){
		lotteryService.add(l);
		return AjaxJson.getSuccessData(l);
	}

	/** 删 */  
	@RequestMapping("delete")
	public AjaxJson delete(String id){
		int line = lotteryService.delete(id);
		return AjaxJson.getByLine(line);
	}
	
	/** 删 - 根据id列表 */  
//	@RequestMapping("deleteByIds")
//	public AjaxJson deleteByIds(){
//		List<Long> ids = SoMap.getRequestSoMap().getListByComma("ids", long.class);
//		int line = SP.publicMapper.deleteByIds(Lottery.TABLE_NAME, ids);
//		return AjaxJson.getByLine(line);
//	}

	/** 查 - 根据id */  
	@RequestMapping("getById")
	public AjaxJson getById(String id){
		Lottery l = lotteryService.getById(id);
		return AjaxJson.getSuccessData(l);
	}

	/** 查集合 - 根据条件（参数为空时代表忽略指定条件） */  
	@RequestMapping("getList")
	public AjaxJson getList() {
		SoMap so = SoMap.getRequestSoMap();
		List<Lottery> list = lotteryService.getList(so.startPage());
		return AjaxJson.getPageData(so.getDataCount(), list);
	}

	/**
	 * 从彩票官网同步数据-》基础主表 lottery
	 * @param lotteryParameter
	 * @return
	 */
	@PostMapping("/sync/Lotterydata")
	public AjaxJson syncLotterydata(@RequestBody LotteryParameter lotteryParameter) {
		lotteryService.syncLotterydata(lotteryParameter);
		return AjaxJson.getSuccess();
	}

	@GetMapping("/convertDoubleSpheres")
	public ResultRes convertDoubleSpheres() {
		ConvertDoubleSpheresReq req = new ConvertDoubleSpheresReq();
		List<String> lotteryReds = Arrays.asList(
				"19,20,23,27,28,31","06,14,17,18,31,33","01,04,07,14,30,31"
				,"01,07,11,14,15,26","06,08,10,14,28,29","03,09,21,23,30,32"
				,"02,06,19,26,30,33","08,21,22,23,29,32","05,09,13,21,28,33"
				,"01,02,06,11,21,26","03,05,13,19,20,25","03,08,09,13,15,18"
				,"06,08,22,24,25,26","03,07,12,14,23,28"
		);
		req.setLotteryReds(lotteryReds);
		// 1-11,12-22,23-33
		//02,10,12,15,24,27
		req.setNoneRed("02,07,08,09,10,11,13,17,21,25,26,33");
		req.setOneRed("01,03,06,16,24,27,30,32");
		req.setTwoRed("04,05,12,14,15,18,20,22,23,28,29");
		req.setThreeRed("19,31");
//        req.setFourRed("06,27");
		//上一期同尾号期号的红球
		req.setCommonAddrRed("03,05,17,21,27,33");
		String filterRed = null;
//        filterRed = "16";
		req.setFilterRed(filterRed);
		List<String> list = lotteryService.convertDoubleSpheres(req);
		System.out.println("总共:"+ list.size()+"注");
		//校验是否存在我们想要的数据以及有多少？
//        String str = "01,07,08,12,13,18";
//        ListUtils.getContainsCount(str,list,5,6);
		return new ResultRes(StatusCode.RESULT_SUCCESS,list);
	}

	@PostMapping("/forest/lotteryConfig")
	public ResultRes lotteryConfig(@RequestBody LotteryParameter lotteryParameter) {
		return  new ResultRes(StatusCode.RESULT_SUCCESS,lotteryService.lotteryConfig(lotteryParameter));
	}


	@GetMapping("/testStrategy")
	public AjaxJson testStrategy(@RequestParam("consecutiveNumber") int consecutiveNumber){
		lotteryService.getLotteryByRed(consecutiveNumber);
		return AjaxJson.getSuccess();
	}


}
