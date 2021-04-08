package com.barry.gateway.config;

//配置类 接收配置参数

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FilterConfig {
    private boolean consoleLog;

    private boolean cacheLog;
}
