package com.studentregistrationsystem.service;

import com.studentregistrationsystem.model.Instructor;
import com.studentregistrationsystem.model.Student;

import java.util.List;

public interface StudentService {

    public void save(Student student);
    public List<Student> listAllStudents();
    public Student getStudentById(Long id);
    public void delete(Student student);
}
