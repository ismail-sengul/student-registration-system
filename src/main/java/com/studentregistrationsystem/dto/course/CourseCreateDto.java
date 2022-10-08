package com.studentregistrationsystem.dto.course;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CourseCreateDto implements Serializable {

    private String name;
    private Integer credit;
    private Integer term;

}
