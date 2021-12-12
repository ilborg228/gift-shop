package ru.samara.giftshop.security;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.samara.giftshop.entity.UserEntity;
import ru.samara.giftshop.repository.UserRepository;

@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(s).orElseThrow(() -> new UsernameNotFoundException(s));
        return UserMapper.userToPrincipal(user);
    }
}
