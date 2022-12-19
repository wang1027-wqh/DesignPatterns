package com.alibab.code.strategy.activity;

import com.alibab.code.strategy.flow.AbstractFlowNode;
import com.alibab.code.strategy.flow.FlowContext;
import com.alibab.code.strategy.flow.FlowResult;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author <a href="mailto:1252532896@qq.com">Hua Cheng</a>
 * @Description
 * @Date 2022/12/19
 **/
@Component
public class CreateOrderActivity<C extends FlowContext> extends AbstractFlowNode<C> {


    @Override
    protected FlowResult process(C flowContext) {
        System.out.println("CreateOrderActivity 执行了");
        return FlowResult.buildSuccessResult();
    }
}
