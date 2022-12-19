package com.alibab.code.strategy.flow;

import com.alibab.code.strategy.utils.ThreadExecutorUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * @author <a href="mailto:1252532896@qq.com">Hua Cheng</a>
 * @Description
 * @Date 2022/12/19
 **/
public class FlowDefinition {
    List<List<FlowNode>> flowNodeList = new ArrayList<>();

    @Getter
    @Setter
    private String flowName;

    protected ExecutorService getExecutor() {
        return ThreadExecutorUtils.defaultExecutorService;
    }

    public void setFlowNodeList(List<List<FlowNode>> flowNodeList) {
        this.flowNodeList = flowNodeList;
    }

    public List<List<FlowNode>> getFlowNodeList() {
        return flowNodeList;
    }


}
