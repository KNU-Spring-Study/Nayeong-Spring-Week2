package com.example.springlogin.controller;

import com.example.springlogin.domain.User;
import com.example.springlogin.dto.LogInUserDTO;
import com.example.springlogin.dto.UserDTO;
import com.example.springlogin.service.UserService;
import com.example.springlogin.service.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("user")
@RequiredArgsConstructor
public class ViewController {

    private final UserService userService;

    @GetMapping("/sign-up")
    public String signUpForm(@ModelAttribute("user") User user) {
        return "web/sign-up";
    }

    /**
     * 회원가입
     * @param user
     * @param bindingResult
     * @return
     */
    @PostMapping("/sign-up")
    public String signUp(@Valid @ModelAttribute User user, BindingResult bindingResult) {
        boolean flag = userService.join(user);
        if(flag) {
            log.info("회원가입 성공");
            return "web/home";
        }
        else {
            log.info("회원가입 실패");
            bindingResult.reject("signUpFail", "이미 존재하는 이메일입니다.");
            return "web/sign-up";
        }
    }

    @GetMapping("/sign-in")
    public String SignInForm(@ModelAttribute("user") LogInUserDTO logInUserDTO) {
        return "web/login";
    }

    /**
     * 로그인
     * @param request
     * @param bindingResult
     * @param logInUserDTO
     * @return
     */
    @PostMapping("/sign-in")
    public String signIn(@Valid @ModelAttribute("user") LogInUserDTO logInUserDTO,
                         BindingResult bindingResult,
                         HttpServletRequest request) {

        if(bindingResult.hasErrors()) {
            log.info("로그인 화면으로 리다이렉트");
            log.info("error={}", bindingResult.getAllErrors());
            return "redirect:/user/sign-in";
        }

        User loginUser = userService.logIn(logInUserDTO);

        if(loginUser == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "/web/login";
        }

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginUser);

        log.info("login={}", loginUser.getId());
        log.info("홈 화면으로 리다이렉트");
        return "redirect:/home";
    }

    /**
     * 로그아웃
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if(session != null)
            session.invalidate();   //세션을 제거한다.

        log.info("logout={}", request.getSession().getId().toString());
        return "redirect:/home";
    }

    /**
     * 유저 정보 조회
     * @param request
     * @param model
     * @return
     */
    @GetMapping("/mypage")
    public String myPage(HttpServletRequest request,
                         Model model) {
        HttpSession session = request.getSession(false);

        if(session == null) {
            return "redirect:/web/home";
        }

        User user = (User) session.getAttribute(SessionConst.LOGIN_MEMBER);
        model.addAttribute("user", UserDTO.from(user));

        return "web/mypage";
    }


}
