package com.studentregistrationsystem.util;

import com.studentregistrationsystem.dto.instructor.InstructorDto;
import com.studentregistrationsystem.dto.student.StudentDto;
import com.studentregistrationsystem.model.Instructor;
import com.studentregistrationsystem.model.Student;

public class StudentUtil {

    private static StudentUtil studentUtil;
    private StudentUtil(){

    }
    public static synchronized StudentUtil getInstance(){
        if(studentUtil == null){
            studentUtil = new StudentUtil();
        }
        return studentUtil;
    }

    public StudentDto entityToDto(Student student){
        return StudentDto
                .builder()
                .id(student.getId())
                .email(student.getEmail())
                .birthDate(student.getBirthDate())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .build();
    }
}
