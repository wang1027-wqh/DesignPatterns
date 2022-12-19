package com.alibab.code.strategy.flow;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author <a href="mailto:1252532896@qq.com">Hua Cheng</a>
 * @Description
 * @Date 2022/12/19
 **/
public class FlowEngine {

    private static final Logger logger = LoggerFactory.getLogger(FlowEngine.class);




    public static FlowEngine getInstance() {
        return SingletonInstance.flowEngine;
    }

    public void start(FlowDefinition flowDefinition, FlowContext context) throws Exception{
        // TODO
    }

    private static class SingletonInstance {
        private static final FlowEngine flowEngine = new FlowEngine();
    }

}
