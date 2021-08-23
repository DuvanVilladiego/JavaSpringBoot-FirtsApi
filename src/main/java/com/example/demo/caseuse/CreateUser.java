package com.example.demo.caseuse;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class CreateUser {
    private UserService userService;

    public CreateUser(UserService userService){
        this.userService = userService;
    }

    public User save(User newUser) {
        return userService.save(newUser);
    }
}
