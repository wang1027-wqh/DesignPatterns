package com.alibab.code.strategy.flow;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;


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

    public FlowResult start(FlowDefinition flowDefinition, FlowContext context) throws Exception{
        try{
            ExecutorService executorService = flowDefinition.getExecutor();
            List<List<FlowNode>> flowNodeList = flowDefinition.getFlowNodeList();

            for (List<FlowNode> subFlowNodes : flowNodeList) {
                if ( Objects.isNull(subFlowNodes) || subFlowNodes.size() == 0) {
                    continue;
                }
                ArrayList<Future<FlowResult>> futureList = new ArrayList<>(subFlowNodes.size());
                CountDownLatch countDownLatch = new CountDownLatch(subFlowNodes.size());
                for (FlowNode flowNodeFuture : subFlowNodes) {
                    Future<FlowResult> task = executorService.submit(() -> {
                        try {
                            return flowNodeFuture.execute(context);
                        } finally {
                            countDownLatch.countDown();
                        }
                    });
                    futureList.add(task);
                }

                countDownLatch.await();
                for (Future<FlowResult> task : futureList) {
                    FlowResult flowResult = task.get();
                    if (!flowResult.success) {
                        return flowResult;
                    }
                }

            }
            return FlowResult.buildSuccessResult();
        }catch (Exception e) {
            logger.error("");
            throw e;
        }
    }

    private static class SingletonInstance {
        private static final FlowEngine flowEngine = new FlowEngine();
    }

}
