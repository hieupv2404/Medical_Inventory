package com.java1906.climan.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //handle css js
        if (!registry.hasMappingForPattern("/static/assets/**")) {
            registry.addResourceHandler("/static/assets/**")
                    .addResourceLocations("classpath:/static/assets/");
        }
        if (!registry.hasMappingForPattern("/templates/**")) {
            registry.addResourceHandler("/templates/**")
                    .addResourceLocations("classpath:/templates/");
        }
    }
}