package com.alibab.code.loop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="mailto:1252532896@qq.com">Hua Cheng</a>
 * @Description
 * @Date 2023/3/5
 **/
@RestController
@RequestMapping("/loop")
public class LoopLongPollingController {

    @Autowired
    LoopLongPollingService loopLongPollingService;

    /**
     * 从服务端拉取被变更的数据
     * @return
     */
    @GetMapping("/pull")
    public Result pull() {
        String result = loopLongPollingService.poll();
        return ResultUtil.success(result);
    }

    /**
     * 向服务端推送变更的数据
     * @param data
     * @return
     */
    @GetMapping("/push")
    public Result push(@RequestParam("data") String data) {
        String result = loopLongPollingService.push(data);
        return ResultUtil.success(result);
    }

}
