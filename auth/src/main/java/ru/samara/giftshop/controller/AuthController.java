package ru.samara.giftshop.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.samara.giftshop.entity.User;
import ru.samara.giftshop.model.TokenRequest;
import ru.samara.giftshop.service.AuthService;

@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/auth/token")
    public User token(@RequestBody TokenRequest tokenRequest) {
        return authService.token(tokenRequest);
    }
}
