package com.alibab.code.observer.event;

import com.alibab.code.observer.ObserverDTO;

/**
 * @author <a href="mailto:1252532896@qq.com">Hua Cheng</a>
 * @Description
 * @Date 2022/12/17
 **/
public class ContextRefreshEvent extends ApplicationContextEvent{

    private ObserverDTO observerDTO;

    /**
     * Constructs a prototypical Event.
     *
     * @param source    The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ContextRefreshEvent(Object source, ObserverDTO observerDTO) {
        super(source, EventType.ContextRefresh);
        this.observerDTO = observerDTO;
    }

    public ObserverDTO getObserverDTO() {
        return observerDTO;
    }
}
