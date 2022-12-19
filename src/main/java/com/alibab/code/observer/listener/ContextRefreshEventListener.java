package com.alibab.code.observer.listener;

import com.alibab.code.observer.ObserverDTO;
import com.alibab.code.observer.event.ContextRefreshEvent;
import com.alibab.code.observer.event.EventType;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author <a href="mailto:1252532896@qq.com">Hua Cheng</a>
 * @Description
 * @Date 2022/12/17
 **/
@Component
public class ContextRefreshEventListener implements ApplicationListener<ContextRefreshEvent>{

    @Override
    public void onApplicationEvent(ContextRefreshEvent event) {
        ObserverDTO observerDTO = event.getObserverDTO();
        System.out.println(observerDTO.toString());
        System.out.println("我执行了");
    }

    @Override
    public EventType getEventType() {
        return EventType.ContextRefresh;
    }
}

