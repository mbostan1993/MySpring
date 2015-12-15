package com.marcelbostan.springmvc.service;

import com.marcelbostan.springmvc.model.Student;

import java.util.List;

/**
 * Created by Marcel Bostan on 12/14/2015.
 */
public interface StudentService {

    Student findById(long id);

    void addStudent(Student student);

    void updateStudent(Student student);

    void delteStudent(long id);

    List<Student> getAllStudent();

    boolean existStudent(Student student);

}
