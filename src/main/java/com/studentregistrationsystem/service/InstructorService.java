package com.studentregistrationsystem.service;

import com.studentregistrationsystem.model.Instructor;

import java.util.List;

public interface InstructorService {

    public void save(Instructor instructor);
    public List<Instructor> listAllInstructor();
    public Instructor getInstructorById(Long id);
    public void delete(Instructor instructor);
}
