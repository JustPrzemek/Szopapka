package com.powalteam.szopapka.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("http://localhost:5173") // Adres Twojego frontendu (lub "*" dla testów)
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Tylko potrzebne metody
            .allowedHeaders("*") // Dopuszcza nagłówki np. "Authorization", "Content-Type"
            .allowCredentials(true); // Wymagane, jeśli używasz ciasteczek/tokenów
    }
}
