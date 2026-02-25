package com.deepak.microservices.APIGatewayService.controllers;

import com.deepak.microservices.APIGatewayService.models.AuthResponse;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private Logger logger = LoggerFactory.getLogger(AuthController.class);

//    @GetMapping("/login")
//    public ResponseEntity<AuthResponse> login(
//            @RegisteredOAuth2AuthorizedClient("auth0") OAuth2AuthorizedClient oAuth2AuthorizedClient,
//            @AuthenticationPrincipal OidcUser oidcUser,
//            Model model) {
//        logger.info("User Email : {} ", oidcUser.getEmail());
//        System.out.println("User Email : {} "+ oidcUser.getEmail());
//        AuthResponse authResponse = new AuthResponse();
//        authResponse.setUserId(oidcUser.getEmail());
//        authResponse.setAccessToken(oAuth2AuthorizedClient.getAccessToken().getTokenValue());
//        authResponse.setRefreshToken(oAuth2AuthorizedClient.getRefreshToken() != null ? oAuth2AuthorizedClient.getRefreshToken().getTokenValue() : null);
//        authResponse.setExpiresIn(oAuth2AuthorizedClient.getAccessToken().getExpiresAt() != null ? oAuth2AuthorizedClient.getAccessToken().getExpiresAt().getEpochSecond() : 0);
//
//         List<String> authorities = oidcUser.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
//
//         authResponse.setAuthorities(authorities);
//
//         return new ResponseEntity<>(authResponse, HttpStatus.OK);
//
//    }

    @Value("${auth0.domain}")
    private String auth0Domain;

    @Value("${auth0.audience}")
    private String audience;

    @Value("${spring.security.oauth2.client.registration.auth0.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.auth0.client-secret}")
    private String clientSecret;

    /**
     * After OAuth2 login, exchange the authorization code for an API access token
     */
    @GetMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RegisteredOAuth2AuthorizedClient("auth0") OAuth2AuthorizedClient oAuth2AuthorizedClient,
            @AuthenticationPrincipal OidcUser oidcUser) {

        logger.info("User Email: {}", oidcUser.getEmail());
        logger.info("User Sub: {}", oidcUser.getSubject());

        // The OAuth2AuthorizedClient token is for Auth0 UserInfo endpoint
        // We need to get a separate API token with the correct audience
        String apiAccessToken = getApiAccessToken(oAuth2AuthorizedClient);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setUserId(oidcUser.getEmail());
        authResponse.setAccessToken(apiAccessToken); // API token, not OAuth2 token
        authResponse.setRefreshToken(
                oAuth2AuthorizedClient.getRefreshToken() != null
                        ? oAuth2AuthorizedClient.getRefreshToken().getTokenValue()
                        : null
        );
        authResponse.setExpiresIn(
                oAuth2AuthorizedClient.getAccessToken().getExpiresAt() != null
                        ? oAuth2AuthorizedClient.getAccessToken().getExpiresAt().getEpochSecond()
                        : 0
        );

        List<String> authorities = oidcUser.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        authResponse.setAuthorities(authorities);

        return ResponseEntity.ok(authResponse);
    }

    /**
     * Exchange the OAuth2 token for an API token with correct audience
     */
    private String getApiAccessToken(OAuth2AuthorizedClient client) {
        try {
            RestTemplate restTemplate = new RestTemplate();

            String tokenUrl = "https://" + auth0Domain + "/oauth/token";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
            body.add("grant_type", "client_credentials");
            body.add("client_id", clientId);
            body.add("client_secret", clientSecret);
            body.add("audience", audience);

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

            ResponseEntity<Map> response = restTemplate.postForEntity(tokenUrl, request, Map.class);

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                return (String) response.getBody().get("access_token");
            }

            logger.error("Failed to get API token: {}", response.getStatusCode());
            return client.getAccessToken().getTokenValue(); // Fallback

        } catch (Exception e) {
            logger.error("Error getting API token", e);
            return client.getAccessToken().getTokenValue(); // Fallback
        }
    }

//    @GetMapping("/")
//    public String home(@AuthenticationPrincipal OidcUser user) {
//        var authentication = SecurityContextHolder.getContext().getAuthentication();
//        var authorities = authentication.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
//
//        return "Hello, " + user.getFullName() + "!<br/><br/>Authorities: " + authorities;
//    }

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
