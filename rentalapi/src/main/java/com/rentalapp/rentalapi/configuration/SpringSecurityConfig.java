package com.rentalapp.rentalapi.configuration;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.rentalapp.rentalapi.service.CustomUserDetailsService;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Configuration
public class SpringSecurityConfig {

    /** Secret key used to encode and decode JWT tokens */
    private final SecretKey jwtKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    /**
     * Configures the security filter chain and defining rules for endpoint access.
     * 
     * @param http the {@link HttpSecurity} object used to configure HTTP security
     * @return a {@link SecurityFilterChain} object with the defined security rules
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers("/swagger-ui/index.html", "/swagger-ui/**",
                                        "/v3/api-docs/**", "/api/auth/**", "/openapi.yaml", "/uploads/**")
                                .permitAll()
                                .anyRequest().authenticated())
                .oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()))
                .build();
    }

    /**
     * Creates and configures an {@link AuthenticationManager} bean, integrating a custom 
     * {@link CustomUserDetailsService} and a password encoder for user authentication.
     * 
     * @param http the {@link HttpSecurity} object used to access the shared authentication manager builder
     * @param bCryptPasswordEncoder the {@link BCryptPasswordEncoder} for encoding passwords
     * @return an {@link AuthenticationManager} for authenticating users
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder)
            throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http
                .getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(bCryptPasswordEncoder);
        return authenticationManagerBuilder.build();
    }

    /**
     * Defines a {@link BCryptPasswordEncoder} bean to hash passwords securely using the BCrypt algorithm.
     * 
     * @return a new instance of {@link BCryptPasswordEncoder}
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Defines a {@link JwtEncoder} bean to encode JWT tokens using the predefined secret key.
     * 
     * @return a {@link JwtEncoder} configured with the secret key
     */
    @Bean
    public JwtEncoder jwtEncoder() {
        return new NimbusJwtEncoder(new ImmutableSecret<>(jwtKey.getEncoded()));
    }

    /**
     * Defines a {@link JwtDecoder} bean to decode JWT tokens using the predefined secret key.
     * 
     * @return a {@link JwtDecoder} configured with the secret key
     */
    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withSecretKey(jwtKey).build();
    }

}
