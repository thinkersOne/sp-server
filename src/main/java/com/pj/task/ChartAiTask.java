package com.pj.task;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.pj.current.global.AiChartConstant;
import com.pj.project.aps.chart_message.ChartMessage;
import com.pj.project.aps.chart_message.ChartMessageMapper;
import com.pj.project.aps.chart_message.ChartMessageService;
import com.pj.utils.Ttime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ChartAiTask {
    @Autowired
    private ChartMessageMapper chartMessageMapper;
    @Autowired
    private ChartMessageService chartMessageService;

    private static final int DELETE_MONTH_SUM = 1;
    private static final int DELETE_COUNT_SUM = 100;

    @Scheduled(cron="0 0 23 * * ?")   //每天执行一次
//    @Scheduled(cron="0/20 * *  * * ? ")   //每5秒执行一次
    public void execute(){
        //查询数据
        List<ChartMessage> list = chartMessageMapper.getListAll();
        List<Long> deleteIds = new ArrayList<>(200);
        // 数据保留一个月时间
        list.stream().forEach(v->{
            String createTime = v.getCreateTime();
            if(Ttime.isBefore(createTime,Ttime.getLocalDateTime(DELETE_MONTH_SUM))){
                deleteIds.add(v.getId());
            }
        });
        // 数据量超过 DELETE_COUNT_SUM时清除老数据
        list.stream().collect(Collectors.groupingBy(v -> v.getUserId()))
                .forEach((w,q)->{
                    if(!CollectionUtils.isEmpty(q) && q.size() > DELETE_COUNT_SUM){
                        int deleteLines = q.size() - DELETE_COUNT_SUM;
                        for (int i = 0; i < deleteLines; i++) {
                            deleteIds.add(q.get(i).getId());
                        }
                    }
                });
        //删除数据
        if(!CollectionUtils.isEmpty(deleteIds)){
            chartMessageService.deleteByIds(deleteIds);
        }
        log.info("删除:{}条数据，执行完毕！",deleteIds.size());
    }

    @Scheduled(cron="0 0 1 1 * *")   //每个月第一天执行
    public void refreshToken(){
        Map<Long, String> tokenMap = AiChartConstant.tokenMap;
        if(tokenMap == null || tokenMap.isEmpty()){
            return;
        }
        tokenMap.clear();
    }


}