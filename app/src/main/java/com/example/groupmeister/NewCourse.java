package com.example.groupmeister;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class NewCourse extends AppCompatActivity {

    private EditText studentNameText, courseNameText;
    private ListView lvStudents;
    private List<Student> students = new ArrayList<Student>();
    int count = 1;
    ArrayAdapter studentArrayAdapter;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_course);
        setTitle("New Course");
        studentNameText = findViewById(R.id.studentNameText);
        courseNameText = findViewById(R.id.courseNameText);
        lvStudents = findViewById(R.id.lvStudentsProject);

        //SQLite code implemented below was adapted from Shad Sluiter's tutorial video on Youtube
        lvStudents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Student clickedStudent = (Student)parent.getItemAtPosition(position);
                databaseHelper = new DatabaseHelper(NewCourse.this, courseNameText.getText().toString());
                databaseHelper.deleteStudent(clickedStudent);
                students.remove(position);
                studentArrayAdapter = new ArrayAdapter(NewCourse.this, android.R.layout.simple_list_item_1, students);
                lvStudents.setAdapter(studentArrayAdapter);
                Toast.makeText(NewCourse.this, clickedStudent.getName()+" was removed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Adds a new student to the displayed list of students. Also creates new Student objects
    public void addStudent(View V){
        Student student;
        String name = studentNameText.getText().toString();
        databaseHelper = new DatabaseHelper(NewCourse.this, courseNameText.getText().toString());

        if(name.equals("")){
            Toast.makeText(this, "Student name required", Toast.LENGTH_SHORT).show();
        }else if (courseNameText.getText().toString().equals("")){
            Toast.makeText(this, "Enter course name", Toast.LENGTH_SHORT).show();
        }else{
            student = new Student(name, count);
            count++;
            studentNameText.setText("");
            boolean success = databaseHelper.addStudent(student);
            Toast.makeText(this, "Success= "+success, Toast.LENGTH_SHORT).show();
            if (success = true){
                students.add(student);
                studentArrayAdapter = new ArrayAdapter(NewCourse.this, android.R.layout.simple_list_item_1, students);
                lvStudents.setAdapter(studentArrayAdapter);
            }
        }
    }

    //Returns to main activity with new course and student info
    public void submitCourse(View V){
        String courseName = courseNameText.getText().toString();
        if (courseName.equals("")){
            Toast.makeText(this, "Course name required", Toast.LENGTH_SHORT).show();
        }else{
            Intent i = new Intent(this, MainActivity.class);
            i.putExtra("dbName", courseName);
            startActivity(i);
        }
    }
}