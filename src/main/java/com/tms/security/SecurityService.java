package com.tms.security;

import com.tms.domain.User;
import com.tms.domain.request.AuthRequest;
import com.tms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SecurityService {

   private final UserRepository userRepository;
   private final JwtService jwtService;
   private final PasswordEncoder passwordEncoder;

   @Autowired
    public SecurityService(UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public String getTokenFromAuthRequest(AuthRequest authRequest){
        Optional<User> user = userRepository.findUserByLogin(authRequest.getLogin());
        if (user.isPresent() && passwordEncoder.matches(authRequest.getPassword(),user.get().getPassword())){
            return jwtService.createJwtToken(authRequest.getLogin());
        }
        return "";
    }
}
