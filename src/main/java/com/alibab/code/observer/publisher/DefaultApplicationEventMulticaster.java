package com.alibab.code.observer.publisher;

import com.alibab.code.observer.event.ApplicationContextEvent;
import com.alibab.code.observer.event.ApplicationEvent;
import com.alibab.code.observer.event.EventType;
import com.alibab.code.observer.listener.ApplicationListener;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author <a href="mailto:1252532896@qq.com">Hua Cheng</a>
 * @Description
 * @Date 2022/12/17
 **/
public class DefaultApplicationEventMulticaster implements ApplicationEventMulticaster{

    /**
     * 所有观察者集合
     * @param listener
     */
    private Set<ApplicationListener> listeners;

    private Map<EventType,Set<ApplicationListener>> specificEventListener;

    public DefaultApplicationEventMulticaster() {
        listeners = new HashSet<>();
        specificEventListener = new HashMap<>();
    }


    @Override
    public void addApplicationListener(ApplicationListener<?> listener) {
        this.listeners.add(listener);
        EventType eventtype = listener.getEventType();
        this.specificEventListener.putIfAbsent(eventtype, new HashSet<>());
        this.specificEventListener.get(eventtype).add(listener);
    }

    @Override
    public void removeApplicationListener(ApplicationListener<?> listener) {

    }

    @Override
    public void removeAllListener() {

    }

    @Override
    public void muticastEvent(ApplicationEvent event) {
        Set<ApplicationListener> applicationListeners = this.specificEventListener.get(((ApplicationContextEvent) event).getEventType());
        for (ApplicationListener applicationListener : applicationListeners) {
            applicationListener.onApplicationEvent((ApplicationContextEvent) event);
        }
    }
}
