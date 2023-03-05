package com.alibab.code.loop;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.concurrent.Callable;

/**
 * @author <a href="mailto:1252532896@qq.com">Hua Cheng</a>
 * @Description
 * @Date 2023/3/5
 **/
@Slf4j
public class LoopPullTask implements Callable<String> {

    @Getter
    @Setter
    public volatile String data;

    private Long TIME_OUT_MILLIS = 10000L;

    @Override
    public String call() throws Exception {
        Long startTime = System.currentTimeMillis();
        while (true) {
            if (!StringUtils.isEmpty(data)) {
                return data;
            }
            if (isTimeOut(startTime)) {
                log.info("获取数据请求超时" + new Date());
                data = "请求超时";
                return data;
            }
            //减轻CPU压力
            Thread.sleep(200);
        }
    }

    private boolean isTimeOut(Long startTime) {
        Long nowTime = System.currentTimeMillis();
        return nowTime - startTime > TIME_OUT_MILLIS;
    }
}
