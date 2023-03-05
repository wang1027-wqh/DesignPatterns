package com.alibab.code.strategy.test;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;

/**
 * @author <a href="mailto:1252532896@qq.com">Hua Cheng</a>
 * @Description
 * @Date 2023/1/14
 **/
public class Demo {
  public static void main(String[] args) {
      ArrayList<Object> objects = new ArrayList<>();
    System.out.println(JSON.toJSONString(objects));
  }
}
