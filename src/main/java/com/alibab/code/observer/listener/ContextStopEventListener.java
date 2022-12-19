package com.alibab.code.observer.listener;

import com.alibab.code.observer.event.ContextStopEvent;
import com.alibab.code.observer.event.EventType;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:1252532896@qq.com">Hua Cheng</a>
 * @Description
 * @Date 2022/12/17
 **/
@Component
public class ContextStopEventListener implements ApplicationListener<ContextStopEvent> {
    @Override
    public void onApplicationEvent(ContextStopEvent event) {


    }

    @Override
    public EventType getEventType() {
        return EventType.ContextStop;
    }
}
