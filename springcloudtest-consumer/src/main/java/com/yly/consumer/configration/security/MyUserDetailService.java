package com.yly.consumer.configration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailService implements UserDetailsService,SocialUserDetailsService {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override  //根据用户名 在数据库 获取加密后的密码，此处简单处理手动生成 加密后的密码
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //根据用户名查找用户信息，根据实际情况写自己的代码，此处简略
        //  此处User是spring 框架中提供的类切实现了UserDetails 接口，第三个参数是  用户所拥有的角色权限，此处也是简单拼装，拥有admin角色的权限
        String encodePWD = passwordEncoder.encode("1234456");  //正常情况下，encode方法 是在注册时时候使用
        return new User("aaa",encodePWD,
                true,true,true,true,  //表示：账号是否可用，账号是否未过期，密码是否未过期，账号是否没有被锁定
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));

        //正常开发中 我们一般不使用Spring提供的User类，而是自己写一个类实现UserDetailService接口，这个类就需要几个方法，包括校：获取密码、账号是否冻结、账户是否过期、密码是否过期、。。。
         }

    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        return new SocialUser(userId,"1234456",
                true,true,true,true,   //表示：账号是否可用，账号是否未过期，密码是否未过期，账号是否没有被锁定
        AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
