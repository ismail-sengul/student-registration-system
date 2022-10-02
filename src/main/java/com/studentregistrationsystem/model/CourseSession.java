package com.studentregistrationsystem.model;

import lombok.*;
import lombok.extern.log4j.Log4j2;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "SESSION")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Log4j2
@Builder
public class CourseSession extends BaseEntity {

    @Column(name = "NAME")
    private String name;

    @Column(name = "YEAR")
    private Integer year;

    @ManyToOne(cascade = {CascadeType.DETACH,
                         CascadeType.MERGE,
                         CascadeType.PERSIST,
                         CascadeType.REFRESH})
    @JoinColumn(name = "COURSE_ID",referencedColumnName = "ID")
    private Course course;

    @ManyToOne(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH})
    @JoinColumn(name = "INSTRUCTOR_ID",referencedColumnName = "ID")
    private Instructor instructor;

    @ManyToMany(mappedBy = "enrolledSessions")
    private Set<Student> enrolledStudents;
}
