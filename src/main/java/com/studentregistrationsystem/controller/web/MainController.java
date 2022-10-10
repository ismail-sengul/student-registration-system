package com.studentregistrationsystem.controller.web;

import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@Controller
public class MainController {

    private ErrorAttributes errorAttributes;

    //http://localhost:9090/
    @GetMapping("/")
    public String mainMenu(Model model){
        model.addAttribute("datetime",new Date());
        return "index";
    }

    @GetMapping("/error")
    public String handleError(Model model, WebRequest webRequest){
        final Throwable error = errorAttributes.getError(webRequest);
        model.addAttribute("exception", error);
        model.addAttribute("message", error == null ? "" : error.getMessage());

        return "error";
    }

}
