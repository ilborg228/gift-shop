package ru.samara.giftshop.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.samara.giftshop.entity.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static User userToPrincipal(UserEntity user) {
        User userp = new User();
        List<SimpleGrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName())).collect(Collectors.toList());

        userp.setUsername(user.getUsername());
        userp.setPassword(user.getPassword());
        userp.setEnabled(user.isEnabled());
        userp.setAuthorities(authorities);
        return userp;
    }

}
