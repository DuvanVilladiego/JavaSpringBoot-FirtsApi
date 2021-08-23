package com.example.demo.configuration;

import com.example.demo.caseuse.GetUser;
import com.example.demo.caseuse.GetUserImplement;
import com.example.demo.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CaseUseConfiguration {

    @Bean
    GetUser getUser(UserService userService){
        return new GetUserImplement(userService);
    }
}
