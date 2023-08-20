package com.example.blog.Config;

import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static com.example.blog.Entity.Permission.*;
import static com.example.blog.Entity.Role.ADMIN;
import static com.example.blog.Entity.Role.MANAGER;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http

                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth->{
                    auth.requestMatchers("/api/v1/auth/**").permitAll();
//                    auth.requestMatchers("api/v1/management/**").hasAnyRole(ADMIN.name(), MANAGER.name());
//                    auth.requestMatchers(HttpMethod.GET, "api/v1/management/**").hasAnyAuthority(ADMIN_READ.name(), MANAGER_READ.name());
//                    auth.requestMatchers(HttpMethod.POST, "api/v1/management/**").hasAnyAuthority(ADMIN_CREATE.name(), MANAGER_CREATE.name());
//                    auth.requestMatchers(HttpMethod.PUT, "api/v1/management/**").hasAnyAuthority(ADMIN_UPDATE.name(), MANAGER_UPDATE.name());
//                    auth.requestMatchers(HttpMethod.DELETE, "api/v1/management/**").hasAnyAuthority(ADMIN_DELETE.name(), MANAGER_DELETE.name());
//                    auth.requestMatchers("api/v1/admin/**").hasRole(ADMIN.name());
//                    auth.requestMatchers(HttpMethod.GET, "api/v1/admin/**").hasAuthority(ADMIN_READ.name());
//                    auth.requestMatchers(HttpMethod.POST, "api/v1/admin/**").hasAuthority(ADMIN_CREATE.name());
//                    auth.requestMatchers(HttpMethod.PUT, "api/v1/admin/**").hasAuthority(ADMIN_UPDATE.name());
//                    auth.requestMatchers(HttpMethod.DELETE, "api/v1/admin/**").hasAuthority(ADMIN_DELETE.name());

                    auth.anyRequest().authenticated();
                    }
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout->logout.logoutUrl("/api/v1/auth/logout").addLogoutHandler(logoutHandler).logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext()));

        return http.build();

    }
}