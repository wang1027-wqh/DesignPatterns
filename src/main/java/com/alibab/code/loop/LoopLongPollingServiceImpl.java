package com.alibab.code.loop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:1252532896@qq.com">Hua Cheng</a>
 * @Description
 * @Date 2023/3/5
 **/
@Service
public class LoopLongPollingServiceImpl implements LoopLongPollingService{

    @Autowired
    ScheduledExecutorService scheduledExecutorService;

    private LoopPullTask loopPullTask;

    @Override
    public String poll() {
        loopPullTask = new LoopPullTask();
        Future<String> result = scheduledExecutorService.schedule(loopPullTask, 0L, TimeUnit.MILLISECONDS);
        try {
            return result.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return "ex";
    }

    @Override
    public String push(String data) {
        Future<String> future = scheduledExecutorService.schedule(new LoopPushTask(loopPullTask, data), 0L, TimeUnit.MILLISECONDS);
        try {
            return future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return "ex";
    }
}
