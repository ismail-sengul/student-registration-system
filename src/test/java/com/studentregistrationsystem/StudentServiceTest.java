package com.studentregistrationsystem;

import com.studentregistrationsystem.model.Instructor;
import com.studentregistrationsystem.model.Student;
import com.studentregistrationsystem.repository.InstructorRepository;
import com.studentregistrationsystem.repository.StudentRepository;
import com.studentregistrationsystem.service.Impl.InstructorServiceImpl;
import com.studentregistrationsystem.service.Impl.StudentServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class StudentServiceTest {

    @InjectMocks
    StudentServiceImpl studentService;

    @Mock
    StudentRepository studentRepository;

    @Test
    public void testSave(){
        Student firstStudent = new Student();
        Student secondStudent = new Student();
        Student thirdStudent = new Student();

        studentService.save(firstStudent);
        studentService.save(thirdStudent);
        studentService.save(secondStudent);

        InOrder inOrder = inOrder(studentRepository);
        inOrder.verify(studentRepository).save(firstStudent);
        inOrder.verify(studentRepository).save(thirdStudent);
        inOrder.verify(studentRepository).save(secondStudent);
    }

    @Test
    public void testListAllInstructor(){

        List<Student> students = studentService.listAllStudents();

        when(studentRepository.findAll()).thenReturn(students);

        Assertions.assertEquals(studentService.listAllStudents(),students);
    }

    @Test
    public void testGetInstructorById(){
        Student buildStudent = Student
                .builder()
                .firstName("FirstName")
                .lastName("LastName")
                .email("email@email.com")
                .build();

        when(studentRepository.getReferenceById(1L)).thenReturn(buildStudent);

        Student student = studentRepository.getReferenceById(1L);

        Assertions.assertEquals(student.getFirstName(),"FirstName");
        Assertions.assertEquals(student.getLastName(),"LastName");
        Assertions.assertEquals(student.getEmail(),"email@email.com");
    }

    @Test
    public void testDelete(){
        Student student = new Student();

        studentService.delete(student);

        verify(studentRepository).delete(student);
    }
}
