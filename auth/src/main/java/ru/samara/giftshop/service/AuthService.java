package ru.samara.giftshop.service;

import org.springframework.stereotype.Service;
import ru.samara.giftshop.entity.User;
import ru.samara.giftshop.exceptions.ApiException;
import ru.samara.giftshop.exceptions.DataNotFoundResponse;
import ru.samara.giftshop.exceptions.DataValidationResponse;
import ru.samara.giftshop.model.TokenRequest;
import ru.samara.giftshop.repository.UserRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {

    private final static String LOGIN = "login";
    private final static String REGISTRATION = "registration";

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User token(TokenRequest tokenRequest) {
        String grantType = tokenRequest.getGrantType();
        switch (grantType) {
            case LOGIN:
                Optional<User> optUser = userRepository.
                        findByUsernameAndPassword(tokenRequest.getUsername(), tokenRequest.getPassword());

                return optUser.orElseThrow(() -> new ApiException(DataNotFoundResponse.USER_NOT_FOUND));
            case REGISTRATION:
                Optional<User> optUser1 = userRepository.
                        findByUsername(tokenRequest.getUsername());
                optUser1.ifPresent(user -> {
                    throw new ApiException(DataValidationResponse.USER_ALREADY_EXIST);
                });

                User user = new User();
                user.setId(UUID.randomUUID());
                user.setUsername(tokenRequest.getUsername());
                user.setPassword(tokenRequest.getPassword());
                user.setRole(User.Status.GUEST);
                return userRepository.save(user);
        }
        throw new ApiException(DataValidationResponse.INVALID_REQUEST);
    }
}
