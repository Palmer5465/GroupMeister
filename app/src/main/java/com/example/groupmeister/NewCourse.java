package com.example.groupmeister;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class NewCourse extends AppCompatActivity {

    private ArrayList<String> studentNames;
    private ArrayAdapter<String> studentNamesAdapter;
    private ListView lvStudents;
    private ArrayList<Student> students;
    private EditText studentNameText;
    private EditText courseNameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_course);
        setTitle("New Course");
        lvStudents = findViewById(R.id.lvStudents);
        studentNameText = findViewById(R.id.studentNameText);
        studentNames = new ArrayList<String>();
        students = new ArrayList<Student>();
        studentNamesAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, studentNames);
        courseNameText = findViewById(R.id.courseNameText);
        lvStudents.setAdapter(studentNamesAdapter);

        setupListViewListener();
    }

    public void addStudent(View V){
        String studentName = studentNameText.getText().toString();
        if (studentName.equals("")){
            Toast.makeText(this, "Student name required", Toast.LENGTH_SHORT).show();
        }else {
            studentNamesAdapter.add(studentName);
            students.add(new Student(studentName));
            studentNameText.setText("");
        }
    }

    //removes student from the course
    public void setupListViewListener(){
        lvStudents.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                studentNames.remove(position);
                students.remove(position);
                studentNamesAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    public void submitCourse(View V){
        String courseName = courseNameText.getText().toString();
        if (courseName.equals("")){
            Toast.makeText(this, "Course name required", Toast.LENGTH_SHORT).show();
        }else{

        }
    }
}