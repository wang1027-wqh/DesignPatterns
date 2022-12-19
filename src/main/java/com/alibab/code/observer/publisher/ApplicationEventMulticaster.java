package com.alibab.code.observer.publisher;

import com.alibab.code.observer.event.ApplicationEvent;
import com.alibab.code.observer.listener.ApplicationListener;

/**
 * @author <a href="mailto:1252532896@qq.com">Hua Cheng</a>
 * @Description
 * @Date 2022/12/17
 **/
public interface ApplicationEventMulticaster {


    void addApplicationListener(ApplicationListener<?> listener);

    void removeApplicationListener(ApplicationListener<?> listener);

    void removeAllListener();

    void muticastEvent(ApplicationEvent event);

}
