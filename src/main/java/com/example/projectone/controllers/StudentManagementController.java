package com.example.projectone.controllers;

import com.example.projectone.entities.Student;
import com.example.projectone.exceptions.StudentNotFoundException;
import com.example.projectone.repositories.StudentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/management/api/v1/students")
public class StudentManagementController {
    private List<Student> studentList = new ArrayList<>(StudentRepository.studentList);

    @GetMapping("/list")
    public List<Student> getStudentList() {
        return studentList;
    }

    @PostMapping("/register")
    public void registerNewStudent(@RequestBody Student student) {
        studentList.add(student);
        System.out.println(student);
    }

    @PutMapping("/update/{studentId}")
    public void updateStudent(@PathVariable Integer studentId, @RequestBody Student student) {
        getById(studentId).copyStudent(student);
    }

    @DeleteMapping("/delete/{studentId}")
    public void deleteStudent(@PathVariable Integer studentId) {
        studentList.remove(getById(studentId));
    }

    private Student getById(Integer id) {
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getId().equals(id))
                return studentList.get(i);
        }
        throw new StudentNotFoundException("Student with ID=" + id + " does not exist");
    }
}
