package com.studentregistrationsystem.controller.rest;

import com.studentregistrationsystem.dto.student.StudentCreateDto;
import com.studentregistrationsystem.dto.student.StudentDto;
import com.studentregistrationsystem.model.Student;
import com.studentregistrationsystem.service.StudentService;
import com.studentregistrationsystem.util.StudentUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/student")
public class StudentRestController {

    StudentUtil studentUtil = StudentUtil.getInstance();
    @Autowired
    private StudentService studentService;

    //http://localhost:9090/api/v1/instructor/save
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public ResponseEntity<StudentDto> saveStudent(@RequestBody StudentCreateDto studentCreateDto){

        if(studentCreateDto.getFirstName() == null){
            throw new IllegalArgumentException("Instructor first name is not entered.");
        }else if(studentCreateDto.getLastName() == null){
            throw new IllegalArgumentException("Instructor last name is not entered.");
        }else if(studentCreateDto.getEmail() == null){
            throw new IllegalArgumentException("Instructor email is not entered.");
        }
        Student student = new Student();
        student.setFirstName(studentCreateDto.getFirstName());
        student.setLastName(studentCreateDto.getLastName());
        student.setEmail(studentCreateDto.getEmail());
        student.setBirthDate(studentCreateDto.getBirthDate());
        student.setPassword(studentCreateDto.getBirthDate());
        studentService.save(student);

        return ResponseEntity.ok(studentUtil.entityToDto(student));
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ResponseEntity<List<StudentDto>> listAllInstructors(){
        List<Student> students = studentService.listAllStudents();

        List<StudentDto> studentDtoList = new ArrayList<>();
        for (Student student : students){
            studentDtoList.add(studentUtil.entityToDto(student));
        }
        return ResponseEntity.ok(studentDtoList);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<StudentDto> getInstructorById(@PathVariable(value = "id") Long id){
        Student student = studentService.getStudentById(id);

        return ResponseEntity.ok(studentUtil.entityToDto(student));
    }

    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public ResponseEntity<StudentDto> update(@RequestBody StudentDto studentDto){
        if (studentDto.getId() == null){
            throw new IllegalArgumentException("Id must not be null.");
        }
        Student student = studentService.getStudentById(studentDto.getId());

        if(student == null){
            throw new IllegalArgumentException("Instructor is not found.");
        }
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setBirthDate(studentDto.getBirthDate());
        student.setEmail(studentDto.getEmail());
        student.setPassword(studentDto.getPassword());

        studentService.save(student);

        return ResponseEntity.ok(studentUtil.entityToDto(student));
    }

    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    public ResponseEntity<StudentDto> delete(@RequestParam Long id){
        Student student = studentService.getStudentById(id);

        if(student == null){
            throw new IllegalArgumentException("Course is not found.");
        }
        StudentDto studentDto = studentUtil.entityToDto(student);

        studentService.delete(student);

        return ResponseEntity.ok(studentDto);
    }

}
