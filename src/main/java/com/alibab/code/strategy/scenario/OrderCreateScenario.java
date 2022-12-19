package com.alibab.code.strategy.scenario;

import com.alibab.code.strategy.BaseScenario;
import com.alibab.code.strategy.activity.CreateOrderActivity;
import com.alibab.code.strategy.activity.ReduceInventoryActivity;
import com.alibab.code.strategy.annotation.Scenario;
import com.alibab.code.strategy.context.CreateOrderContext;
import com.alibab.code.strategy.flow.FlowNode;
import com.alibab.code.strategy.resp.CreateOrderRS;
import com.alibab.code.strategy.utils.Result;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author <a href="mailto:1252532896@qq.com">Hua Cheng</a>
 * @Description
 * @Date 2022/12/19
 **/
@Scenario(domain = "TRADE",action = "CREATE_ORDER")
@Component
public class OrderCreateScenario extends BaseScenario<CreateOrderContext, CreateOrderRS> {

    @Resource
    private CreateOrderActivity createOrderActivity;

    @Resource
    private ReduceInventoryActivity reduceInventoryActivity;

    @Override
    protected List<List<FlowNode>> registerActivity() {
        return Lists.newArrayList(
                Lists.newArrayList(createOrderActivity),
                Lists.newArrayList(reduceInventoryActivity));
    }

    @Override
    public void initContext(Object context) {
        System.out.println("初始化参数");
    }

    @Override
    public void beforeCheck(Object context) {
        System.out.println("参数校验");
    }

    @Override
    public Result buildResult(Object context) {
        System.out.println("构建结果");
        return null;
    }
}
