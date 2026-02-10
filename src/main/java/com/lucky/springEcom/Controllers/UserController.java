package com.lucky.springEcom.Controllers;

import com.lucky.springEcom.Models.User;
import com.lucky.springEcom.Models.dto.UserRequest;
import com.lucky.springEcom.Services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> loginUser(@RequestBody UserRequest  user){

        return new ResponseEntity<>(userService.saveUser(user),HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> registerUser(@RequestBody UserRequest user){

        log.warn(user.username()+" trying to register through controller");

        try {
             final String token = userService.registerUser(user);
             return new ResponseEntity<>(token, HttpStatus.OK);

        }catch (UsernameNotFoundException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.FORBIDDEN);
        }

    }
}
