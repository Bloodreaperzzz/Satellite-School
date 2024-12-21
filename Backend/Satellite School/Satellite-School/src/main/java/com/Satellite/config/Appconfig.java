package com.Satellite.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity

public class Appconfig {
    //to configure spring security
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
http.sessionManagement(management->management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(Authorize->Authorize
                .requestMatchers("/api/principal/**").hasAuthority("PRINCIPAL")
                .requestMatchers("/api/teacher/**").hasAuthority("TEACHER")
                .requestMatchers("/api/student/**").hasAuthority("STUDENT")
                .requestMatchers("/api/founder_manager/**").hasAuthority("FOUNDER_MANAGER")
                .requestMatchers("/api/transport_worker/**").hasAuthority("TRANSPORT_WORKER")
                .requestMatchers("/api/**").authenticated()
                .requestMatchers("/new").permitAll()
                .requestMatchers("/student/register").permitAll()
                .anyRequest().permitAll()).addFilterBefore(new JwtTokenValidator(), BasicAuthenticationFilter.class)
        .csrf(csrf-> csrf.disable())
        .cors(cors->cors.configurationSource(corsConfigrationSource()));
        return http.build();


    }
    private CorsConfigurationSource corsConfigrationSource()
    {
        return new CorsConfigurationSource()//Corsconfigration is a interface when i tried to return an interface it made a temporary class itsel to override thae fn so i can return object of that class as an instance of interface cant be returned
        {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request)
            {
                CorsConfiguration cfg =new CorsConfiguration();
                cfg.setAllowedOrigins(Arrays.asList("http://localhost:8080","https://localhost:443,http://localhost:5454","http://localhost:3000"));
                 cfg.setAllowedMethods(Collections.singletonList("*"));
                cfg.setAllowCredentials(true);
                 cfg.setAllowedHeaders(Collections.singletonList("*"));
                 cfg.setExposedHeaders(Arrays.asList("Authorization"));
                 cfg.setMaxAge(3600L);
                 return cfg;

            }
        };

    }
   @Bean
    PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }


}
