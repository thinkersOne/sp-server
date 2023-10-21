package com.pj.project.lottery.lottery_calculate_nine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.pj.models.so.SoMap;
import com.pj.project.lottery.lottery_calculate_count.LotteryCalculateCount;
import com.pj.project.lottery.lottery_calculate_per.LotteryCalculatePer;
import com.pj.project.lottery.lottery_calculate_per.LotteryCalculatePerMapper;
import com.pj.project.lottery.lottery_calculate_per.LotteryCalculatePerService;
import com.pj.project.lottery.unionLotto.enums.CalTypeEnum;
import com.pj.project.lottery.unionLotto.enums.NineTypeEnum;
import com.pj.utils.IntegerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * Service: lottery_calculate_nine -- 九转连环图统计表
 * @author lizhihao 
 */
@Service
public class LotteryCalculateNineService {

	/** 底层 Mapper 对象 */
	@Autowired
	LotteryCalculateNineMapper lotteryCalculateNineMapper;
	@Autowired
	LotteryCalculatePerMapper lotteryCalculatePerMapper;

	/** 增 */
	int add(LotteryCalculateNine l){
		return lotteryCalculateNineMapper.add(l);
	}

	/** 删 */
	int delete(Long id){
		return lotteryCalculateNineMapper.delete(id);
	}

	/** 改 */
	int update(LotteryCalculateNine l){
		return lotteryCalculateNineMapper.update(l);
	}

	/** 查 */
	LotteryCalculateNine getById(Long id){
		return lotteryCalculateNineMapper.getById(id);
	}

	/** 查集合 - 根据条件（参数为空时代表忽略指定条件） */  
	List<LotteryCalculateNine> getList(SoMap so) {
		String calType = (String)so.get("calType");
		String sortType = "";
		if("1".equals(calType)){
			sortType = "2";
		}else if("2".equals(calType)){
			sortType = "3";
		}else if ("4".equals(calType)) {
			sortType = "5";
		}
		so.set("sortType", sortType);
		return lotteryCalculateNineMapper.getList(so);	
	}

	void lotteryCalculateNine(){
		//删除数据
		lotteryCalculateNineMapper.deleteAll();
		//查询数据
		List<LotteryCalculatePer> lotteryCalculatePerMapperList = lotteryCalculatePerMapper.getList(new SoMap());
		if(CollectionUtils.isEmpty(lotteryCalculatePerMapperList)){
			throw new RuntimeException("没有数据!");
		}
		//按照code XXX0-XXX9分组
		Map<String,List<LotteryCalculatePer>> codeMap = new HashMap<>(100);
		List<LotteryCalculatePer> list = new ArrayList<>(10);
		StringBuffer sb = new StringBuffer();
		Map<String,LotteryCalculatePer> beforeMap = new HashMap<>(100);
		lotteryCalculatePerMapperList.sort((a,b)-> a.getCode().compareTo(b.getCode()));
		Stream.iterate(0, n -> n + 1).limit(lotteryCalculatePerMapperList.size()).forEach(i ->{
			LotteryCalculatePer lotteryCalculatePer = lotteryCalculatePerMapperList.get(i);
			String code = lotteryCalculatePer.getCode();
			String year = code.substring(0, 4);
			if(lotteryCalculatePer.getCode().endsWith("1")){
				if(!beforeMap.isEmpty() && !beforeMap.containsKey(year)){
					LotteryCalculatePer beforLcp = lotteryCalculatePerMapperList.get(i - 1);
					if(!beforLcp.getCode().endsWith("0")){
						sb.append("-").append(beforLcp.getCode());
						List<LotteryCalculatePer> perList = new ArrayList<>(list);
						codeMap.put(sb.toString(), perList);
						sb.delete(0, sb.length());
						list.clear();
					}
				}
				list.add(lotteryCalculatePer);
				sb.append(lotteryCalculatePer.getCode());
			}else if(lotteryCalculatePer.getCode().endsWith("0")){
				list.add(lotteryCalculatePer);
				sb.append("-").append(lotteryCalculatePer.getCode());
				List<LotteryCalculatePer> perList = new ArrayList<>(list);
				codeMap.put(sb.toString(), perList);
				sb.delete(0, sb.length());
				list.clear();
			}else{
				list.add(lotteryCalculatePer);
			}
			beforeMap.put(year,lotteryCalculatePer);
		});
		List<LotteryCalculateNine> codesLotteryCalculateNineList = getList(codeMap,CalTypeEnum.CODE.getCalType());
		lotteryCalculateNineMapper.batchInsertLotteryCalculateNine(codesLotteryCalculateNineList);
		//按照年份分组
		Map<Integer, List<LotteryCalculatePer>> yearList = lotteryCalculatePerMapperList.stream()
				.collect(Collectors.groupingBy(LotteryCalculatePer::getYear));
		List<LotteryCalculateNine> yearLotteryCalculateNineList = getList(yearList,CalTypeEnum.YEAR.getCalType());
		lotteryCalculateNineMapper.batchInsertLotteryCalculateNine(yearLotteryCalculateNineList);
		//按照年+月份分组
		Map<String, List<LotteryCalculatePer>> yearMonthList =
				lotteryCalculatePerMapperList.stream().collect(Collectors.groupingBy(v-> v.getYear()+"_"+v.getMonth()));
		List<LotteryCalculateNine> monthLotteryCalculateNineList = getList(yearMonthList,CalTypeEnum.MONTH.getCalType());
		lotteryCalculateNineMapper.batchInsertLotteryCalculateNine(monthLotteryCalculateNineList);
	}

	public <T> List<LotteryCalculateNine> getList(Map<T, List<LotteryCalculatePer>> map,int calType) {
		List<LotteryCalculateNine> list = new ArrayList<>(10);
		map.forEach((key,value) ->{
			Map<String,Integer> nine09Map = new HashMap<>(100);
			Map<String,Integer> nine17Map = new HashMap<>(100);
			Map<String,Integer> nine33Map = new HashMap<>(100);
			value.stream().forEach(v->{
				String nineTurn09 = v.getNineTurn09().split("\\|")[0];
				nine09Map.put(nineTurn09, IntegerUtils.convertToInt(nine09Map.get(nineTurn09)) +1);
				String nineTurn17 = v.getNineTurn17().split("\\|")[0];
				nine17Map.put(nineTurn17, IntegerUtils.convertToInt(nine17Map.get(nineTurn17)) +1);
				String nineTurn33 = v.getNineTurn33().split("\\|")[0];
				nine33Map.put(nineTurn33, IntegerUtils.convertToInt(nine33Map.get(nineTurn33)) +1);
			});
			list.addAll(getList(nine09Map, calType, key, NineTypeEnum.NINE09.getType()));
			list.addAll(getList(nine17Map, calType, key, NineTypeEnum.NINE17.getType()));
			list.addAll(getList(nine33Map, calType, key, NineTypeEnum.NINE33.getType()));
		});
		return list;
	}

	private <T> List<LotteryCalculateNine> getList(Map<String,Integer> map,int calType,T key,int nineTurnType) {
		List<LotteryCalculateNine> lotteryCalculateNineList = new ArrayList<>(50);
		map.forEach((k,v)-> {
			LotteryCalculateNine l = new LotteryCalculateNine();
			if(CalTypeEnum.YEAR.getCalType() == calType){
				l.setYear((Integer) key);
			} else if (CalTypeEnum.MONTH.getCalType() == calType) {
				String[] split = ((String) key).split("_");
				l.setYear(Integer.valueOf(split[0]));
				l.setMonth(Integer.valueOf(split[1]));
			}else if (CalTypeEnum.CODE.getCalType() == calType) {
				l.setCodes((String) key);
			}
			l.setCalType(calType);
			l.setNineTurn(k);
			l.setNineTurnCount(v);
			l.setNineTurnType(nineTurnType);
			lotteryCalculateNineList.add(l);
		});
		return lotteryCalculateNineList;
	}

}
