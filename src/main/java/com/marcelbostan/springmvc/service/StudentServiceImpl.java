package com.marcelbostan.springmvc.service;

import com.marcelbostan.springmvc.model.Student;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Marcel Bostan on 12/14/2015.
 */
@Service("studentService")
@Transactional
public class StudentServiceImpl implements StudentService {

    private static final AtomicLong counter = new AtomicLong();

    private static List<Student> students;

    static {
        students = populateList();
    }

    private static List<Student> populateList() {
        List<Student> returnList = new ArrayList<Student>();

        returnList.add(new Student(counter.incrementAndGet(), "marcel", "bostan", "123"));
        returnList.add(new Student(counter.incrementAndGet(), "dorin", "bostan", "435"));
        returnList.add(new Student(counter.incrementAndGet(), "dorin", "radu", "667"));
        returnList.add(new Student(counter.incrementAndGet(), "serii", "puhalschi", "123"));

        return returnList;
    }

    public Student findById(long id) {
        Student student = null;
        for (Student s: students){
            if (s.getId() == id)
                student = s;
        }
        return student;
    }

    public void addStudent(Student student) {
        boolean existStudent = existStudent(student);
        if (!existStudent) {
            student.setId(counter.incrementAndGet());
            students.add(student);
        }
    }

    public void updateStudent(Student student) {
        int index = students.indexOf(student);
        students.set(index, student);
    }

    public void delteStudent(long id) {
        for (Student student: students){
            if (student.getId() == id)
                students.remove(student);
        }
    }

    public List<Student> getAllStudent() {
        return students;
    }

    public boolean existStudent(Student student) {
        boolean flag = false;
        for (Student s: students){
            if (s.getCnp().equals(student.getCnp()))
                flag = true;
        }
        return flag;
    }
}
