package com.example.springlogin.service.validator;

import com.example.springlogin.domain.User;
import com.example.springlogin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserValidator {

    private final UserRepository userRepository;

    /**
     * 이메일 중복 검사
     * @param email
     * @return
     */
    public User validateDuplicate(String email) {
        Optional<User> userByUserEmail = userRepository.findUserByUserEmail(email);
        return userByUserEmail.orElse(null);
    }
}
