package com.yly.filter;


import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**FilterRegistrationBean设置多个 filter
 * Created by Administrator on 2017/11/30.
 */
@Order(1)
@WebFilter(filterName ="testFilterRegistration" ,
        initParams = {@WebInitParam(name="encoding", value="GBK"),
                        @WebInitParam(name="name1",value = "value1")},
        urlPatterns = "/*")
public class MyFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        System.out.println("这是MyFilter，url:" + request.getRequestURI());
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
