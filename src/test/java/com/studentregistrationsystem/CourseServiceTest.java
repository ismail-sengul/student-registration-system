package com.studentregistrationsystem;

import com.studentregistrationsystem.model.Course;
import com.studentregistrationsystem.repository.CourseRepository;
import com.studentregistrationsystem.service.Impl.CourseServiceImpl;
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
public class CourseServiceTest {

    @InjectMocks
    CourseServiceImpl courseService;

    @Mock
    CourseRepository courseRepository;

    @Test
    public void testSave(){
        Course firstCourse = new Course();
        Course secondCourse = new Course();
        Course thirdCourse = new Course();

        courseService.save(firstCourse);
        courseService.save(thirdCourse);
        courseService.save(secondCourse);

        InOrder inOrder = inOrder(courseRepository);
        inOrder.verify(courseRepository).save(firstCourse);
        inOrder.verify(courseRepository).save(thirdCourse);
        inOrder.verify(courseRepository).save(secondCourse);
    }

    @Test
    public void testListAllCourses(){

        List<Course> list = courseService.listAllCourses();

        when(courseRepository.findAll()).thenReturn(list);

        Assertions.assertEquals(courseService.listAllCourses(),list);
    }

    @Test
    public void testGetCourseById(){
        Course course = Course.builder().courseHour(15).build();

        when(courseRepository.getReferenceById(1L)).thenReturn(course);

        Assertions.assertEquals(courseService.getCourseById(1L).getCourseHour(),15);
    }

    @Test
    public void testDelete(){
        Course course = Course.builder().courseHour(25).build();

        courseService.delete(course);

        verify(courseRepository).delete(course);

    }
}
