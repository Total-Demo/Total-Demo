package com.barry.consumerfeign.server;

import org.springframework.stereotype.Component;

@Component
public class FallbackServiceImpl implements FeignService{
    @Override
    public String test(String message) {
        return "test fallback";
    }
}
