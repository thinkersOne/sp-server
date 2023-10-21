package com.pj.project.lottery;

import com.pj.project.aps.category.CategoryMapper;
import com.pj.project.aps.category.CategoryService;
import com.pj.project.aps.password.PasswordMapper;
import com.pj.project.aps.password.PasswordService;
import com.pj.project.aps.user.UserMapper;
import com.pj.project.aps.user.UserService;
import com.pj.project.lottery.lottery_calculate_count.LotteryCalculateCountMapper;
import com.pj.project.lottery.lottery_calculate_count.LotteryCalculateCountService;
import com.pj.project.lottery.lottery_calculate_nine.LotteryCalculateNineMapper;
import com.pj.project.lottery.lottery_calculate_nine.LotteryCalculateNineService;
import com.pj.project.lottery.lottery_calculate_per.LotteryCalculatePerMapper;
import com.pj.project.lottery.lottery_calculate_per.LotteryCalculatePerService;
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
    public static LotteryCalculateCountMapper lotteryCalculateCountMapper;		// Mapper：按照不同时间维度统计每个红蓝球情况
    public static LotteryCalculateNineMapper lotteryCalculateNineMapper;		// Mapper：九转连环图统计表



    // ======================================== 所有Service ==============================================

    public static LotteryService lotteryService;		// Service：
    public static PublicService publicService;						// Service：公共service
    public static LotteryCalculatePerService lotteryCalculatePerService;		// Service：统计计算每期双色球表
    public static LotteryCalculateCountService lotteryCalculateCountService;		// Service：按照不同时间维度统计每个红蓝球情况
    public static LotteryCalculateNineService lotteryCalculateNineService;		// Service：九转连环图统计表

    // ======================================== 所有注入所有Bean ==============================================

    // 注入
    @Autowired
    public void setBean(
            LotteryMapper lotteryMapper,
            PublicMapper publicMapper,
            LotteryCalculatePerMapper lotteryCalculatePerMapper,
            LotteryCalculateCountMapper lotteryCalculateCountMapper,
            LotteryCalculateNineMapper lotteryCalculateNineMapper,


            LotteryService lotteryService,
            LotteryCalculatePerService lotteryCalculatePerService,
            LotteryCalculateCountService lotteryCalculateCountService,
            LotteryCalculateNineService lotteryCalculateNineService,
            PublicService publicService
    ) {
        LOTTERY_FC.lotteryMapper = lotteryMapper;
        LOTTERY_FC.publicMapper = publicMapper;
        LOTTERY_FC.lotteryCalculatePerMapper = lotteryCalculatePerMapper;
        LOTTERY_FC.lotteryCalculateCountMapper = lotteryCalculateCountMapper;
        LOTTERY_FC.lotteryCalculateNineMapper = lotteryCalculateNineMapper;

        LOTTERY_FC.lotteryService = lotteryService;
        LOTTERY_FC.publicService = publicService;
        LOTTERY_FC.lotteryCalculatePerService = lotteryCalculatePerService;
        LOTTERY_FC.lotteryCalculateCountService = lotteryCalculateCountService;
        LOTTERY_FC.lotteryCalculateNineService = lotteryCalculateNineService;

    }

}