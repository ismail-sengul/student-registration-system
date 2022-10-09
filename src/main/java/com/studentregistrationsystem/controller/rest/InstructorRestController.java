package com.studentregistrationsystem.controller.rest;

import com.studentregistrationsystem.dto.instructor.InstructorCreateDto;
import com.studentregistrationsystem.dto.instructor.InstructorDto;
import com.studentregistrationsystem.exceptions.InstructorNotFound;
import com.studentregistrationsystem.model.Instructor;
import com.studentregistrationsystem.service.InstructorService;
import com.studentregistrationsystem.util.InstructorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/instructor")
public class InstructorRestController {

    InstructorUtil instructorUtil = InstructorUtil.getInstance();
    @Autowired
    private InstructorService instructorService;

    //http://localhost:9090/api/v1/instructor/save
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public ResponseEntity<InstructorDto> saveInstructor(@RequestBody InstructorCreateDto instructorCreateDto){

        if(instructorCreateDto.getFirstName() == null){
            throw new IllegalArgumentException("Instructor first name is not entered.");
        }else if(instructorCreateDto.getLastName() == null){
            throw new IllegalArgumentException("Instructor last name is not entered.");
        }else if(instructorCreateDto.getEmail() == null){
            throw new IllegalArgumentException("Instructor email is not entered.");
        }
        Instructor instructor = new Instructor();
        instructor.setFirstName(instructorCreateDto.getFirstName());
        instructor.setLastName(instructorCreateDto.getLastName());
        instructor.setEmail(instructorCreateDto.getEmail());
        instructor.setTitle(instructorCreateDto.getTitle());
        instructor.setPassword(instructorCreateDto.getPassword());
        instructorService.save(instructor);

        return ResponseEntity.ok(instructorUtil.entityToDto(instructor));
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ResponseEntity<List<InstructorDto>> listAllInstructors(){
        List<Instructor> instructors = instructorService.listAllInstructor();

        List<InstructorDto> instructorDtoList = new ArrayList<>();
        for (Instructor instructor : instructors){
            instructorDtoList.add(instructorUtil.entityToDto(instructor));
        }
        return ResponseEntity.ok(instructorDtoList);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<InstructorDto> getInstructorById(@PathVariable(value = "id") Long id) throws InstructorNotFound {
        Instructor instructor = instructorService.getInstructorById(id);

        if(instructor == null){
            throw new InstructorNotFound("Instructor is not found.");
        }

        return ResponseEntity.ok(instructorUtil.entityToDto(instructor));
    }

    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public ResponseEntity<InstructorDto> update(@RequestBody InstructorDto instructorDto) throws InstructorNotFound {
        if (instructorDto.getId() == null){
            throw new IllegalArgumentException("Id must not be null.");
        }
        Instructor instructor = instructorService.getInstructorById(instructorDto.getId());

        if(instructor == null){
            throw new InstructorNotFound("Instructor is not found.");
        }
        instructor.setFirstName(instructorDto.getFirstName());
        instructor.setLastName(instructorDto.getLastName());
        instructor.setTitle(instructorDto.getTitle());
        instructor.setEmail(instructorDto.getEmail());
        instructor.setPassword(instructorDto.getPassword());

        instructorService.save(instructor);

        return ResponseEntity.ok(instructorUtil.entityToDto(instructor));
    }

    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    public ResponseEntity<InstructorDto> delete(@RequestParam Long id) throws InstructorNotFound {
        Instructor instructor = instructorService.getInstructorById(id);

        if(instructor == null){
            throw new InstructorNotFound("Course is not found.");
        }
        InstructorDto instructorDto = instructorUtil.entityToDto(instructor);

        instructorService.delete(instructor);

        return ResponseEntity.ok(instructorDto);
    }

}
