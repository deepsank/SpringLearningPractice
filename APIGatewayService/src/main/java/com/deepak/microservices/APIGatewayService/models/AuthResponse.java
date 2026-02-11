package com.deepak.microservices.APIGatewayService.models;

import lombok.*;

import java.util.Collection;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private String userId;
    private String accessToken;
    private String refreshToken;
    private long expiresIn;
    private Collection<String> authorities;

}
