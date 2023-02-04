package com.example.springlogin.controller;

import com.example.springlogin.domain.User;
import com.example.springlogin.dto.UserDTO;
import com.example.springlogin.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;


@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 회원가입
     * @param user
     * @return
     */
    @PostMapping("/sign-up")
    public ResponseEntity signUp(@RequestBody User user) {
        boolean flag = userService.join(user);
        if(flag) {
            log.info("회원가입 성공");
            return new ResponseEntity(HttpStatus.OK);
        }
        else {
            log.info("회원가입 실패");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 로그인
     * @param linkedHashMap
     * @return
     */
    @PostMapping("/sign-in")
    public ResponseEntity<UserDTO> signIn(@RequestBody LinkedHashMap linkedHashMap) {
        UserDTO userDTO = userService.logIn(linkedHashMap);

        if(userDTO == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity(userDTO, HttpStatus.OK);
    }
}
