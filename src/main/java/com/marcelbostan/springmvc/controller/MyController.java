package com.marcelbostan.springmvc.controller;

import com.marcelbostan.springmvc.model.Student;
import com.marcelbostan.springmvc.service.StudentService;
import com.marcelbostan.springmvc.service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * Created by Marcel Bostan on 12/14/2015.
 */

@RestController
public class MyController {

    @Autowired
    StudentService studentService;

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public ResponseEntity<List<Student>> listAllStudent() {

        System.out.println("Hello test git");
        List<Student> students = studentService.getAllStudent();

        if (students.isEmpty()){
            return new ResponseEntity<List<Student>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<Student>>(students, HttpStatus.OK);
    }

    @RequestMapping(value = "/student", method = RequestMethod.POST)
    public ResponseEntity<Void> createStudent(@RequestBody Student student, UriComponentsBuilder uriComponentsBuilder) {

        if (studentService.existStudent(student)) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        studentService.addStudent(student);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponentsBuilder.path("/student/{id}").buildAndExpand(student.getId()).toUri());

        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/student/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Student> updateStudent(@PathVariable("id") long id, @RequestBody Student student) {
        System.out.println("Updating student .... " + id );

        Student currentStundet = studentService.findById(id);

        if (currentStundet == null){
            return  new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
        }

        currentStundet.setNume(student.getNume());
        currentStundet.setPrenume(student.getPrenume());
        currentStundet.setCnp(student.getCnp());

        studentService.updateStudent(currentStundet);

        return new ResponseEntity<Student>(currentStundet, HttpStatus.OK);
    }

    @RequestMapping(value = "/student/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Student> delelteStudent(@PathVariable("id") long id) {

        Student student = studentService.findById(id);

        if (student == null) {
            return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
        }

        studentService.delteStudent(id);

        return new ResponseEntity<Student>(HttpStatus.NO_CONTENT);
    }
}
