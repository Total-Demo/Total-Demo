package com.barry.nacosprovider.server.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.barry.nacosprovider.server.TestService;
import com.barry.nacosprovider.util.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TestServiceImpl implements TestService {

    @SentinelResource(value = "test", blockHandler = "handleException", blockHandlerClass = {ExceptionUtil.class})
    public String hello(String message) {
        return message;
    }

    // blockHandler 是位于当前类下的 exceptionHandler 方法，需符合对应的类型限制.
    @SentinelResource(value = "hello", blockHandler = "exceptionHandler")
    public String hello2(String message) {
        return message;
    }


    public String exceptionHandler(String s, BlockException ex) {
        // Do some log here.
        ex.printStackTrace();
        return "Oops, error occurred at " + s;
    }


    // 熔断与降级处理
    @SentinelResource(value = "doSomeThing2", fallback = "fallbackHandler")
    public String doSomeThing2(String str) {
        log.info(str);
        throw new RuntimeException("发生异常");
    }

    public String fallbackHandler(String str) {
        log.error("fallbackHandler：" + str);
        return "fallbackHandler：" + str;
    }

}
