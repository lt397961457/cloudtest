package com.yly.consumer.ZuulFilter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2018/1/2.
 */
//@Component
public class FirstZuulFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    } // pre ： 前(zuul服务请求其他微服务之前)，routing：过程中，post：后 ，error： 错误

    //多个filter时候的顺序
    @Override
    public int filterOrder() {
        System.out.println("zuul_filter_filterOrder===============================================");
        return 1;
    }

    //是否启用该filter
    @Override
    public boolean shouldFilter() {
        System.out.println("zuul_filter_shouldFilter===============================================");
        return true;
    }

    //具体逻辑
    @Override
    public Object run() {
        System.out.println("zuul_filter_run===============================================");
        RequestContext requestContext  = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        Object accessToken = request.getParameter("token");
        if(accessToken == null){
            System.out.print("token is empty");
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(401);
            try{
                requestContext.getResponse().getWriter().write("token is empty!!!");
            }catch (Exception e){}
            return null;
        }
        return null;
    }
}
