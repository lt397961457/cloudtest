package com.yly.consumer.controller.security.authhandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yly.consumer.constant.LoginType;
import com.yly.consumer.utils.propertise.SecurityPropertise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("myAuthFailureHandler")
//implements AuthenticationFailureHandler
public class MyAuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Autowired
    private SecurityPropertise securityPropertise ;
    @Autowired
    private ObjectMapper objectMapper; //JSON 工具，Spring初始化的时候，已经自定生成了ObjectMapper的bean
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        System.out.print("登陆失败");
        if(LoginType.JSON.equals(securityPropertise.getBrowser().getLoginType())){
            httpServletResponse.setContentType("application/json;charset=UTF-8");
            httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value()) ; //500 服务器内部错误
            httpServletResponse.getWriter().write(objectMapper.writeValueAsString(e));
        } else{
            super.onAuthenticationFailure(httpServletRequest,httpServletResponse,e );//父类的方法默认就是跳转对应的错误页面
        }
    }
}
