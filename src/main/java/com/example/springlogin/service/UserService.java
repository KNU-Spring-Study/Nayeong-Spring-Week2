package com.example.springlogin.service;

import com.example.springlogin.dto.LogInUserDTO;
import com.example.springlogin.dto.UserConfirmationDTO;
import com.example.springlogin.repository.JPAUserRepository;
import com.example.springlogin.domain.User;
import com.example.springlogin.service.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final JPAUserRepository userRepository;
    private final UserValidator userValidator;

    /**
     * 중복검사 후 레포지토리에 저장
     * @param user
     * @return
     */
    public boolean join(User user) {

        if(userValidator.validateDuplicate(user.getEmail()) == null) {
            userRepository.save(user);
            return true;
        }
        else return false;

    }

    /**
     * 로그인
     * @param logInUserDTO
     * @return User.class
     */
    public User logIn(LogInUserDTO logInUserDTO) {

        User user = userValidator.validateDuplicate(logInUserDTO.getEmail());

        if (user == null || !user.getPassword().equals(logInUserDTO.getPassword())) {
            log.info("로그인 실패");
            return null;
        }

        log.info("로그인 성공");

        return user;

    }

    /**
     * 비밀번호 일치하는지 확인
     * @param userConfirmationDTO
     * @param user
     * @return
     */
    public Boolean passwordCheck(UserConfirmationDTO userConfirmationDTO, User user) {
        return userValidator.validatePassword(userConfirmationDTO.getPassword(), user);
    }

    public User findByUserId(Long userId) {
        return userRepository.findById(userId).get();
    }

    /**
     * 회원정보 수정
     * @param userId
     * @param updateUser
     */
    public void modifyUser(Long userId, User updateUser) {
//        userRepository.updateUser(userId, updateUser);
        Optional<User> optionalUser = userRepository.findById(userId);

        User user = optionalUser.get();

        user.setName(updateUser.getName());
        user.setPassword(updateUser.getPassword());
        user.setPhoneNumber(updateUser.getPhoneNumber());

        userRepository.save(user);
    }

    /**
     * 회원 정보 삭제
     * @param user
     */
    public void delete(User user) {
        userRepository.deleteById(user.getId());
        log.info("회원 정보 삭제={}", user.getId());
    }
}
