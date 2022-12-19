package com.alibab.code.strategy.flow;

/**
 * @author <a href="mailto:1252532896@qq.com">Hua Cheng</a>
 * @Description
 * @Date 2022/12/19
 **/
public interface FlowNode {

    /**
     * Process node execution entry
     * 流程节点执行入口
     * @param context       上下文参数
     * @return              结果
     * @throws Exception    异常信息
     */
    FlowResult execute(FlowContext context) throws Exception;
}
