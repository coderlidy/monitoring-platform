package com.car.demo.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器路径配置(注册拦截器并添加拦截路径)
 */
//@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private InterceptorHandle interceptorHandle;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册拦截器并添加拦截路径
        //registry.addInterceptor(interceptorHandle).addPathPatterns("/**");
        InterceptorRegistration interceptorRegistration=registry.addInterceptor(interceptorHandle);
        interceptorRegistration.addPathPatterns("/**");
        interceptorRegistration.excludePathPatterns(
                "/wx/loginStatus",//微信登录判断
                "/login",       //登录判断地址
                "/logout",       //退出登录
                "/**/*.html",            //html静态资源
                "/**/*.js",              //js静态资源
                "/**/*.css",            //css静态资源
                "/**/*.woff",
                "/**/*.ttf",
                "/**/*.ico"
        );
    }
}
