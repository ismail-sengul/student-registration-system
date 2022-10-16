package com.studentregistrationsystem.controller.web;

import com.studentregistrationsystem.exceptions.StudentNotFound;
import com.studentregistrationsystem.model.Course;
import com.studentregistrationsystem.model.Student;
import com.studentregistrationsystem.service.CourseService;
import com.studentregistrationsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @Autowired
    CourseService courseService;

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
                    return showStudentProfilePage(student);
                }
            }
        }
        return "login/login-student";
    }

    @GetMapping("/profile")
    public String showStudentProfilePage(@ModelAttribute("student") Student student){
        return "/student/student-profile-page";
    }

    @GetMapping("/profile/{id}")
    public String onClickProfileLink(@PathVariable("id") Long id, Model model) throws StudentNotFound {
        Student student = studentService.getStudentById(id);
        if(student == null){
            throw new StudentNotFound("Student not found");
        }
        model.addAttribute("student",student);
        return showStudentProfilePage(student);
    }

    @GetMapping("/update/{id}")
    public String showUpdateStudentForm(@PathVariable(value = "id") Long id, Model model){
        model.addAttribute("student",studentService.getStudentById(id));
        return "/student/student-update-page";
    }

    @PostMapping("/update/{id}")
    public String updateInstructor(@PathVariable("id") Long id,
                                   @Valid @ModelAttribute("student") Student student,
                                   Model model, BindingResult result){
        if(result.hasErrors()){
            return showUpdateStudentForm(id,model);
        }
        studentService.save(student);
        model.addAttribute("student",student);
        return showStudentProfilePage(student);
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteInstructor(@PathVariable("id")Long id){
        studentService.delete(studentService.getStudentById(id));
        return "redirect:/student/login";
    }

    @GetMapping(value = "/course/list/{id}")
    public String showAllCoursesPage(@PathVariable("id") Long id,
                                     Model model){
        Student student = studentService.getStudentById(id);
        model.addAttribute("coursePage","ALL");
        model.addAttribute("student",student);

        List<Course> courses = courseService.listAllCourses();
        courses.removeAll(student.getEnrolledCourses());

        model.addAttribute("courses",courses);
        return "/student/student-courses-page";
    }

    @GetMapping(value = "/courses/{id}")
    public String showMyCoursesPage(@PathVariable("id") Long id,
                                     Model model){
        Student student = studentService.getStudentById(id);
        model.addAttribute("coursePage","MY");
        model.addAttribute("student",student);
        model.addAttribute("courses",student.getEnrolledCourses());
        return "/student/student-courses-page";
    }

    @GetMapping(value = "/course/save/{studentId}/{courseId}")
    public String getCourseForStudent(@PathVariable("studentId") Long studentId,
                                      @PathVariable("courseId") Long courseId,
                                      Model model){
        Course course = courseService.getCourseById(courseId);
        Student student = studentService.getStudentById(studentId);
        student.getEnrolledCourses().add(course);
        studentService.save(student);
        return showAllCoursesPage(studentId,model);
    }

    @GetMapping(value = "/course/delete/{studentId}/{courseId}")
    public String deleteCourseForStudent(@PathVariable("studentId") Long studentId,
                                           @PathVariable("courseId") Long courseId,
                                           Model model){
        Student student = studentService.getStudentById(studentId);
        student.getEnrolledCourses().remove(courseService.getCourseById(courseId));
        studentService.save(student);
        return showMyCoursesPage(studentId,model);
    }

}
