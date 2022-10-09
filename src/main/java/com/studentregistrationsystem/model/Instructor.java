package com.studentregistrationsystem.model;

import lombok.*;
import lombok.extern.log4j.Log4j2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "INSTRUCTOR")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Log4j2
public class Instructor extends BaseEntity {

    @Column(name = "FIRST_NAME")
    @NotNull(message = "Name must not be null")
    private String firstName;

    @Column(name = "LAST_NAME")
    @NotNull(message = "Surname must not be null")
    private String lastName;

    @Column(name = "EMAIL")
    @NotNull(message = "Email must not be null")
    @Email
    private String email;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "PASSWORD")
    @Size(min = 6,max = 35, message = "Size of password have to be between 6 and 35 character")
    private String password;

    @OneToMany(mappedBy = "instructor")
    private Set<CourseSession> takenSession;

}
