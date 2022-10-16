package com.studentregistrationsystem.service.Impl;

import com.studentregistrationsystem.model.Course;
import com.studentregistrationsystem.repository.CourseRepository;
import com.studentregistrationsystem.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Override
    @Transactional
    public void save(Course course) {
        courseRepository.save(course);
    }

    @Override
    @Transactional
    public List<Course> listAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    @Transactional
    public Course getCourseById(Long id) {
        return courseRepository.getReferenceById(id);
    }

    @Override
    @Transactional
    public void delete(Course course) {
        courseRepository.delete(course);
    }
}
