package com.example.groupmeister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lvCourses;
    private ArrayList<String> courseNames;
    private ArrayAdapter<String> courseNamesAdapter;
    private ArrayList<Course> courses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Courses");
        lvCourses = findViewById(R.id.lvCourses);
    }

    public void newCourse(View V){
        //code below demonstrated in Android App Development tutorial series by Caleb Curry
        Intent i = new Intent(this, NewCourse.class);
        startActivity(i);
    }

}