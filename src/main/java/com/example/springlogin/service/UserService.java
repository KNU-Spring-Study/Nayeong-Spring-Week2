package com.example.springlogin.service;

import com.example.springlogin.dto.UserDTO;
import com.example.springlogin.repository.UserRepository;
import com.example.springlogin.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    /**
     * 중복검사 후 레포지토리에 저장
     * @param user
     * @return
     */
    public boolean join(User user) {

        if(validateDuplicateUser(user.getEmail()) == null) {
            userRepository.save(user);
            return true;
        }
        else return false;

    }

    /**
     * 이메일 중복 검사
     * @param email
     * @return
     */
    public User validateDuplicateUser(String email) {
        Optional<User> userByUserEmail = userRepository.findUserByUserEmail(email);
        return userByUserEmail.orElse(null);
    }

    /**
     * 로그인
     * @param linkedHashMap
     * @return
     */
    public User logIn(LinkedHashMap linkedHashMap) {
        String email = linkedHashMap.get("email").toString();
        String password = linkedHashMap.get("password").toString();

        User user = validateDuplicateUser(email);

        if (user == null || !user.getPassword().equals(password)) {
            log.info("로그인 실패");
            return null;
        }

        log.info("로그인 성공");

        return user;

    }
}
