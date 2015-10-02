/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miraflorescarwash.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

/**
 *
 * @author ty
 */
@Configuration
@ComponentScan(basePackages = {
    "com.miraflorescarwash.controller",
    "com.miraflorescarwash.dao",
    "com.miraflorescarwash.service"
})
@EnableWebMvc
public class Config extends WebMvcConfigurerAdapter {

    @Bean
    public UrlBasedViewResolver setupSpringViewResolver() {
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/Views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/templates/**").addResourceLocations("/templates/");
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
        registry.addResourceHandler("/resources/img/**").addResourceLocations("/resources/img/");
        registry.addResourceHandler("/resources/css/**").addResourceLocations("/resources/css/");
        registry.addResourceHandler("/resources/fonts/**").addResourceLocations("/resources/fonts/");
        registry.addResourceHandler("/resources/js/**").addResourceLocations("/resources/js/");
    }

//    @Bean
//    public ResourceBundleMessageSource messageSource() {
//        ResourceBundleMessageSource rb = new ResourceBundleMessageSource();
//        rb.setBasenames(new String[]{"messages/messages", "messages/validation"});
//        return rb;
//    }
}
