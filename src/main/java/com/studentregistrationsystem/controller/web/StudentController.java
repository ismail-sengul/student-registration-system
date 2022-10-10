package com.studentregistrationsystem.controller.web;

import com.studentregistrationsystem.model.Instructor;
import com.studentregistrationsystem.model.Student;
import com.studentregistrationsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping("/login")
    public String loginStudent(){
        return "login/login-student";
    }

    @GetMapping("/register")
    public String showRegisterStudentForm(Model model){
        model.addAttribute("student",new Student());
        model.addAttribute("date",new String());
        return "/register/student-register";
    }

    @PostMapping("/save")
    public String registerStudent(@Valid @ModelAttribute("student") Student student){
        studentService.save(student);
        return "redirect:/student/login";
    }

    @PostMapping("/login")
    public String loginStudentControl(@ModelAttribute("email") String email,
                                         @ModelAttribute("password") String password,
                                         Model model){
        List<Student> studentList = studentService.listAllStudents();

        for (Student student : studentList){
            if(student.getEmail().equals(email)){
                if(student.getPassword().equals(password)){
                    model.addAttribute("student",student);
                    return showStudentControlPage(student);
                }
            }
        }
        return "login/login-student";
    }

    @GetMapping("/profile")
    public String showStudentControlPage(@ModelAttribute("student") Student student){
        return "/student/student-control-panel";
    }

}
