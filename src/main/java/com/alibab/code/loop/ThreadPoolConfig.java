package com.alibab.code.loop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author <a href="mailto:1252532896@qq.com">Hua Cheng</a>
 * @Description
 * @Date 2023/3/5
 **/
@Configuration
public class ThreadPoolConfig {
    @Bean
    public ScheduledExecutorService getScheduledExecutorService() {
        AtomicInteger poolNum = new AtomicInteger(0);
        ScheduledExecutorService scheduler = new ScheduledThreadPoolExecutor(2, r -> {
            Thread t = new Thread(r);
            t.setName("LoopLongPollingThread-" + poolNum.incrementAndGet());
            return t;
        });
        return scheduler;
    }

}
