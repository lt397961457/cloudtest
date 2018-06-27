package com.yly.consumer.controller.security;

import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/authentication")
public class AuthController {
    //此处主要用于获取跳转到此请求之前的 请求是 HTML 还是REST
    private RequestCache requestCache = new HttpSessionRequestCache();
    //Spring提供的 请求跳转的 工具类
    private RedirectStrategy redirectStrategy  = new DefaultRedirectStrategy();

    @GetMapping("/require")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public SimpleReponse selectResponseType(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取跳转到此之前的请求（引发跳转的请求）
        SavedRequest savedRequest = requestCache.getRequest(request,response);
        if(savedRequest  != null){
// 获取引发跳转的url名称
            String target = savedRequest .getRedirectUrl();
//判断url地址是否是 html的后缀，若果是HTML请求，则跳转到对应登录页面，否则就是REST请求，则直接返回错误JSON信息
            if(StringUtils.endsWithIgnoreCase(target ,".html")){
                redirectStrategy .sendRedirect(request,response,"/myLogin.html");  //各个调用模块里面自定义登录页面

            }
        }
        return new SimpleReponse("访问的服务需要身份认证，请引导用户到登录页面"); //SimpleResponse 是自定的
    }
}
