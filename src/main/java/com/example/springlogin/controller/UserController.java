package com.example.springlogin.controller;

import com.example.springlogin.domain.User;
import com.example.springlogin.service.session.SessionManager;
import com.example.springlogin.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.LinkedHashMap;


@Slf4j
@RestController
@RequestMapping("user")
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
     * @param response
     * @param linkedHashMap
     * @throws IOException
     */
    @PostMapping("/sign-in")
    public void signIn(HttpServletResponse response,
                       @RequestBody LinkedHashMap linkedHashMap) throws IOException {
        User loginUser = userService.logIn(linkedHashMap);

        if(loginUser == null) {
            log.info("홈 화면으로 리다이렉트");
            response.sendRedirect("http://localhost:8080/home");
            return;
        }

        sessionManager.createSession(loginUser, response);
        log.info("login={}", loginUser.getId());
        log.info("홈 화면으로 리다이렉트");
        response.sendRedirect("http://localhost:8080/home");
    }

    /**
     * 로그아웃
     * @param request
     * @param response
     * @throws IOException
     */
    @PostMapping("/logout")
    public void logout(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        sessionManager.expire(request);
        log.info("logout={}", request.getSession().getId().toString());
        response.sendRedirect("http://localhost:8080/home");
    }

}
