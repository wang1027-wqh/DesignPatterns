package com.alibab.code.loop.NacosLoop;

import org.springframework.stereotype.Service;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:1252532896@qq.com">Hua Cheng</a>
 * @Description
 * @Date 2023/3/5
 **/
@Service
public class NacosLongPollingServiceImpl implements NacosLongPollingService{

    final ScheduledExecutorService scheduler;
    final Queue<NacosPullTask> nacosPullTasks;

    public NacosLongPollingServiceImpl() {
        scheduler = new ScheduledThreadPoolExecutor(1, r -> {
            Thread t = new Thread(r);
            t.setName("NacosLongPollingTask");
            t.setDaemon(true);
            return t;
        });
        nacosPullTasks = new ConcurrentLinkedQueue<>();
        scheduler.scheduleAtFixedRate(() -> System.out.println("线程存活状态:" + new Date()), 0L, 60, TimeUnit.SECONDS);
    }

    @Override
    public void doGet(String dataId, HttpServletRequest req, HttpServletResponse resp) {
        // 一定要由当前HTTP线程调用，如果放在task线程容器会立即发送响应
        final AsyncContext asyncContext = req.startAsync();
        scheduler.execute(new NacosPullTask(nacosPullTasks, scheduler, asyncContext, dataId, req, resp));
    }

    @Override
    public void push(String dataId, String data) {
        scheduler.schedule(new NacosPushTask(dataId, data, nacosPullTasks), 0L, TimeUnit.MILLISECONDS);
    }
}
