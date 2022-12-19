package com.alibab.code.strategy.flow;

import lombok.extern.slf4j.Slf4j;

/**
 * @author <a href="mailto:1252532896@qq.com">Hua Cheng</a>
 * @Description
 * @Date 2022/12/19
 **/
@Slf4j
public abstract class AbstractFlowNode<C extends FlowContext> implements FlowNode {

    @Override
    public FlowResult execute(FlowContext context) {
        try{
            return process((C) context);
        } catch (Exception e) {
            log.error("");
            throw e;
        }
    }

    /**
     * The specific action of the process node: the interface that needs to be implemented
     * 流程节点的具体动作:需要实现的接口
     * @param flowContext
     * @return
     */
    protected abstract FlowResult process(C flowContext);
}
