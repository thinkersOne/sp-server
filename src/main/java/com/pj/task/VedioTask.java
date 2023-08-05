package com.pj.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class VedioTask {

    @Scheduled(cron="0/5 * *  * * ? ")   //每5秒执行一次
    public void execute(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //设置日期格式
        System.out.println("欢迎访问 pan_junbiao的博客 " + df.format(new Date()));
    }


}
