package com.example.projectone.repositories;

import com.example.projectone.entities.Student;
import com.example.projectone.exceptions.StudentNotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudentRepository {
    public static List<Student> studentList = new ArrayList<>(
            Arrays.asList(
                    new Student(5, "Andrew"),
                    new Student(2, "Artem"),
                    new Student(3, "Vlad")
            ));


}



