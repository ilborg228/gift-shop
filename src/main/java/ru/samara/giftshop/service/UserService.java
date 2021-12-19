package ru.samara.giftshop.service;

import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.samara.giftshop.entity.UserEntity;
import ru.samara.giftshop.repository.UserRepository;

@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserEntity getByLogin(@NonNull String login) {
        return userRepository.findByUsername(login).orElseThrow(() -> new UsernameNotFoundException(login));
    }
}
