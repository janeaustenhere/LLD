package com.example.amazonLocker.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http

                // Disable CSRF for APIs + H2 console
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers(
                                new AntPathRequestMatcher("/h2-console/**"),
                                new AntPathRequestMatcher("/locker/**")
                        )
                )

                // Allow H2 console frames
                .headers(headers ->
                        headers.frameOptions(frame -> frame.disable())
                )

                // Allow all requests
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/h2-console/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/v3/api-docs/**",
                                "/locker/**"
                        ).permitAll()
                        .anyRequest().permitAll()
                )

                // Optional basic auth
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}