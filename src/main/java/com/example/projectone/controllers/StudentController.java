package com.example.projectone.controllers;

import com.example.projectone.entities.Student;
import com.example.projectone.exceptions.StudentNotFoundException;
import com.example.projectone.repositories.StudentRepository;
import com.example.projectone.security.ApplicationUserPermission;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    private List<Student> studentList = StudentRepository.studentList;

    @GetMapping(path = "{studentId}")
    public Student getStudent(@PathVariable("studentId") Integer studentId) {
        for (int i = 0; i < studentList.size(); i++) {
            if (studentId.equals(studentList.get(i).getId()))
                return studentList.get(i);
        }
        throw new StudentNotFoundException("Student with ID=" + studentId + " does not exist");
    }

    @GetMapping("/hello")
    public String helloPage(){
        return "Welcome to home page ";
    }
}
