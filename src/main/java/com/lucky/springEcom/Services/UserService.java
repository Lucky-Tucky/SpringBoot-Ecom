package com.lucky.springEcom.Services;

import com.lucky.springEcom.Models.User;
import com.lucky.springEcom.Models.dto.UserRequest;
import com.lucky.springEcom.Models.dto.UserResponse;
import com.lucky.springEcom.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);


    public UserResponse saveUser(UserRequest userRequest){

        User user = new User();
        user.setUsername(userRequest.username());
        user.setPassword(bCryptPasswordEncoder.encode(userRequest.password()));

        User saved_user = userRepository.save(user);

        return UserResponse.builder()
                .username(saved_user.getUsername())
                .build();

    }

    public String registerUser(UserRequest user){

        log.warn(user.username() + " Registering In the Service Class ");

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.username(), user.password()));

        if(authentication.isAuthenticated()){
            log.warn(user.username() + " is Authenticated ");
            return jwtService.generateToke(user.username());
        }else{
            log.warn(user.username() + " Authentication Failed");
            throw new UsernameNotFoundException("Authentication Failed..");
        }

    }
}
