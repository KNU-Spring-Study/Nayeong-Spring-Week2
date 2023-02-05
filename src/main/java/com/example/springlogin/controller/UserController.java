package com.example.springlogin.controller;

import com.example.springlogin.domain.User;
import com.example.springlogin.SessionManager;
import com.example.springlogin.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.LinkedHashMap;


@Slf4j
@RestController("/user")
@RequiredArgsConstructor
public class UserController {

    private SessionManager sessionManager = new SessionManager();
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
    public void signIn(HttpServletRequest request, HttpServletResponse response,
                       @RequestBody LinkedHashMap linkedHashMap) throws IOException {
        User loginUser = userService.logIn(linkedHashMap);

        if(loginUser == null) {
            response.sendRedirect("http://localhost:8080/user/sign-in");
        }

        sessionManager.createSession(loginUser, response);
        response.sendRedirect("http://localhost:8080/home");
    }
}
