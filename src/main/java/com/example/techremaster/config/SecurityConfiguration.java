package com.example.techremaster.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

  @Bean
  public SecurityFilterChain configure(HttpSecurity http) throws Exception {
    http
        .oauth2Client(Customizer.withDefaults())
        .oauth2Login(config -> config
            .defaultSuccessUrl("/whoami")
            .userInfoEndpoint(Customizer.withDefaults())
            .tokenEndpoint(Customizer.withDefaults()));

    http.sessionManagement(config -> config.sessionCreationPolicy(SessionCreationPolicy.ALWAYS));

    http.authorizeHttpRequests(config -> config
        .requestMatchers("/logged-out", "/oauth2/**", "/login/**").permitAll()
        .anyRequest().fullyAuthenticated());

    final String realmName = "Tech";

    final String logoutUrlTemplate =
        "http://localhost:8080/realms/%s/protocol/openid-connect/logout?redirect_uri=http://localhost:8081/logged-out";

    http.logout(config -> config
        .logoutSuccessUrl(String.format(logoutUrlTemplate, realmName)));

    return http.build();
  }
}