package com.studentregistrationsystem.controller.web;

import com.studentregistrationsystem.exceptions.InstructorNotFound;
import com.studentregistrationsystem.model.Course;
import com.studentregistrationsystem.model.Instructor;
import com.studentregistrationsystem.service.CourseService;
import com.studentregistrationsystem.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/instructor")
public class InstructorController {

    @Autowired
    private InstructorService instructorService;

    @Autowired
    private CourseService courseService;

    @GetMapping("/login")
    public String loginInstructor(Model model){
        model.addAttribute("email");
        model.addAttribute("password");
        return "login/login-instructor";
    }

    @PostMapping("/login")
    public String loginInstructorControl(@ModelAttribute("email") String email,
                                         @ModelAttribute("password") String password,
                                         Model model){
        List<Instructor> instructorList = instructorService.listAllInstructor();

        for (Instructor instructor : instructorList){
            if(instructor.getEmail().equals(email)){
                if(instructor.getPassword().equals(password)){
                    model.addAttribute("instructor",instructor);
                    return showInstructorProfilePage(instructor);
                }
            }
        }
        return "login/login-instructor";
    }

    @GetMapping("/register")
    public String showInstructorRegisterForm(Model model){
        model.addAttribute("instructor",new Instructor());
        return "/register/instructor-register";
    }

    @PostMapping("/save")
    public String registerInstructor(@Valid @ModelAttribute("instructor") Instructor instructor,
                                     BindingResult result){
        if(result.hasErrors()){
            return "redirect:/instructor/register";
        }
        instructorService.save(instructor);
        return "redirect:/instructor/login";
    }

    @GetMapping("/profile")
    public String showInstructorProfilePage(@ModelAttribute("instructor") Instructor instructor){
        return "/instructor/instructor-profile-page";
    }

    @GetMapping("/profile/{id}")
    public String onClickProfileLink(@PathVariable("id") Long id,Model model) throws InstructorNotFound {
        Instructor instructor = instructorService.getInstructorById(id);
        if(instructor == null){
            throw new InstructorNotFound("Instructor not found");
        }
        model.addAttribute("instructor",instructor);
        return showInstructorProfilePage(instructor);
    }

    @GetMapping("/update/{id}")
    public String showUpdateInstructorForm(@PathVariable(value = "id") Long id, Model model){
        model.addAttribute("instructor",instructorService.getInstructorById(id));
        return "/instructor/instructor-update-page";
    }

    @PostMapping("/update/{id}")
    public String updateInstructor(@PathVariable("id") Long id,
                                    @Valid @ModelAttribute("instructor") Instructor instructor,
                                   Model model, BindingResult result){
        if(result.hasErrors()){
            return showUpdateInstructorForm(id,model);
        }
        instructorService.save(instructor);
        model.addAttribute("instructor",instructor);
        return showInstructorProfilePage(instructor);
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteInstructor(@PathVariable("id")Long id){
        instructorService.delete(instructorService.getInstructorById(id));
        return "redirect:/instructor/login";
    }

    @GetMapping(value = "/courses/{id}")
    public String showInstructorCoursesPage(@PathVariable("id") Long id,
                                            Model model){
        Instructor instructor = instructorService.getInstructorById(id);
        model.addAttribute("instructor",instructor);
        model.addAttribute("courses",instructorService.getInstructorById(id).getOpenedCourses());
        return "/instructor/instructor-courses-page";
    }

    @GetMapping("/save/course/{id}")
    public String showNewCoursePage(@PathVariable("id") Long id, Model model){
        model.addAttribute("course",new Course());
        model.addAttribute("instructor",instructorService.getInstructorById(id));
        return "/instructor/new-course-page";
    }

    @PostMapping("/save/course/{id}")
    public String saveNewCourse(@PathVariable("id") Long id,
                                @ModelAttribute("course") Course course,
                                Model model){
        Instructor instructor = instructorService.getInstructorById(id);
        course.setInstructor(instructor);
        courseService.save(course);
        return "redirect:/instructor/courses/"+id;
    }
}
