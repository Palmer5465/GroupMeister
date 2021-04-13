package com.example.groupmeister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ViewGroups extends AppCompatActivity {

    private String projectTitle;
    private int numGroups;
    private ListView lvGroups;
    private List<List<Student>> groups = new ArrayList<List<Student>>();
    ArrayAdapter groupArrayAdapter;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_groups);
        Intent i = getIntent();
        lvGroups = findViewById(R.id.lvGroups);
        projectTitle = i.getStringExtra("project name");
        numGroups = i.getIntExtra("numGroups", -1);
        setTitle(projectTitle + " Groups");

        for (int group = 1; group <= numGroups; group++){
            databaseHelper = new DatabaseHelper(this, projectTitle+group);
            List<Student> students = databaseHelper.getStudents();
            System.out.println(students);
            groups.add(students);
        }

        groupArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, groups);
        lvGroups.setAdapter(groupArrayAdapter);
    }
}