package com.alibab.code.strategy.enums;

import com.alibab.code.strategy.scenario.OrderCreateScenario;
//import com.alibab.code.strategy.scenario.ShopSearchScenario;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * @author <a href="mailto:1252532896@qq.com">Hua Cheng</a>
 * @Description
 * @Date 2022/12/19
 **/
public enum ScenarioConfig {

    //-------------------- 搜索 -----------------------
//    SHOP_SEARCH(ShopSearchScenario.class,"SEARCH","SHOP_SEARCH"),


    //-------------------- 交易 -----------------------
    ORDER_CREATE(OrderCreateScenario.class,"TRADE","ORDER_CREATE"),



    ;


    /**
     * The action class corresponding to the scene
     * 场景对应的动作类
     */
    private Class aClass;

    /**
     * Domain
     * 域
     */
    private String domain;

    /**
     * Specific actions in the scene
     * 场景下的具体动作
     */
    private String action;

    ScenarioConfig(Class aClass, String domain, String action) {
        this.aClass = aClass;
        this.domain = domain;
        this.action = action;
    }

    public static ScenarioConfig getByClass(Class aClass) {
        for (ScenarioConfig value : values()) {
           if ( Objects.equals(aClass.getName(),value.getaClass().getName())) {
               return value;
           }
        }
        return null;
    }

    public Class getaClass() {
        return aClass;
    }

    public String getDomain() {
        return domain;
    }

    public String getAction() {
        return action;
    }
}
