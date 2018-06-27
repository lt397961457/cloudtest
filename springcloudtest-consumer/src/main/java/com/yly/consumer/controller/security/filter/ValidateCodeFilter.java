package com.yly.consumer.controller.security.filter;

import com.yly.consumer.controller.security.ValidateCodeController;
import com.yly.consumer.controller.security.authhandler.MyAuthFailureHandler;
import com.yly.consumer.exception.ValidateCodeException;
import com.yly.consumer.utils.ValidImage.ImageCode;
import freemarker.template.utility.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//OncePerRequestFilter 是Spring提供的一个工具类，用于保证该过滤器一个请求中只会被执行一次
public class ValidateCodeFilter extends OncePerRequestFilter {
    //Spring提供的session social工具类
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    //之前学习的AuthenticationFailureHandler
    private AuthenticationFailureHandler authFailureHandler;
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        //当是登录表单提交过来的时候就校验imageCode
        if(StringUtils.equalsIgnoreCase("/unameAndPwdSubmit",httpServletRequest.getRequestURI())
                &&  StringUtils.equalsIgnoreCase(httpServletRequest.getMethod(),"post")){
            try{
                    validate(new ServletWebRequest(httpServletRequest));
                    filterChain.doFilter(httpServletRequest,httpServletResponse);
                }catch(ValidateCodeException e){ //ValidateCodeException  自定义异常 继承了 AuthenticationException
                    authFailureHandler.onAuthenticationFailure(httpServletRequest,httpServletResponse,e);
                }
            }else{
                 filterChain.doFilter(httpServletRequest,httpServletResponse);
            }
    }

    private  void validate(ServletWebRequest servletWebRequest) throws ServletRequestBindingException {
        //session中保留的code
        ImageCode codeInsession = (ImageCode)sessionStrategy.getAttribute(servletWebRequest, ValidateCodeController.SEESION_KEY );
        //前台表单传过来的code
        String codeInrequest = ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(),"imageCode");
        if(StringUtils.isBlank(codeInrequest )){
            throw new ValidateCodeException("验证码不能为空");
        }
        if(codeInsession  == null){
            throw new ValidateCodeException("验证码不存在");
        }
        if(codeInsession .isExpired()){
            sessionStrategy.removeAttribute(servletWebRequest,ValidateCodeController.SEESION_KEY);
            throw new ValidateCodeException("验证码已过期");
        }
        if(!StringUtils.equals(codeInsession .getCode(),codeInrequest )){
            throw new ValidateCodeException("验证码不匹配");
        }
        sessionStrategy.removeAttribute(servletWebRequest,ValidateCodeController.SEESION_KEY);
    }

    public SessionStrategy getSessionStrategy() {
        return sessionStrategy;
    }

    public void setSessionStrategy(SessionStrategy sessionStrategy) {
        this.sessionStrategy = sessionStrategy;
    }

    public AuthenticationFailureHandler getAuthFailureHandler() {
        return authFailureHandler;
    }

    public void setAuthFailureHandler(AuthenticationFailureHandler authFailureHandler) {
        this.authFailureHandler = authFailureHandler;
    }
}
