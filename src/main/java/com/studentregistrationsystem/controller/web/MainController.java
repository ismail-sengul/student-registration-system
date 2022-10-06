package com.studentregistrationsystem.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String mainMenu(){
        return "index";
    }

    @GetMapping("/login/instructor")
    public String loginInstructor(){
        return "login/login-instructor";
    }

    @GetMapping("/login/student")
    public String loginStudent(){
        return "login/login-student";
    }
}
