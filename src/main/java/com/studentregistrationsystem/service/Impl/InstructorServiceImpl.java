package com.studentregistrationsystem.service.Impl;

import com.studentregistrationsystem.model.Instructor;
import com.studentregistrationsystem.repository.InstructorRepository;
import com.studentregistrationsystem.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorServiceImpl implements InstructorService {

    @Autowired
    private InstructorRepository instructorRepository;

    @Override
    public void save(Instructor instructor) {
        instructorRepository.save(instructor);
    }

    @Override
    public List<Instructor> listAllInstructor() {
        return instructorRepository.findAll();
    }

    @Override
    public Instructor getInstructorById(Long id) {
        return instructorRepository.getReferenceById(id);
    }

    @Override
    public Instructor update(Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    @Override
    public void delete(Instructor instructor) {
        instructorRepository.delete(instructor);
    }
}
