package com.pj.project.lottery;

import java.util.*;
import java.util.stream.Collectors;

import cn.hutool.json.JSONUtil;
import com.pj.current.enums.LotteryTypeEnum;
import com.pj.models.so.SoMap;
import com.pj.project.lottery.lottery_all.LotteryAllService;
import com.pj.project.lottery.lottery_calculate_count.LotteryCalculateCount;
import com.pj.project.lottery.lottery_calculate_count.LotteryCalculateCountMapper;
import com.pj.project.lottery.lottery_calculate_count.LotteryCalculateCountService;
import com.pj.project.lottery.lottery_calculate_nine.LotteryCalculateNineService;
import com.pj.project.lottery.lottery_calculate_nine_count.LotteryCalculateNineCountService;
import com.pj.project.lottery.lottery_calculate_per.LotteryCalculatePer;
import com.pj.project.lottery.lottery_calculate_per.LotteryCalculatePerMapper;
import com.pj.project.lottery.lottery_calculate_per.LotteryCalculatePerService;
import com.pj.project.lottery.lottery_config.LotteryConfig;
import com.pj.project.lottery.lottery_config.LotteryConfigMapper;
import com.pj.project.lottery.lottery_red_proportion.LotteryRedProportionService;
import com.pj.project.lottery.lottery_select.LotterySelectCodesDTO;
import com.pj.project.lottery.lottery_select.LotterySelectService;
import com.pj.project.lottery.unionLotto.*;
import com.pj.project.lottery.unionLotto.domain.ConvertDoubleSpheresReq;
import com.pj.project.lottery.unionLotto.domain.Lottery;
import com.pj.project.lottery.unionLotto.enums.CalTypeEnum;
import com.pj.project.lottery.unionLotto.enums.ParityRatioEnum;
import com.pj.project.lottery.unionLotto.enums.RangeEnum;
import com.pj.project.lottery.unionLotto.utils.PersonalLawUtils;
import com.pj.project.lottery.unionLotto.utils.RuleUtils;
import com.pj.utils.FileGenerator;
import com.pj.utils.MappedBiggerFileWriter;
import com.pj.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * Service: lottery -- 
 * @author lizhihao 
 */
@Service
@Slf4j
public class LotteryService {

	/** 底层 Mapper 对象 */
	@Autowired
	LotteryMapper lotteryMapper;
	@Autowired
	LotteryCalculatePerMapper lotteryCalculatePerMapper;
	@Autowired
	LotteryCalculateCountMapper lotteryCalculateCountMapper;
	@Autowired
	LotterySelectService lotterySelectService;
	@Autowired
	LotteryConfigMapper lotteryConfigMapper;
	@Autowired
	UnionLotto unionLotto;

	/** 增 */
	int add(Lottery l){
		return lotteryMapper.add(l);
	}

	/** 删 */
	int delete(String code){
		return lotteryMapper.delete(code);
	}

	/** 改 */
	int update(Lottery l){
		return lotteryMapper.update(l);
	}

	/** 查 */
	Lottery getById(String code){
		return lotteryMapper.getById(code);
	}

	/** 查集合 - 根据条件（参数为空时代表忽略指定条件） */  
	List<Lottery> getList(SoMap so) {
		return lotteryMapper.getList(so);
	}

	public void syncLotterydata(LotteryParameter lotteryParameter){
		lotteryMapper.deleteAll();
		if(LotteryTypeEnum.UNION_LOTTO.getType() == lotteryParameter.getType()){
			lotteryParameter.setName(LotteryTypeEnum.UNION_LOTTO.getCode());
			//查询数据
			List<Lottery> lotterys = unionLotto.getLotterys(lotteryParameter);
			log.info(JSONUtil.toJsonStr(lotterys));
			lotteryMapper.batchInsertLottery(lotterys);
		}
	}

	/**
	 * 获取最终结果
	 * @param req
	 * @return
	 */
	public List<String> convertDoubleSpheres(ConvertDoubleSpheresReq req) {
		List<String> list = RuleUtils.getAllGenerateCompose(Arrays.asList(
				RuleUtils.getChooseLotteryRed(req).split(",")));
		//按照app上所选的进行过滤，不超过5个相同
		if(!CollectionUtils.isEmpty(req.getLotteryReds())){
			list = RuleUtils.filterLotterysAmount(list, req.getLotteryReds(), 3);
		}
		//根据指定规则获取红球
		List<String> filterLotterys = filterLotterys(list,req.getFilterRed());
		//对结果 按照一定规律 进行筛选
		//查询是否存在当前期号数据
		Lottery currentLottery = lotteryMapper.getCurrentLottery();
		if(currentLottery != null){
			req.setCurrentLotteryRed(currentLottery.getRed());
		}
		List<String> result = PersonalLawUtils.filterByPersonalLaw(filterLotterys, req);
		List<String> finalFilterList = new ArrayList<>(result);
//        List<String> finalFilterList = RuleUtils.filterLotterysAmount(result, 3);
		//蓝球
		List<String> blueResult =
				RuleUtils.getParity(RuleUtils.getBlueAreaThree(RuleUtils.getBlueBig())
				);
		List<String> collect = finalFilterList.stream().map(v ->
				v + "," + blueResult.toString()
		).collect(Collectors.toList());
		return collect;
	}

	/**
	 * 从已选的红球中过滤出不满足条件的红球组合
	 * @param chooseLotterys
	 * @return
	 */
	public List<String> filterLotterys(List<String> chooseLotterys,String filterRed){
		if(CollectionUtils.isEmpty(chooseLotterys)){
			return new ArrayList<>(1);
		}
		//策略
		List<String> result = getStrings(chooseLotterys);
		//筛选出 1连号
		result = RuleUtils.filterEvenNoCount(result,0);
		return result;
	}


	private List<String> getStrings(List<String> filterLotterys) {
		//2:3:1 或 1:2:3 或 3:2:1   连号   4:2 或 3:3
		List<String> filterEvenNoYes = RuleUtils.filterEvenNoNo(filterLotterys);
		Set<String> set = new HashSet<>();
//        List<String> list1 = RuleUtils.filterParityRatio(filterEvenNoYes, ParityRatioEnum.FOUR_TWO.getCode());
//        List<String> list2 = RuleUtils.filterParityRatio(filterEvenNoYes, ParityRatioEnum.THREE_THREE.getCode());
		List<String> list3 = RuleUtils.filterParityRatio(filterEvenNoYes, ParityRatioEnum.TWO_FOUR.getCode());
//        List<String> list4 = RuleUtils.filterParityRatio(filterEvenNoYes, ParityRatioEnum.FIVE_ONE.getCode());
//        set.addAll(list1);
//        set.addAll(list2);
		set.addAll(list3);
//        set.addAll(list4);
		Set<String> set1 = new HashSet<>();
		List<String> convertList = new ArrayList<>(set);
//        List<String> list11 = RuleUtils.filterRange(convertList, RangeEnum.ONE_THREE_TWO.getCode());
//        List<String> list12 = RuleUtils.filterRange(convertList, RangeEnum.TWO_TWO_TWO.getCode());
//        List<String> list13 = RuleUtils.filterRange(convertList, RangeEnum.TWO_THREE_ONE.getCode());
//        List<String> list14 = RuleUtils.filterRange(convertList, RangeEnum.ZERO_FIVE_ONE.getCode());
//        List<String> list15 = RuleUtils.filterRange(convertList, RangeEnum.THREE_TWO_ONE.getCode());
		List<String> list16 = RuleUtils.filterRange(convertList, RangeEnum.ONE_TWO_THREE.getCode());
//        List<String> list17 = RuleUtils.filterRange(convertList, RangeEnum.TWO_ONE_THREE.getCode());
//        set1.addAll(list11);
//        set1.addAll(list12);
//        set1.addAll(list13);
//        set1.addAll(list14);
//        set1.addAll(list15);
		set1.addAll(list16);
//        set1.addAll(list17);
		List<String> result = new ArrayList<>(5000);
		result.addAll(set1);
		return result;

//        return filterLotterys;
	}

	/**
	 * 计算红球 按照 consecutiveNumber个连续红球出现的情况统计
	 * @param consecutiveNumber
	 * @return
	 */
	public List<Lottery> getLotteryByRed(int consecutiveNumber){
		List<Lottery> result = new ArrayList<>(30);
		for (int i = 0; i < RuleUtils.PARTITION_ONE.size() - consecutiveNumber; i++) {
			result.addAll(lotteryMapper.getLotteryByRed(getRed(consecutiveNumber, i)));
		}
		for (int i = 0; i < RuleUtils.PARTITION_TWO.size() - consecutiveNumber; i++) {
			result.addAll(lotteryMapper.getLotteryByRed(getRed(consecutiveNumber, i)));
		}
		for (int i = 0; i < RuleUtils.PARTITION_THREE.size() - consecutiveNumber; i++) {
			result.addAll(lotteryMapper.getLotteryByRed(getRed(consecutiveNumber, i)));
		}
		log.info("共有:"+ result.size() +"条连续"+consecutiveNumber+"个号码的数据");
		String codes = result.stream().map(v -> v.code).collect(Collectors.joining(","));
		log.info("连续"+consecutiveNumber+"个号码的数据期号:"+ codes);
		return result;
	}

	private static String getRed(int consecutiveNumber, int i) {
		StringBuffer sb = new StringBuffer();
		for (int j = 0; j < consecutiveNumber; j++) {
			if(j == consecutiveNumber - 1){
				sb.append(RuleUtils.PARTITION_ONE.get(i +j));
			}else{
				sb.append(RuleUtils.PARTITION_ONE.get(i +j)).append(",");
			}
		}
		return sb.toString();
	}

}
