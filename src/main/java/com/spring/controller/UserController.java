package com.spring.controller;

import com.spring.model.domain.User;
import com.spring.model.service.impl.UserServiceImpl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    
    @PostMapping("/")
    public ModelAndView postSignIn(@RequestParam("email") String email,
                             @RequestParam("password") String password,
                             HttpSession session,
                             Model model) {
        //User user = userService.findByLoginAndPassword(email,  password);
    	User user = userService.findByLogin(email);
        if (user != null && user.getPassword().equals(DigestUtils.sha256Hex(password))) {        	
        	session.setAttribute("user", user);
        	session.setAttribute("userNotExists", null);
        	log.info("Авторизация пользователя " + user.getName());
            String userType = user.getUserType().getType();
            if (userType.equalsIgnoreCase("goods_spec")) {				//товаровед
            	return new ModelAndView("redirect:/goods");
    		} else if (userType.equalsIgnoreCase("cashier")) {			//кассир
    			return new ModelAndView("redirect:/check");
    		} else if (userType.equalsIgnoreCase("senior_cashier")) {	//старший кассир
    			return new ModelAndView("redirect:/cancel");
    		}
        } else {
        	session.setAttribute("user", null);
			session.setAttribute("userNotExists", true);			
		}
        model.addAttribute("user", user);
        return new ModelAndView("redirect:/");
    }   
}
