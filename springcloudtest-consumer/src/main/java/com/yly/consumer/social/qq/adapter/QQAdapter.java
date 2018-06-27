package com.yly.consumer.social.qq.adapter;

import com.yly.consumer.social.qq.api.QQ;
import com.yly.consumer.social.qq.api.QQUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

import java.io.IOException;

/**
 * ApiAdaptor 的主要功能是将AbstractOAuth2ApiBinding (QQImpl已经继承) 获取的到不同的服务提供商个性化的返回数据
 * 适配 成Spring-Social标准格式。
 * 泛型指定的类型，表示 要将 哪一种数据（此处QQ）适配成Spring-Social 的标准格式
 */
public class QQAdapter implements ApiAdapter<QQ> {
    //测试我们的应用在QQ方服务是否可用，此处就不做测试 直接返回ture
    @Override
    public boolean test(QQ qq) {
        return true;
    }
    //适配数据的真正方法，从api（QQ）中获取数据，放入 ConnectionValue中
    @Override
    public void setConnectionValues(QQ api, ConnectionValues connectionValues) {
        try {
            QQUserInfo userInfo = api.getUserInfo();
            connectionValues.setDisplayName(userInfo.getNickname());
            connectionValues.setImageUrl(userInfo.getFigureurl_qq_1());
            //个人主页地址，QQ是没有的，像微博这种才有
            connectionValues.setProfileUrl(null);
            //用户在服务商这边（QQ）的用户ID（和QQ号一一对应，没有重复的）
            connectionValues.setProviderUserId(userInfo.getOpenId());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //和绑定解绑有关 暂不处理
    @Override
    public UserProfile fetchUserProfile(QQ qq) {
        return null;
    }
    //更新用户状态，QQ上一般不用，同样也是微博这类的才会有，比如更新了一条微博后
    @Override
    public void updateStatus(QQ qq, String s) {

    }
}
