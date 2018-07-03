package com.yly.consumer.social.qq.config;

import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * 自定义 SpringSocialConfigurer
 * 目的：让第跳转“QQ登录”的URL 是可以配置的，默认SocialAuthenticationFilter 拦截的路径是/auth/{providerId}
 */
public class MySpringSocialConfigurer extends SpringSocialConfigurer {
    private String filterProcessUrl;

    public MySpringSocialConfigurer(String filterProcessUrl) {
        this.filterProcessUrl = filterProcessUrl;
    }

    @Override
    protected <T> T postProcess(T object) {
        SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);
        filter.setFilterProcessesUrl(filterProcessUrl);
        return (T) filter;
    }
}
