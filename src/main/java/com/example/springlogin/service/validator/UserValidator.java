package com.example.springlogin.service.validator;

import com.example.springlogin.domain.User;
import com.example.springlogin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
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

    /**
     * 비밀번호 검사
     * @param password
     * @param user
     * @return
     */
    public Boolean validatePassword(String password, User user) {
        if(!user.getPassword().equals(password)) {
            log.info("비밀번호가 일치하지 않음");
            return false;
        }
        return true;
    }
}
