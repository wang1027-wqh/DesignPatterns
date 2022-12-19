package com.alibab.code.observer.listener;

import com.alibab.code.observer.event.ApplicationContextEvent;
import com.alibab.code.observer.event.EventType;

import java.util.EventListener;

/**
 * @author <a href="mailto:1252532896@qq.com">Hua Cheng</a>
 * @Description
 * @Date 2022/12/17
 **/
public interface ApplicationListener<E extends ApplicationContextEvent> extends EventListener {


    void onApplicationEvent(E event);


    EventType getEventType();

}
