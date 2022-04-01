package com.zy.maintenance.config;

import com.zy.maintenance.interceptor.JWTInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/manage/**","/**/import","/**/export",
                        "/file/**","/echarts/**","/user/**",
                        "/store/**","/common/**");
    }
    @Bean
    public JWTInterceptor jwtInterceptor(){
        return new JWTInterceptor();
    }
}
