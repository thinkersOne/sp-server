package com.pj.project.lottery;

import com.pj.project.aps.category.CategoryMapper;
import com.pj.project.aps.category.CategoryService;
import com.pj.project.aps.password.PasswordMapper;
import com.pj.project.aps.password.PasswordService;
import com.pj.project.aps.user.UserMapper;
import com.pj.project.aps.user.UserService;
import com.pj.project.lottery.lottery_all.LotteryAllMapper;
import com.pj.project.lottery.lottery_all.LotteryAllService;
import com.pj.project.lottery.lottery_calculate_count.LotteryCalculateCountMapper;
import com.pj.project.lottery.lottery_calculate_count.LotteryCalculateCountService;
import com.pj.project.lottery.lottery_calculate_nine.LotteryCalculateNineMapper;
import com.pj.project.lottery.lottery_calculate_nine.LotteryCalculateNineService;
import com.pj.project.lottery.lottery_calculate_nine_count.LotteryCalculateNineCountMapper;
import com.pj.project.lottery.lottery_calculate_nine_count.LotteryCalculateNineCountService;
import com.pj.project.lottery.lottery_calculate_per.LotteryCalculatePerMapper;
import com.pj.project.lottery.lottery_calculate_per.LotteryCalculatePerService;
import com.pj.project.lottery.lottery_config.LotteryConfigMapper;
import com.pj.project.lottery.lottery_config.LotteryConfigService;
import com.pj.project.lottery.lottery_forecast.LotteryForecastMapper;
import com.pj.project.lottery.lottery_forecast.LotteryForecastService;
import com.pj.project.lottery.lottery_red_proportion.LotteryRedProportionMapper;
import com.pj.project.lottery.lottery_red_proportion.LotteryRedProportionService;
import com.pj.project.lottery.lottery_select.LotterySelectMapper;
import com.pj.project.lottery.lottery_select.LotterySelectService;
import com.pj.project.lottery.lottery_strategy_record.LotteryStrategyRecordMapper;
import com.pj.project.lottery.lottery_strategy_record.LotteryStrategyRecordService;
import com.pj.project.sp_dev.public4mapper.PublicMapper;
import com.pj.project.sp_dev.public4mapper.PublicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * SpringBean依赖清单，项目中所有Bean在此定义
 */
@Component
public class LOTTERY_FC {

    // ======================================== 所有Mapper ==============================================

    public static LotteryMapper lotteryMapper;		// Mapper：
    public static PublicMapper publicMapper;					// Mapper: 公共Mapper
    public static LotteryCalculatePerMapper lotteryCalculatePerMapper;		// Mapper：统计计算每期双色球表
    public static LotteryStrategyRecordMapper lotteryStrategyRecordMapper;		// Mapper：策略记录表
    public static LotteryCalculateCountMapper lotteryCalculateCountMapper;		// Mapper：按照不同时间维度统计每个红蓝球情况
    public static LotteryCalculateNineMapper lotteryCalculateNineMapper;		// Mapper：九转连环图统计表
    public static LotteryAllMapper lotteryAllMapper;		// Mapper：所有可能得红球组合
    public static LotterySelectMapper lotterySelectMapper;		// Mapper：经过初步筛选后的全量双色球表
    public static LotteryForecastMapper lotteryForecastMapper;		// Mapper：号码预测
    public static LotteryCalculateNineCountMapper lotteryCalculateNineCountMapper;		// Mapper：统计九转中四行数据每行上出现个数的统计表
    public static LotteryRedProportionMapper lotteryRedProportionMapper;		// Mapper：各红球号占比
    public static LotteryConfigMapper lotteryConfigMapper;		// Mapper：配置表
    // ======================================== 所有Service ==============================================

    public static LotteryService lotteryService;		// Service：
    public static PublicService publicService;						// Service：公共service
    public static LotteryCalculatePerService lotteryCalculatePerService;		// Service：统计计算每期双色球表
    public static LotteryStrategyRecordService lotteryStrategyRecordService;		// Service：策略记录表
    public static LotteryCalculateCountService lotteryCalculateCountService;		// Service：按照不同时间维度统计每个红蓝球情况
    public static LotteryCalculateNineService lotteryCalculateNineService;		// Service：九转连环图统计表
    public static LotteryAllService lotteryAllService;		// Service：所有可能得红球组合
    public static LotterySelectService lotterySelectService;		// Service：经过初步筛选后的全量双色球表
    public static LotteryForecastService lotteryForecastService;		// Service：号码预测
    public static LotteryCalculateNineCountService lotteryCalculateNineCountService;		// Service：统计九转中四行数据每行上出现个数的统计表
    public static LotteryRedProportionService lotteryRedProportionService;		// Service：各红球号占比
    public static LotteryConfigService lotteryConfigService;		// Service：配置表

    // ======================================== 所有注入所有Bean ==============================================

    // 注入
    @Autowired
    public void setBean(
            LotteryMapper lotteryMapper,
            PublicMapper publicMapper,
            LotteryCalculatePerMapper lotteryCalculatePerMapper,
            LotteryCalculateCountMapper lotteryCalculateCountMapper,
            LotteryStrategyRecordMapper lotteryStrategyRecordMapper,
            LotteryCalculateNineMapper lotteryCalculateNineMapper,
            LotteryAllMapper lotteryAllMapper,
            LotterySelectMapper lotterySelectMapper,
            LotteryForecastMapper lotteryForecastMapper,
            LotteryCalculateNineCountMapper lotteryCalculateNineCountMapper,
            LotteryRedProportionMapper lotteryRedProportionMapper,
            LotteryConfigMapper lotteryConfigMapper,

            LotteryService lotteryService,
            LotteryCalculatePerService lotteryCalculatePerService,
            LotteryCalculateCountService lotteryCalculateCountService,
            LotteryCalculateNineCountService lotteryCalculateNineCountService,
            LotteryCalculateNineService lotteryCalculateNineService,
            LotteryStrategyRecordService lotteryStrategyRecordService,
            LotteryAllService lotteryAllService,
            LotterySelectService lotterySelectService,
            LotteryForecastService lotteryForecastService,
            LotteryRedProportionService lotteryRedProportionService,
            LotteryConfigService lotteryConfigService,
            PublicService publicService
    ) {
        LOTTERY_FC.lotteryMapper = lotteryMapper;
        LOTTERY_FC.publicMapper = publicMapper;
        LOTTERY_FC.lotteryCalculatePerMapper = lotteryCalculatePerMapper;
        LOTTERY_FC.lotteryCalculateCountMapper = lotteryCalculateCountMapper;
        LOTTERY_FC.lotteryCalculateNineMapper = lotteryCalculateNineMapper;
        LOTTERY_FC.lotteryCalculateNineCountMapper = lotteryCalculateNineCountMapper;
        LOTTERY_FC.lotteryAllMapper = lotteryAllMapper;
        LOTTERY_FC.lotteryStrategyRecordMapper = lotteryStrategyRecordMapper;
        LOTTERY_FC.lotterySelectMapper = lotterySelectMapper;
        LOTTERY_FC.lotteryForecastMapper = lotteryForecastMapper;
        LOTTERY_FC.lotteryRedProportionMapper = lotteryRedProportionMapper;
        LOTTERY_FC.lotteryConfigMapper = lotteryConfigMapper;

        LOTTERY_FC.lotteryService = lotteryService;
        LOTTERY_FC.publicService = publicService;
        LOTTERY_FC.lotteryCalculatePerService = lotteryCalculatePerService;
        LOTTERY_FC.lotteryStrategyRecordService = lotteryStrategyRecordService;
        LOTTERY_FC.lotteryCalculateCountService = lotteryCalculateCountService;
        LOTTERY_FC.lotteryCalculateNineService = lotteryCalculateNineService;
        LOTTERY_FC.lotteryCalculateNineCountService = lotteryCalculateNineCountService;
        LOTTERY_FC.lotteryAllService = lotteryAllService;
        LOTTERY_FC.lotterySelectService = lotterySelectService;
        LOTTERY_FC.lotteryForecastService = lotteryForecastService;
        LOTTERY_FC.lotteryRedProportionService = lotteryRedProportionService;
        LOTTERY_FC.lotteryConfigService = lotteryConfigService;
    }

}