package com.pj.task;

import com.pj.project.lottery.lottery_calculate_per.LotteryCalculatePerMapper;
import com.pj.project.lottery.lottery_calculate_per.LotteryCalculatePerService;
import com.pj.project.lottery.unionLotto.domain.Lottery;
import com.pj.project.lottery.LotteryMapper;
import com.pj.project.lottery.LotteryService;
import com.pj.project.lottery.unionLotto.UnionLotto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
    UnionLotto unionLotto;

    @Scheduled(cron = "0 0 21 ? * 1,3,5")
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

}
