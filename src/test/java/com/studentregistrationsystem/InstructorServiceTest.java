package com.studentregistrationsystem;

import com.studentregistrationsystem.model.Course;
import com.studentregistrationsystem.model.Instructor;
import com.studentregistrationsystem.repository.CourseRepository;
import com.studentregistrationsystem.repository.InstructorRepository;
import com.studentregistrationsystem.service.Impl.CourseServiceImpl;
import com.studentregistrationsystem.service.Impl.InstructorServiceImpl;
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
public class InstructorServiceTest {

    @InjectMocks
    InstructorServiceImpl instructorService;

    @Mock
    InstructorRepository instructorRepository;

    @Test
    public void testSave(){
        Instructor firstInstructor = new Instructor();
        Instructor secondInstructor = new Instructor();
        Instructor thirdInstructor = new Instructor();

        instructorService.save(firstInstructor);
        instructorService.save(thirdInstructor);
        instructorService.save(secondInstructor);

        InOrder inOrder = inOrder(instructorRepository);
        inOrder.verify(instructorRepository).save(firstInstructor);
        inOrder.verify(instructorRepository).save(thirdInstructor);
        inOrder.verify(instructorRepository).save(secondInstructor);
    }

    @Test
    public void testListAllInstructor(){

        List<Instructor> instructors = instructorService.listAllInstructor();

        when(instructorRepository.findAll()).thenReturn(instructors);

        Assertions.assertEquals(instructorService.listAllInstructor(),instructors);
    }

    @Test
    public void testGetInstructorById(){
        Instructor buildInstructor = Instructor
                .builder()
                .firstName("FirstName")
                .lastName("LastName")
                .email("email@email.com")
                .build();

        when(instructorRepository.getReferenceById(1L)).thenReturn(buildInstructor);

        Instructor instructor = instructorService.getInstructorById(1L);

        Assertions.assertEquals(instructor.getFirstName(),"FirstName");
        Assertions.assertEquals(instructor.getLastName(),"LastName");
        Assertions.assertEquals(instructor.getEmail(),"email@email.com");
    }

    @Test
    public void testDelete(){
        Instructor instructor = new Instructor();

        instructorService.delete(instructor);

        verify(instructorRepository).delete(instructor);
    }
}
