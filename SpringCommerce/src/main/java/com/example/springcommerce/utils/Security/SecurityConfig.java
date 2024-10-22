package com.example.springcommerce.utils.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated());
        http.sessionManagement((session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)));
        http.httpBasic((withDefaults()));
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails userInfo = User.withUsername("user")
                .password("{noop}password")
                .roles("USER")
                .build();
        UserDetails adminInfo = User.withUsername("admin")
                .password("{noop}password")
                .roles("Admin")
                .build();
        return new InMemoryUserDetailsManager(userInfo, adminInfo);
    }
}
