package com.example.schedulingtasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

//泛指组件，当组件不好归类的时候(不属于 Service Controller Repository ...)
@Component
public class ScheduledTasks {

    //日志记录组件
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    //日期显示组件
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    //fixedRate: 指定调用方法之间的间隔(每隔五秒自动执行一次方法)
    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        log.info("The time is new {}", dateFormat.format(new Date()));
    }
}
