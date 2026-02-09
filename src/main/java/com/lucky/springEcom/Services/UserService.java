package com.lucky.springEcom.Services;

import com.lucky.springEcom.Models.User;
import com.lucky.springEcom.Models.dto.UserRequest;
import com.lucky.springEcom.Models.dto.UserResponse;
import com.lucky.springEcom.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

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
}
