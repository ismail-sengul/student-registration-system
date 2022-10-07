package com.studentregistrationsystem.service;

import com.studentregistrationsystem.model.Course;

import java.util.List;

public interface CourseService {
    public void save(Course course);
    public List<Course> listAllCourses();
    public Course getCourseById(Long id);
    public Course update(Course course);
    public void delete(Course course);
}
