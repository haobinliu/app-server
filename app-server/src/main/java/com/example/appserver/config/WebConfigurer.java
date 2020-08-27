package com.example.appserver.config;

import com.example.appserver.Interceptor.LoginInterceptor;
import org.springframework.web.servlet.config.annotation.*;

import javax.annotation.Resource;

/**
 * @Author: 13005
 * @DATE: 2020/8/27 16:21
 */
public class WebConfigurer implements WebMvcConfigurer {

    @Resource
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/userLogin/**");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/userLogin").setViewName("loginView");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/resource/**").addResourceLocations("classpath:/resource/");
    }
}
