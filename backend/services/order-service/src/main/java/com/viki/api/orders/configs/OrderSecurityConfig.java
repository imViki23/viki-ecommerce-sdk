package com.viki.api.orders.configs;

import com.viki.api.security.filters.AppAuthorizationFilter;
import com.viki.api.security.services.OpaAuthorizationService;
import com.viki.api.security.utils.JwtUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class OrderSecurityConfig {

    @Bean
    public AppAuthorizationFilter appAuthorizationFilter(OpaAuthorizationService opaAuthorizationService, JwtUtils jwtUtils) {
        return new AppAuthorizationFilter(opaAuthorizationService, jwtUtils);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AppAuthorizationFilter appAuthorizationFilter) {
        http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                        .requestMatchers("/actuator/**").permitAll()
                        .anyRequest()
                        .authenticated()
                );
        http.addFilterBefore(appAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}

