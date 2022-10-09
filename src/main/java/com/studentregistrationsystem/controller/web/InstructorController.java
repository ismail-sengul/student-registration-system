package com.studentregistrationsystem.controller.web;

import com.studentregistrationsystem.model.Instructor;
import com.studentregistrationsystem.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class InstructorController {

    @Autowired
    private InstructorService instructorService;

    @GetMapping("/login/instructor")
    public String loginInstructor(Model model){
        model.addAttribute("email");
        model.addAttribute("password");
        return "login/login-instructor";
    }

    @PostMapping("/login/instructor")
    public String loginInstructorControl(@ModelAttribute("email") String email,
                                         @ModelAttribute("password") String password,
                                         Model model){
        List<Instructor> instructorList = instructorService.listAllInstructor();

        for (Instructor instructor : instructorList){
            if(instructor.getEmail().equals(email)){
                if(instructor.getPassword().equals(password)){
                    model.addAttribute("instructor",instructor);
                    return showInstructorControlPage(instructor);
                }
            }
        }
        return "login/login-instructor";
    }

    @GetMapping("/register/instructor")
    public String showInstructorRegisterForm(Model model){
        model.addAttribute("instructor",new Instructor());
        return "/register/instructor-register";
    }

    @PostMapping("/save/instructor")
    public String registerInstructor(@Valid @ModelAttribute("instructor") Instructor instructor){
        instructorService.save(instructor);
        return "redirect:/login/instructor";
    }

    @GetMapping("/instructor")
    public String showInstructorControlPage(@ModelAttribute("instructor") Instructor instructor){
        return "/instructor/instructor-control-page";
    }
}
