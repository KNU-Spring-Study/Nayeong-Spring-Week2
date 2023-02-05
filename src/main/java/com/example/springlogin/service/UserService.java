package com.example.springlogin.service;

import com.example.springlogin.SpringConfig;
import com.example.springlogin.repository.UserRepository;
import com.example.springlogin.repository.UserRepositoryImpl;
import com.example.springlogin.domain.User;
import com.example.springlogin.service.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    SpringConfig springConfig = new SpringConfig();
    private final UserRepository userRepository = springConfig.UserRepository();

    /**
     * 중복검사 후 레포지토리에 저장
     * @param user
     * @return
     */
    public boolean join(User user) {

        if(UserValidator.validateDuplicate(user.getEmail()) == null) {
            userRepository.save(user);
            return true;
        }
        else return false;

    }


    /**
     * 로그인
     * @param linkedHashMap
     * @return
     */
    public User logIn(LinkedHashMap linkedHashMap) {
        String email = linkedHashMap.get("email").toString();
        String password = linkedHashMap.get("password").toString();

        User user = UserValidator.validateDuplicate(email);

        if (user == null || !user.getPassword().equals(password)) {
            log.info("로그인 실패");
            return null;
        }

        log.info("로그인 성공");

        return user;

    }
}
