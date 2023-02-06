package com.example.springlogin.controller;

import com.example.springlogin.domain.User;
import com.example.springlogin.dto.UserDTO;
import com.example.springlogin.service.session.SessionConst;
import com.example.springlogin.service.session.SessionManager;
import com.example.springlogin.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
    public ResponseEntity signUp(@RequestBody User user, BindingResult bindingResult) {
        boolean flag = userService.join(user);
        if(flag) {
            log.info("회원가입 성공");
            return new ResponseEntity(HttpStatus.OK);
        }
        else {
            log.info("회원가입 실패");
            bindingResult.reject("signUpFail", "이미 존재하는 이메일입니다.");
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
    public void signIn(HttpServletRequest request,
                       HttpServletResponse response,
                       BindingResult bindingResult,
                       @RequestBody LinkedHashMap linkedHashMap) throws IOException {
        if(bindingResult.hasErrors()) {
            log.info("홈 화면으로 리다이렉트");
            response.sendRedirect("http://localhost:8080/home");
            return;
        }

        User loginUser = userService.logIn(linkedHashMap);

        if(loginUser == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            log.info("홈 화면으로 리다이렉트");
            response.sendRedirect("http://localhost:8080/home");
            return;
        }

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginUser);

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

        HttpSession session = request.getSession(false);
        if(session != null)
            session.invalidate();   //세션을 제거한다.

        log.info("logout={}", request.getSession().getId().toString());
        response.sendRedirect("http://localhost:8080/home");
    }

    /**
     * 유저 정보 조회
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @GetMapping("/mypage")
    public ResponseEntity<UserDTO> myPage(HttpServletRequest request,
                                          HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);

        if(session == null) {
            response.sendRedirect("http://localhost:8080/user/login");
        }

        User user = (User) session.getAttribute(SessionConst.LOGIN_MEMBER);

        return new ResponseEntity(UserDTO.from(user), HttpStatus.OK);
    }

}
