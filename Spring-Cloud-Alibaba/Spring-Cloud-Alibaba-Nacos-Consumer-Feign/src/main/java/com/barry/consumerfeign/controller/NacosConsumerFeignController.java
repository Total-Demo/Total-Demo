package com.barry.consumerfeign.controller;

import com.barry.consumerfeign.server.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NacosConsumerFeignController {

    @Autowired
    private FeignService FeignService;

    @GetMapping(value = "/test/hi")
    public String test() {
        return FeignService.test("Hi Feign");
    }
}
