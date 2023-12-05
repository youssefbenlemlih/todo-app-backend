package com.yousefbee.todoappbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf(AbstractHttpConfigurer::disable)

        .cors(httpSecurityCorsConfigurer -> {
          final CorsConfiguration corsConfiguration = new CorsConfiguration();
          corsConfiguration.setAllowedHeaders(List.of(CorsConfiguration.ALL));
          corsConfiguration.setAllowedOriginPatterns(List.of(CorsConfiguration.ALL));
          corsConfiguration.setAllowedMethods(List.of(CorsConfiguration.ALL));
          corsConfiguration.setAllowCredentials(true);
          corsConfiguration.setExposedHeaders(List.of(CorsConfiguration.ALL));

          final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
          source.registerCorsConfiguration("/**", corsConfiguration);
          httpSecurityCorsConfigurer.configurationSource(source);
        })
        .authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll())
    ;
    return http.build();

  }
}
