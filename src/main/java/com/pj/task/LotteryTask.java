package com.pj.task;

import com.pj.current.global.LotteryConstant;
import com.pj.project.lottery.LotteryParameter;
import com.pj.project.lottery.lottery_calculate_count.LotteryCalculateCountService;
import com.pj.project.lottery.lottery_calculate_nine.LotteryCalculateNineService;
import com.pj.project.lottery.lottery_calculate_per.LotteryCalculatePerMapper;
import com.pj.project.lottery.lottery_calculate_per.LotteryCalculatePerService;
import com.pj.project.lottery.lottery_forecast.LotteryForecastService;
import com.pj.project.lottery.lottery_forecast.LotteryForestVo;
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
    private LotterySelectService lotterySelectService;
    @Autowired
    UnionLotto unionLotto;
    @Autowired
    private LotteryForecastService lotteryForecastService;


    @Scheduled(cron = "0 0 23 * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void syncAllLottery() {
        try{
            LotteryParameter lotteryParameter = LotteryParameter.builder().type(1).pageNo(1).pageSize(1650).build();
            lotteryService.syncLotterydata(lotteryParameter);

            lotteryCalculatePerService.lotteryCalculatePer();

            lotteryCalculateCountService.lotteryCalculateCount();

            lotteryCalculateNineService.lotteryCalculateNine();

            lotterySelectService.lotterySelect();
        }catch (Exception e){
            log.error("同步失败", e);
            throw new RuntimeException("同步失败");
        }
    }

//    @Scheduled(cron = "0 0 21 ? * 1,3,5")
    @Transactional(rollbackFor = Exception.class)
    public void syncCurrentLottery() {
        try{
            //获取数据库最新期号
            Lottery lottery = lotteryMapper.getCurrentLottery();

            //获取官网最新期号
            Lottery currentLottery = unionLotto.getCurrentLottery();

            if(lottery == null || currentLottery == null || lottery.getCode().equals(currentLottery.getCode())){
                return;
            }
            lotteryMapper.add(currentLottery);
            lotteryCalculatePerService.add(currentLottery);
            log.info("同步完成！");
        }catch (Exception e){
            log.error("同步最新期号失败", e);
            throw new RuntimeException("同步最新期号失败");
        }
    }

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
