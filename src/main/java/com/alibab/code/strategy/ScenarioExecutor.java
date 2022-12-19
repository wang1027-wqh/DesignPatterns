package com.alibab.code.strategy;

import com.alibab.code.strategy.annotation.Scenario;
import com.alibab.code.strategy.enums.ScenarioConfig;
import com.alibab.code.strategy.flow.FlowContext;
import com.alibab.code.strategy.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:1252532896@qq.com">Hua Cheng</a>
 * @Description
 * @Date 2022/12/19
 **/
@Slf4j
@Component
public class ScenarioExecutor implements ApplicationContextAware {

    private final static String SPILT = "-";

    private final Map<String,BaseScenario> scenarioMap = new HashMap<>();
    private ApplicationContext applicationContext;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    public void init() {
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(Scenario.class);
        for (Map.Entry<String, Object> entry : beansWithAnnotation.entrySet()) {
            BaseScenario scenario = (BaseScenario) entry.getValue();
            ScenarioConfig scenarioConfig = ScenarioConfig.getByClass(scenario.getClass());
            String key = generateScenarioMapKey(scenarioConfig.getDomain(),scenarioConfig.getAction());
            if (scenarioMap.get(key) == null) {
                BaseScenario bean = (BaseScenario)applicationContext.getBean(entry.getKey());
                scenarioMap.put(key,bean);
            } else {
                throw new RuntimeException("场景流程初始化异常");
            }
        }
    }

    public Result execute(String domain, String action, FlowContext flowContext) throws Exception {
        BaseScenario scenario = this.selectScenario(domain, action);

        if (scenario == null) {
            throw new RuntimeException("找不到对应的场景流程");
        }

        try {

            scenario.initContext(flowContext);
            scenario.beforeCheck(flowContext);
            scenario.process(flowContext);
            Result result = scenario.buildResult(flowContext);
            return result;
        } catch (Exception e) {
            log.error("");
            throw e;
        }
    }


    private BaseScenario selectScenario(String domain, String action) {
        String key = generateScenarioMapKey(domain,action);
        return scenarioMap.get(key);
    }

    private String generateScenarioMapKey(String domain, String action) {
        return domain + SPILT + action;
    }
}
