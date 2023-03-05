package com.alibab.code.loop;

import lombok.Data;

/**
 * @author <a href="mailto:1252532896@qq.com">Hua Cheng</a>
 * @Description
 * @Date 2023/3/5
 **/
@Data
public class Result<T> {
    private T data;
    private Integer code;
    private Boolean success;
}
