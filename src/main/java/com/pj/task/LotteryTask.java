package com.pj.task;

import com.pj.current.global.LotteryConstant;
import com.pj.project.lottery.LotteryParameter;
import com.pj.project.lottery.lottery_calculate_count.LotteryCalculateCountService;
import com.pj.project.lottery.lottery_calculate_nine.LotteryCalculateNineService;
import com.pj.project.lottery.lottery_calculate_nine_count.LotteryCalculateNineCountService;
import com.pj.project.lottery.lottery_calculate_per.LotteryCalculatePerMapper;
import com.pj.project.lottery.lottery_calculate_per.LotteryCalculatePerService;
import com.pj.project.lottery.lottery_forecast.LotteryForecastService;
import com.pj.project.lottery.lottery_forecast.LotteryForestVo;
import com.pj.project.lottery.lottery_red_proportion.LotteryRedProportionService;
import com.pj.project.lottery.lottery_select.LotterySelectService;
import com.pj.project.lottery.unionLotto.domain.Lottery;
import com.pj.project.lottery.LotteryMapper;
import com.pj.project.lottery.LotteryService;
import com.pj.project.lottery.unionLotto.UnionLotto;
import com.pj.utils.cache.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Slf4j
@Component
public class LotteryTask {
    @Autowired
    private LotteryService lotteryService;
    @Autowired
    private LotteryMapper lotteryMapper;
    @Autowired
    private LotteryCalculatePerService lotteryCalculatePerService;
    @Autowired
    private LotteryCalculateCountService lotteryCalculateCountService;
    @Autowired
    private LotteryCalculateNineService lotteryCalculateNineService;
    @Autowired
    private LotteryCalculateNineCountService lotteryCalculateNineCountService;
    @Autowired
    private LotteryRedProportionService lotteryRedProportionService;
    @Autowired
    private LotterySelectService lotterySelectService;
    @Autowired
    UnionLotto unionLotto;
    @Autowired
    private LotteryForecastService lotteryForecastService;

    /**
     * 每周二、周四、周日执行  设置主表
     */
    @Scheduled(cron = "0 0 23 * * 2,4,7")
    @Transactional(rollbackFor = Exception.class)
    public void syncLottery() {
        try{
            LotteryParameter lotteryParameter = LotteryParameter.builder().type(1).pageNo(1).pageSize(1650).build();
            lotteryService.syncLotterydata(lotteryParameter);
        }catch (Exception e){
            log.error("同步失败", e);
//            throw new RuntimeException("同步失败");
            syncLottery();//同步失败则继续重试
        }
    }

    /**
     * 每周二、周四、周日执行  设置副表
     */
    @Scheduled(cron = "0 0 23 * * 2,4,7")
    @Transactional(rollbackFor = Exception.class)
    public void syncLotteryTemp() {
        try{
            LotteryParameter lotteryParameter = LotteryParameter.builder().type(1).pageNo(1).pageSize(1650).build();
            lotteryService.syncLotteryTempdata(lotteryParameter);
        }catch (Exception e){
            log.error("同步失败", e);
            //throw new RuntimeException("同步失败");
            syncLotteryTemp();
        }
    }

    @Scheduled(cron = "0 10 23 * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void syncLotteryCalculatePer() {
        try{
            lotteryCalculatePerService.lotteryCalculatePer();
        }catch (Exception e){
            log.error("同步失败", e);
            //throw new RuntimeException("同步失败");
            syncLotteryCalculatePer();
        }
    }

    @Scheduled(cron = "0 20 23 * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void syncLotteryCalculateCount() {
        try{
            lotteryCalculateCountService.lotteryCalculateCount();
        }catch (Exception e){
            log.error("同步失败", e);
            //throw new RuntimeException("同步失败");
            syncLotteryCalculateCount();
        }
    }

    @Scheduled(cron = "0 20 23 * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void syncLotteryCalculateNine() {
        try{
            lotteryCalculateNineService.lotteryCalculateNine();
        }catch (Exception e){
            log.error("同步失败", e);
            //throw new RuntimeException("同步失败");
            syncLotteryCalculateNine();
        }
    }

    @Scheduled(cron = "0 20 23 * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void syncLotteryCalculateNineCount() {
        try{
            lotteryCalculateNineCountService.lotteryCalculateNineCount();
        }catch (Exception e){
            log.error("同步失败", e);
            //throw new RuntimeException("同步失败");
            syncLotteryCalculateNineCount();
        }
    }

    @Scheduled(cron = "0 20 23 * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void syncLotteryRedProportion() {
        try{
            lotteryRedProportionService.lotteryRedProportion();
        }catch (Exception e){
            log.error("同步失败", e);
            //throw new RuntimeException("同步失败");
            syncLotteryRedProportion();
        }
    }

    @Scheduled(cron = "0 30 23 * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void syncLotterySelect() {
        try{
            lotterySelectService.lotterySelect();
        }catch (Exception e){
            log.error("同步失败", e);
            //throw new RuntimeException("同步失败");
            syncLotterySelect();
        }
    }
//    @Scheduled(cron = "0 0 23 * * ?")
//    @Transactional(rollbackFor = Exception.class)
//    public void syncAllLottery() {
//        try{
//            LotteryParameter lotteryParameter = LotteryParameter.builder().type(1).pageNo(1).pageSize(1650).build();
//            lotteryService.syncLotterydata(lotteryParameter);
//
//            lotteryCalculatePerService.lotteryCalculatePer();
//
//            lotteryCalculateCountService.lotteryCalculateCount();
//
//            lotteryCalculateNineService.lotteryCalculateNine();
//
//            lotterySelectService.lotterySelect();
//        }catch (Exception e){
//            log.error("同步失败", e);
//            throw new RuntimeException("同步失败");
//        }
//    }

    /**
     * 定时 将预测策略 失败的数据重新进行预测
     */
    @Scheduled(cron = "0 0 0,23 * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void reLotteryConfig() {
        List<Object> objects = RedisUtil.forListGet(LotteryConstant.LOTTERY_FORECAST_ERROR_KEY);
        if(!CollectionUtils.isEmpty(objects)){
            //先删除 再针对错误的code重新执行
            RedisUtil.forListRemove(LotteryConstant.LOTTERY_FORECAST_ERROR_KEY);
            objects.stream().forEach(v->{
                LotteryForestVo lotteryForestVo = LotteryForestVo.builder()
                        .code((String) v).type(0).orderBy(0).build();
                lotteryForecastService.lotteryConfig(lotteryForestVo);
            });
            log.info("重新预测完成！");
        }
    }

}
