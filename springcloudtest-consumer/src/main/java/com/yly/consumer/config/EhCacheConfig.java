package com.yly.consumer.config;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by Administrator on 2017/12/21.
 */
@Configuration
@EnableCaching //允许缓存
public class EhCacheConfig {
    /**
     * EhCacheManagerFactoryBean
     * @return
     */
    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
        EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        ehCacheManagerFactoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
        ehCacheManagerFactoryBean.setShared(true);
        return ehCacheManagerFactoryBean;
    }
    /**
     * EhCacheCacheManagerBean
     * @return
     */
    @Bean
    public EhCacheCacheManager ehCacheCacheManager(EhCacheManagerFactoryBean ehCacheManagerFactoryBean){
        return new EhCacheCacheManager(ehCacheManagerFactoryBean.getObject());
    }


}
