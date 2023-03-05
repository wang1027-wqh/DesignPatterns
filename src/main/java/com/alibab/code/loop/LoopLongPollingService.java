package com.alibab.code.loop;

/**
 * @author <a href="mailto:1252532896@qq.com">Hua Cheng</a>
 * @Description
 * @Date 2023/3/5
 **/
public interface LoopLongPollingService {


    /**
     * 拉取变更数据
     * @return
     */
    String poll();

    /**
     *
     * @param data
     * @return
     */
    String push(String data);

}
