package com.yly.consumer.social.qq.config;

import com.yly.consumer.utils.propertise.SecurityPropertise;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.security.SpringSocialConfigurer;

@EnableSocial
@Configuration
public class MySocialConfig extends SocialConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private SecurityPropertise securityPropertise;

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        //ConnectionFactoryLocator  根据条件查找 我们该用哪一个ConnectionFactory，
        // 因为一个项目里面可能有多个ConnectionFactory；
        // Encryptor 的作用是将插入到数据库的数据加解密 noOpText 不做任何加密，直接放入数据库
        //创建存储UserConnection信息的表，Sql语句在JdbcUsersConnectionRepository.class的旁边，UserConnection的表名是不可以修改的
        JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource,connectionFactoryLocator, Encryptors.noOpText());
        repository.setTablePrefix("staryea_"); //;// 如果用户信息的表UserConnection 表 加了前缀，如 staryea_UserConnection
        return repository;
    }

    /**
     * 生成一个 SpringSocialConfigurer  名称是 mySocialSecurityConfig，
     * SpringSocialConfigurer 如果生效（需配置在总配置文件里面，此处是：BrowserSecurityConfig），
     * 那么认证链路上就会增加SocialAuthenticationFilter
     * @return
     */
    @Bean
    public SpringSocialConfigurer mySocialSecurityConfig(){
        /**
         * Spring默认的配置，里面默认SocialAuthenticationFilter 拦截的路径是/auth/{providerId}，现在想把/auth  改成可配置
         */
//        return new SpringSocialConfigurer ();
        String filterProcessUrl = securityPropertise.getSocial().getFilterProcessUrl();
        MySpringSocialConfigurer mySpringSocialConfigurer = new MySpringSocialConfigurer(filterProcessUrl);
        return  mySpringSocialConfigurer;
    }

}
