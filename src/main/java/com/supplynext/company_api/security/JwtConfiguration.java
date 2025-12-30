package com.supplynext.company_api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class JwtConfiguration {

    @Autowired
    JwtFilter jwtFilter;

    /**
     * In this method we will be telling which endpoints are private or public
     * @param http
     * @return
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(c -> c.disable())
                .cors(c -> c.configure(http))
                .authorizeHttpRequests(
                        auth -> auth.requestMatchers(
                                        "/c2d/api/v1/company/start-onboarding",
                                        "/c2d/api/v1/user/login"
                                ).permitAll()
                                .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
