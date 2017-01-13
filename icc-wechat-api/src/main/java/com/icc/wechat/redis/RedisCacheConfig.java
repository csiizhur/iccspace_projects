package com.icc.wechat.redis;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
/**
 * redis缓存配置--(注解)
 * @description
 * @author zhurun
 * @date 2016年12月16日上午11:48:13
 */
//@Configuration  
//@EnableCaching  
public class RedisCacheConfig extends CachingConfigurerSupport {  
  
   // @Bean  
    public JedisConnectionFactory redisConnectionFactory() {  
        JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();  
  
        // Defaults  
        redisConnectionFactory.setHostName("139.196.225.220");  
        redisConnectionFactory.setPort(6379);  
        redisConnectionFactory.setPassword("root");
        return redisConnectionFactory;
    }  
  
    //@Bean  
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory cf) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();  
        redisTemplate.setConnectionFactory(cf);  
        return redisTemplate;  
    }  
  
    //@Bean  
    public CacheManager cacheManager(RedisTemplate redisTemplate) {  
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);  
  
        // Number of seconds before expiration. Defaults to unlimited (0)  
        cacheManager.setDefaultExpiration(7200); // Sets the default expire time (in seconds)  
        return cacheManager;  
    }  
      
}