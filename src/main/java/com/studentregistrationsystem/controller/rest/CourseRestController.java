package com.studentregistrationsystem.controller.rest;

import com.studentregistrationsystem.dto.course.CourseCreateDto;
import com.studentregistrationsystem.dto.course.CourseDto;
import com.studentregistrationsystem.model.Course;
import com.studentregistrationsystem.service.CourseService;
import com.studentregistrationsystem.util.CourseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/course")
public class CourseRestController {

    CourseUtil courseUtil = CourseUtil.getInstance();
    @Autowired
    private CourseService courseService;

    //http://localhost:9090/api/v1/course/save
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public ResponseEntity<CourseDto> saveCourse(@RequestBody CourseCreateDto courseCreateDTO){

        if(courseCreateDTO.getName() == null){
            throw new IllegalArgumentException("Course name is not entered.");
        }
        Course course = new Course();
        course.setCourseHour(courseCreateDTO.getCourseHour());
        course.setName(courseCreateDTO.getName());
        courseService.save(course);

        return ResponseEntity.ok(courseUtil.entityToDto(course));
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ResponseEntity<List<CourseDto>> listAllCourses(){
        List<Course> courses = courseService.listAllCourses();

        List<CourseDto> courseDtoList = new ArrayList<>();
        for (Course course : courses){
            courseDtoList.add(courseUtil.entityToDto(course));
        }
        return ResponseEntity.ok(courseDtoList);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<CourseDto> getCourseById(@PathVariable(value = "id") Long id){
        Course course = courseService.getCourseById(id);

        return ResponseEntity.ok(courseUtil.entityToDto(course));
    }

    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public ResponseEntity<CourseDto> update(@RequestBody CourseDto courseDto){
        if (courseDto.getId() == null){
            throw new IllegalArgumentException("Id must not be null.");
        }
        Course course = courseService.getCourseById(courseDto.getId());

        if(course == null){
            throw new IllegalArgumentException("Course is not found.");
        }
        course.setCourseHour(courseDto.getCourseHour());
        course.setName(courseDto.getName());

        courseService.save(course);

        return ResponseEntity.ok(courseUtil.entityToDto(course));
    }

    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    public ResponseEntity<CourseDto> delete(@RequestParam Long id){
        Course course = courseService.getCourseById(id);

        if(course == null){
            throw new IllegalArgumentException("Course is not found.");
        }
        CourseDto courseDto = courseUtil.entityToDto(course);

        courseService.delete(course);

        return ResponseEntity.ok(courseDto);
    }

}
