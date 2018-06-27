package com.yly.consumer.configration;

import com.yly.consumer.configration.security.MyPWDEncoder;
import com.yly.consumer.controller.security.filter.ValidateCodeFilter;
import com.yly.consumer.utils.SecurityConstants;
import com.yly.consumer.utils.propertise.SecurityPropertise;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;

@Configuration  //无需其他配置  已经生效
@EnableConfigurationProperties(SecurityPropertise.class) //允许SecurityPropertise读取配置文件
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    private AuthenticationSuccessHandler myAuthSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler myAuthFailureHandler;

    @Autowired
    private UserDetailsService userDetailsService;//用于“记住我”功能在DB读取完usertoken以后，进行登录

    @Autowired
    private DataSource dataSource;

    /**
     * spring-social的配置
     */
    @Autowired
    SpringSocialConfigurer mySocialSecurityConfig;



    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
//        jdbcTokenRepository.setCreateTableOnStartup(true);//启动时候创建token存储的表,，注意如果表已经存在就会报错
        return jdbcTokenRepository;
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setAuthFailureHandler(myAuthFailureHandler);
            http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                    .formLogin()
//                .loginPage("/myLogin.html")
                .loginPage("/authentication/require")
                .loginProcessingUrl("/unameAndPwdSubmit")
                    .successHandler(myAuthSuccessHandler)
                    .failureHandler(myAuthFailureHandler)
                .and()
                    .apply(mySocialSecurityConfig)  //spring social
                .and()
                .rememberMe()
                    .tokenRepository(persistentTokenRepository())
                    .tokenValiditySeconds(24*60)
                    .userDetailsService(userDetailsService)

                .and()
                .authorizeRequests()
//                    .antMatchers("/myLogin.html").permitAll()
                    .antMatchers("/authentication/require","/myLogin.html","/code/image").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable(); //暂时禁用CSRF （禁用跨站防护），否则提交会报错

    }
    //配置密码 加密和解密算法类
    @Bean
    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder(); //spring 提供，可以自定义一个类实现PasswordEncoder 接口，实现对应的方法：match、encode 。。等
        return new MyPWDEncoder();
    }
}
