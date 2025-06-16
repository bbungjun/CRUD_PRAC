package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        // 모든 출처 허용 (개발 환경용)
        config.addAllowedOrigin("http://localhost:3000");

        // 프로덕션 환경에서는 특정 도메인만 허용하도록 변경해야 합니다
        // config.addAllowedOrigin("https://yourdomain.com");

        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.setAllowCredentials(true);

        source.registerCorsConfiguration("/api/**", config);
        return new CorsFilter(source);
    }
}
