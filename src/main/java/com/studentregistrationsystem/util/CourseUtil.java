package com.studentregistrationsystem.util;

import com.studentregistrationsystem.dto.CourseDto;
import com.studentregistrationsystem.model.Course;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class CourseUtil {
    private static CourseUtil courseUtil;
    private CourseUtil(){

    }
    public static synchronized CourseUtil getInstance(){
        if(courseUtil == null){
            courseUtil = new CourseUtil();
        }
        return courseUtil;
    }

    public CourseDto entityToDto(Course course){
        CourseDto courseDto = new CourseDto();

        courseDto.setId(course.getId());
        courseDto.setCredit(course.getCredit());
        courseDto.setName(course.getName());
        courseDto.setTerm(course.getTerm());
        return courseDto;
    }

    public Course dtoToEntity(CourseDto courseDto){
        Course course = new Course();

        course.setId(courseDto.getId());
        course.setCredit(courseDto.getCredit());
        course.setName(courseDto.getName());
        course.setTerm(courseDto.getTerm());
        return course;
    }
}
