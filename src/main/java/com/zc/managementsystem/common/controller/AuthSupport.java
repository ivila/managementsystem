package com.zc.managementsystem.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class AuthSupport extends WebMvcConfigurationSupport {
    private AuthInterceptor interceptor;

    @Autowired
    public void setAuthInterceptor(AuthInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor).
                addPathPatterns("/api/**").
                excludePathPatterns("/api/auth/login");
    }
}


