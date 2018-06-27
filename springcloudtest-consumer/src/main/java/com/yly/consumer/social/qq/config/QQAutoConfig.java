package com.yly.consumer.social.qq.config;

import com.yly.consumer.social.qq.connection.QQConnectionFactory;
import com.yly.consumer.utils.propertise.QQProperties;
import com.yly.consumer.utils.propertise.SecurityPropertise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;
@Configuration
@ConditionalOnProperty(prefix = "myconf.security.social.qq",name="app-id")  //当我的配置文件面有myconf.security.social.qq配置项的时候 该配置才生效
public class QQAutoConfig extends SocialAutoConfigurerAdapter {
    @Autowired
    private SecurityPropertise securityPropertise;
    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        QQProperties qqProperties = securityPropertise.getSocial().getQq();
        return new QQConnectionFactory(qqProperties.getProviderId(),qqProperties.getAppId(),qqProperties.getAppSecret());
    }
}
