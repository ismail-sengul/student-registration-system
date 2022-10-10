package com.studentregistrationsystem.util;

import com.studentregistrationsystem.dto.course.CourseDto;
import com.studentregistrationsystem.model.Course;

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
        courseDto.setCourseHour(course.getCourseHour());
        courseDto.setName(course.getName());
        return courseDto;
    }

    public Course dtoToEntity(CourseDto courseDto){
        Course course = new Course();

        course.setId(courseDto.getId());
        course.setCourseHour(courseDto.getCourseHour());
        course.setName(courseDto.getName());
        return course;
    }
}
