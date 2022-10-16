package com.studentregistrationsystem.service.Impl;

import com.studentregistrationsystem.model.Instructor;
import com.studentregistrationsystem.repository.InstructorRepository;
import com.studentregistrationsystem.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class InstructorServiceImpl implements InstructorService {

    @Autowired
    private InstructorRepository instructorRepository;

    @Override
    @Transactional
    public void save(Instructor instructor) {
        instructorRepository.save(instructor);
    }

    @Override
    @Transactional
    public List<Instructor> listAllInstructor() {
        return instructorRepository.findAll();
    }

    @Override
    @Transactional
    public Instructor getInstructorById(Long id) {
        return instructorRepository.getReferenceById(id);
    }


    @Override
    @Transactional
    public void delete(Instructor instructor) {
        instructorRepository.delete(instructor);
    }
}
