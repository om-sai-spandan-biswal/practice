package com.practice.om.blogApp.config;

import com.practice.om.blogApp.filter.JwtAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, JwtAuthFilter jwtAuthFilter) {
        httpSecurity.authorizeHttpRequests(auth -> auth
                .requestMatchers("/note", "/error","/auth/**").permitAll()
                .anyRequest().authenticated())
                .formLogin(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class) ;
        return httpSecurity.build() ;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) {
        return configuration.getAuthenticationManager() ;
    }


}

//
//@Bean
//public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//    httpSecurity.authorizeHttpRequests(auth -> auth
//                    .requestMatchers("/note","/error","/auth/**").permitAll()
//                    .anyRequest().authenticated())
//            .csrf(csrfConfig -> csrfConfig.disable())
//            .formLogin(formConfig -> formConfig.disable())
//            .sessionManagement(sessionConfig -> sessionConfig
//                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)) ;
//    return httpSecurity.build() ;
//}
//
//@Bean
//public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) {
//    return configuration.getAuthenticationManager() ;
//}