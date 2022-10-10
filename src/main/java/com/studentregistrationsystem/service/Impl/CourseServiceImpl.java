package com.studentregistrationsystem.service.Impl;

import com.studentregistrationsystem.model.Course;
import com.studentregistrationsystem.repository.CourseRepository;
import com.studentregistrationsystem.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public void save(Course course) {
        courseRepository.save(course);
    }

    @Override
    public List<Course> listAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.getReferenceById(id);
    }

    @Override
    public void delete(Course course) {
        courseRepository.delete(course);
    }
}
