package ru.samara.giftshop.model;

import lombok.Data;

@Data
public class TokenRequest {
    private String grantType;

    private String username;
    private String password;

    private String refreshToken;

    private String verificationCode;
}
