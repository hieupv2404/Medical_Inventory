package com.java1906.climan;

import com.java1906.climan.interceptor.AuthenticationAndLoggingInterceptor;
import com.java1906.climan.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.persistence.Entity;

@SpringBootApplication
public class ClinicManagementApplication implements WebMvcConfigurer {

    @Value("${application.randomHash}")
    private String randomHash;

    public static void main(String[] args) {
        SpringApplication.run(ClinicManagementApplication.class, args);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthenticationAndLoggingInterceptor());
    }

    @EventListener(classes = {ContextStartedEvent.class, ContextStoppedEvent.class})
    public void handleMultipleEvents() {
        PasswordUtils.getInstance().setRandomHash(randomHash);
    }

}
