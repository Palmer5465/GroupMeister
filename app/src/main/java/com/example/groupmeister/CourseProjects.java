package com.example.groupmeister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CourseProjects extends AppCompatActivity {

    private String courseTitle, projectTitle;
    private int numGroups;
    private List<String> projects = new ArrayList<String>();
    private ListView lvProjects;
    ArrayAdapter projectArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_projects);
        Intent i = getIntent();
        courseTitle = i.getStringExtra("courseName");
        projectTitle = i.getStringExtra("project name");
        numGroups = i.getIntExtra("numGroups", -1);
        setTitle(courseTitle);
        lvProjects = findViewById(R.id.lvProjects);
        if (projectTitle != null) {
            projects.add(projectTitle);
            projectTitle = null;
            projectArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, projects);
            lvProjects.setAdapter(projectArrayAdapter);
        }

        lvProjects.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(CourseProjects.this, ViewGroups.class);
                i.putExtra("project name", (String)parent.getItemAtPosition(position));
                i.putExtra("numGroups", numGroups);
                startActivity(i);
            }
        });
    }

    public void newProject(View V){
        //code below demonstrated in Android App Development tutorial series by Caleb Curry
        Intent i = new Intent(this, NewProject.class);
        i.putExtra("course title",courseTitle);
        startActivity(i);
    }
}