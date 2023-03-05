//package com.alibab.code.strategy.test;
//
//import com.alibab.code.strategy.ScenarioExecutor;
//import com.alibab.code.strategy.context.CreateOrderContext;
//import com.alibab.code.strategy.enums.ScenarioConfig;
//import com.alibab.code.strategy.utils.Result;
//import lombok.SneakyThrows;
//import org.springframework.beans.BeansException;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.event.ContextStartedEvent;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//
///**
// * @author <a href="mailto:1252532896@qq.com">Hua Cheng</a>
// * @Description
// * @Date 2022/12/19
// **/
//@Component
//public class Test implements ApplicationContextAware {
////
////    @Resource
////    private ScenarioExecutor scenarioExecutor;
////
////
////    @SneakyThrows
////    @Override
////    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
////        // 构建上下文
////        CreateOrderContext context = new CreateOrderContext();
////        context.setMsg("我是请求参数");
////
////        // 执行场景动作
////        Result result = scenarioExecutor.execute(ScenarioConfig.ORDER_CREATE.getDomain(),
////                ScenarioConfig.ORDER_CREATE.getAction(),
////                context);
////        System.out.println(result);
////    }
//}
