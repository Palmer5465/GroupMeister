package com.example.groupmeister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvCourses;
    private ArrayList<String> courseNames;
    private ArrayAdapter<String> courseNamesAdapter;
    private String newCourse;
    //DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Courses");
        lvCourses = findViewById(R.id.lvCourses);

        Intent i = getIntent();
        newCourse = i.getStringExtra("dbName");
        //databaseHelper = new DatabaseHelper(this, newCourse);

        courseNames = new ArrayList<String>();
        if(newCourse!=null){
            //attempt to get dbName from intent and make/display new course object
            courseNames.add(newCourse);
            newCourse = null;
            courseNamesAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, courseNames);
            lvCourses.setAdapter(courseNamesAdapter);
        }

        lvCourses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainActivity.this, CourseProjects.class);
                i.putExtra("courseName", (String)parent.getItemAtPosition(position));
                startActivity(i);
            }
        });
    }

    public void newCourse(View V){
        //code below demonstrated in Android App Development tutorial series by Caleb Curry
        Intent i = new Intent(this, NewCourse.class);
        startActivity(i);
    }

}