package com.yly.consumer.configration.security;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 自定义密码加密算法，为了简单此处没做任何处理，没加密仅仅是 在密码后面追加了一个"encoder"，匹配直接equals
 */
public class MyPWDEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString()+"encoder";
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        rawPassword = rawPassword +"encoder";  //比对前 输入的密码也要加密后在比对
        return rawPassword.equals(encodedPassword);
    }
}
