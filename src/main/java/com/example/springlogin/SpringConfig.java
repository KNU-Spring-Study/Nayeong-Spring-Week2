package com.example.springlogin;

import com.example.springlogin.repository.UserRepository;
import com.example.springlogin.repository.UserRepositoryImpl;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    public UserRepository UserRepository() {
        return new UserRepositoryImpl();
    }
}
