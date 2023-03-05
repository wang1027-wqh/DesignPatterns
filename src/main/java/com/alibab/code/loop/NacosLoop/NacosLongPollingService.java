package com.alibab.code.loop.NacosLoop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author <a href="mailto:1252532896@qq.com">Hua Cheng</a>
 * @Description
 * @Date 2023/3/5
 **/
public interface NacosLongPollingService {
    void doGet(String dataId, HttpServletRequest req, HttpServletResponse resp);

    void push(String dataId, String data);
}
