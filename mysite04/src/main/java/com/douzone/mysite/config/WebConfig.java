package com.douzone.mysite.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.douzone.mysite.config.web.SecurityConfig;
import com.douzone.mysite.event.ApplicationContextEventListener;
import com.douzone.mysite.interceptor.MainInterceptor;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan({ "com.douzone.mysite.controller" })
@Import({ WebConfig.class, SecurityConfig.class })
public class WebConfig implements WebMvcConfigurer {

	// Site Inteceptor
	@Bean
	public HandlerInterceptor mainInterceptor() {
		return new MainInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(mainInterceptor()).addPathPatterns("/**");
	}

	// ApplicationContextEventListener
	@Bean
	public ApplicationContextEventListener applicationContextEventListener() {
		return new ApplicationContextEventListener();
	}
}
