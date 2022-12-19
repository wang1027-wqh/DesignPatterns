package com.alibab.code.strategy.utils;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author <a href="mailto:1252532896@qq.com">Hua Cheng</a>
 * @Description
 * @Date 2022/12/19
 **/
public class ThreadExecutorUtils {

    public static ExecutorService defaultExecutorService = new ThreadPoolExecutor(10,30,
            1L, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(1000),
            new MyThreadFactory("default-pool"),
            new ThreadPoolExecutor.AbortPolicy());

    public static class MyThreadFactory implements ThreadFactory {
        final ThreadGroup group;
        final AtomicInteger threadNumber = new AtomicInteger(1);
        final String namePrefix;


        public MyThreadFactory(String name) {
            SecurityManager securityManager = System.getSecurityManager();
            group = (securityManager != null) ? securityManager.getThreadGroup() : Thread.currentThread().getThreadGroup();
            namePrefix = "appname" + name + "-";
        }

        @Override
        public Thread newThread(final Runnable r) {
            Thread thread = new Thread(group,r,getNamePrefix() + threadNumber.getAndIncrement() + "#",0);
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            if (thread.getPriority() != Thread.NORM_PRIORITY) {
                thread.setPriority(Thread.NORM_PRIORITY);
            }
            return thread;
        }

        public String getNamePrefix() {
            return namePrefix;
        }
    }
}
