package com.studentregistrationsystem.dto.course;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CourseDto implements Serializable {

    private Long id;
    private String name;
    private Integer credit;
    private Integer term;
}
