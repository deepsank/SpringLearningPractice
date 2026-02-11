package com.deepak.microservices.APIGatewayService.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationSuccessHandler;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfigs {

    @Bean
    public SecurityWebFilterChain configure(ServerHttpSecurity httpSecurity) {
       return httpSecurity
                .authorizeExchange(exchanges -> exchanges
                        .anyExchange().authenticated()
                )
               .oauth2Login(Customizer.withDefaults())
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(Customizer.withDefaults())
                )
//               .oauth2Login(oauth2 -> oauth2
//                       .authenticationSuccessHandler(
//                        new RedirectServerAuthenticationSuccessHandler("/auth/login")
//                ))
                .build();
    }
}
