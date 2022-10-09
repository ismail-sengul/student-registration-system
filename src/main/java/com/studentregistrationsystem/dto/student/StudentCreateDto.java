package com.studentregistrationsystem.dto.student;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Builder
public class StudentCreateDto implements Serializable {

    private String firstName;
    private String lastName;
    private String email;
    private String birthDate;
}
