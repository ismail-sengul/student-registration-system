package com.studentregistrationsystem.service.Impl;

import com.studentregistrationsystem.model.Student;
import com.studentregistrationsystem.repository.StudentRepository;
import com.studentregistrationsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    @Transactional
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    @Transactional
    public List<Student> listAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    @Transactional
    public Student getStudentById(Long id) {
        return studentRepository.getReferenceById(id);
    }
    

    @Override
    @Transactional
    public void delete(Student student) {
        studentRepository.delete(student);
    }
}
