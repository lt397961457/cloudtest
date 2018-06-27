package com.yly.consumer.social.qq.api.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.corba.se.impl.oa.toa.TOA;
import com.yly.consumer.exception.UserException;
import com.yly.consumer.social.qq.api.QQ;
import com.yly.consumer.social.qq.api.QQUserInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.io.IOException;

public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {
    //通过token 获取用户openid的url
    private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s"; //注意%s占位符
    //QQ API 里面提供的 获取用户信息的url,本来还有一个参数access_token=YOUR_ACCESS_TOKEN ，但是在父类里面会自动将这个这个参数拼接上，所以此处就不用拼接了
    private static final String URL_GET_USERINFO="https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";
    //请见QQ互联的API说明，这是OAuth2协议的3个通用参数，access_token 在父类中已经有申明了。
    private String appId; //每个应用要使用QQ的三方登录，必须先要注册一个应用到QQ中，此时就会有一个appId
    private String openId;
    private ObjectMapper objectMapper = new ObjectMapper();


    public QQImpl(String accessToken,String appid){
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId = appid;
        //通过token获取openId
        String url = String.format(URL_GET_OPENID, accessToken);
        String resutl = getRestTemplate().getForObject(url,String.class);
        System.out.println("REST接口{"+url+"}获取OPENID的响应："+resutl);
        this.openId = StringUtils.substringBetween(resutl,"\"Oopenid\":\"","\"}");

    }
    @Override
    public QQUserInfo getUserInfo() throws IOException {
        String userInfoUrl = String.format(URL_GET_USERINFO,appId,openId);
        String infoResult = getRestTemplate().getForObject(userInfoUrl,String.class);
        System.out.println("REST接口："+userInfoUrl +"获取到的用户信息"+infoResult);

        QQUserInfo qqUserInfo = objectMapper.readValue(infoResult,QQUserInfo.class);
        qqUserInfo.setOpenId(openId);
        return qqUserInfo;
    }
}
