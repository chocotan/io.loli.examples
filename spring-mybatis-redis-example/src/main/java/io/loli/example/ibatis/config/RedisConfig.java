package io.loli.example.ibatis.config;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
@EnableCaching
@PropertySource("classpath:database.properties")
public class RedisConfig {

    @Value(value = "${redis.host}")
    private String host;
    @Value(value = "${redis.port}")
    private Integer port;

    @Value(value = "${redis.usePool}")
    private Boolean usePool;

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory f = new JedisConnectionFactory();
        f.setHostName(host);
        f.setPort(port);
        f.setUsePool(usePool);
        return f;
    }

    @Bean
    @SuppressWarnings("rawtypes")
    public RedisTemplate redisTemplate() {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        return redisTemplate;
    }

    // @Bean
    public CacheManager cacheManager() {
        // configure and return an implementation of Spring's CacheManager SPI
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Arrays.asList(new ConcurrentMapCache("clazz")));
        return cacheManager;
    }

    @Bean
    public RedisCacheManager redisCacheManager() {
        RedisCacheManager manager = new RedisCacheManager(redisTemplate());
        // manager.setCacheNames(Arrays.asList(new String[] { "clazz" }));
        return manager;
    }

    // @Bean
    // custom key generator
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object o, Method method, Object... objects) {
                // This will generate a unique key of the class name, the method
                // name,
                // and all method parameters appended.
                StringBuilder sb = new StringBuilder();
                sb.append(o.getClass().getName());
                sb.append(method.getName());
                for (Object obj : objects) {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }
}
