package com.shop.controller;

import com.shop.model.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserServiceImpl userService;

    @GetMapping("/")
    public String getSignIn() {
        return "/index";
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return new ModelAndView("redirect:/");
    }

    @RequestMapping("/")
    public String postSignIn() {
        return "/index";
    }
}
