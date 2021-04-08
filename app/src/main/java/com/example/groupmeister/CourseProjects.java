package com.example.groupmeister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CourseProjects extends AppCompatActivity {

    private String courseTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_projects);
        Intent i = getIntent();
        courseTitle = i.getStringExtra("dbName");
        setTitle(courseTitle);
    }

    public void newProject(View V){
        //code below demonstrated in Android App Development tutorial series by Caleb Curry
        Intent i = new Intent(this, NewProject.class);
        startActivity(i);
    }
}