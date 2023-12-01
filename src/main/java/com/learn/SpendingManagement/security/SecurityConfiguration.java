package com.learn.SpendingManagement.security;


import com.learn.SpendingManagement.security.error.UnAuthenticationCustomHandler;
import com.learn.SpendingManagement.security.error.UnAuthorizationCustomHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

  private final UnAuthenticationCustomHandler unAuthenticationCustomHandler;
  private final UnAuthorizationCustomHandler unAuthorizationCustomHandler;


  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
          .cors().and()
          .csrf().disable()
          .authorizeHttpRequests()
          .requestMatchers("/api/v1/**")
          .permitAll()
          .anyRequest().permitAll()
          .and()
          .exceptionHandling()
          .accessDeniedHandler(unAuthorizationCustomHandler)
          .authenticationEntryPoint(unAuthenticationCustomHandler)
          .and().sessionManagement()
          .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
          .and().build();
  }

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
//    configuration.setAllowedOrigins(
//          Arrays.asList("localhost:3001", "http://localhost:3001",
//                "https://dongduongsport.vn",
//                "http://dongduongsport.vn")
//    );
//    configuration.setAllowedMethods(Arrays.asList("GET","POST", "PUT", "DELETE", "OPTIONS", "PATCH", "HEAD"));
    configuration.addAllowedHeader("*");
    configuration.addAllowedOrigin("*");
    configuration.addAllowedMethod("*");
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }
}
