package com.deepak.microservices.Userservice.config.interceptors;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class FeignClientInterceptor implements RequestInterceptor {

    private OAuth2AuthorizedClientManager oAuth2AuthorizedClientManager;
    @Override
    public void apply(RequestTemplate requestTemplate) {
        String token = oAuth2AuthorizedClientManager.authorize(OAuth2AuthorizeRequest.withClientRegistrationId("auth0")
                .principal("internal")
                .build()).getAccessToken().getTokenValue();
        requestTemplate.header("Authorization", "Bearer "+ token);

    }
}
