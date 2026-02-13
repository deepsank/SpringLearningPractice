package com.deepak.microservices.Userservice.config.interceptors;

import com.netflix.discovery.converters.Auto;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class FeignClientInterceptor implements RequestInterceptor {

//    @Autowired
//    private OAuth2AuthorizedClientManager oAuth2AuthorizedClientManager;
//    @Override
//    public void apply(RequestTemplate requestTemplate) {
//        String token = oAuth2AuthorizedClientManager.authorize(OAuth2AuthorizeRequest.withClientRegistrationId("auth0")
//                .principal("internal")
//                .build()).getAccessToken().getTokenValue();
//        requestTemplate.header("Authorization", "Bearer "+ token);
//
//    }

    @Override
    public void apply(RequestTemplate requestTemplate) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof JwtAuthenticationToken jwtAuth) {

            String token = jwtAuth.getToken().getTokenValue();

            requestTemplate.header("Authorization", "Bearer " + token);
        }
    }
}
