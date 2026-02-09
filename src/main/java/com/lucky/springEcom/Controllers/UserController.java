package com.lucky.springEcom.Controllers;

import com.lucky.springEcom.Models.User;
import com.lucky.springEcom.Models.dto.UserRequest;
import com.lucky.springEcom.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserRequest  user){

        return new ResponseEntity<>(userService.saveUser(user),HttpStatus.OK);
    }
}
