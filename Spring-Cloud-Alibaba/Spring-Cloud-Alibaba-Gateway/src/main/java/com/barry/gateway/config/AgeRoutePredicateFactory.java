package com.barry.gateway.config;

import com.alibaba.cloud.commons.lang.StringUtils;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

//泛型 用于接收一个配置类,配置类用于接收中配置文件中的配置
@Component
public class AgeRoutePredicateFactory extends AbstractRoutePredicateFactory<Config> {

    public AgeRoutePredicateFactory() {

        super(Config.class);

    }

//用于从配置文件中获取参数值赋值到配置类中的属性上

    @Override

    public List<String> shortcutFieldOrder() {

//这里的顺序要跟配置文件中的参数顺序一致

        return Arrays.asList("minAge", "maxAge");

    }

//断言

    @Override

    public Predicate<ServerWebExchange> apply(Config config) {

        return new Predicate<ServerWebExchange>() {

            @Override

            public boolean test(ServerWebExchange serverWebExchange) {

//从serverWebExchange获取传入的参数

                String ageStr =

                        serverWebExchange.getRequest().getQueryParams().getFirst("age");

                if (StringUtils.isNotEmpty(ageStr)) {

                    int age = Integer.parseInt(ageStr);

                    return age > config.getMinAge() && age < config.getMaxAge();

                }

                return true;

            }

        };

    }

}

