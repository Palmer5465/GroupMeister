package com.example.groupmeister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Courses");
    }

    public void newCourse(View V){
        //code below demonstrated in Android App Development tutorial series by Caleb Curry
        Intent i = new Intent(this, NewCourse.class);
        startActivity(i);
    }

}