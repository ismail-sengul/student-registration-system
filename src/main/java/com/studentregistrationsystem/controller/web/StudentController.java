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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping("/login/student")
    public String loginStudent(){
        return "login/login-student";
    }

    @GetMapping("/register/student")
    public String showRegisterStudentForm(Model model){
        model.addAttribute("student",new Student());
        model.addAttribute("date",new String());
        return "/register/student-register";
    }

    @PostMapping("/save/student")
    public String registerStudent(@ModelAttribute("student") Student student,
                                  @ModelAttribute("date") String date){
        System.out.println(date);
        studentService.save(student);
        return "redirect:/login/student";
    }

    @PostMapping("/login/student")
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

    @GetMapping("/student")
    public String showStudentControlPage(@ModelAttribute("student") Student student){
        return "/student/student-control-panel";
    }

}
