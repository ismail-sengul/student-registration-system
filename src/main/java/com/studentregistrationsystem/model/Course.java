package com.studentregistrationsystem.model;

import lombok.*;
import lombok.extern.log4j.Log4j2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
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
    private String name;

    @Column(name = "CREDIT")
    private Integer credit;

    @Column(name = "TERM")
    private Integer term;

}
