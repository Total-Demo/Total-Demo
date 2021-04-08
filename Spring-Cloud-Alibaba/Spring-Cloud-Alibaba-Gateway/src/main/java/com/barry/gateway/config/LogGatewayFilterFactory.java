package com.barry.gateway.config;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Component
public class LogGatewayFilterFactory extends AbstractGatewayFilterFactory<FilterConfig> {

    //构造函数

    public LogGatewayFilterFactory() {

        super(FilterConfig.class);

    }

//读取配置文件中的参数 赋值到 配置类中

    @Override

    public List<String> shortcutFieldOrder() {

        return Arrays.asList("consoleLog", "cacheLog");

    }

//过滤器逻辑

    @Override
    public GatewayFilter apply(FilterConfig config) {

        return new GatewayFilter() {

            @Override

            public Mono<Void> filter(ServerWebExchange exchange,

                                     GatewayFilterChain chain) {

                if (config.isCacheLog()) {

                    System.out.println("cacheLog已经开启了....");

                }

                if (config.isConsoleLog()) {

                    System.out.println("consoleLog已经开启了....");

                }

                return chain.filter(exchange);

            }

        };

    }
}
