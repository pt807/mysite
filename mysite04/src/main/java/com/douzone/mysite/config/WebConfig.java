package com.douzone.mysite.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.douzone.mysite.config.web.SecurityConfig;
import com.douzone.mysite.event.ApplicationContextEventListener;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan({"com.douzone.mysite.controller"})
@Import({WebConfig.class, SecurityConfig.class})
public class WebConfig {
	
	// ApplicationContextEventListener
	@Bean
	public ApplicationContextEventListener applicationContextEventListener() {
		return new ApplicationContextEventListener();
	}
}
