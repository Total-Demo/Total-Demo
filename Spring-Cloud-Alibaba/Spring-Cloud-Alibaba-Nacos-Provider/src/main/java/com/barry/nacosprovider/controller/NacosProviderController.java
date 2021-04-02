package com.barry.nacosprovider.controller;


import com.barry.nacosprovider.server.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class NacosProviderController {

    @Value("${server.port}")
    private String port;

    // 注入配置文件上下文
    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @Autowired
    TestService testService;

    @GetMapping(value = "/test/{message}")
    public String test(@PathVariable String message) {
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
        return "Hello " + applicationContext.getEnvironment().getProperty("user.name.barry")+",abc:"+applicationContext.getEnvironment().getProperty("abc");
    }

    @GetMapping(value = "/doSomeThing2/{message}")
    public String doSomeThing2(@PathVariable String message) {
        return testService.doSomeThing2(message);
    }
}
