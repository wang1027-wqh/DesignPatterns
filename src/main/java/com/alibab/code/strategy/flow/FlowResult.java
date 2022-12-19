package com.alibab.code.strategy.flow;

import java.io.Serializable;

/**
 * @author <a href="mailto:1252532896@qq.com">Hua Cheng</a>
 * @Description
 * @Date 2022/12/19
 **/
public class FlowResult implements Serializable {

    private static final long serialVersionUID = 4546445646464664L;

    /**
     * Execution result, default to true
     * 执行结果，默认为true
     */
    protected boolean success = true;

    /**
     * Error code
     * 错误码
     */
    protected String errorCode;

    /**
     * Error Msg
     * 错误信息
     */
    protected String errorMsg;

    /**
     * Whether to retry
     * 是否重试
     */
    protected boolean canRetry = false;

    public FlowResult(boolean success, String errorCode, String errorMsg, boolean canRetry) {
        this.success = success;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.canRetry = canRetry;
    }

    public static FlowResult buildSuccessResult() {
        return new FlowResult(true,null,null,false);
    }

}
