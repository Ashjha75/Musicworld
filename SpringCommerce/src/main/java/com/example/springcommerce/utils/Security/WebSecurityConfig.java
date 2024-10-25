package com.example.springcommerce.utils.Security;

import com.example.springcommerce.entity.roleEntity;
import com.example.springcommerce.entity.userEntity;
import com.example.springcommerce.repository.RoleRepo;
import com.example.springcommerce.repository.userRepo;
import com.example.springcommerce.utils.Enums.AppRoles;
import com.example.springcommerce.utils.Security.Service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Set;

/**
 * Configuration class for Spring Security.
 * This class sets up the security configuration for the application.
 */
@Configuration
@EnableWebSecurity
//@EnableMethodSecurity
public class WebSecurityConfig {
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    private final AuthEntryPointJwt unauthorizedHandler;

    /**
     * Constructor for WebSecurityConfig.
     *
     * @param unauthorizedHandler the unauthorized handler to be injected
     */
    @Autowired
    public WebSecurityConfig( AuthEntryPointJwt unauthorizedHandler) {
        this.unauthorizedHandler = unauthorizedHandler;
    }

    /**
     * Bean for AuthTokenFilter.
     * This filter is used to handle JWT authentication.
     *
     * @return a new instance of AuthTokenFilter
     */
    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    /**
     * Bean for DaoAuthenticationProvider.
     * This provider is used to authenticate users using the userDetailsService and passwordEncoder.
     *
     * @return a configured DaoAuthenticationProvider
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /**
     * Bean for PasswordEncoder.
     * This encoder is used to encode passwords.
     *
     * @return a PasswordEncoder instance
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Bean for AuthenticationManager.
     * This manager is used to handle authentication.
     *
     * @param authConfig the authentication configuration
     * @return an AuthenticationManager instance
     * @throws Exception if an error occurs while creating the AuthenticationManager
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/api/auth/**").permitAll()
                                .requestMatchers("/v3/api-docs/**").permitAll()
                                //.requestMatchers("/api/admin/**").permitAll()
                                //.requestMatchers("/api/public/**").permitAll()
                                .requestMatchers("/swagger-ui/**").permitAll()
                                .requestMatchers("/api/test/**").permitAll()
                                .requestMatchers("/images/**").permitAll()
                                .anyRequest().authenticated()
                );

        http.authenticationProvider(authenticationProvider());

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        http.headers(headers -> headers.frameOptions(
                frameOptions -> frameOptions.sameOrigin()));

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web -> web.ignoring().requestMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**"));
    }
//    @Bean
//    public CommandLineRunner initData(RoleRepo RoleRepo, userRepo userRepo, PasswordEncoder passwordEncoder) {
//        return args -> {
//            // Retrieve or create roles
//            roleEntity userRole = RoleRepo.findByRoleName(AppRoles.ROLE_USER)
//                    .orElseGet(() -> {
//                        roleEntity newUserRole = new roleEntity(AppRoles.ROLE_USER);
//                        return RoleRepo.save(newUserRole);
//                    });
//
//            roleEntity sellerRole = RoleRepo.findByRoleName(AppRoles.ROLE_SELLER)
//                    .orElseGet(() -> {
//                        roleEntity newSellerRole = new roleEntity(AppRoles.ROLE_SELLER);
//                        return RoleRepo.save(newSellerRole);
//                    });
//
//            roleEntity adminRole = RoleRepo.findByRoleName(AppRoles.ROLE_ADMIN)
//                    .orElseGet(() -> {
//                        roleEntity newAdminRole = new roleEntity(AppRoles.ROLE_ADMIN);
//                        return RoleRepo.save(newAdminRole);
//                    });
//
//            Set<roleEntity> userRoles = Set.of(userRole);
//            Set<roleEntity> sellerRoles = Set.of(sellerRole);
//            Set<roleEntity> adminRoles = Set.of(userRole, sellerRole, adminRole);
//
//
//            // Create users if not already present
//            if (!userRepo.existsByUsername("user1")) {
//                userEntity user1 = new userEntity("user1", "user1@example.com", passwordEncoder.encode("password1"));
//                userRepo.save(user1);
//            }
//
//            if (!userRepo.existsByUsername("seller1")) {
//                userEntity seller1 = new userEntity("seller1", "seller1@example.com", passwordEncoder.encode("password2"));
//                userRepo.save(seller1);
//            }
//
//            if (!userRepo.existsByUsername("admin")) {
//                userEntity admin = new userEntity("admin", "admin@example.com", passwordEncoder.encode("adminPass"));
//                userRepo.save(admin);
//            }
//
//            // Update roles for existing users
//            userRepo.findByUsername("user1").ifPresent(user -> {
//                user.setRoles(userRoles);
//                userRepo.save(user);
//            });
//
//            userRepo.findByUsername("seller1").ifPresent(seller -> {
//                seller.setRoles(sellerRoles);
//                userRepo.save(seller);
//            });
//
//            userRepo.findByUsername("admin").ifPresent(admin -> {
//                admin.setRoles(adminRoles);
//                userRepo.save(admin);
//            });
//        };
//    }

}
