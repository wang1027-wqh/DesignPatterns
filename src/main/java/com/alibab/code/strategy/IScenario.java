package com.alibab.code.strategy;

import com.alibab.code.strategy.utils.Result;

/**
 * @author <a href="mailto:1252532896@qq.com">Hua Cheng</a>
 * @Description
 * @Date 2022/12/19
 **/
public interface IScenario<C,R> {

    /**
     * Initialize the context
     * 初始化上下文
     * @param context
     */
    public abstract void initContext(C context);

    /**
     * Context Parameter validation
     * 上下文参数校验
     * @param context
     */
    public abstract void beforeCheck(C context);

    /**
     * Scenario process execution
     * 场景流程执行
     * @param context       上下文参数
     * @throws Exception    运行时异常
     */
    public void process(C context) throws Exception;

    /**
     * Build the response result
     * 构建响应结果
     * @param context
     * @return
     */
    public abstract Result<R> buildResult(C context);

}
