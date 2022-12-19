package com.alibab.code.strategy;

import com.alibab.code.strategy.enums.ScenarioConfig;
import com.alibab.code.strategy.flow.FlowContext;
import com.alibab.code.strategy.flow.FlowDefinition;
import com.alibab.code.strategy.flow.FlowEngine;
import com.alibab.code.strategy.flow.FlowNode;
import org.springframework.beans.factory.InitializingBean;

import java.util.List;

/**
 * @author <a href="mailto:1252532896@qq.com">Hua Cheng</a>
 * @Description
 * @Date 2022/12/19
 **/
public abstract class BaseScenario<C,R> implements IScenario, InitializingBean {

    private FlowDefinition flowDefinition;

    /**
     * Process node registration
     * 流程节点注册
     *
     * @return
     */
    protected abstract List<List<FlowNode>> registerActivity();

    @Override
    public void process(Object context) throws Exception {
        FlowEngine.getInstance().start(flowDefinition,(FlowContext) context);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        flowDefinition = new FlowDefinition();
        List<List<FlowNode>> flowNodeList = registerActivity();
        flowDefinition.setFlowName(ScenarioConfig.getByClass(this.getClass()).name());
        flowDefinition.setFlowNodeList(flowNodeList);
    }
}
