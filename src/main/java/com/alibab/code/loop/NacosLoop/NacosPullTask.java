package com.alibab.code.loop.NacosLoop;

import com.alibab.code.loop.Result;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Queue;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:1252532896@qq.com">Hua Cheng</a>
 * @Description
 * @Date 2023/3/5
 **/
@Slf4j
public class NacosPullTask  implements Runnable{
    Queue<NacosPullTask> nacosPullTasks;
    ScheduledExecutorService scheduler;
    AsyncContext asyncContext;
    String dataId;
    HttpServletRequest req;
    HttpServletResponse resp;

    Future<?> asyncTimeoutFuture;

    public NacosPullTask(Queue<NacosPullTask> nacosPullTasks, ScheduledExecutorService scheduler,
                         AsyncContext asyncContext, String dataId, HttpServletRequest req, HttpServletResponse resp) {
        this.nacosPullTasks = nacosPullTasks;
        this.scheduler = scheduler;
        this.asyncContext = asyncContext;
        this.dataId = dataId;
        this.req = req;
        this.resp = resp;
    }

    @Override
    public void run() {
    asyncTimeoutFuture =
        scheduler.schedule(
            () -> {
              // 这里如果remove this会失败,内部类中的this指向的并非当前对象,而是匿名内部类对象
                log.info("dataId:{}长时间未响应，断开任务直接返回",this.dataId);
              nacosPullTasks.remove(NacosPullTask.this);
              // sendResponse(null);
            },
            30,
            TimeUnit.SECONDS);
        nacosPullTasks.add(this);
        log.info("30秒后断开长轮询任务:" + new Date() + "dataId:" + this.dataId);
    }

    /**
     * 发送响应
     *
     * @param result
     */
    public void sendResponse(String dataId,String result) {
        System.out.println("发送响应:" + new Date() + "dataId" + dataId + "result" + result);
        //取消等待执行的任务,避免已经响完了,还有资源被占用
        if (asyncTimeoutFuture != null) {
            //设置为true会立即中断执行中的任务,false对执行中的任务无影响,但会取消等待执行的任务
            asyncTimeoutFuture.cancel(false);
        }

        //设置页码编码
        resp.setContentType("application/json; charset=utf-8");
        resp.setCharacterEncoding("utf-8");

        //禁用缓存
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Cache-Control", "no-cache,no-store");
        resp.setDateHeader("Expires", 0);
        resp.setStatus(HttpServletResponse.SC_OK);
        //输出Json流
        sendJsonResult(result);
    }

    /**
     * 发送响应流
     *
     * @param result
     */
    private void sendJsonResult(String result) {
        Result<String> pojoResult = new Result<>();
        pojoResult.setCode(200);
        pojoResult.setSuccess(!StringUtils.isEmpty(result));
        pojoResult.setData(result);
        PrintWriter writer = null;
        try {
            writer = asyncContext.getResponse().getWriter();
            writer.write(pojoResult.toString());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            asyncContext.complete();
            if (null != writer) {
                writer.close();
            }
        }
    }

}
