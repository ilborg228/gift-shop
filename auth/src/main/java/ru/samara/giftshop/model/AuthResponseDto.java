package ru.samara.giftshop.model;

import lombok.Data;

@Data
public class AuthResponseDto {

    private String accessToken;
    private String tokenType;
    private Long expiresIn;
    private String refreshToken;
    private String idToken;
}
