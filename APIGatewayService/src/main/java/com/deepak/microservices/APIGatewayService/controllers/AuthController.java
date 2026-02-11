package com.deepak.microservices.APIGatewayService.controllers;

import com.deepak.microservices.APIGatewayService.models.AuthResponse;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    @GetMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RegisteredOAuth2AuthorizedClient("auth0") OAuth2AuthorizedClient oAuth2AuthorizedClient,
            @AuthenticationPrincipal OidcUser oidcUser,
            Model model) {
        logger.info("User Email : {} ", oidcUser.getEmail());
        System.out.println("User Email : {} "+ oidcUser.getEmail());
        AuthResponse authResponse = new AuthResponse();
        authResponse.setUserId(oidcUser.getEmail());
        authResponse.setAccessToken(oAuth2AuthorizedClient.getAccessToken().getTokenValue());
        authResponse.setRefreshToken(oAuth2AuthorizedClient.getRefreshToken() != null ? oAuth2AuthorizedClient.getRefreshToken().getTokenValue() : null);
        authResponse.setExpiresIn(oAuth2AuthorizedClient.getAccessToken().getExpiresAt() != null ? oAuth2AuthorizedClient.getAccessToken().getExpiresAt().getEpochSecond() : 0);

         List<String> authorities = oidcUser.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

         authResponse.setAuthorities(authorities);

         return new ResponseEntity<>(authResponse, HttpStatus.OK);

    }

    @GetMapping("/")
    public String home(@AuthenticationPrincipal OidcUser user) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toSet());

        return "Hello, " + user.getFullName() + "!<br/><br/>Authorities: " + authorities;
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('Administrator')")
    public String admin(@AuthenticationPrincipal OidcUser user) {
        return "Hello, Admin!<br/><br/><img src=" + user.getPicture() + " width=200/>";
    }

    @GetMapping("/profile")
    @PreAuthorize("hasAuthority('SCOPE_profile')")
    public Map<String, Object> profile(OAuth2AuthenticationToken authentication) {
        return authentication.getPrincipal().getAttributes();
    }
}
