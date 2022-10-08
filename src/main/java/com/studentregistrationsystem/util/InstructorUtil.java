package com.studentregistrationsystem.util;

import com.studentregistrationsystem.dto.instructor.InstructorDto;
import com.studentregistrationsystem.model.Instructor;

public class InstructorUtil {
    private static InstructorUtil instructorUtil;
    private InstructorUtil(){

    }
    public static synchronized InstructorUtil getInstance(){
        if(instructorUtil == null){
            instructorUtil = new InstructorUtil();
        }
        return instructorUtil;
    }

    public InstructorDto entityToDto(Instructor instructor){
       return InstructorDto
               .builder()
               .id(instructor.getId())
               .email(instructor.getEmail())
               .title(instructor.getTitle())
               .firstName(instructor.getFirstName())
               .lastName(instructor.getLastName())
               .build();
    }


}
