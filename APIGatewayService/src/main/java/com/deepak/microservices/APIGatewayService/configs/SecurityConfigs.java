package com.deepak.microservices.APIGatewayService.configs;

import com.deepak.microservices.APIGatewayService.controllers.AuthController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.HttpStatusServerEntryPoint;
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationSuccessHandler;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatcher;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;
import org.springframework.web.server.WebFilter;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfigs {

    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String issuerUri;
    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    /**
     * API Security Chain - JWT Bearer Token Authentication
     * Triggered when Authorization header contains Bearer token
     */
//    @Bean
//    @Order(1)
//    public SecurityWebFilterChain apiFilterChain(ServerHttpSecurity http) {
//        http
//                .securityMatcher(ServerWebExchangeMatchers.pathMatchers("/users/**", "/hotels/**", "/staffs/**", "/ratings/**"))
//                .authorizeExchange(exchanges -> exchanges.anyExchange().authenticated())
//                .oauth2ResourceServer(oauth2 -> oauth2
//                        .jwt(jwt -> jwt
//                                .jwtDecoder(jwtDecoder())
//                        )
//                )
//                .exceptionHandling(exceptions -> exceptions
//                        .authenticationEntryPoint((exchange, ex) -> {
//                            System.out.println("❌ API Auth Failed: " + ex.getMessage());
//                            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//                            return exchange.getResponse().setComplete();
//                        })
//                );
//
//        http.csrf(ServerHttpSecurity.CsrfSpec::disable);
//        http.httpBasic(ServerHttpSecurity.HttpBasicSpec::disable);
//        http.formLogin(ServerHttpSecurity.FormLoginSpec::disable);
//
//        // Add this debug filter
//        http.addFilterBefore(debugFilter(), SecurityWebFiltersOrder.AUTHENTICATION);
//        return http.build();
//    }
//
//    @Bean
//    @Order(2)
//    public SecurityWebFilterChain webSecurityFilterChain(ServerHttpSecurity http) {
//        return http
//                .csrf(csrf -> csrf.disable())
//                .authorizeExchange(exchanges -> exchanges
//                        .pathMatchers(
//                                "/",
//                                "/favicon.ico",
//                                "/login/**",
//                                "/oauth2/**",
//                                "/error",
//                                "/actuator/health"
//                        ).permitAll()
//                        .anyExchange().authenticated()
//                )
//                .oauth2Login(oauth2 -> oauth2
//                        .authenticationSuccessHandler((webFilterExchange, authentication) -> {
//                            webFilterExchange.getExchange().getResponse()
//                                    .setStatusCode(HttpStatus.FOUND);
//                            webFilterExchange.getExchange().getResponse().getHeaders()
//                                    .setLocation(java.net.URI.create("/"));
//                            return webFilterExchange.getExchange().getResponse().setComplete();
//                        })
//                )
//                .oauth2Client(oauth2Client -> {})
//                .build();
//    }
//
//    @Bean
//    public ReactiveJwtDecoder jwtDecoder() {
//        return NimbusReactiveJwtDecoder.withIssuerLocation(issuerUri).build();
//    }
//
//
//    /**
//     * Debug filter to log incoming requests
//     */
//    private WebFilter debugFilter() {
//        return (exchange, chain) -> {
//            String path = exchange.getRequest().getPath().value();
//            String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
//
//            logger.info("🔍 API Request: {}", path);
//            logger.info("🔍 Auth Header: {}", authHeader != null ? "Bearer ***" : "None");
//
//            if (authHeader != null && authHeader.startsWith("Bearer ")) {
//                logger.info("✅ Bearer token present");
//            } else {
//                logger.warn("❌ No Bearer token found");
//            }
//
//            return chain.filter(exchange);
//        };
//    }

    @Bean
    public SecurityWebFilterChain configure(ServerHttpSecurity httpSecurity) {
       return
        httpSecurity
                .authorizeExchange(exchanges -> exchanges
                        .anyExchange().authenticated()
                )
               .oauth2ResourceServer(oauth2 -> oauth2
                       .jwt(jwt -> jwt
                               .jwtDecoder(jwtDecoder()))
               )
               .oauth2Login(Customizer.withDefaults())
               .oauth2Client(Customizer.withDefaults())

               .build();
    }

    @Bean
    public ReactiveJwtDecoder jwtDecoder() {
        return NimbusReactiveJwtDecoder.withIssuerLocation(issuerUri).build();
    }



}
