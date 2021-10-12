package com.barry.nacosprovider.config;

import com.alibaba.fastjson.parser.ParserConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * @author fred.zhang
 * @date on  2020/2/2
 * @description Redis自动配置类
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {
    private static Logger logger = LoggerFactory.getLogger(RedisConfig.class);



    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();

        template.setConnectionFactory(redisConnectionFactory);
        logger.info("Configure RedisTemplate completed.");
        return template;
    }

    /**
     * 自定义Redis错误处理器，实现Redis
     */
    @Bean
    @Override
    public CacheErrorHandler errorHandler() {
        CacheErrorHandler cacheErrorHandler = new CacheErrorHandler() {
            @Override
            public void handleCachePutError(RuntimeException exception, Cache cache,
                                            Object key, Object value) {
            }

            /**
             * 如果从Redis获取缓存失败，则打印错误信息，但是继续放行；
             * 保证Redis服务器出现连接等问题时不影响程序的正常运行。
             * @param exception
             * @param cache
             * @param key
             */
            @Override
            public void handleCacheGetError(RuntimeException exception, Cache cache,
                                            Object key) {
                logger.error("redis异常：key={},exception:{}", key, exception.getMessage());
            }

            @Override
            public void handleCacheEvictError(RuntimeException exception, Cache cache,
                                              Object key) {
            }

            @Override
            public void handleCacheClearError(RuntimeException exception, Cache cache) {
            }

        };
        return cacheErrorHandler;
    }
}


