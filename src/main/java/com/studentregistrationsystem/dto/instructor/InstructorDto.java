package com.studentregistrationsystem.dto.instructor;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class InstructorDto implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String title;
    private String password;
}
