package com.example.groupmeister;

import java.util.Arrays;

public class Course{
    private String name;
    private Student[] students;

    Course(String name, Student[] students){
        this.name = name;
        this.students = students;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", students=" + Arrays.toString(students) +
                '}';
    }
}
