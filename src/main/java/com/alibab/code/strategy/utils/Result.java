package com.alibab.code.strategy.utils;

/**
 * @author <a href="mailto:1252532896@qq.com">Hua Cheng</a>
 * @Description
 * @Date 2022/12/19
 **/
public interface Result<R> {

    /**
     * Get the success ID
     * 获取成功标识
     * @return  true:成功 false:失败
     */
    boolean isSuccess();

    /**
     * Set the success identity
     * 设置成功标识
     * @param success  是否成功
     */
    void setSuccess(boolean success);

    /**
     * Returns the result code
     * 返回结果码
     * @return    result code {@link }
     */
    String getResultCode();

    /**
     * Returns the notes information
     * 返回备注信息
     * @return
     */
    String getResultMsg();

    /**
     * Set up notes
     * 设置备注
     * @param msg       notes
     */
    void setResultMsg(String msg);

    /**
     * Gets the result return value
     * 获取结果返回值
     * @return
     */
    R getModle();

    /**
     * Sets the return value
     * 设置返回值
     * @param model     return value
     * @return
     */
    Result<R> setModel(R model);

    /**
     * Gets the request ID of the call
     * 获取调用的请求标识
     * @return
     */
    String getRequestId();

    /**
     * Sets the calling identity of the request
     * 设置请求的调用标识
     * @param requestId
     */
    void setRequestId(String requestId);
}
