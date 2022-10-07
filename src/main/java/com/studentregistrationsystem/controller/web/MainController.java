package com.studentregistrationsystem.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;

@Controller
public class MainController {

    //http://localhost:9090/
    @GetMapping("/")
    public String mainMenu(Model model){
        model.addAttribute("datetime",new Date());
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
