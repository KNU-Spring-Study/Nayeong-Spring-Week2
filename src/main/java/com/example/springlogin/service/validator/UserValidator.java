package com.example.springlogin.service.validator;

import com.example.springlogin.SpringConfig;
import com.example.springlogin.domain.User;
import com.example.springlogin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserValidator {

    static SpringConfig springConfig = new SpringConfig();
    private static final UserRepository userRepository = springConfig.UserRepository();

    /**
     * 이메일 중복 검사
     * @param email
     * @return
     */
    public static User validateDuplicate(String email) {
        Optional<User> userByUserEmail = userRepository.findUserByUserEmail(email);
        return userByUserEmail.orElse(null);
    }
}
