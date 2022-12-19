package com.alibab.code.observer.publisher;

import com.alibab.code.observer.event.ApplicationEvent;

/**
 * @author <a href="mailto:1252532896@qq.com">Hua Cheng</a>
 * @Description
 * @Date 2022/12/17
 **/
@FunctionalInterface
public interface AoolicationEventPublisher {

    void publicsEvent(ApplicationEvent event);
}
