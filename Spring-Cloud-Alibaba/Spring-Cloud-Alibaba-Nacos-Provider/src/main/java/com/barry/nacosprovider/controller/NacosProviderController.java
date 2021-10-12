package com.barry.nacosprovider.controller;


import com.barry.nacosprovider.server.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@RestController
@RefreshScope
@Slf4j
public class NacosProviderController {

    @Value("${server.port}")
    private String port;

    // 注入配置文件上下文
    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @Autowired
    TestService testService;

    @Autowired
    RedisTemplate redisTemplate;

    @GetMapping(value = "/test/{message}")
    public String test(@PathVariable String message) {
        log.info("Hello Nacos Discovery");
        log.info("Hello Nacos Discovery2");
        log.info("Hello Nacos Discovery3");
        log.info("Hello Nacos Discovery4");
        return "Hello Nacos Discovery " + message + " i am from port " + port;
    }

    @GetMapping(value = "/hello/{message}")
    public String hello(@PathVariable String message) {
        return testService.hello(message);
    }

    @GetMapping(value = "/hello2/{message}")
    public String hello2(@PathVariable String message) {
        return testService.hello2(message);
    }

    // 从上下文中读取配置
    @GetMapping(value = "/hi")
    public String sayHi() {
        redisTemplate.opsForValue().set("abcbarry", UUID.randomUUID().toString());
        return "Hello " + applicationContext.getEnvironment().getProperty("user.name.barry")+",abc:"+applicationContext.getEnvironment().getProperty("abc");
    }

    @GetMapping(value = "/doSomeThing2/{message}")
    public String doSomeThing2(@PathVariable String message) {
        return testService.doSomeThing2(message);
    }
}
