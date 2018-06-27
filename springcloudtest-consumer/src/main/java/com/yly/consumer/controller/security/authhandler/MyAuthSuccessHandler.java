package com.yly.consumer.controller.security.authhandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yly.consumer.constant.LoginType;
import com.yly.consumer.utils.propertise.SecurityPropertise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("myAuthSuccessHandler")
//implements AuthenticationSuccessHandler
public class MyAuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Autowired
    private SecurityPropertise securityPropertise;
    @Autowired
    private ObjectMapper objectMapper; //JSON 工具，Spring初始化的时候，已经自定生成了ObjectMapper的bean
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        System.out.print("  登陆成功");

        if(LoginType.JSON.equals(securityPropertise.getBrowser().getLoginType())){
            httpServletResponse.setContentType("application/json;charset=UTF-8");
            httpServletResponse.getWriter().write(objectMapper.writeValueAsString(authentication ));
        } else{
            super.onAuthenticationSuccess(httpServletRequest,httpServletResponse,authentication );//父类的方法默认就是跳转回引发登录的页面
        }
    }
}
