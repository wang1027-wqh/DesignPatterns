package com.alibab.code.loop;

import lombok.Data;

/**
 * @author <a href="mailto:1252532896@qq.com">Hua Cheng</a>
 * @Description
 * @Date 2023/3/5
 **/
public class ResultUtil {
    public static Result success() {
        Result result = new Result();
        result.setCode(200);
        result.setSuccess(true);
        return result;
    }

    public static Result success(Object data) {
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(200);
        result.setData(data);
        return result;
    }
}
