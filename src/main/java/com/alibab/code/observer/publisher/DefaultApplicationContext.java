package com.alibab.code.observer.publisher;

import com.alibab.code.observer.ObserverDTO;
import com.alibab.code.observer.event.ApplicationEvent;
import com.alibab.code.observer.event.ContextRefreshEvent;
import com.alibab.code.observer.listener.ApplicationListener;
import lombok.SneakyThrows;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author <a href="mailto:1252532896@qq.com">Hua Cheng</a>
 * @Description
 * @Date 2022/12/17
 **/
@Component
public class DefaultApplicationContext implements AoolicationEventPublisher,ApplicationContextAware {

    private ApplicationEventMulticaster eventMulticaster;

    public DefaultApplicationContext() {
        eventMulticaster = new DefaultApplicationEventMulticaster();
    }

    @SneakyThrows
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // 容器启动
        Map<String, ApplicationListener> beansOfType = applicationContext.getBeansOfType(ApplicationListener.class);

        DefaultApplicationContext defaultApplicationContext = new DefaultApplicationContext();
        for (Map.Entry<String, ApplicationListener> entry : beansOfType.entrySet()) {
            ApplicationListener Listener = entry.getValue();
            defaultApplicationContext.eventMulticaster.addApplicationListener((com.alibab.code.observer.listener.ApplicationListener<?>) Listener.getClass().newInstance());
        }
        ObserverDTO observerDTO = new ObserverDTO();
        observerDTO.setOrderId("54544564451");
        observerDTO.setType(1);
        defaultApplicationContext.publicsEvent(new ContextRefreshEvent(defaultApplicationContext,observerDTO));
    }

    @Override
    public void publicsEvent(ApplicationEvent event) {
        eventMulticaster.muticastEvent(event);
    }

//  public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
//      DefaultApplicationContext defaultApplicationContext = new DefaultApplicationContext();
//      Class<?> aClass = Class.forName(ApplicationListener.class.getName());
//      Class<?>[] classes = aClass.getClasses();
//      for (Class<?> oneClass : classes) {
////        defaultApplicationContextEventPublisher.eventMulticaster.addApplicationListener((ApplicationListener<?>) oneClass.getClass().newInstance());
//      }
//      ObserverDTO observerDTO = new ObserverDTO();
//      observerDTO.setOrderId("54544564451");
//      observerDTO.setType(1);
//      defaultApplicationContext.publicsEvent(new ContextRefreshEvent(defaultApplicationContext,observerDTO));
//  }
}
