package com.yly.redis;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.lang.reflect.Method;
import java.util.UUID;

/**
 * Created by Administrator on 2017/11/30.
 */

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.timeout}")
    private int timeout;

    /**
     * 缓存数据时Key（redis种的key）的生成器，可以依据业务和技术场景自行定制,
     * 使用这个生成器要在对应的类上注解：@CacheConfig(cacheNames = "users",keyGenerator="keyGenerator") 指定
     * 或者在 对应的方法上指定
     * 如果不想使用KeyGernerator 则可以在每个方法上适用@CacheConfig(key = "#p0") 表示适用该方法的第一个参数作为redis中的key
     * 具体参考 UserServiceImpl
     * @return
     */
    @Bean
    public org.springframework.cache.interceptor.KeyGenerator keyGenerator() {
        return new org.springframework.cache.interceptor.KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                //类名+方法名
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object obj : params) {
                    sb.append(obj.toString());
                }
                return sb.toString();

            }

        };
    }

    @SuppressWarnings("rawtypes")
    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager rcm = new RedisCacheManager(redisTemplate);
        //设置缓存过期时间
        //  rcm.setDefaultExpiration(60);//秒，便于测试
        return rcm;
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
        StringRedisTemplate template = new StringRedisTemplate(factory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

//    /**
//     * springboot 中默认根据配置文件（application.properties） 会生成一个factory 所以这个Bean也可以不用设置
//     * @return
//     */
//    @Bean
//    public JedisConnectionFactory redisConnectionFactory() {
//        JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();
//        redisConnectionFactory.setHostName("127.0.0.1");
//        redisConnectionFactory.setPort(6379);
//        return redisConnectionFactory;
//    }
}

