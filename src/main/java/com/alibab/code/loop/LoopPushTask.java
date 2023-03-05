package com.alibab.code.loop;

import java.util.concurrent.Callable;

/**
 * @author <a href="mailto:1252532896@qq.com">Hua Cheng</a>
 * @Description
 * @Date 2023/3/5
 **/
public class LoopPushTask implements Callable<String> {

    private LoopPullTask loopPullTask;

    private String data;

    public LoopPushTask(LoopPullTask loopPullTask, String data) {
        this.loopPullTask = loopPullTask;
        this.data = data;
    }


    @Override
    public String call() throws Exception {
        loopPullTask.setData(data);
        return "changed";
    }
}
