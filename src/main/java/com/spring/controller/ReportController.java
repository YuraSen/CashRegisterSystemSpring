package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ReportController {
	  
    @GetMapping("/report")
    public String reportView() {
        return "/report";
    }
}
