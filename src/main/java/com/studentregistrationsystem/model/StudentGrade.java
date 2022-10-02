package com.studentregistrationsystem.model;

import lombok.*;
import lombok.extern.log4j.Log4j2;

import javax.persistence.*;

@Entity
@Table(name = "STUDENT_GRADE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Log4j2
@Builder
public class StudentGrade extends BaseEntity{

    @ManyToOne(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH})
    @JoinColumn(name = "STUDENT_ID",referencedColumnName = "ID")
    private Student student;

    @ManyToOne(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH})
    @JoinColumn(name = "SESSION_ID",referencedColumnName = "ID")
    private CourseSession session;

    @Column(name = "MIDTERM")
    private Integer midterm;

    @Column(name = "FINAL")
    private Integer finalExam;

}
