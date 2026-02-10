package com.lucky.springEcom.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class securityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public AuthenticationProvider authenticationProvider(){

        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider(userDetailsService);

        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder(12));

        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        return http
                .csrf(crsf->crsf.disable())
                .authorizeHttpRequests(request->
                        request
                                .requestMatchers("/api/order/**").authenticated()
                                .requestMatchers(HttpMethod.POST, "/api/product/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/api/product/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/product/**").hasRole("ADMIN")
                                .anyRequest().permitAll()
                )
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(sesion->
                        sesion.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{

        return authenticationConfiguration.getAuthenticationManager();

    }
}
