package com.example.SpringSecurity.service;

import com.example.SpringSecurity.entity.UserPrinciple;
import com.example.SpringSecurity.entity.Users;
import com.example.SpringSecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    JwtConfigService jwtConfigService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    @Lazy
    AuthenticationManager authenticationManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user = userRepository.findByUserName(username);
        Optional<Users> optional = Optional.ofNullable(user);
        if (optional.isEmpty()) {
            throw new NullPointerException("No User Found In Data Base");
        }
        return new UserPrinciple(user);
    }

    public Users registerUser(Users users) {
        users.setPassword(new BCryptPasswordEncoder(12).encode(users.getPassword()));
        return userRepository.save(users);
    }

    public String validateUser(Users users) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(users.getUserName(), users.getPassword()));
        if (authentication.isAuthenticated())
            return jwtConfigService.generateToken(users.getUserName());

        return "Authentication Failed";
    }

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }
}
