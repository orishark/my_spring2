package com.myspring.config;

import java.util.ArrayList;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

import com.myspring.interceptor.LoginInterceptor;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport{

	@Override
	 public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
	 }
	
	@Bean
	public ViewResolver getViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setOrder(1);
		return viewResolver;
	}
	
	@Bean
    public TilesConfigurer tilesConfigurer(){
        final TilesConfigurer configurer = new TilesConfigurer();
        configurer.setDefinitions("/WEB-INF/tiles/*.xml");
        configurer.setCheckRefresh(true);
        return configurer;
    }
     
    @Bean
    public UrlBasedViewResolver tilesViewResolver(){
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        resolver.setViewClass(TilesView.class);
        resolver.setOrder(0);
        return resolver;
    }
    
    @Inject
    LoginInterceptor loginInterceptor;
    
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
    	
    	ArrayList<String> pathPatterns = new ArrayList<>();
    	
    	pathPatterns.add("/board/write.do");
    	
    	registry.addInterceptor(loginInterceptor).addPathPatterns(pathPatterns);
    	
    	
    }
}
