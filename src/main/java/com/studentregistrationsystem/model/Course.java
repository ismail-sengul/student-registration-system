package com.studentregistrationsystem.model;

import lombok.*;
import lombok.extern.log4j.Log4j2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "COURSE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Log4j2
@Builder
public class Course extends BaseEntity{

    @Column(name = "NAME")
    @NotNull(message = "Course must have a name")
    private String name;

    @Column(name = "CREDIT")
    @Min(value = 1,message = "Credit must be greater than 1")
    @Max(value = 15 ,message = "Credit must be less than 15")
    private Integer credit;

    @Column(name = "TERM")
    private Integer term;

}
