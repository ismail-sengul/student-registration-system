package com.studentregistrationsystem.model;

import lombok.*;
import lombok.extern.log4j.Log4j2;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

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

    @Column(name = "COURSE_HOUR")
    @Min(value = 1,message = "Course must be greater than 1 hours")
    private Integer courseHour;

    @ManyToOne(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH})
    @JoinColumn(name = "INSTRUCTOR_ID",referencedColumnName = "ID")
    private Instructor instructor;

    @ManyToMany(mappedBy = "enrolledCourses",cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH})
    private Set<Student> enrolledStudents;

}
