package com.yly.consumer.social.qq.provider;

import com.yly.consumer.social.qq.api.QQ;
import com.yly.consumer.social.qq.api.impl.QQImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Template;

public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {
    private String appId;
    //获取授权码 的url ，第三步
    private static final String URL_AUTHORIZE="https://graph.qq.com/oauth2.0/authorize";
    //获取 token的 URL 第五步
    private static final String URL_ACCESS_TOKEN="https://graph.qq.com/oauth2.0/token";
    //appId,appSecret  相当于 第三方应用调用QQ 的的应用ID和密码
    public QQServiceProvider(String appId,String appSecret) {
        super(new OAuth2Template(appId,appSecret,URL_AUTHORIZE,URL_ACCESS_TOKEN));
        this.appId = appId;
    }


    @Override
    public QQ getApi(String accessToken) {
        return new QQImpl(accessToken,appId);
    }
}
