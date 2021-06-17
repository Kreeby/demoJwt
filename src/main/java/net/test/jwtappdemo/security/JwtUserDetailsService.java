package net.test.jwtappdemo.security;

import lombok.extern.slf4j.Slf4j;
import net.test.jwtappdemo.model.User;
import net.test.jwtappdemo.security.jwt.JwtUser;
import net.test.jwtappdemo.security.jwt.JwtUserFactory;
import net.test.jwtappdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("User with phone number: " + username + " not found");
        }
        JwtUser jwtUser = JwtUserFactory.create(user);
        log.info("IN loadByUserName - user with username {} successfully loaded", username);
        return jwtUser;
    }
}
