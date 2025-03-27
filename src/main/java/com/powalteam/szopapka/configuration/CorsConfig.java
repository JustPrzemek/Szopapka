package com.powalteam.szopapka.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    public CorsConfig() {
    }

    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods(new String[]{"*"});
    }
}