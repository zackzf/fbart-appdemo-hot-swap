package com.fbart.research.appdemo.hot.swap.web;

import com.fbart.research.appdemo.hot.swap.HotswapUtil;
import com.fbart.research.appdemo.hot.swap.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@RequestMapping("/api/test")
public class TestController {
    @Autowired
    private TestService testService;
    /**
     * 刷新
     * @return
     */
    @RequestMapping("/refresh")
    @ResponseBody
    public Map<String, Object> refresh(HttpServletRequest request) {
        HotswapUtil.swap3();

        String dealResult = testService.deal("heihei");
        System.out.println(dealResult);

        return new HashMap<>();
    }

}
