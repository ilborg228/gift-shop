package ru.samara.giftshop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.samara.giftshop.entity.User;
import ru.samara.giftshop.model.TokenRequest;
import ru.samara.giftshop.repository.UserRepository;
import ru.samara.giftshop.service.AuthService;

@RestController
public class AuthController {

    private final AuthService authService;
    private final UserRepository userRepository;

    public AuthController(AuthService authService, UserRepository userRepository) {
        this.authService = authService;
        this.userRepository = userRepository;
    }

    @PostMapping("/auth/token")
    public User token(@RequestBody TokenRequest tokenRequest) {
        return authService.token(tokenRequest);
    }

    @PatchMapping("/users/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatus(@PathVariable Long userId, @RequestParam String name) {
        userRepository.findById(userId).ifPresent(user -> {
            user.setUsername(name);
            userRepository.save(user);
        });
    }
}
