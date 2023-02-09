package com.example.springlogin.controller;

import com.example.springlogin.domain.User;
import com.example.springlogin.dto.UserDTO;
import com.example.springlogin.service.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping
    public String home(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) User user, Model model) {

        if(user == null) return "web/home";

        model.addAttribute("user", UserDTO.from(user));
        return "web/login-home";
    }
}
